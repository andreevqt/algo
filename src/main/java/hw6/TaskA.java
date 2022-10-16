package main.java.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskA {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var st = new StringTokenizer(br.readLine());
    var n = Integer.parseInt(st.nextToken());
    var m = Integer.parseInt(st.nextToken());
    List<List<Node>> g = new ArrayList<>(Stream.generate(ArrayList<Node>::new)
        .limit(n).collect(Collectors.toList()));

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      var from = Integer.parseInt(st.nextToken()) - 1;
      var to = Integer.parseInt(st.nextToken()) - 1;
      var weight = Integer.parseInt(st.nextToken());
      g.get(from).add(new Node(to, weight));
      g.get(to).add(new Node(from, weight));
    }

    System.out.println(findMST(g, 0));
  }

  private static class Node {
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
      this.vertex = vertex;
      this.weight = weight;
    }
  }

  private static String findMST(List<List<Node>> graph, int s) {
    var V = graph.size();
    var visited = new boolean[V];
    var visitedCount = 0;

    var pq = new PriorityQueue<Node>((v1, v2) -> v2.weight - v1.weight);
    pq.add(new Node(s, 0));

    var maxWeight = 0;

    while (pq.size() > 0) {
      var curr = pq.poll();

      if (visited[curr.vertex]) {
        continue;
      }

      maxWeight += curr.weight;
      visited[curr.vertex] = true;
      visitedCount++;

      for (var node: graph.get(curr.vertex)) {
        if (!visited[node.vertex]) {
          pq.add(node);
        }
      }
    }

    return visitedCount == V ? String.valueOf(maxWeight) : "Oops! I did it again";
  }
 
}
