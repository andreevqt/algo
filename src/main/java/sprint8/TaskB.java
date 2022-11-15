package main.java.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskB {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var str1 = br.readLine();
    var str2 = br.readLine();

    var n = str1.length();
    var m = str2.length();

    if (Math.abs(n - m) > 1) {
      System.out.println("FAIL");
      return;
    }

    String minStr = null;
    String maxStr = null;

    if (str1.length() > str2.length()) {
      maxStr = str1;
      minStr = str2;
    } else {
      minStr = str1;
      maxStr = str2;
    }

    var start = -1;
    for (int i = 0; i < minStr.length(); i++) {
      if (str1.charAt(i) != str2.charAt(i)) {
        start = i;
        break;
      }
    }

    if (start == -1) {
      System.out.println("OK");
      return;
    }

    for (int i = start + 1; i < maxStr.length(); i++) {
      if (minStr.charAt(n != m ? i - 1 : i) != maxStr.charAt(i)) {
        System.out.println("FAIL");
        return;
      }
    }

    System.out.println("OK");
  }
}
