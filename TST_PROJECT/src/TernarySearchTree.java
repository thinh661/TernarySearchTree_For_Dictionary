
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TernarySearchTree {

    private TSTNode root;

    private class TSTNode {
        char data;
        boolean isEndOfWord;
        TSTNode left, middle, right;

        public TSTNode(char data) {
            this.data = data;
        }
    }

    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    private TSTNode insert(TSTNode node, char[] word, int index) {
        if (node == null) {
            node = new TSTNode(word[index]);
        }

        if (word[index] < node.data) {
            node.left = insert(node.left, word, index);
        } else if (word[index] > node.data) {
            node.right = insert(node.right, word, index);
        } else {
            if (index < word.length - 1) {
                node.middle = insert(node.middle, word, index + 1);
            } else {
                node.isEndOfWord = true;
            }
        }

        return node;
    }

    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    private boolean search(TSTNode node, char[] word, int index) {
        if (node == null) {
            return false;
        }

        if (word[index] < node.data) {
            return search(node.left, word, index);
        } else if (word[index] > node.data) {
            return search(node.right, word, index);
        } else {
            if (index == word.length - 1) {
                return node.isEndOfWord;
            } else {
                return search(node.middle, word, index + 1);
            }
        }
    }

    public List<String> prefixSearch(String prefix) {
        List<String> words = new ArrayList<>();
        TSTNode node = findPrefixNode(prefix);
        if (node != null) {
            collectWords(node.middle, new StringBuilder(prefix), words);
        }
        return words;
    }

    private TSTNode findPrefixNode(String prefix) {
        TSTNode node = root;
        int index = 0;
        while (node != null && index < prefix.length()) {
            if (prefix.charAt(index) < node.data) {
                node = node.left;
            } else if (prefix.charAt(index) > node.data) {
                node = node.right;
            } else {
                index++;
                if (index == prefix.length()) {
                    return node;
                }
                node = node.middle;
            }
        }
        return null;
    }

    private void collectWords(TSTNode node, StringBuilder prefix, List<String> words) {
        if (node == null) {
            return;
        }
        collectWords(node.left, prefix, words);
        if (node.isEndOfWord) {
            words.add(prefix.toString() + node.data);
        }
        collectWords(node.middle, prefix.append(node.data), words);
        prefix.setLength(prefix.length() - 1);
        collectWords(node.right, prefix, words);
    }

    public static String[] ReadFile(){
        // Specify the path of the file you want to read
        String[] words = new String[3000];
        String filePath = "C:\\Users\\pc\\Desktop\\TST_PROJECT\\src\\word.txt";

        // Create a File object
        File file = new File(filePath);
        int index = 0;
        try {
            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            // Read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                words[index] = line;
                index += 1;
            }

            // Close the scanner when finished reading
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return words;
    }


}


