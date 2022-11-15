package main.java.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TaskE {

  private static String insert(String source, String subString, int index) {
    var len = source.length();
    var shift = subString.length();

    var res = new char[len + shift];

    for (int i = 0; i < index; i++) {
      res[i] = source.charAt(i);
    }

    for (int i = index; i < index + shift; i++) {
      res[i] = subString.charAt(i - index);
    }

    for (int i = index + shift; i < res.length; i++) {
      res[i] = source.charAt(i - shift);
    }

    return String.valueOf(res);
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));

    var source = br.readLine();
    var n = Integer.parseInt(br.readLine());

    var res = source;
    var idx = 0;
    for (int i = 0; i < n; i++) {
      var st = new StringTokenizer(br.readLine());
      var substring = st.nextToken();
      idx += Integer.parseInt(st.nextToken());
      res = insert(res, substring, idx);
      idx += substring.length();
    }

    System.out.println(res);
  }
}
