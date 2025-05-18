package algorithms.trie;

/**
 * A Trie (prefix tree) implementation for storing and searching strings.
 * The Trie supports basic operations such as insertion, search, prefix matching,
 * deletion, and printing its structure.
 */

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Main method to test
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("bat");
        trie.insert("ball");
        trie.insert("barn");
        trie.insert("bar");

        trie.insert("kapil");
        trie.insert("kap");
        trie.insert("karan");


        System.out.println(trie.search("bat"));    // true
        System.out.println(trie.search("barn"));   // true
        System.out.println(trie.search("ba"));     // false (not a complete word)
        System.out.println(trie.startsWith("ba"));      // true (prefix exists)
        System.out.println(trie.search("bad"));


        System.out.println("📦 Trie BEFORE deleting 'kap':");
        trie.print();

        trie.delete("kap");

        System.out.println("\n🧹 Trie AFTER deleting 'kap':");
        trie.print(); // true// false
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Map 'a' to 0, 'b' to 1, ..., 'z' to 25
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }


    public boolean delete(String word) {
        return deleteHelper(root, word.toLowerCase(), 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int depth) {
        if (node == null) return false;

        // If we reached the end of the word
        if (depth == word.length()) {
            if (!node.isEndOfWord) return false; // word doesn't exist
            node.isEndOfWord = false;
            return isEmpty(node); // if no children, node can be deleted
        }

        int idx = word.charAt(depth) - 'a';
        TrieNode child = node.children[idx];

        boolean shouldDeleteCurrentNode = deleteHelper(child, word, depth + 1);

        if (shouldDeleteCurrentNode) {
            node.children[idx] = null;
            return !node.isEndOfWord && isEmpty(node);
        }

        return false;
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) return false;
        }
        return true;
    }

    // 🔍 Print the trie like a tree
    public void print() {
        printHelper(root, "", "");
    }

    private void printHelper(TrieNode node, String prefix, String path) {
        if (node == null) return;

        if (node.isEndOfWord) {
            System.out.println("🔹 " + path);
        }

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char c = (char) (i + 'a');
                printHelper(node.children[i], prefix + "  ", path + c);
            }
        }
    }

    /**
     * Represents a node in a Trie (prefix tree) data structure.
     * Each node contains an array of child nodes representing the 26 lowercase English letters
     * ('a' to 'z'). It also contains a boolean flag to mark whether the node is the end of a word.
     */
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // a to z
        boolean isEndOfWord;

        public TrieNode() {
            isEndOfWord = false;
        }
    }
}
