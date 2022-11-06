package main.java.sprint6;

/* 
 * Найдите кратчайшее расстояние между парой вершин в неориентированном графе. 
 * Граф может быть несвязным.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskF {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    List<List<Integer>> g = new ArrayList<>(Stream.generate(ArrayList<Integer>::new)
        .limit(n).collect(Collectors.toList()));

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;
      g.get(from).add(to);
      g.get(to).add(from);
    }


    st = new StringTokenizer(br.readLine());

    int s = Integer.parseInt(st.nextToken()) - 1;
    int t = Integer.parseInt(st.nextToken()) - 1;

    System.out.println(bfs(g, s, t));
  }

  private static int bfs(List<List<Integer>> g, int s, int t) {
    int V = g.size();
    StringBuilder sb = new StringBuilder();
    boolean visited[] = new boolean[V];
    visited[s] = true;
    int distance[] = new int[V];
    Arrays.fill(distance, -1);
    distance[s] = 0;

    LinkedList<Integer> queue = new LinkedList<>();
    queue.push(s);

    while (queue.size() > 0) {
      int front = queue.poll();
      sb.append(front + 1).append(" ");

      List<Integer> siblings = g.get(front);
      for (int i = 0; i < siblings.size(); i++) {
        int vertex = siblings.get(i);
        if (!visited[vertex]) {
          visited[vertex] = true;
          distance[vertex] = distance[front] + 1;
          queue.add(vertex);

          if (vertex == t) {
            break;
          }
        }
      }
    }

    return distance[t];
  }
}
