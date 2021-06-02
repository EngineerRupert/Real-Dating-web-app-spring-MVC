package ru.realdating.project;

public class TestClass {

    public int[] bubbleSort() {
        int[] array = {24, 1 , 4, -9, 0};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int first = array[i];
                    int second = array[j];
                    array[i] = second;
                    array[j] = first;
                }
            }
        }
        return array;
    }

    public String arrayToString(int[] array) {
        String string = "";
        for (int i = 0; i < array.length; i++) {
            string += String.valueOf(array[i]);
        }
        return string;
    }

}
