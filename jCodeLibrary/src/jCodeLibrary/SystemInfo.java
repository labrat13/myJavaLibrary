/**
 * @author jsmith
 *         Created: Feb 12, 2022 6:55:33 PM
 *         State: Feb 12, 2022 6:55:33 PM - initial
 */
package jCodeLibrary;

/**
 * Информация о текущей операционной системе
 * 
 * @author jsmith
 *
 */
public class SystemInfo
{
    
    /**
     * OS title from System.getProperty("os.name")
     */
    private static String OS   = System.getProperty("os.name").toLowerCase();
    
    /**
     * OS arch from System.getProperty("os.arch")
     */
    private static String ARCH = System.getProperty("os.arch").toLowerCase();
    
    /**
     * Проверить, что ОС это Windows
     * 
     * @return Возвращает True если ОС это Windows любой версии.
     */
    public static boolean isWindows()
    {
        return OS.contains("win");
    }
    
    /**
     * Проверить, что ОС это Apple Makintosh
     * 
     * @return Возвращает True если ОС это Apple Makintosh любой версии.
     */
    public static boolean isMac()
    {
        return OS.contains("mac");
    }
    
    /**
     * Проверить, что ОС это Unix/Linux
     * 
     * @return Возвращает True если ОС это Unix/Linux любой версии.
     */
    public static boolean isUnix()
    {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }
    
    /**
     * Проверить, что ОС это Solaris
     * 
     * @return Возвращает True если ОС это Solaris любой версии.
     */
    public static boolean isSolaris()
    {
        return OS.contains("sunos");
    }
    
    /**
     * Получить 3-буквенную строку укороченного названия ОС.
     * 
     * @return Возвращает 3-буквенную строку укороченного названия ОС: "win",
     *         "osx", "uni", "sol". Возвращает "err" для любой другой ОС.
     */
    public static String getOS()
    {
        if (isWindows())
        {
            return "win";
        }
        else if (isMac())
        {
            return "osx";
        }
        else if (isUnix())
        {
            return "uni";
        }
        else if (isSolaris())
        {
            return "sol";
        }
        else
        {
            return "err";
        }
    }
    
    /**
     * Получить значение System.getProperty("os.arch").toLowerCase()
     * 
     * @return Функция возвращает значение из
     *         System.getProperty("os.arch").toLowerCase()
     */
    public static String getArch()
    {
        return ARCH;
    }
    
}
