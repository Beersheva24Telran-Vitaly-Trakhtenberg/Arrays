package telran.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static telran.utils.Arrays.*;

public class ArraysTests
{
    int[] numbers = {10, 7, 12, -5, 3, -13, 25};

    @Test
    void searchFirstEntryTest()
    {
        assertEquals(0, searchFirstEntry(numbers, 10));   // first element
        assertEquals(6, searchFirstEntry(numbers, 25));   // last element
        assertEquals(3, searchFirstEntry(numbers, -5));   // element in the middle
        assertEquals(-1, searchFirstEntry(numbers, 100));
    }

    @Test
    void addItemTest()
    {
        int new_value = 100;
        int[] expected_array = {10, 7, 12, -5, 3, -13, 25, 100};

        assertArrayEquals(addItem(numbers, new_value), expected_array);
    }

    @Test
    void insertItemTest()
    {
        int new_value = 123;
        int key_new_value = 3;
        int[] expected_array = {10, 7, 12, new_value, -5, 3, -13, 25};

        assertArrayEquals(insertItem(numbers, new_value, key_new_value), expected_array);

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> insertItem(numbers, new_value, 50));
        assertEquals("arraycopy: last source index 50 out of bounds for int[" + numbers.length + "]", exception.getMessage());

        exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> insertItem(numbers, new_value, -50));
        assertEquals("arraycopy: length -50 is negative", exception.getMessage());
    }

    @Test
    void removeItemTest()
    {
        int key_removed = 4;
        int[] expected_array = {10, 7, 12, -5, -13, 25};

        assertArrayEquals(removeItem(numbers, key_removed), expected_array);

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> removeItem(numbers, 50));
        assertEquals("arraycopy: last source index 50 out of bounds for int[" + numbers.length + "]", exception.getMessage());

        exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> removeItem(numbers, -50));
        assertEquals("arraycopy: length -50 is negative", exception.getMessage());
    }
}
