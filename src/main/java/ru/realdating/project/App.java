package ru.realdating.project;

public class App {

    public static void main(String[] args) {

        int[] array = new TestClass().bubbleSort();
        String string = "";
        for (int i = 0; i < array.length; i++) {
            string += " " + String.valueOf(array[i]);
        }
        for (Integer j :array) {
            System.out.println(j);
        }
        //System.out.println(string);

    }

}
