package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// interface for part a, b, c
interface Product {double operate(double a, double b);}

// interface for part d
interface MyLambda {String getMessage();}

public class Homework3 {
  public static void main(String[] args) {
    // part a
    Product multiply = (double a, double b) -> a * b;
    System.out.println(multiply.operate(4.5, 3.0));

    // part b
    Product multiply2 = (a, b) -> a * b;
    System.out.println(multiply2.operate(7.5, 2.5));

    // part c
    Product multiply3 = (a, b) -> { return a * b; };
    System.out.println(multiply3.operate(2.5, 3.0));

    // part d
    MyLambda welcomeMessage = () -> "Welcome to lambdas!";
    System.out.println(welcomeMessage.getMessage());

    // part e
    List<Integer> e = Stream.of(1, 2, 3).collect(Collectors.toCollection(ArrayList::new));
    System.out.println(e);

    // part f, for this part I need java FX, I will attach other .java file
  }


}

