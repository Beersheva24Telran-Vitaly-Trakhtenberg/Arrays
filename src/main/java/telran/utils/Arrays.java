package telran.utils;

import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays
{
    // JAVA Array is a reference to the first element of the array

    public enum SortStatusChecking {
        ASCENDING_UNIQUE,
        DESCENDING_UNIQUE,
        ASCENDING_NONE_UNIQUE,
        DESCENDING_NONE_UNIQUE,
    }

    /**
     * Method searches the index of the first entry of given value to existed array
     *
     * @param source_array existed array
     * @param value searched value
     * @return reference to a new array containing @source_array with added @new_item
     */
    public static int searchFirstEntry(int[] source_array, int value)
    {
        int index = 0;
        while(index < source_array.length && value != source_array[index])
        {
            index++;
        }
        return index == source_array.length ? -1 : index;
    }

    /**
     * Method adds int Item to the end of existed array.
     *
     * @param source_array existed array
     * @param new_item value to add
     * @return reference to a new array containing @source_array with added @new_item
     */
    public static int[] addItem(int[] source_array, int new_item) {
        int[] res = java.util.Arrays.copyOf(source_array, source_array.length + 1);
        res[source_array.length] = new_item;

        return res;
    }

    /**
     * Method inserts int Item into the given position of existed array.
     *
     * @param source_array  existed array
     * @param new_item value to insert
     * @param key_new_item index (placement) where to insert
     * @return reference to a new array
     */
    public static int[] insertItem(int[] source_array, int new_item, int key_new_item)
    {
        int[] res = new int[source_array.length + 1];

        System.arraycopy(source_array, 0, res, 0, key_new_item);
        res[key_new_item] = new_item;
        System.arraycopy(source_array, key_new_item, res, key_new_item + 1, source_array.length - key_new_item);

        return res;
    }

    /**
     * Method removes item of existed array at the given position
     *
     * @param source_array existed array
     * @param key_removed_item index of removed item
     * @return reference to a new array
     */
    public static int[] removeItem(int[] source_array, int key_removed_item)
    {
        int[] res = new int[source_array.length-1];

        System.arraycopy(source_array, 0, res, 0, key_removed_item);
        System.arraycopy(source_array, key_removed_item + 1, res, key_removed_item, source_array.length - key_removed_item - 1);

        return res;
    }

    public static void pushMaxToEnd(int[] source_array)
    {
        for (int i = 1; i < source_array.length; i++) {
            if (source_array[i] < source_array[i - 1]) {
                swap(source_array, i, i - 1);
            }
        }
    }

    public static void sortBubbleMinToMax(int[] source_array)
    {
        for (int j = 1; j < source_array.length; j++) {
            pushMaxToEnd(source_array);
        }
    }

    /**
     *
     * @param source_sorted_array - sorted array when search will be appeared
     * @param searched_value - value, which index will be searched
     * @return - index of the searched value is success; -1 if any fails (not found, array wasn't sorted etc.);
     */
    public static int binarySearch(int[] source_sorted_array, int searched_value)
    {
        int res = -1;

        int left = 0;
        int right = source_sorted_array.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (source_sorted_array[middle] == searched_value) {
                res = middle;
                break;
            }

            if (source_sorted_array[middle] < searched_value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        if (res == -1) {
            res = -1 * (left < source_sorted_array.length && source_sorted_array[left] < searched_value ? left + 1 : left) - 1;
        }

        return res;
    }

    /**
     *
     * @param source_sorted_array
     * @param new_value
     * @return index of position @new_value to keep array sorted
     */
    public static int[] insertSorted(int[] source_sorted_array, int new_value)
    {
        int new_value_index = indexInsertToSorted(source_sorted_array, new_value);

        return insertItem(source_sorted_array, new_value, new_value_index);
    }

    /**
     *
     * @param source_array
     * @return true if a given array has exactly one swap to get sort
     */
    public static boolean isOneSwap_ver1(int[] source_array)
    {
        boolean res = !isArraySorted(source_array, SortStatusChecking.ASCENDING_UNIQUE) &&
                !isArraySorted(source_array, SortStatusChecking.DESCENDING_UNIQUE);

        int position1 = -1;
        int position2 = -1;
        int i;
        while (res) {
            for (i = 0; i < source_array.length - 1; i++) {
                if (source_array[i]>source_array[i+1]) {
                    if (position1 == -1) {
                        position1 = i;
                        position2 = i + 1;
                    } else if (source_array[i + 1] < source_array[position1 + 1]) {
                        position2 = i + 1;
                    } else {
                        res = false;
                        break;
                    }
                }
            }

            if (res && i == source_array.length - 1) {
                if (position1 != -1 && position2 != -1 && source_array[position2] < source_array[position1]) {
                    if (position1 == 0) {
                        if (position2 == source_array.length - 1) {
                            res = source_array[position1] > source_array[position2 - 1];
                        } else {
                            res = source_array[position1] > source_array[position2 - 1] &&
                                    source_array[position1] < source_array[position2 + 1];
                        }
                    } else {
                        if (position2 == source_array.length - 1) {
                            res = source_array[position1] > source_array[position2 - 1] &&
                                    source_array[position2] < source_array[position1 + 1];
                        } else {
                            res = source_array[position1] < source_array[position2 + 1] &&
                                    source_array[position2] > source_array[position1 - 1] &&
                                    source_array[position2] <= source_array[position1];
                        }
                    }
                }
                break;
            }
        }

        return res;
    }

    public static boolean isOneSwap_ver2(int[] source_array) {
        int n = source_array.length;
        int first = -1, second = -1;
        boolean res = true;

        for (int i = 0; i < n - 1; i++) {
            if (source_array[i] > source_array[i + 1]) {
                first = i;
                break;
            }
        }

        if (first == -1) {
            res =  false;
        }

        for (int i = n - 1; i > 0 && res; i--) {
            if (source_array[i] < source_array[i - 1]) {
                second = i;
                break;
            }
        }

        if (first != -1 && second != -1 && res) {
            swap(source_array, first, second);
            boolean isSorted = isArraySorted(source_array, SortStatusChecking.ASCENDING_NONE_UNIQUE);
            swap(source_array, first, second);
            res = isSorted;
        }

        return res;
    }

    /**
     * Method checks if given array sorted.
     * Checking could be performed for some variants of sort:
     * - Ascending unique: 1, 2, 3, 4, 5
     * - Ascending non-unique: 1, 2, 2, 4, 5
     * - Descending unique: 5, 4, 3, 2, 1
     * - Descending non-unique: 5, 4, 4, 2, 1
     *
     * @param source_array
     * @param order
     * @return
     */
    public static boolean isArraySorted(int[] source_array, SortStatusChecking order)
    {
        boolean res = true;

        for (int i = 1; res && i < source_array.length; i++) {
            switch (order) {
                case SortStatusChecking.ASCENDING_UNIQUE -> {
                    if (source_array[i-1] >= source_array[i]) {
                        res = false;
                    }
                }
                case SortStatusChecking.DESCENDING_UNIQUE -> {
                    if (source_array[i-1] <= source_array[i]) {
                        res = false;
                    }
                }
                case SortStatusChecking.ASCENDING_NONE_UNIQUE -> {
                    if (source_array[i-1] > source_array[i]) {
                        res = false;
                    }
                }
                case SortStatusChecking.DESCENDING_NONE_UNIQUE -> {
                    if (source_array[i-1] < source_array[i]) {
                        res = false;
                    }
                }
            }
        }
        return res;
    }

    /**
     *
     * @param source_array
     * @param index1
     * @param index2
     */
    private static void swap(int[] source_array, int index1, int index2)
    {
        int tmp = source_array[index1];
        source_array[index1] = source_array[index2];
        source_array[index2] = tmp;
    }

    /**
     *
     * @param source_sorted_array
     * @param new_value
     * @return
     */
    private static int indexInsertToSorted(int[] source_sorted_array, int new_value)
    {
        int new_value_index = -1;

        if (isArraySorted(source_sorted_array, SortStatusChecking.ASCENDING_UNIQUE)) {
            if (new_value < source_sorted_array[0]) {
                new_value_index = 0;
            }
            if (new_value > source_sorted_array[source_sorted_array.length - 1]) {
                new_value_index = source_sorted_array.length;
            }
            int left = 0;
            int right = source_sorted_array.length - 1;

            while (left <= right && new_value_index == -1) {
                int middle = left + (right - left) / 2;
                if (source_sorted_array[middle-1] <= new_value && source_sorted_array[middle] >= new_value ) {
                    new_value_index = middle;
                    break;
                }

                if (source_sorted_array[middle] < new_value) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return new_value_index;
    }

    public static <T> void sort(T[] source_array, Comparator<T> comparator)
    {
        int length = source_array.length;
        boolean flag_sorted;
        do {
            length--;
            flag_sorted = true;
            for (int i = 0; i < length; i++) {
                if (comparator.compare(source_array[i], source_array[i+1]) > 0) {
                    swapAnyTypes(source_array, i, i + 1);
                    flag_sorted = false;
                }
            }
        } while (!flag_sorted);
    }

    public static <T> void sort(T[] source_array, Comparator<T> comparator, SortStatusChecking sorting_direction)
    {
        int length = source_array.length;
        boolean flag_sorted;
        do {
            length--;
            flag_sorted = true;
            for (int i = 0; i < length; i++) {
                boolean condition = (sorting_direction == SortStatusChecking.ASCENDING_UNIQUE || sorting_direction == SortStatusChecking.ASCENDING_NONE_UNIQUE)
                        ? comparator.compare(source_array[i], source_array[i + 1]) > 0
                        : comparator.compare(source_array[i], source_array[i + 1]) < 0;
                if (condition) {
                    swapAnyTypes(source_array, i, i + 1);
                    flag_sorted = false;
                }
            }
        } while (!flag_sorted);
    }

    private static <T> void swapAnyTypes(T[] array, int i, int j)
    {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static <T> int binarySearch(T[] source_sorted_array,T searched_value, Comparator<T> comparator)
    {
        int res = -1;

        int left = 0;
        int right = source_sorted_array.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (source_sorted_array[middle] == searched_value) {
                res = middle;
                break;
            }

            if (comparator.compare(source_sorted_array[middle], searched_value) < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        if (res == -1) {
            res = -1 * (left < source_sorted_array.length && (comparator.compare(source_sorted_array[left], searched_value) < 0) ? left + 1 : left) - 1;
        }

        return res;
    }

    /*
    * binarySearchWithoutComparator_ver1
    * Uses standard method java.util.Arrays.binarySearch
    */
    public static <T extends Comparable<? super T>> int binarySearchWithoutComparator_ver1(T[] source_sorted_array, T searched_value)
    {
        return java.util.Arrays.binarySearch(source_sorted_array, searched_value);
    }

    /*
     * binarySearchWithoutComparator_ver2
     * Uses recursive call of own private method
     */
    public static <T extends Comparable<T>> int binarySearchWithoutComparator_ver2(T[] array, T key)
    {
        return binarySearchRecursive_ver2(array, key, 0, array.length - 1);
    }

    /*
    * Private additional method (helper for binarySearchWithoutComparator_ver2 uses ternary operands
     */
    private static <T extends Comparable<T>> int binarySearchRecursive_ver2(T[] array, T key, int left, int right)
    {
        return left > right ? -left - 1 : array[(left + right) / 2].compareTo(key) == 0 ? (left + right) / 2 : array[(left + right) / 2].compareTo(key) > 0 ? binarySearchRecursive_ver2(array, key, left, (left + right) / 2 - 1) : binarySearchRecursive_ver2(array, key, (left + right) / 2 + 1, right);
    }
    /* ***************** */

    public static <T> T[] insert(T[] array, int index, T item)
    {
        T[] result = java.util.Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, result, index + 1, array.length - index );
        result[index] = item;
        return result;
    }

    public static <T> T[] find(T[] array, Predicate<T> predicate)
    {
        T[] result = java.util.Arrays.copyOf(array, 0);
        for (int i=0; i < array.length; i++) {
            if (predicate.test(array[i])) {
                result = insert(result, result.length, array[i]);
            }
        }
        return result;
    }

    public static <T> T[] removeIf(T[] array, Predicate<T> predicate)
    {
        return array; // TODO
    }
}
