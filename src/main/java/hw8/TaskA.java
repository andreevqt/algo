package main.java.hw8;

/*
 * https://contest.yandex.ru/contest/26133/run-report/75550215/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Распаковываем строки и потом считаем их общий префикс
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Чтобы распаковать строку будем использовать 2 стека - один для хранения слов, другой для хранения множителей,
 * это необходимо чтобы корректно обрабатывать вложенные запакованные строки. Потом, отсортируя распакованные строки,
 * найдем найбольший общий префикс.
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * decode: O(L * S) - где, L - длина строки, S - сумма множителей
 * longestCommonPrefix = O(MIN*N)  - где, N - количество строк, MIN - минимальная длина строки
 * O(L * S) * N + O(MAX*N*Log(N)) = O(L*S*N + MAX*N*Log(N)) - сложность всего алгоритма
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(N*M) - где N - количество строк, M - средняя длина строки
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TaskA {
  private static String decode(String str) {
    var counts = new Stack<Integer>();
    var words = new Stack<String>();

    var multiplier = 0;

    var word = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      var ch = str.charAt(i);
      if (Character.isDigit(ch)) {
        multiplier = Character.getNumericValue(ch);
        continue;
      }

      if (ch == '[') {
        counts.add(multiplier);
        words.add(word.toString());
        word.setLength(0);
        multiplier = 0;
        continue;
      }

      if (ch == ']') {
        var times = counts.pop();
        var sb = new StringBuilder();
        sb.append(words.pop());
        for (int j = 0; j < times; j++) {
          sb.append(word);
        }
        word = sb;
        continue;
      }

      word.append(ch);
    }

    return word.toString();
  }

  private static String longestCommonPrefix(String min, String strings[]) {
    var sb = new StringBuilder();
    var isOk = true;

    for (int i = 0; i < min.length(); i++) {
      for (int j = 1; j < strings.length; j++) {
        if (strings[0].charAt(i) != strings[j].charAt(i)) {
          isOk = false;
          break;
        }
      }
      if (!isOk) {
        break;
      }
      sb.append(min.charAt(i));
    }

    return sb.toString();
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var n = Integer.parseInt(br.readLine());

    var strings = new String[n];
    String min = null;

    for (int i = 0; i < n; i++) {
      var str = decode(br.readLine());
      if (min == null || str.length() < min.length()) {
        min = str;
      }

      strings[i] = str;
    }

    var commonPrefix = longestCommonPrefix(min, strings);
    System.out.println(commonPrefix);
  }
}
