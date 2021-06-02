package ru.realdating.project;

public class App {

    public static void main(String[] args) {

        int[] array = new TestClass().bubbleSort();

        System.out.println(new TestClass().arrayToString(array));

    }

}
