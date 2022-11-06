package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskH {

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var st = new StringTokenizer(br.readLine());

    var n = Integer.parseInt(st.nextToken());
    var m = Integer.parseInt(st.nextToken());

    var cost = new int[n][m];
    var dp = new int[n][m];

    for (int i = 0; i < n; i++) {
      var row = br.readLine();
      for (int j = 0; j < m; j++) {
        cost[i][j] = Character.getNumericValue(row.charAt(j));
      }
    }

    for (int i = n - 1; i >= 0; i--) {
      for (int j = 0; j < m; j++) {
        var dpY = i < n - 1 ? dp[i + 1][j] : 0;
        var dpX = j > 0 ? dp[i][j - 1] : 0;
        dp[i][j] = Math.max(dpX, dpY) + cost[i][j];
      }
    }

    System.out.println(dp[0][m - 1]);
  }
}
