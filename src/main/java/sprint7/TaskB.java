package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class TaskB {

  private static String fmt(double d) {
    if (d == (long) d)
      return String.format("%d", (long) d);
    else
      return String.format("%s", d);
  }

  private static class Lesson {
    double from;
    double to;

    public Lesson(double from, double to) {
      this.to = to;
      this.from = from;
    }

    @Override
    public String toString() {
      return fmt(from) + " " + fmt(to);
    }
  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var n = Integer.parseInt(br.readLine());
    var lessons = new ArrayList<Lesson>();
    for (int i = 0; i < n; i++) {
      var st = new StringTokenizer(br.readLine());
      lessons.add(new Lesson(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
    }

    Collections.sort(lessons, (a, b) -> {
      var res = Double.compare(a.to, b.to);
      if (res != 0) {
        return res;
      }

      return Double.compare(a.from, b.from);
    });

    // System.out.println(lessons);

    var res = new Stack<Lesson>();
    res.add(lessons.get(0));

    for (int i = 1; i < lessons.size(); i++) {
      var last = res.peek();
      var lesson = lessons.get(i);
      if (lesson.from >= last.to) {
        res.add(lesson);
      }
    }

    var sb = new StringBuilder();
    sb.append(res.size()).append('\n');
    for (var lesson: res) {
      sb.append(lesson).append('\n');
    }

    System.out.println(sb);
  }
}
