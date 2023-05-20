public class Method_TST {
    private Node root;

    private static class Node {
        private char data;
        private boolean isEndOfWord;
        private Node left, middle, right;

        public Node(char data) {
            this.data = data;
            this.isEndOfWord = false;
            this.left = null;
            this.middle = null;
            this.right = null;
        }
    }

    public void insert(String word) {
        root = insert(root, word, 0);
    }

    private Node insert(Node node, String word, int index) {
        char c = word.charAt(index);

        if (node == null) {
            node = new Node(c);
        }

        if (c < node.data) {
            node.left = insert(node.left, word, index);
        } else if (c > node.data) {
            node.right = insert(node.right, word, index);
        } else {
            if (index < word.length() - 1) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEndOfWord = true;
            }
        }

        return node;
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int index) {
        if (node == null) {
            return false;
        }

        char c = word.charAt(index);

        if (c < node.data) {
            return search(node.left, word, index);
        } else if (c > node.data) {
            return search(node.right, word, index);
        } else {
            if (index == word.length() - 1) {
                return node.isEndOfWord;
            } else {
                return search(node.middle, word, index + 1);
            }
        }
    }

    public void delete(String word) {
        root = delete(root, word, 0);
    }

    private Node delete(Node node, String word, int index) {
        if (node == null) {
            return null;
        }

        char c = word.charAt(index);

        if (c < node.data) {
            node.left = delete(node.left, word, index);
        } else if (c > node.data) {
            node.right = delete(node.right, word, index);
        } else {
            if (index == word.length() - 1) {
                if (node.isEndOfWord) {
                    node.isEndOfWord = false;
                }
            } else {
                node.middle = delete(node.middle, word, index + 1);
            }
        }

        // Clean up unused nodes
        if (node.left == null && node.middle == null && node.right == null && !node.isEndOfWord) {
            return null;
        }

        return node;
    }

    public static void main(String[] args) {
        Method_TST tst = new Method_TST();

        tst.insert("apple");
        tst.insert("banana");
        tst.insert("orange");
        tst.delete("apple");

        System.out.println(tst.search("apple"));   // false
        System.out.println(tst.search("banana"));  // true
        System.out.println(tst.search("orange"));  // true
        System.out.println(tst.search("grape"));   // false
    }
}
