/**
 * @author Селяков Павел
 *         Created: Feb 15, 2022 4:30:02 PM
 *         State: Feb 15, 2022 4:30:02 PM - v1.0
 */
package jCodeLibrary;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NR - Regex менеджер для упрощения функций регекса в моих проектах
 * 
 * @author jsmith
 *
 */
public class RegexHelperBase
{
    
    // Замечания:
    // 1.Если указан флаг Pattern.UNICODE_CHARACTER_CLASS, то \w обозначает
    // символы Unicode, в том числе, русские.
    // Если не указан, то \w обозначает только [a-zA-Z_0-9] символы.
    // 2. Русские символы в названиях групп приводят к выдаче исключения в
    // процессе разбора.
    // Имена групп должны соответствовать спецификации Java regex:
    // [a-zA-Z][a-zA-Z_0-9]
    
    /**
     * Символы, которые необходимо экранировать при переработке простого регекса
     * в нормальный регекс
     */
    protected final static String unsafeRegexChars  = ".$%[](?+*:^\\|{}";
    
    /**
     * Pattern string for getGroupNames() function
     */
    protected final static String GGNPatternString  = "\\(\\?<([\\w]+)>";
    
    /**
     * Pattern constant regex object for getGroupNames() function - optimization
     */
    protected static Pattern      GGNPattern        = Pattern
            .compile(GGNPatternString, Pattern.UNICODE_CHARACTER_CLASS);
    
    /**
     * Pattern string for makeNewArgName() function
     */
    protected final static String MNANPatternString = "[a-zA-Z][a-zA-Z_0-9]*";
    
    /**
     * Pattern constant regex object for makeNewArgName() function - optimization
     */
    protected static Pattern      MNANPattern       = Pattern.compile(MNANPatternString);
    
    /**
     * 
     */
    public RegexHelperBase()
    {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * NT-Извлечь из нормального регекса все имена групп
     * 
     * @param regex
     *            Строка регекса.
     * @return Функция возвращает названия групп в порядке их появления в строке
     *         регекса.
     */
    public static LinkedList<String> GetGroupNames(String regex)
    {
        LinkedList<String> result = new LinkedList<String>();
        // в названии групп в регексе можно использовать только латинские
        // символы.
        // Русские символы приводят к выбросу исключения далее в разборе
        // выражения.
        // Поэтому надо на этапе преобразования регекса из простого в нормальный
        // заменять русские имена на arg0 итп, строго по порядку появления в
        // регексе.
        // Значит, нужна функция, позволяющая определить, что название аргумента
        // - группы содержит не-латинские символы, или начинается не с буквы.
        Matcher m = GGNPattern.matcher(regex);
        
        while (m.find())
        {
            result.add(m.group(1));
        }
        
        return result;
    }
    
    /**
     * NT-Получить словарь названиеГруппы - значениеГруппы для одной строки
     * текста.
     * 
     * @param text
     *            Одна строка текста.
     * @param regex
     *            Строка однострочного нормального регекса, в которой группы не повторяются.
     * @return Функция возвращает:
     *         - null если нет совпадений регекса.
     *         - пустой словарь, если регекс не содержит групп.
     *         - не пустой словарь, если регекс содержит группы.
     */
    public static HashMap<String, String> MakeGroupDictionary(String text, String regex)
    {
        // Никак иначе получить имена групп в Java regex нельзя, кроме способа
        // Reflection
        LinkedList<String> groupNames = GetGroupNames(regex);
        // регекс не работает с группами, названия которых на русском языке -
        // выдает исключение
        Pattern p = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m = p.matcher(text);
        
        // Проверить что совпадение регекса и текста есть
        if (m.matches() == false)
            return null;
        // Извлечь имена и значения групп тут
        // int c1 = m.groupCount();//Счетчик групп = 2 (на самом деле, групп 3,
        // но в регексе 2 группы)
        // //if(c1 == 1) return new String[0]; //return empty array
        //// String c2 = m.group(0);//full string
        //// String c3 = m.group(1);//first group text
        //// String c4 = m.group(2);//second group text
        
        HashMap<String, String> result = new HashMap<String, String>();
        for (String key : groupNames)
        {
            String value = m.group(key);
            result.put(key, value);
        }
        
        return result;
    }
    
    /**
     * NT-Получить массив совпадений для объекта регекса и входного текста.
     * 
     * @param p
     *            Инициализированный объект регекса: Pattern p = Pattern.compile(pattern);
     * @param query
     *            Текст для поиска совпадений.
     * @return Функция возвращает массив совпадений для объекта регекса и входной строки.
     */
    public MatchResult[] GetMatches(Pattern p, String query)
    {
        LinkedList<MatchResult> matches = new LinkedList<MatchResult>();
        Matcher m = p.matcher(query);
        
        while (m.find())
        {
            // //get group text
            // String argname = m.group();
            // int start = m.start();
            // int end = m.end();
            matches.add(m.toMatchResult());
        }
        
        MatchResult[] result = matches.toArray(new MatchResult[matches.size()]);
        matches.clear();// clear temp list
        
        return result;
    }
    
    /**
     * NT-Экранировать символы простого текста, используемые в нормальном регексе, чтобы они не нарушали разбор.
     * 
     * @param part
     *            входной текст
     * @return Функция возвращает строку с экранированными служебными символами регекса
     */
    public static String MakeSafeRegexChars(String part)
    {
        // Список символов, которые надо экранировать - перенесен в статические члены класса.
        
        StringBuilder sb = new StringBuilder();
        for (char ch : part.toCharArray())
        {
            // если символ из входной строки есть в списке символов, которые нужно экранировать, то
            if (unsafeRegexChars.indexOf((int) ch) != -1)
                sb.append("\\");// добавить экранирующий слеш
            // Добавить сам символ
            sb.append(ch);
        }
        
        return sb.toString();
    }
    
    /**
     * NT-Проверить, является ли название аргумента допустимым в качестве названия группы в регексе.
     * 
     * @param title
     *            Проверяемое название для группы
     * @return Функция возвращает True, если название подходит для использования в качестве названия именованной группы в регексе.
     *         Функция возвращает False в противном случае.
     */
    public static boolean IsValidNamedGroupTitle(String title)
    {
        Matcher m = MNANPattern.matcher(title);
        // все ли название состоит из допустимых символов?
        boolean valid = m.find();
        
        return valid;
    }
    
    /**
     * NT-Проверить, есть ли хотя бы одно совпадение регекса в проверяемом тексте.
     * 
     * @param p
     *            Инициализированный объект регекса: Pattern p = Pattern.compile(pattern);
     * @param text
     *            Проверяемый текст.
     * @return Функция возвращает True, если встречено первое совпадение, False в противном случае.
     */
    public static boolean IsMatch(Pattern p, String text)
    {
        Matcher m = p.matcher(text);
        boolean valid = m.find();
        
        return valid;
    }
    
}
