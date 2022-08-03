/**
 * @author Селяков Павел
 *         Created: Feb 15, 2022 5:19:22 PM
 *         State: Feb 15, 2022 5:19:22 PM - v1.0
 */
package jCodeLibrary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NR-Класс для работы с Простым регексом, удобным для пользователя.
 * В простом регексе аргументы помечаются знаком %.
 * Например,"Копировать %arg1 в %arg2"
 * Для исполнения Простой регекс нужно преобразовать в нормальный регекс.
 * 
 * @author Селяков Павел
 */
public class SimpleRegexHelper extends RegexHelperBase
{
    
    /**
     * Pattern string for makeNormalRegex() function
     */
    protected final static String MNRPatternString = "%\\w+";
    
    /**
     * Pattern constant regex object for makeNormalRegex() function - optimization
     */
    protected static Pattern      MNRPattern       = Pattern.compile(MNRPatternString, Pattern.UNICODE_CHARACTER_CLASS);
    
    /**
     * NT-Создать правильное название аргумента для нормального регекса
     * 
     * @param argname
     *            Название аргумента
     * @param argCounter
     *            Порядковый номер аргумента в выражении.
     * @return Возвращает правильное название аргумента arg_0 для использования в качестве названия группы в регексе.
     */
    public static String MakeNewArgName(String argname, int argCounter)
    {
        String newName = argname.trim().substring(1);
        // все ли название состоит из допустимых символов?
        boolean valid = RegexHelperBase.IsValidNamedGroupTitle(newName);
        if (valid == true)
            return newName;
        else
            return "arg_" + Integer.toString(argCounter);
    }
    
    /**
     * NFT-Преобразовать простой регекс в нормальный регекс.
     * Аргументы с русскими названиями будут переименованы в arg_0.
     * 
     * @param simpleRegex
     *            Строка простого регекса.
     * @return Функция возвращает строку нормального регекса с имегованными группами вместо аргументов.
     */
    public static String MakeNormalRegex(String simpleRegex)
    {
        // тут надо распарсить строку запроса, выделив аргументы.
        // затем заменить аргументы на строки, с учетом количества аргументов.
        // Затем добавить символы ^ и $ для нормального регекса
        
        // Пример простого регекса:
        // Копировать %файл в %папка
        String query = simpleRegex.trim();
        
        Matcher m = MNRPattern.matcher(query);
        StringBuilder sb = new StringBuilder();
        int lastEnd = 0;
        // iterate matches
        int argCounter = 0;
        while (m.find())
        {
            // find match
            String argname = m.group();
            int start = m.start();
            int end = m.end();// next after end
            // add part of query before match
            String part = query.substring(lastEnd, start);
            String partS = RegexHelperBase.MakeSafeRegexChars(part);
            sb.append(partS);
            // добавить замену простого аргумента и удалить первый символ "%" из имени аргумента
            // если имя аргумента содержит символы кроме [a-zA-Z_0-9], то заменить все имя на arg_#, где # - порядковый номер аргумента в выражении.
            // потому что русскоязычные группы не работают в java regex.
            sb.append("(?<");
            String newArgName = MakeNewArgName(argname, argCounter);
            sb.append(newArgName);
            sb.append(">.+)");
            // set new lastend
            lastEnd = end;
            // increment loop counter
            argCounter++;
        }
        // add last part after last match
        // if no matches found, then lastEnd = 0, all query be copied to result.
        sb.append(RegexHelperBase.MakeSafeRegexChars(query.substring(lastEnd)));
        
        return "^" + sb.toString() + "$";
    }
    
    /**
     * NT-Заменить аргументы простого регекса %arg в исходной строке их значениями
     * 
     * @param line
     *            Строка с аргументами в стиле Простой регекс.
     * @param args
     *            Список значений для аргументов, строго по порядку аргументов.
     * @return Функция возвращает строку с значениями вместо аргументов.
     */
    public static String ConvertSimpleRegexString(String line, String[] args)
    {
        // распарсить строку вида: my app.exe -t -d%arg1%[56*4765] -c"%arg2"
        // аргумент начинается с % и содержит буквы или цифры, но не знаки или пробелы
        // тут надо заменить аргументы в строке Простого регекса их значениями
        
        // 1. найти аргументы в исходной строке
        // 2. заменить аргументы на значения
        // 3. вернуть получившуюся строку
        
        String query = line.trim();
        
        Matcher m = MNRPattern.matcher(query);
        StringBuilder sb = new StringBuilder();
        int lastEnd = 0;
        int argCounter = 0;
        int start, end;
        String part;
        // iterate matches
        while (m.find())
        {
            // find match
            // String argname = m.group(); - not used
            start = m.start();
            end = m.end();// next after end
            // add part of query before match
            part = query.substring(lastEnd, start);
            sb.append(part);
            // добавить значение простого аргумента по его индексу
            sb.append(args[argCounter]);
            // set new lastend
            lastEnd = end;
            // increment loop counter
            argCounter++;
        }
        // add last part after last match
        // if no matches found, then lastEnd = 0, all query be copied to result.
        sb.append(query.substring(lastEnd));
        
        return sb.toString();
    }
    
}
