package ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private static final TrieNode root = new TrieNode();
    private static final ArrayList<String> trieWords = new ArrayList<String>();

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        TrieNode() {
            children = new TreeMap<Character, TrieNode>();
            isEndOfWord = false;
        }
    }
    /**
     * Search all prefixes.
     */
    public static ArrayList<String> search(String target) {
        trieWords.clear();
        if (target.isEmpty()) {
            return trieWords;
        }

        TrieNode curNode = root;
        int length = target.length();
        for (int level = 0; level < length; level++) {
            char cur = target.charAt(level);
            if (curNode.children.get(cur) == null) {
                //no words have this prefix
                return trieWords;
            }
            curNode = curNode.children.get(cur);
        }
        //get all the words with prefix target.
        getAllPrefixes(target, curNode);
        return trieWords;
    }

    /**
     * dfs to get all words that have prefix target
     * @param target - String
     * @param curNode - current Trie Node.
     */
    private static void getAllPrefixes(String target, TrieNode curNode) {
        if (curNode.isEndOfWord) {
            trieWords.add(target);
            //return;
        }
        for (char index : curNode.children.keySet()) {
            if (curNode.children.get(index) != null) {
                getAllPrefixes(target + index, curNode.children.get(index));
            }
        }
    }
}
