/**
 * @author Селяков Павел
 *         Created: Feb 15, 2022 6:21:18 PM
 *         State: Feb 15, 2022 6:21:18 PM - initial
 */
package Testing;

/**
 * @author Селяков Павел
 *
 */
public class SimpleRegexHelper_test
{
    
    /**
     * 
     */
    public SimpleRegexHelper_test()
    {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        // test regex sample
        String regex = "^копировать (?<file>.+) в (?<folder>.+)$";
        String regexRu = "^копировать (?<файл>.+) в (?<папку>.+)$";
        
        // typical commands
        String s1 = "копировать кошку в серую кошку";
        String s2 = "Копировавать точку в двоеточие";
        String s3 = "Копировать кошку в красную Кошку";
        String s6 = "my app.exe -t -dhttps://www.google.com%[56*4765] -c\"udaff.com\"";
        String s61 = "my app.exe -t -dwww.google.com%[56*4765] -c\"udaff.com\"";
        // simple regex
        String s4 = "Копировать %arg1 в %arg2";
        String s41 = "копировать %файл в %папку";
        String s5 = "my app.exe -t -d%arg1%[56*4765] -c\"%arg2\"";
        
        // 3. надо определить, путь исполнения это путь к процедуре или к
        // приложению.
        // - Это функция RegexManager.IsAssemblyCodePath().
        String s7 = "assemblyTitle.classTitle.Function()";// true
        String s8 = "SampleAsm.myClass.Function(arg1, arg2,arg3)";// true
        String s9 = "SampleAsm-myClass.Function()";// false
        String s10 = "SampleAsm.MyClass.Function ( arg1, arg2,arg3,arg4,arg5,arg6)";// false
        String s11 = "SampleAsm.MyClass.Function(arg1, arg2,arg3,arg4,arg5,arg6 )";// true
        
        // boolean result1 = isAssemblyCodePath(s7);//ok
        // boolean result2 = isAssemblyCodePath(s8);//ok
        // boolean result3 = isAssemblyCodePath(s9);//ok
        // boolean result4 = isAssemblyCodePath(s10);//ok
        // boolean result5 = isAssemblyCodePath(s11);//ok
        
        // // 1. Из регекса каждой Процедуры сделать нормальный регекс.
        // // 1. convert simple regex string to normal regex string
        // String r1 = makeNormalRegex2(s4);//ok
        // String r2 = makeNormalRegex2(s5);//ok
        
        // 2. Функция RegexManager.ExtractArgumentsFromCommand() выполняет поиск
        // и возвращает:
        // - null если нет совпадений - процедура не годится для исполнения.
        // - пустую коллекцию аргументов, если регекс Процедуры не содержит
        // аргументов.
        // - не пустую коллекцию аргументов, если регекс содержит аргументы.
        // Далее список аргументов передается в Процедуру, которая решает,
        // подходит ли ей этот набор аргументов.
        // 2. Parse input string by NormalRegex
        // HashMap<String, String> args1 = parseNormalRegex2(s1, regex);//success
        // HashMap<String, String> args1ru = parseNormalRegex2(s1, regexRu);//fail - ru group names fault in regex!
        // HashMap<String, String> args2 = parseNormalRegex2(s2, regex);//null - first word mismatch regex
        // HashMap<String, String> args3 = parseNormalRegex2(s3, regex);//null - case sensitive
        
        // rx = ^my app.exe -t -d(?<arg1>.+?)%[56*4765] -c"(?<arg2>.+?)"$
        // text = my app.exe -t -dhttps://www.google.com%[56*4765] -c"udaff.com"
        //
        // регекс нельзя так просто скопировать - типа простой регекс.
        // символы .$%[](?+*:^\|{} в простом регексе портят его.
        // Надо экранировать все символы разметки регекса в нем.
        // rx = ^my app.exe -t -d(?<arg1>.+?)%[56*4765] -c"(?<arg2>.+?)"$ - неправильный регекс
        // rx = ^my app\.exe -t -d(?<arg1>.+?)\%\[56\*4765\] -c"(?<arg2>.+?)"$ - правильный регекс
        
        // sample processing
        // String NormalRegEx = makeNormalRegex2(s5);
        // //Должен получиться регекс: ^my app\.exe -t -d(?<arg1>.+?)\%\[56\*4765\] -c"(?<arg2>.+?)"$
        // HashMap<String,String> arguments = parseNormalRegex2(s6, NormalRegEx);//matched
        //
        // String NormalRegEx2 = makeNormalRegex2(s4);
        // HashMap<String,String> arguments2 = parseNormalRegex2(s3, NormalRegEx2);//matched
        
        // String NormalRegEx3 = makeNormalRegex2(s41);
        // HashMap<String,String> arguments3 = parseNormalRegex2(s1, NormalRegEx3);//matched
        
        // String cmdLine = ConvertApplicationCommandString(s41, new String[] { "аргумент1", "аргумент2" });
        
    }
    
}
