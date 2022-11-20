package main.java.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskJ {

  private static class Node {
    Map<Character, Node> children;
    boolean endOfWord;

    public Node(boolean endOfWord) {
      this.children = new HashMap<>();
      this.endOfWord = endOfWord;
    }

    @Override
    public String toString() {
      return "Node [children=" + children + ", endOfWord=" + endOfWord + "]";
    }

  }

  private static void insert(Node root, String str) {
    var current = root;
    for (int i = 0; i < str.length(); i++) {
      var ch = str.charAt(i);
      if (!current.children.containsKey(ch)) {
        var node = new Node(i < str.length() - 1 ? false : true);
        current.children.put(ch, node);
      }
      current = current.children.get(ch);
    }
  }

  private static void search(Node root, String pattern) {
    var current = root;
    for (int i = 0; i < pattern.length(); i++) {
      var ch = pattern.charAt(i);
      var node = current.children.get(ch);
    }
  }

  private static String solution(String words[], String patterns[]) {
    var trie = new Node(false);

    for (int i = 0; i < words.length; i++) {
      insert(trie, words[i]);
    }

    System.out.println(trie);

    return "";
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));

    var n = Integer.parseInt(br.readLine());

    var words = new String[n];
    for (int i = 0; i < n; i++) {
      words[i] = br.readLine();
    }

    var m = Integer.parseInt(br.readLine());
    var patterns = new String[m];

    for (int i = 0; i < m; i++) {
      patterns[i] = br.readLine();
    }

    System.out.println(solution(words, patterns));
  }
}
