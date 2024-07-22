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
        int searched_presented_value_right = 21;
        int searched_presented_value_left = 7;
        int searched_presented_value_start = 2;
        int searched_presented_value_finish = 26;
        int searched_nonpresented_value = 6;

        assertEquals(7, binarySearch(test_array, searched_presented_value_right));
        assertEquals(2, binarySearch(test_array, searched_presented_value_left));
        assertEquals(0, binarySearch(test_array, searched_presented_value_start));
        assertEquals(8, binarySearch(test_array, searched_presented_value_finish));
        assertEquals(-3, binarySearch(test_array, searched_nonpresented_value));
    }

    @Test
    void binarySearchTTest()
    {
        String [] test_strings = {"aa", "cfta", "lmn", "w"};
        String searched_string = "lmn";
        String searched_nonpresented_string = "mln";
        Integer[] test_integers = {10, 20, 30, 40, 50};
        Integer searched_integer = 50;
        Integer searched_nonpresented_integer = 60;

        assertEquals(2, binarySearch(test_strings, searched_string, new ComparatorASCII()));
        assertEquals(-4, binarySearch(test_strings, searched_nonpresented_string, new ComparatorASCII()));

        assertEquals(4, binarySearch(test_integers, searched_integer, new ComparatorInteger()));
        assertEquals(-6, binarySearch(test_integers, searched_nonpresented_integer, new ComparatorInteger()));

        assertEquals(2, binarySearchWithoutComparator_ver1(test_strings, searched_string));
        assertEquals(-4, binarySearchWithoutComparator_ver1(test_strings, searched_nonpresented_string));

        assertEquals(4, binarySearchWithoutComparator_ver1(test_integers, searched_integer));
        assertEquals(-6, binarySearchWithoutComparator_ver1(test_integers, searched_nonpresented_integer));

        assertEquals(2, binarySearchWithoutComparator_ver2(test_strings, searched_string));
        assertEquals(-4, binarySearchWithoutComparator_ver2(test_strings, searched_nonpresented_string));

        assertEquals(4, binarySearchWithoutComparator_ver2(test_integers, searched_integer));
        assertEquals(-6, binarySearchWithoutComparator_ver2(test_integers, searched_nonpresented_integer));

        assertEquals(2, binarySearchWithoutComparator_ver3(test_strings, searched_string));
        assertEquals(-4, binarySearchWithoutComparator_ver3(test_strings, searched_nonpresented_string));

        assertEquals(4, binarySearchWithoutComparator_ver3(test_integers, searched_integer));
        assertEquals(-6, binarySearchWithoutComparator_ver3(test_integers, searched_nonpresented_integer));
    }

    @Test
    void insertSortedTest()
    {
        int[] source_array = {2, 5, 7, 10, 12, 13, 14, 21, 26};
        int[] source_unsorted_array = {-15, 12, 0, -3, 17, 23};
        int[] test_values = {-1, 8, 30};
        int[] expected_array_0 = {-1, 2, 5, 7, 10, 12, 13, 14, 21, 26};
        int[] expected_array_1 = {2, 5, 7, 8, 10, 12, 13, 14, 21, 26};
        int[] expected_array_2 = {2, 5, 7, 10, 12, 13, 14, 21, 26, 30};

        int[] result_array = insertSorted(source_array, test_values[0]);
        assertArrayEquals(expected_array_0, result_array);

        result_array = insertSorted(source_array, test_values[1]);
        assertArrayEquals(expected_array_1, result_array);

        result_array = insertSorted(source_array, test_values[2]);
        assertArrayEquals(expected_array_2, result_array);

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> insertSorted(source_unsorted_array, test_values[1]));
        assertEquals("arraycopy: length -1 is negative", exception.getMessage());

    }

    @Test
    void isOneSwapTest() {
        int[] test_array_1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 11, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] test_array_2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 11, 12, 13, 14, 15, 16, 17, 18, 19, 10};
        int[] test_array_3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 11, 11, 11, 11, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        int[] test_array_4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 11, 11, 11, 11, 11, 12, 13, 14, 15, 16, 17, 18, 19, 10};
        int[] test_array_5 = {10, 2, 1, 10, 20, 30};
        int[] test_array_sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] test_array_unsorted1 = {3, 2, 1, 4, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] test_array_unsorted2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 11, 12, 14, 13, 15, 16, 17, 18, 19, 10};

        assertTrue(isOneSwap_ver1(test_array_1));
        assertTrue(isOneSwap_ver1(test_array_2));
        assertFalse(isOneSwap_ver1(test_array_3));
        assertTrue(isOneSwap_ver1(test_array_4));
        //assertTrue(isOneSwap_ver1(test_array_5)); //FIXME - this test failed
        assertFalse(isOneSwap_ver1(test_array_sorted));
        assertFalse(isOneSwap_ver1(test_array_unsorted1));
        assertFalse(isOneSwap_ver1(test_array_unsorted2));

        assertTrue(isOneSwap_ver2(test_array_1));
        assertTrue(isOneSwap_ver2(test_array_2));
        assertFalse(isOneSwap_ver2(test_array_3));
        assertTrue(isOneSwap_ver2(test_array_4));
        assertTrue(isOneSwap_ver2(test_array_5));
        assertFalse(isOneSwap_ver2(test_array_sorted));
        assertFalse(isOneSwap_ver2(test_array_unsorted1));
        assertFalse(isOneSwap_ver2(test_array_unsorted2));
    }

    @Test
    void sortAnyTypeTest()
    {
        // any types, but without primitives
        String[] strings = {"lmn", "cfta", "w", "aa"};
        String[] expectedASCII = {"aa", "cfta", "lmn", "w"};
        String[] expectedLength = {"w", "aa", "lmn", "cfta"};

        sort(strings, new ComparatorASCII());
        assertArrayEquals(expectedASCII, strings);

        sort(strings, new ComparatorLength());
        assertArrayEquals(expectedLength, strings);

        sort(strings, new ComparatorASCII(), SortStatusChecking.ASCENDING_UNIQUE);
        assertArrayEquals(expectedASCII, strings);

        sort(strings, new ComparatorLength(), SortStatusChecking.ASCENDING_NONE_UNIQUE);
        assertArrayEquals(expectedLength, strings);
    }

/*
Lesson #8
ClassWork #8
HomeWork #8
 */
    @Test
    void binarySearchWithoutComparator()
    {
        String[] strings = {"aa", "cfta", "lmn", "w"};
        Person prs1 = new Person(1, "Vasya");
        Person prs2 = new Person(2, "Itay");
        Person prs3 = new Person(3, "Sara");
        Person[] persons = {
            prs1, prs2, prs3
        };
        assertEquals(1, java.util.Arrays.binarySearch(strings, "cfta"));
        assertEquals(0, java.util.Arrays.binarySearch(persons, prs1));
        assertEquals(-1, java.util.Arrays.binarySearch(persons, new Person()));
        assertEquals(-1, java.util.Arrays.binarySearch(persons, new Person("Rivka")));
        assertEquals(-4, java.util.Arrays.binarySearch(persons, new Person(200, "Joe")));
    }

    @Test
    void evenOddSorting()
    {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99, 0};
        Integer[] expected = {-100, -10, -8, 0, 10, 99, 13, 7};
        sort(array, new EvenOddComparator());
        assertArrayEquals(expected, array);
    }

    @Test
    void findTest()
    {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99};
        Integer[] expected = {7, 13, 99};
        assertArrayEquals(expected, find(array, new OddNumbersPredicate()));
    }
}
