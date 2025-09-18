package com.josegc;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application {
  private static final int HOURGLASS_SIZE = 2;

  public static void main(String[] args) {
    int[] ints = new int[] {5, 2, 66, -1, 1, 23, 0};
    int[][] intsOfInts = {{5, 8, 123, 87}, {9754, 21343, 748}, {0}};

    String stringArg = "I love Love to To tO code";

    String regex = "(\\w+)(\\s+\\1)+";
    Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    Matcher m = p.matcher(stringArg);

    while (m.find()) {
      stringArg = stringArg.replaceAll(m.group(), m.group(1));
    }

    System.out.println(stringArg);
  }

  private static void hourglass() {

    int[][] arr = {
      {1, 1, 1, 0, 0, 0},
      {0, 1, 0, 0, 0, 0},
      {1, 1, 1, 0, 0, 0},
      {0, 0, 2, 4, 4, 0},
      {0, 0, 0, 2, 0, 0},
      {0, 0, 1, 2, 4, 0}
    };

    int max = 0;

    for (int i = 0; i < arr.length - HOURGLASS_SIZE; i++) {
      for (int j = 0; j < arr[i].length - HOURGLASS_SIZE; j++) {
        int sum =
            arr[i][j]
                + arr[i][j + 1]
                + arr[i][j + 2]
                + arr[i + 1][j + 1]
                + arr[i + 2][j]
                + arr[i + 2][j + 1]
                + arr[i + 2][j + 2];

        if (max < sum) {
          max = sum;
        }
      }
    }
    System.out.println(max);
  }

  private static void flattenedSort(int[][] intsOfInts) {
    List<List<Integer>> listOfLists = flatten(intsOfInts);

    List<Integer> toSout =
        listOfLists.stream()
            .flatMap(s -> s.stream())
            .sorted(
                (a, b) -> {
                  return a.compareTo(b);
                })
            .collect(Collectors.toList());

    System.out.println(toSout);
  }

  private static List<List<Integer>> flatten(int[][] intsOfInts) {
    List<List<Integer>> listOfLists =
        Arrays.stream(intsOfInts)
            .map(row -> Arrays.stream(row).boxed().collect(Collectors.toList()))
            .collect(Collectors.toList());
    return listOfLists;
  }

  private static void streamSort(int[] ints) {
    List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());

    System.out.println(
        list.stream()
            .sorted(
                (a, b) -> {
                  return b.compareTo(a);
                })
            .collect(Collectors.toList()));
  }

  private static void selectionSort(int[] ints) {
    for (int i = 0; i < ints.length; i++) {
      int smallest = i;

      for (int j = i + 1; j < ints.length; j++) {
        if (ints[smallest] > ints[j]) {
          smallest = j;
        }
      }

      int aux = ints[i];
      ints[i] = ints[smallest];
      ints[smallest] = aux;
    }

    System.out.println(Arrays.toString(ints));
  }
}
