package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TaskI {
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

    var i = 0;
    var j = m - 1;
    var path = new ArrayList<Character>();
    if (n != 1 && m != 1) {
      while (true) {
        if (i < n - 1 && j > 0) {
          if (dp[i + 1][j] > dp[i][j - 1]) {
            path.add('U');
            i = i + 1;
          } else {
            path.add('R');
            j = j - 1;
          }
        } else if (i < n - 1) {
          path.add('U');
          i = i + 1;
        } else {
          path.add('R');
          j = j - 1;
        }

        if (i == n - 1 && j == 0) {
          break;
        }
      }
    }

    Collections.reverse(path);
    System.out.println(dp[0][m - 1]);

    var sb = new StringBuilder();
    for (var item : path) {
      sb.append(item);
    }

    System.out.println(sb);
  }

}
