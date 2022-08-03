/**
 * @author Селяков Павел
 *         Created: Mar 5, 2022 2:00:19 AM
 *         State: Mar 5, 2022 2:00:19 AM - initial
 */
package jCodeLibrary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Селяков Павел
 *
 */
public class StringOperations
{
    
    /**
     * Русский язык для конверсий и зависимых от языка операций
     */
    public static final Locale  RuCulture            = new Locale("ru", "RU");
    
    /**
     * Буквы русского алфавита маленькие (Строчные)
     */
    private static final String RussianAlphabet      = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    
    /**
     * Буквы русского алфавита большие (Прописные)
     */
    private static final String RussianAlphabetShift = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    
    /**
     * NT-Возвращает True если проверяемый символ - русская буква
     * 
     * @param p
     *            Проверяемый символ
     * @return Возвращает True если проверяемый символ - русская буква
     */
    public static boolean IsRussianLetter(char p)
    {
        int pi = (int) p;
        return ((RussianAlphabet.indexOf(pi) != -1) || (RussianAlphabetShift.indexOf(pi) != -1));
    }
    // #endregion
    
    /**
     * NT-Get part of source string, between from and to
     * 
     * @param text
     *            Source string.
     * @param from
     *            Non-included start pattern for substring. Example: start.
     * @param to
     *            Non-included end pattern for substring. Example: end.
     * @return Function returns substring without start and end patterns.
     *         Function return null if no pattern founded.
     */
    public static String getStringPart(String text, String from, String to)
    {
        int startLen = from.length();
        int startPos = text.indexOf(from);
        if (startPos == -1)
            return null;// Error: Start pattern not founded in
                        // file.
        int endPos = text.indexOf(to, startPos + startLen);
        if (endPos == -1)
            return null;// Error: End pattern not founded in file.
        // substring
        String result = text.substring(startPos + startLen, endPos);
        
        return result;
    }
    
    /**
     * NT-Check String.IsNullOrEmpty()
     * 
     * @param s
     *            string object
     * @return Returns true if string is null or empty. Returns false otherwise/
     */
    public static boolean StringIsNullOrEmpty(String s)
    {
        if (s == null)
            return true;
        // else
        return s.isEmpty();
    }
    
    /**
     * Compare two strings ignore case
     * 
     * @param s1
     *            String
     * @param s2
     *            String
     * @return Returns True if strings are equal, returns False otherwise.
     */
    public static boolean StringEqualsOrdinalIgnoreCase(String s1, String s2)
    {
        return s1.equalsIgnoreCase(s2);
    }
    
    /**
     * Compare two strings
     * 
     * @param s1
     *            String
     * @param s2
     *            String
     * @return Returns True if strings are equal, returns False otherwise.
     */
    public static boolean StringEquals(String s1, String s2)
    {
        return (s1.compareTo(s2) == 0);
    }
    
    /**
     * NT-Create copy of specified string
     * 
     * @param s
     *            String for copy
     * @return Returns copy of specified string
     */
    public static String StringCopy(String s)
    {
        // TODO: проверить что это работает и копируется а не хз что.
        String result = new String(s);
        return result;
    }
    
    /**
     * NT-Return formatted string for DateTime.Now
     * 
     * @return Return formatted string for DateTime.Now
     */
    
    public static String DateTimeNowToString()
    {
        LocalDateTime dt = LocalDateTime.now();
        
        return DateTimeToString(dt);
    }
    
    /**
     * NT-Return formatted string for specified LocalDateTime object.
     * 
     * @param dt
     *            LocalDateTime object.
     * @return Return formatted string for specified LocalDateTime object.
     */
    public static String DateTimeToString(LocalDateTime dt)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", RuCulture);
        
        return dtf.format(dt);
    }
    
    /**
     * RT-Форматировать дату и время в русской культуре/
     * Пример: воскресенье, 26 апреля 2020г. 01:03:18
     * 
     * @param dt
     *            дата и время
     * @return Функция возвращает строку даты и времени.
     */
    public static String CreateLongDatetimeString(LocalDateTime dt)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("cccc, dd LLLL yyyy'г. 'HH:mm:ss", RuCulture);
        
        return dtf.format(dt);
    }
    
    /**
     * NT-Return part of filename string for specified LocalDateTime object.
     * 
     * @param dt
     *            LocalDateTime object.
     * @return Return part of filename for specified LocalDateTime object.
     */
    public static String DateTimeToFileNameString(LocalDateTime dt)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd_HHmmss", RuCulture);
        
        return dtf.format(dt);
    }
    
    /**
     * NT-Remove empty string items from source array
     * 
     * @param sar
     *            Source array with empty or null items
     * @return Result array without empty or null items
     */
    public static String[] RemoveEmptyItems(String[] sar)
    {
        int count = 0;
        // find size of result array
        for (String s : sar)
            if (!StringIsNullOrEmpty(s))
                count++;
        // create result array
        String[] result = new String[count];
        // fill array
        count = 0;
        for (String s : sar)
            if (!StringIsNullOrEmpty(s))
            {
                result[count] = s;
                count++;
            }
        
        return result;
    }
    
    /**
     * NT-Split string by regex and optional remove empty elements from result
     * array
     * 
     * @param text
     *            Source string
     * @param regex
     *            regex string as described in String.split() function
     *            documentation.
     *            " " -> " ";
     *            "k" "m " -> "[km]" and so on...
     * @param RemoveEmptyItems
     *            if True - remove empty items from result array.
     * @return Returns array of string's
     */
    public static String[] StringSplit(String text, String regex, boolean RemoveEmptyItems)
    {
        String[] sar = text.split(regex);
        
        if (RemoveEmptyItems)
            return RemoveEmptyItems(sar);
        else
            return sar;
    }
    
    /**
     * NT-Faster split string at first match delimiter string
     * 
     * @param text
     *            Source string
     * @param delimiter
     *            Delimiter string as "="
     * @return Returns array of 2 parts: before and after delimiter. Returns
     *         null if delimiter not found.
     */
    public static String[] StringSplitFirstMatch(String text, String delimiter)
    {
        String[] result = null;
        
        int start = text.indexOf(delimiter);
        int delimiterLength = delimiter.length();
        if (start >= 0)
        {
            result = new String[2];
            result[0] = text.substring(0, start);
            result[1] = text.substring(start + delimiterLength);
        }
        // else if(start == 0)
        // {
        // result = new String[2];
        // result[0] = "";
        // result[1] = text.substring(start+delimiterLength);
        // }
        else
            result = null;
        
        return result;
    }
    
    /**
     * NT-Faster split string at last match delimiter string
     * 
     * @param text
     *            Source string
     * @param delimiter
     *            Delimiter string as "="
     * @return Returns array of 2 parts: before and after delimiter. Returns
     *         null if delimiter not found.
     */
    public static String[] StringSplitLastMatch(String text, String delimiter)
    {
        String[] result = null;
        
        int start = text.lastIndexOf(delimiter);
        int delimiterLength = delimiter.length();
        if (start >= 0)
        {
            result = new String[2];
            result[0] = text.substring(0, start);
            result[1] = text.substring(start + delimiterLength);
        }
        else
            result = null;
        
        return result;
    }
    
    /**
     * NT-Return string value or [Null] if string is null.
     * 
     * @param s
     *            String
     * @return Return string value or [Null] if string is null.
     */
    public static String GetStringTextNull(String s)
    {
        if (s == null)
            return "[Null]";
        else
            return s;
    }
    
}
