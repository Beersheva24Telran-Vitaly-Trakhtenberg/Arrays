package telran.utils;

import java.util.MissingFormatArgumentException;

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
    public static int binarySearch_ver1(int[] source_sorted_array, int searched_value)
    {
        int res = -1;
        if (isArraySorted(source_sorted_array, SortStatusChecking.ASCENDING_UNIQUE) && searched_value >= source_sorted_array[0] && searched_value <= source_sorted_array[source_sorted_array.length - 1]) {
            int middle = source_sorted_array.length / 2;
            int left = 0;
            if (searched_value == source_sorted_array[0]) {
                res = 0;
            } else if (searched_value == source_sorted_array[source_sorted_array.length-1]) {
                res = source_sorted_array.length-1;
            }
            while (res == -1 && middle != 0) {
                if (searched_value != source_sorted_array[left + middle]) {
                    if (searched_value < source_sorted_array[left + middle]) {
                        middle = middle / 2;
                    } else {
                        left = left + middle;
                        middle = (source_sorted_array.length - 1 - left) / 2;
                    }
                } else {
                    res = left + middle;
                }
            }
        } else {
            throw new MissingFormatArgumentException("Given array wasn't sorted");
        }

        if (res == -1) {
            res = -1 * indexInsertToSorted(source_sorted_array, searched_value) - 1;    // (-(insertion point) - 1)
        }

        return res;
    }
    /**
     *
     * @param source_sorted_array - sorted array when search will be appeared
     * @param searched_value - value, which index will be searched
     * @return - index of the searched value is success; -1 if any fails (not found, array wasn't sorted etc.);
     */
    public static int binarySearch_ver2(int[] source_sorted_array, int searched_value)
    {
        int res = -1;

        if (isArraySorted(source_sorted_array, SortStatusChecking.ASCENDING_UNIQUE)) {
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
        } else {
            throw new MissingFormatArgumentException("Given array wasn't sorted");
        }

        if (res == -1) {
            res = -1 * indexInsertToSorted(source_sorted_array, searched_value) - 1;    // (-(insertion point) - 1)
        }

        return res;
    }

    public static int[] insertSorted(int[] source_sorted_array, int new_value)
    {
        int new_value_index = indexInsertToSorted(source_sorted_array, new_value);

        int[] res = insertItem(source_sorted_array, new_value, new_value_index);

        return res;
    }

    public static boolean isOneSwap(int[] source_array)
    {
        boolean res = true;

        if (isArraySorted(source_array, SortStatusChecking.ASCENDING_UNIQUE) || isArraySorted(source_array, SortStatusChecking.DESCENDING_UNIQUE)) {
            res = false;
        }

        int position1 = -1;
        int position2 = -1;
        int i = 0;
        while (res) {
            for (i = 0; i < source_array.length - 1; i++) {
                if (source_array[i]>source_array[i+1]) {
                    if (position1 == -1 && position2 == -1) {
                        position1 = i;
                        position2 = i+1;
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

    private static void swap(int[] source_array, int index1, int index2)
    {
        int tmp = source_array[index1];
        source_array[index1] = source_array[index2];
        source_array[index2] = tmp;
    }

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
}
