package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TaskL {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var st = new StringTokenizer(br.readLine());

    var n = Integer.parseInt(st.nextToken());
    var m = Integer.parseInt(st.nextToken());

    var weight = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    var dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (j >= weight[i - 1]) {
          dp[i][j] = Math.max(dp[i - 1][j], weight[i - 1] + dp[i - 1][j - weight[i - 1]]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    System.out.println(dp[n][m]);
  }
}
