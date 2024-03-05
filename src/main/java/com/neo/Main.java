package com.neo;



import com.neo.combinatorial.*;
import com.neo.generator.SA;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args){

    // input parameters
    int parameter = 5;
    int[] value = new int[]{2, 2, 2, 3, 3};
    int strength = 2;
    List<List<String>> constraint = new ArrayList<>();
    constraint.add(Arrays.asList("0/0", "1/0"));         // [0, 0, -, -, -]
    constraint.add(Arrays.asList("2/1", "4/2"));         // [-, -, 1, -, 2]
    constraint.add(Arrays.asList("2/0", "3/0", "4/1"));  // [-, -, 0, 0, 1]

    // run generation algorithm
    CTModel model = new CTModel(parameter, value, strength, constraint);
    TestSuite ts = new TestSuite();
    SA gen = new SA(false);

    Instant start = Instant.now();
    gen.generation(model, ts);
    Instant end = Instant.now();

    // output: testsuite, size, time
    for (TestCase each : ts.suite) {
      System.out.println(Arrays.toString(each.test));
    }
    System.out.println("size = " + ts.suite.size());
    System.out.println("time = " + Duration.between(start, end).getSeconds());

  }

}
