/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: найти код и написать новый менеджер информации системы.
 */
package jCodeLibrary;

/**
 * Менеджер информации о операционной системе
 * 
 * @author 1
 */
public class SystemInfoManager
{
    /**
     * Get Operating system name
     * 
     * @return Operating system name
     */
    public static String GetOsTitle()
    {
        return System.getProperty("os.name");
    }
    
    /**
     * Get Operating system architecture
     * 
     * @return Operating system architecture
     */
    public static String GetOsArchTitle()
    {
        return System.getProperty("os.arch");
    }
    
    /**
     * Get Operating system version
     * 
     * @return Operating system version
     */
    public static String GetOsVersionString()
    {
        return System.getProperty("os.version");
    }
    
    /**
     * Get Line separator ("\n" on UNIX)
     * 
     * @return Line separator ("\n" on UNIX)
     */
    public static String GetLineSeparator()
    {
        return System.getProperty("line.separator");
    }
    
    /**
     * Get File separator ("/" on UNIX)
     * 
     * @return File separator ("/" on UNIX)
     */
    public static String GetFileSeparator()
    {
        return System.getProperty("file.separator");
    }
    
    /**
     * Get Path separator (":" on UNIX)
     * 
     * @return Path separator (":" on UNIX)
     */
    public static String GetPathSeparator()
    {
        return System.getProperty("path.separator");
    }
    
    /**
     * Get User's account name
     * 
     * @return User's account name
     */
    public static String GetUserTitle()
    {
        return System.getProperty("user.name");
    }
    
    /**
     * Get User's home directory
     * 
     * @return User's home directory
     */
    public static String GetUserHomeFolderPath()
    {
        return System.getProperty("user.home");
    }
    
    /**
     * Get User's current working directory
     * 
     * @return User's current working directory
     */
    public static String GetUserCurrentDirectory()
    {
        return System.getProperty("user.dir");
    }
    
    /**
     * Get Java Runtime Environment version
     * 
     * @return Java Runtime Environment version
     */
    public static String GetJREVersion()
    {
        return System.getProperty("user.dir");
    }
    
    //
    //
    // /// <summary>
    // /// Функция возвращает True если операционная система 64-битная.
    // /// </summary>
    // /// <returns></returns>
    // public static bool is64bitProcess()
    // {
    // return (IntPtr.Size == 8);
    // }
    
}
