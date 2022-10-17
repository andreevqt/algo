package main.java.hw6;

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

        for (var sibling : g.get(vertex)) {
          if (colors[sibling] == Colors.WHITE) {
            stack.add(sibling);
            continue;
          }

          if (colors[sibling] == Colors.GRAY) {
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
