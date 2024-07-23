package telran.utils;

public class CharacterUtils
/**
 * Не все стандартные проверки представлены в методах класса символов .is*.
 * This class creates custom rules
  */
{
    public static boolean isPunctuation(char ch) {
        int type = Character.getType(ch);
        return type == Character.CONNECTOR_PUNCTUATION ||
                type == Character.DASH_PUNCTUATION ||
                type == Character.END_PUNCTUATION ||
                type == Character.FINAL_QUOTE_PUNCTUATION ||
                type == Character.INITIAL_QUOTE_PUNCTUATION ||
                type == Character.OTHER_PUNCTUATION ||
                type == Character.START_PUNCTUATION;
    }
}
