package ua.dp.mign.trees.binary;// tree.java

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

////////////////////////////////////////////////////////////////
class HuffmanNode {
    char data;              // data item (key)
    HuffmanNode leftChild;         // this node's left child
    HuffmanNode rightChild;        // this node's right child
    int frequency;

    public HuffmanNode(char c, int freq) {
        this(c);
        this.frequency = freq;
    }

    public HuffmanNode(char c) {
        this.data = c;
    }

    @Override
    public String toString() {
        return String.format("{%s}", data);
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean hasChildren() {
        return this.leftChild != null || this.rightChild != null;
    }
}  // end class Node

////////////////////////////////////////////////////////////////
class HuffmanTree {
    private HuffmanNode root;             // first node of tree

    // -------------------------------------------------------------
    public HuffmanTree(HuffmanNode node)                  // constructor
    {
        root = node;
    }            // no nodes in tree yet

    // -------------------------------------------------------------
    public Map<Character,String> buildCodeTable() {
        Map<Character, String> table = new HashMap<>();
        encodeNode(root, table, "");
        return table;
    }

    private static void encodeNode(HuffmanNode node, Map<Character, String> table, String code) {
        if (node == null) {
            return;
        }
        if (node.hasChildren()) {
            encodeNode(node.leftChild, table, code + "0");
            encodeNode(node.rightChild, table, code + "1");
        } else {
            table.put(node.data, code);
        }
    }

    public static void printTable(Map<Character, String> codingTable) {
        System.out.println("Coding table.");
        for (Map.Entry<Character, String> entry : codingTable.entrySet()) {
            System.out.printf("Char: %s, Code: %s\n", entry.getKey(), entry.getValue());
        }
    }
// -------------------------------------------------------------
}  // end class Tree

////////////////////////////////////////////////////////////////
class HuffmanTreeApp {
    public static void main(String[] args) throws IOException {
        String theString = "If the message is short, the program should be able to display the Huffman tree\n" +
                "after creating it. The ideas in Programming Projects 8.1, 8.2, and 8.3 might\n" +
                "prove helpful. You can use String variables to store binary numbers as arrangements\n" +
                "of the characters 1 and 0. Don’t worry about doing actual bit manipulation\n" +
                "unless you really want to.";

        HuffmanTree tree = buildTree(theString.toLowerCase());
        Map<Character, String> dictionary = tree.buildCodeTable();

        String encoded = encode(theString.toLowerCase(), dictionary);
        System.out.println("Encoded:");
        System.out.println(encoded);

        String decoded = decode(encoded, inverse(dictionary));
        System.out.println("Decoded:");
        System.out.println(decoded);
    }  // end main()

    private static String encode(String plainText, Map<Character, String> dictionary) {
        return plainText.chars().mapToObj(c -> dictionary.get(Character.valueOf((char)c))).collect(joining());
    }

    private static String decode(String decodedText, Map<String, Character> dictionary) {
        StringBuilder result = new StringBuilder();
        String word = "";
        for (char c : decodedText.toCharArray()) {
            word += c;
            if (dictionary.containsKey(word)) {
                result.append(dictionary.get(word));
                word = "";
            }
        }
        return result.toString();
    }

    private static HuffmanTree buildTree(String string) {
        Map<Character, Long> frequency = string.chars()
                .mapToObj(c -> Character.valueOf((char) c))
                .collect(groupingBy(identity(), counting()));

        List<HuffmanNode> nodes = frequency.entrySet().stream()
                .map(e -> new HuffmanNode(e.getKey().charValue(), e.getValue().intValue()))
                .collect(toList());

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(comparing(HuffmanNode::getFrequency));
        queue.addAll(nodes);

        while (queue.size() != 1) {
            HuffmanNode first = queue.remove();
            HuffmanNode second = queue.remove();
            HuffmanNode parent = new HuffmanNode(' ', first.getFrequency() + second.getFrequency());
            parent.leftChild = first;
            parent.rightChild = second;
            queue.add(parent);
        }

        return new HuffmanTree(queue.remove());
    }

    private static <K, V> Map<V, K> inverse(Map<K, V> map) {
        return map.entrySet().stream().collect(toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}  // end class TreeApp
////////////////////////////////////////////////////////////////
