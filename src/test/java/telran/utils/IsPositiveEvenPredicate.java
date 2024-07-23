package telran.utils;

import java.util.function.Predicate;

public class IsPositiveEvenPredicate implements Predicate<Integer>
{
    @Override
    public boolean test(Integer arg0) {
        boolean res = arg0 % 2 == 0 || arg0 < 0;
        return arg0 % 2 == 0 || arg0 < 0;
    }
}
