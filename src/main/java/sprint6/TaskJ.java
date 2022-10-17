package main.java.sprint6;

/* 
 * Дан ациклический ориентированный граф (так называемый DAG, directed acyclic graph). Найдите его топологическую сортировку, то есть выведите его вершины в таком порядке,
 * что все рёбра графа идут слева направо. У графа может быть несколько подходящих перестановок вершин. Вам надо найти любую топологическую сортировку.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskJ {

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var st = new StringTokenizer(br.readLine());
    var n = Integer.parseInt(st.nextToken());
    var m = Integer.parseInt(st.nextToken());
    var g = new ArrayList<ArrayList<Integer>>(Stream.generate(ArrayList<Integer>::new)
        .limit(n).collect(Collectors.toList()));

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      var from = Integer.parseInt(st.nextToken()) - 1;
      var to = Integer.parseInt(st.nextToken()) - 1;
      g.get(from).add(to);
    }

    System.out.println(solution(g));
  }

  private static String solution(ArrayList<ArrayList<Integer>> g) {
    var order = new Stack<Integer>();
    var visited = new boolean[g.size()];

    for (int i = 0; i < g.size(); i++) {
      if (!visited[i]) {
        dfs(g, i, order, visited);
      }
    }

    var sb = new StringBuilder();
    while (order.size() > 0) {
      sb.append(order.pop() + 1).append(" ");
    }

    return sb.toString();
  }

  private static void dfs(ArrayList<ArrayList<Integer>> g, int s, Stack<Integer> order, boolean[] visited) {
    var queue = new LinkedList<Integer>();
    queue.push(s);

    while (queue.size() > 0) {
      var front = queue.poll();

      if (!visited[front]) {
        order.push(front);
        visited[front] = true;
      }

      var siblings = g.get(front);
      for (var i = 0; i < siblings.size(); i++) {
        var vertex = siblings.get(i);
        if (!visited[vertex]) {
          queue.add(vertex);
          order.push(front);
        }
      }
    }
  }

}