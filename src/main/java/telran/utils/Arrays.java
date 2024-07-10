package telran.utils;

import java.io.OutputStream;

public class Arrays
{
    // JAVA Array is a reference to the first element of the array
    /**
     * Method searches the index of the first entry of given value to existed array
     *
     * @param source_array
     * @param value
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
     * @param source_array
     * @param new_item
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
     * @param source_array
     * @param new_item
     * @return reference to a new array
     */
    public static int[] insertItem(int[] source_array, int new_item, int key_new_item)
    {
        int[] res = new int[source_array.length + 1];
        if (key_new_item >= source_array.length) {
            System.arraycopy(source_array, 0, res, 0, source_array.length);
            res[source_array.length] = new_item;
        } else {
            System.arraycopy(source_array, 0, res, 0, key_new_item);
            res[key_new_item] = new_item;
            System.arraycopy(source_array, key_new_item, res, key_new_item + 1, source_array.length - key_new_item);
        }

        return res;
    }

    /**
     * Method removes item of existed array at the given position
     *
     * @param source_array
     * @param key_removed_item
     * @return reference to a new array
     */
    public static int[] removeItem(int[] source_array, int key_removed_item)
    {
        int[] res = new int[source_array.length-1];
        if (key_removed_item >= source_array.length) {
            System.arraycopy(source_array, 0, res, 0, source_array.length);
        } else {
            System.arraycopy(source_array, 0, res, 0, key_removed_item);
            System.arraycopy(source_array, key_removed_item + 1, res, key_removed_item, source_array.length - key_removed_item - 1);
        }

        return res;
    }
}
