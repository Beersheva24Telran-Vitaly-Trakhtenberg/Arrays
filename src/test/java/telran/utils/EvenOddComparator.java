package telran.utils;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer>
{

    @Override
    public int compare(Integer o1, Integer o2)
    {
        int res = 0;

        boolean isO1Even = o1 % 2 == 0;
        boolean isO2Even = o2 % 2 == 0;

        if (isO1Even && isO2Even) {
            res = Integer.compare(o1, o2);
        } else if ((!isO1Even && !isO2Even)) {
            res = Integer.compare(o2, o1);
        } else {
            res = isO1Even ? -1 : 1;
        }

        return res;
    }
}
