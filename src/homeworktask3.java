public class homeworktask3 {
    class RedBlackTree {

        private Node root;
        private Node nullNode;

        // Класс Node представляет узел дерева
        private static class Node {
            int data;
            Node parent;
            Node left;
            Node right;
            boolean isRed;

            Node(int data) {
                this.data = data;
            }
        }

        public RedBlackTree() {
            nullNode = new Node(-1);
            root = nullNode;
        }

        // Метод для добавления элемента в дерево
        public void add(int item) {
            Node node = new Node(item);
            node.left = nullNode;
            node.right = nullNode;
            node.isRed = true;

            Node parent = null;
            Node current = root;

            while (current != nullNode) {
                parent = current;
                if (item < current.data) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            node.parent = parent;

            if (parent == null) {
                root = node;
            } else if (item < parent.data) {
                parent.left = node;
            } else {
                parent.right = node;
            }

            balanceAfterInsert(node);
        }

        // Метод для балансировки дерева после добавления элемента
        private void balanceAfterInsert(Node node) {
            while (node.parent.isRed) {
                if (node.parent == node.parent.parent.left) {
                    Node uncle = node.parent.parent.right;
                    if (uncle.isRed) {
                        node.parent.isRed = false;
                        uncle.isRed = false;
                        node.parent.parent.isRed = true;
                        node = node.parent.parent;
                    } else {
                        if (node == node.parent.right) {
                            node = node.parent;
                            rotateLeft(node);
                        }
                        node.parent.isRed = false;
                        node.parent.parent.isRed = true;
                        rotateRight(node.parent.parent);
                    }
                } else {
                    Node uncle = node.parent.parent.left;
                    if (uncle.isRed) {
                        node.parent.isRed = false;
                        uncle.isRed = false;
                        node.parent.parent.isRed = true;
                        node = node.parent.parent;
                    } else {
                        if (node == node.parent.left) {
                            node = node.parent;
                            rotateRight(node);
                        }
                        node.parent.isRed = false;
                        node.parent.parent.isRed = true;
                        rotateLeft(node.parent.parent);
                    }
                }

                if (node == root) {
                    break;
                }
            }

            root.isRed = false;
        }

        // Левый поворот вокруг узла x
        private void rotateLeft(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left != nullNode) {
                y.left.parent = x;
            }
            y.parent = x.parent;
            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
            y.left = x;
            x.parent = y;
        }

        // Правый поворот вокруг узла x
        private void rotateRight(Node x) {
            Node y = x.left;
            x.left = y.right;
            if (y.right != nullNode) {
                y.right.parent = x;
            }
            y.parent = x.parent;
            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.right) {
                x.parent.right = y;
            } else {
                x.parent.left = y;
            }
            y.right = x;
            x.parent = y;
        }

        // Пример использования
        public static void main(String[] args) {
            RedBlackTree tree = new RedBlackTree();
            tree.add(10);
            tree.add(20);
            tree.add(30);
            tree.add(40);
            tree.add(50);
        }
    }
}