package telran.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static telran.utils.Arrays.*;

public class ArraysTests
{
    int[] numbers = {10, 7, 12, -5, 3, -13, 11};

    @Test
    void searchFirstEntryTest()
    {
        assertEquals(0, searchFirstEntry(numbers, 10));   // first element
        assertEquals(6, searchFirstEntry(numbers, 11));   // last element
        assertEquals(3, searchFirstEntry(numbers, -5));   // element in the middle
        assertEquals(-1, searchFirstEntry(numbers, 100));
    }

    @Test
    void addItemTest()
    {
        int new_value = 100;
        int[] expected_array = {10, 7, 12, -5, 3, -13, 11, 100};

        assertArrayEquals(addItem(numbers, new_value), expected_array);
    }

    @Test
    void insertItemTest()
    {
        int new_value = 123;
        int key_new_value = 3;
        int[] expected_array = {10, 7, 12, new_value, -5, 3, -13, 11};

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
        int[] expected_array = {10, 7, 12, -5, -13, 11};

        assertArrayEquals(removeItem(numbers, key_removed), expected_array);

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> removeItem(numbers, 50));
        assertEquals("arraycopy: last source index 50 out of bounds for int[" + numbers.length + "]", exception.getMessage());

        exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> removeItem(numbers, -50));
        assertEquals("arraycopy: length -50 is negative", exception.getMessage());
    }

    @Test
    void pushMaxToEndTest()
    {
        int[] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        //int[] expected_array = {-13, -5,  3, 7, 10, 12,25};

        pushMaxToEnd(testNumbers);

        assertEquals(12, testNumbers[testNumbers.length-1]);
        assertEquals(11, testNumbers[testNumbers.length-2]);
    }

    @Test
    void sortBubbleMinToMaxTest()
    {
        int[] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        int[] expected_array = {-13, -5,  3, 7, 10, 11,12};

        sortBubbleMinToMax(testNumbers);

        assertArrayEquals(testNumbers, expected_array);
    }

    @Test
    void isArraySortedTest()
    {
        int[] test_yes_asc_uniq = {-13, -5,  3, 7, 10, 11, 12};
        int[] test_yes_asc_non_uniq = {-13, -5,  -5, 3, 7, 10, 10, 11, 12};
        int[] test_no = {-15, 12, 0, -3, 17, 23};
        int[] test_yes_desc_uniq = {13, 5,  3, -7, -10, -11, -12};
        int[] test_yes_desc_non_uniq = {13, 5, 5, 3, -7, -10, -10, -11, -12};

        assertFalse(isArraySorted(test_no, SortStatusChecking.ASCENDING_UNIQUE));
        assertTrue(isArraySorted(test_yes_asc_uniq, SortStatusChecking.ASCENDING_UNIQUE));
        assertTrue(isArraySorted(test_yes_asc_non_uniq, SortStatusChecking.ASCENDING_NONE_UNIQUE));
        assertFalse(isArraySorted(test_no, SortStatusChecking.DESCENDING_UNIQUE));
        assertTrue(isArraySorted(test_yes_desc_uniq, SortStatusChecking.DESCENDING_UNIQUE));
        assertTrue(isArraySorted(test_yes_desc_non_uniq, SortStatusChecking.DESCENDING_NONE_UNIQUE));
    }

    @Test
    void binarySearchTest()
    {
        int[] test_array = {2, 5, 7, 10, 12, 13, 14, 21, 26};
        int[] test_unsorted_array = {-15, 12, 0, -3, 17, 23};
        int searched_presented_value_right = 21;
        int searched_presented_value_left = 7;
        int searched_presented_value_start = 2;
        int searched_presented_value_finish = 26;
        int searched_nonpresented_value = 6;

        assertEquals(7, binarySearch_ver1(test_array, searched_presented_value_right));
        assertEquals(2, binarySearch_ver1(test_array, searched_presented_value_left));
        assertEquals(0, binarySearch_ver1(test_array, searched_presented_value_start));
        assertEquals(8, binarySearch_ver1(test_array, searched_presented_value_finish));
        assertEquals(-1, binarySearch_ver1(test_array, searched_nonpresented_value));
        assertEquals(-1, binarySearch_ver1(test_unsorted_array, searched_nonpresented_value));

        assertEquals(7, binarySearch_ver2(test_array, searched_presented_value_right));
        assertEquals(2, binarySearch_ver2(test_array, searched_presented_value_left));
        assertEquals(0, binarySearch_ver2(test_array, searched_presented_value_start));
        assertEquals(8, binarySearch_ver2(test_array, searched_presented_value_finish));
        assertEquals(-1, binarySearch_ver2(test_array, searched_nonpresented_value));
        assertEquals(-1, binarySearch_ver2(test_unsorted_array, searched_nonpresented_value));
    }
}
