package main.java.hw8;

/*
 * https://contest.yandex.ru/contest/26133/run-report/75439278/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Добавляем строки в trie и потом методом dp проверяем можно ли разбить подстроку на слова
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * После того как добавили элементы в trie, проходим по всем символам из строки и проверяем можно ли разбить 
 * эти подстроки на слова и сохраняем эту информацию в dp
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * вставка в trie: O(L), L - суммарная длина всех слов во множестве
 * цикл с: dp O(N^2) - кол-во символов в строке
 * весь алгоритм: O(L + N^2)
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(L + N), где L - суммарная длина всех слов во множестве, N - количество символов в строке
 * */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TaskB {

  private static class Node {
    Map<Character, Node> children;
    boolean endOfWord;

    public Node() {
      this.children = new HashMap<>();
      this.endOfWord = false;
    }
  }

  private static void insert(Node root, String word) {
    var current = root;
    for (int i = 0; i < word.length(); i++) {
      var ch = word.charAt(i);
      if (!current.children.containsKey(ch)) {
        current.children.put(ch, new Node());
      }
      current = current.children.get(ch);
    }
    current.endOfWord = true;
  }

  public static void main(String[] args) throws Exception {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var str = br.readLine();

    var n = Integer.parseInt(br.readLine());

    var root = new Node();
    for (int i = 0; i < n; i++) {
      insert(root, br.readLine());
    }

    var dp = new boolean[str.length() + 1];
    dp[0] = true;

    for (int i = 0; i < str.length(); i++) {
      var current = root;

      if (!dp[i]) {
        continue;
      }

      for (int j = i; j <= str.length(); j++) {
        if (current.endOfWord) {
          dp[j] = true;
        }
        if (j == str.length() || !current.children.containsKey(str.charAt(j))) {
          break;
        }

        current = current.children.get(str.charAt(j));
      }

    }

    System.out.println(dp[dp.length - 1] ? "YES" : "NO");
  }
}
