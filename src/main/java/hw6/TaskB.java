package main.java.hw6;
/*
 * https://contest.yandex.ru/contest/25070/run-report/72261903/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * С помощью dfs, проверяем граф на наличие циклов, если циклы есть, то карта неоптимальна иначе - все ок.
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Создадим функцию isCyclic, в которой:
 *   1) cоздадим массив colors, где будем хранить цвета для каждой из вершин графа: WHITE - вершина не посещена, GRAY - обрабатывается, BLACK - посещена.
 *   2) создадим стек, в котором будм хранить вершины для обхода
 *   3) пока стек не пустой, снимем со стека вершину, если она белая то покрасим её в серый цвет и добавим на стек
 *   4) для каждой соседней вершины если она белая то добаим её на стек, если серая то вернем true, т.е мы нашли цикл
 *   5) иначе если вершина серая то покрасим её в черный цвет
 * Проверим каждую вершину графа на цикл с помощью isCyclic
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Средний случай: O(V + E), где V - кол-во вершин, E - кол-во ребер
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(E*V) - используемая память для хранения списка смежности, где V - кол-во вершин, E - кол-во ребер
 * O(V) - память для хранения цветов и стека, где V - кол-во вершин
 * => O(E*V) + O(V) = O(E*V + V)
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskB {
  private static enum Colors {
    WHITE,
    GRAY,
    BLACK
  }

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var n = Integer.parseInt(br.readLine());
    List<List<Integer>> g = new ArrayList<>(Stream.generate(ArrayList<Integer>::new)
        .limit(n).collect(Collectors.toList()));

    for (int i = 0; i < n - 1; i++) {
      var str = br.readLine();
      for (int j = 0; j < str.length(); j++) {
        var ch = str.charAt(j);
        if (ch == 'B') {
          g.get(i).add(i + j + 1);
          continue;
        }
        g.get(i + j + 1).add(i);
      }
    }

    System.out.println(solution(g));
  }

  private static String solution(List<List<Integer>> g) {
    var V = g.size();
    var colors = new Colors[V];
    Arrays.fill(colors, Colors.WHITE);

    for (int i = 0; i < V; i++) {
      if (isCyclic(g, i, colors)) {
        return "NO";
      }
    }

    return "YES";
  }

  private static boolean isCyclic(List<List<Integer>> g, int s, Colors colors[]) {
    var stack = new Stack<Integer>();
    stack.add(s);

    while (stack.size() > 0) {
      var vertex = stack.pop();

      if (colors[vertex] == Colors.WHITE) {
        colors[vertex] = Colors.GRAY;
        stack.add(vertex);

        for (var neighbour : g.get(vertex)) {
          if (colors[neighbour] == Colors.WHITE) {
            stack.add(neighbour);
            continue;
          }

          if (colors[neighbour] == Colors.GRAY) {
            return true;
          }
        }

        continue;
      }

      if (colors[vertex] == Colors.GRAY) {
        colors[vertex] = Colors.BLACK;
      }
    }

    return false;
  }
}
