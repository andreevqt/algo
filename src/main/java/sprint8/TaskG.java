package main.java.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TaskG {

  private static int find(int[] seq, int[] pattern, int start) {
    for (int i = start; i < seq.length - pattern.length + 1; i++) {
      var match = true;
      var diff = seq[i] - pattern[0];
      for (int j = 1; j < pattern.length; j++) {
        if (seq[i + j] != pattern[j] + diff) {
          match = false;
          break;
        }
      }
      if (match) {
        return i;
      }
    }

    return -1;
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var n = Integer.parseInt(br.readLine());
    var seq = new int[n];

    var st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    var m = Integer.parseInt(br.readLine());
    var pattern = new int[m];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      pattern[i] = Integer.parseInt(st.nextToken());
    }

    var sb = new StringBuilder();
    var start = 0;
    var res = 0;
    while ((res = find(seq, pattern, start)) != -1) {
      sb.append(res + 1).append(" ");
      start = res + 1;
    }
    
    System.out.println(sb);
  }
}
