package ru.realdating.project;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestClassTest {

    @Test
    public void testBubbleSort() {
        int[] array = {-9, 0, 1, 4, 24};
        assertArrayEquals(array, new TestClass().bubbleSort());
    }

    @Test
    public void testBubbleSortIndexNumber() {
        assertEquals(5, new TestClass().bubbleSort().length);
    }

    @Test
    public void testArrayToString() {
        assertEquals("-901424", new TestClass().arrayToString(new TestClass().bubbleSort()));
    }

}