package main.java.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TaskK {

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var str1 = br.readLine();
    var str2 = br.readLine();

    var l1 = new ArrayList<Character>();
    var l2 = new ArrayList<Character>();

    for (int i = 0; i < str1.length(); i++) {
      if ((int) str1.charAt(i) % 2 == 0) {
        l1.add(str1.charAt(i));
      }
    }

    for (int i = 0; i < str2.length(); i++) {
      if ((int) str2.charAt(i) % 2 == 0) {
        l2.add(str2.charAt(i));
      }
    }

    var min = Math.min(l1.size(), l2.size());
    for (int i = 0; i < min; i++) {
      var res = Integer.compare(l1.get(i), l2.get(i));
      if (res != 0) {
        System.out.println(res);
        return;
      }
    }

    if (l1.size() == l2.size()) {
      System.out.println(0);
      return;
    }

    System.out.println(Integer.compare(l1.size(), l2.size()));
  }
}
