package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TaskK {

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));

    var n = Integer.parseInt(br.readLine());
    var seq1 = new int[n];
    var st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      seq1[i] = Integer.parseInt(st.nextToken());
    }

    var m = Integer.parseInt(br.readLine());
    var seq2 = new int[m];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      seq2[i] = Integer.parseInt(st.nextToken());
    }

    var dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (seq1[i - 1] == seq2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    var indices1 = new ArrayList<Integer>();
    var indices2 = new ArrayList<Integer>();

    var i = n;
    var j = m;

    while (i > 0 && j > 0) {
      if (seq1[i - 1] == seq2[j - 1]) {
        indices1.add(i);
        indices2.add(j);
        i--;
        j--;
      } else if (dp[i][j] == dp[i - 1][j]) {
        i--;
      } else if (dp[i][j] == dp[i][j - 1]) {
        j--;
      }
    }

    Collections.reverse(indices1);
    Collections.reverse(indices2);

    System.out.println(dp[n][m]);

    var sb = new StringBuilder();
    for (var index : indices1) {
      sb.append(index).append(' ');
    }
    sb.append('\n');

    for (var index : indices2) {
      sb.append(index).append(' ');
    }

    System.out.println(sb);

  }

}
