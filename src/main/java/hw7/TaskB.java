package main.java.hw7;

/*
 * https://contest.yandex.ru/contest/25597/run-report/74000209/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Задача сводится к решению задачи о сумме подмножеств
 * Заведем двумерный массив dp[i][j] где i пробегает сумму подмножества элементов, j - элементы
 * и dp[i][j] - будет true если существет такое подмножество games[0...j] что его сумма равна i
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Создадим массив dp[sum + 1][n + 1]. Инициализируем 1 строку true, потому что если сумма = 0 то подмножество существует,
 * А первый столбец все false кроме первого элемента
 * Базовый случай dp[0][0] = 0 сумма пустого множества 0
 * Переход динамики:
 *  1) dp[i][j] = dp[i][j] || dp[i - games[j - 1]][j - 1]
 * Ответ будет находится в dp[sum][n], где sum - сумма n - количество игр
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Средний случай: O(M * N) - где M - сумма, N - количество игр
  * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(M * N) - хранение массива dp, где M - сумма, N - количество игр
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;  

public class TaskB {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    var n = Integer.parseInt(br.readLine());

    var st = new StringTokenizer(br.readLine());   
 
    var games = new int[n];
    var total = 0;

    for (int i = 0; i < n; i++) {
      games[i] = Integer.parseInt(st.nextToken());
      total += games[i];
    }

    if (total % 2 != 0) {
      System.out.println("False");
      return;
    }

    var sum = total / 2;
    var dp = new boolean[sum + 1][n + 1];

    for (int i = 0; i <= n; i++) {
      dp[0][i] = true;
    }

    for (int i = 1; i <= sum; i++) {
      dp[i][0] = false;
    }

    for (int i = 1; i <= sum; i++) {
      for (int j = 1; j <= n; j++) {
        dp[i][j] = dp[i][j - 1];
        if (i >= games[j - 1]) {
          dp[i][j] = dp[i][j] || dp[i - games[j - 1]][j - 1];
        }

        if (i == sum && dp[i][j]) {
          System.out.println("True");
          return;
        }
      }
    }

    System.out.println("False");
  }
}
