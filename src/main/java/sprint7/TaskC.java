package main.java.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskC {

  private static class Sack {
    long amount;
    long price;

    Sack(long price, long amount) {
      this.amount = amount;
      this.price = price;
    }

  }

  public static void main(String[] args) throws NumberFormatException, IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var freeSpace = Integer.parseInt(br.readLine());
    var n = Integer.parseInt(br.readLine());

    var sacks = new Sack[n];
    for (int i = 0; i < n; i++) {
      var st = new StringTokenizer(br.readLine());
      sacks[i] = new Sack(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
    }

    Arrays.sort(sacks, (a, b) -> Long.compare(b.price, a.price));

    var profit = 0L;
    for (int i = 0; i < n && freeSpace > 0; i++) {
      var amount = sacks[i].amount <= freeSpace ? sacks[i].amount : freeSpace;
      profit += amount * sacks[i].price;
      freeSpace -= amount;
    }

    System.out.println(profit);
  }
}
