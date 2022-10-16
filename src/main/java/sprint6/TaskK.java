package main.java.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskK {

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var st = new StringTokenizer(br.readLine());
    var n = Integer.parseInt(st.nextToken());
    var m = Integer.parseInt(st.nextToken());
    var g = new ArrayList<ArrayList<Node>>(Stream.generate(ArrayList<Node>::new)
        .limit(n).collect(Collectors.toList()));

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      var from = Integer.parseInt(st.nextToken()) - 1;
      var to = Integer.parseInt(st.nextToken()) - 1;
      var weight = Integer.parseInt(st.nextToken());
      g.get(from).add(new Node(to, weight));
      g.get(to).add(new Node(from, weight));
    }

    var res = new StringBuilder();
    for (int i = 0; i < n; i++) {
      dijkstra(g, i, res);
    }

    System.out.println(res);
  }

  private static class Node {
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
      this.vertex = vertex;
      this.weight = weight;
    }
  }

  private static void dijkstra(ArrayList<ArrayList<Node>> graph, int s, StringBuilder res) {
    var V = graph.size();
    var dist = new int[V];

    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[s] = 0;

    var pq = new PriorityQueue<Node>((v1, v2) -> v1.weight - v2.weight);
    pq.add(new Node(s, 0));

    while (pq.size() > 0) {
      var curr = pq.poll();

      for (var node: graph.get(curr.vertex)) {
        if (dist[curr.vertex] + node.weight < dist[node.vertex]) {
          dist[node.vertex] = node.weight + dist[curr.vertex];
          pq.add(new Node(node.vertex, dist[node.vertex]));
        }
      }
    }

    
    for (var d: dist) {
      res.append(d < Integer.MAX_VALUE ? d : -1).append(" ");
    }

    res.append('\n');
  }

}
