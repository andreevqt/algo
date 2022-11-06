package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TaskA {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));

    var n = Integer.parseInt(br.readLine());
    var prices = new int[n];

    var st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      prices[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(solution(prices));
  }

  private static int solution(int prices[]) {
    var profit = 0;

    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        profit += prices[i] - prices[i - 1];
      }
    }

    return profit;
  }
}
