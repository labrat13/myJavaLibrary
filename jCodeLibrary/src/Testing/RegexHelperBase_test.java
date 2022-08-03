/**
 * @author Селяков Павел
 *         Created: Feb 15, 2022 6:22:14 PM
 *         State: Feb 15, 2022 6:22:14 PM - initial
 */
package Testing;

/**
 * @author Селяков Павел
 *
 */
public class RegexHelperBase_test
{
    
    /**
     * 
     */
    public RegexHelperBase_test()
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
        
    }
    
}
