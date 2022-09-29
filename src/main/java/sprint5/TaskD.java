package main.java.sprint5;

/* 
 * Задан неориентированный граф. Обойдите поиском в ширину все вершины, 
 * достижимые из заданной вершины s, и выведите их в порядке обхода, если начинать обход из s.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskD {

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
      g.get(to).add(from);
    }

    var s = Integer.parseInt(br.readLine()) - 1;

    for (var item : g) {
      Collections.sort(item);
    }

    System.out.println(bfs(g, s));
  }

  private static String bfs(ArrayList<ArrayList<Integer>> g, int s) {
    var sb = new StringBuilder();
    var visited = new boolean[g.size()];
    visited[s] = true;

    var queue = new LinkedList<Integer>();
    queue.push(s);

    while (queue.size() > 0) {
      var front = queue.poll();
      sb.append(front + 1).append(" ");

      var siblings = g.get(front);
      for (var i = 0; i < siblings.size(); i++) {
        var vertex = siblings.get(i);
        if (!visited[vertex]) {
          visited[vertex] = true;
          queue.add(vertex);
        }
      }
    }

    return sb.toString();
  }

}