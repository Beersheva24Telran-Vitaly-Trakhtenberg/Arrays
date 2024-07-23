/**
 * HW #9
 */
package telran.utils;

import java.util.function.Predicate;

public class CharacterRule
{
    private boolean flag;   // the rule must be or must not be
    private Predicate<Character> predicate;
    private String errorMessage;

    public CharacterRule(boolean flag, Predicate<Character> predicate, String errorMessage)
    {
        this.flag = flag;
        this.predicate = predicate;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return this.errorMessage;
    }

    public Predicate<Character> getPredicate()
    {
        return this.predicate;
    }

    public boolean getFlag()
    {
        return this.flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
}
