package main.java.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class TaskA {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));

    var st = new StringTokenizer(br.readLine());

    var stack = new Stack<String>();

    while (st.hasMoreTokens()) {
      var str = st.nextToken();
      stack.add(str);
    }

    var sb = new StringBuilder();
    while (stack.size() > 0) {
      sb.append(stack.pop()).append(" ");
    }

    System.out.println(sb);
  }
}
