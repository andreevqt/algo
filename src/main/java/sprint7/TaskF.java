package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TaskF {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var st = new StringTokenizer(br.readLine());
    var n = Integer.parseInt(st.nextToken());
    var k = Integer.parseInt(st.nextToken());

    var dp = new int[n];
    dp[0] = 1;
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= k; j++) {
        if (i - j >= 0) {
          dp[i] = (dp[i] + dp[i - j]) % 1000000007;
        }
      }
    }

    System.out.println(dp[n - 1]);
  }
}
