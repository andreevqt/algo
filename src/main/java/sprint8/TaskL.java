package main.java.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskL {

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var str = br.readLine();

    var sb = new StringBuilder();
    var dp = new int[str.length()];

    sb.append(dp[0]).append(" ");
    for (int i = 1; i < dp.length; i++) {
      var k = dp[i - 1];
      while (k > 0 && str.charAt(k) != str.charAt(i)) {
        k = dp[k - 1];
      }

      if (str.charAt(k) == str.charAt(i)) {
        k++;
      }

      dp[i] = k;
      sb.append(dp[i]).append(" ");
    }

    System.out.println(sb);
  }
}
