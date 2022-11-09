package main.java.hw7;

/*
 * https://contest.yandex.ru/contest/25597/run-report/73995284/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Заведем двумерный массив dp[i][j] где i - будет пробегать первую строку, j - вторую
 * и dp[i][j] - будет расстояние между подстроками 1...i, 1...j
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Создадим массив dp[n + 1][m + 1]. В dp мы будем хранить расстояние между подстроками i, j. Инициализируем 1 строку числами 0...m и 1 столбец 0...n - 
 * это будут расстояния между подстрокми первой строки и пустой второй строкой и подстроками второй строки и пустой первой соответственно.
 * Базовый случай dp[0][0] = 0 если 2 пустые подстроки.
 * Переход динамики:
 *  1) если символы i - 1, j - 1 равны, то dp[i][j] = dp[i - 1][j - 1] (влево вверх по диагонали на 1 клеточку)
 *  2) иначе min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1 - минимальное значение между символом слева и символом сверху + 1
 * Порядок обхода: от начала до конца каждой строки
 * Ответ будет находится в dp[n][m], где n,m длины строк
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Средний случай: O(N * M) - где N - длина первой строки, M - длина второй
  * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(N * M) - хранение массива dp, где N - длина первой строки, M - длина второй
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskA {

  private static int min(int a, int b, int c) {
    return Math.min(Math.min(a, b), c);
  }

  private static int levensteinDistance(String str1, String str2) {
    var n = str1.length();
    var m = str2.length();

    var dp = new int[2][m + 1];

    for (int i = 0; i <= m; i++) {
      dp[0][i] = i;
    }

    for (int i = 1; i <= n; i++) {
      dp[i % 2][0] = i;

      for (int j = 1; j <= m; j++) {
        if (str1.charAt((i - 1) % 2) == str2.charAt(j - 1)) {
          dp[i % 2][j] = dp[(i - 1) % 2][j - 1];
        } else {
          dp[i % 2][j] = min(dp[i % 2][j - 1], dp[(i - 1) % 2][j], dp[(i - 1) % 2][j - 1]) + 1;
        }

        if (i == n && j == m) {
          return dp[i % 2][j];
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));

    var str1 = br.readLine();
    var str2 = br.readLine();

    System.out.println(levensteinDistance(str1, str2));
  }
}
