/**
 * @author jsmith
 *         Created: Feb 12, 2022 7:10:48 PM
 *         State: Feb 12, 2022 7:10:48 PM - initial
 */
package jCodeLibrary;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NT- Try load external library to current java code
 * 
 * @author jsmith
 *
 */
public class LoadLibrary
{
    
    // вызов функции из подгруженной Си-библиотеки:
    // public native char readKey();
    
    /**
     * NT-Create dynamic library file extension as .dll for Windows, or .so for
     * Linux.
     * 
     * @return Returns dynamic library file extension.
     */
    public static String makeLibFileExt()
    {
        return (SystemInfo.isWindows() ? ".dll" : ".so");
    }
    
    /**
     * NT-Create library filename as libname-arch.ext for Windows or Linux.
     * Example: "sysinfo-x86.so"
     * platforms
     * 
     * @param libname
     *            plain library title without arch and file extension. Example: "sysinfo"
     * @return Returns library filename as libname-arch.ext for Windows or Linux.
     */
    public static String makeLibFileName(String libname)
    {
        return libname + "-" + SystemInfo.getArch() + makeLibFileExt();
    }
    
    /**
     * NT-Load external library for using within java code
     * 
     * @param tmpAppFolder
     *            - подкаталог в System.getProperty("java.io.tmpdir") для
     *            размещения загружаемой библиотеки.
     * @param library
     *            - название библиотеки, созданное функцией
     *            LoadLibrary.makeLibFileName().
     * @return Return True if loading is success, returns False if loading
     *         failed.
     */
    public static boolean loadLibrary(String tmpAppFolder, String library)
    {
        boolean result = true;
        try
        {
            String tempDirPath = System.getProperty("java.io.tmpdir") + "/" + tmpAppFolder + "/";
            
            // create folder for library file
            File tmpDir = new File(tempDirPath);
            if (!tmpDir.exists())
                tmpDir.mkdirs();
            
            // extract library file from jar to specified tempDrPath
            String libPath = tempDirPath + library;
            if (!isFileExist(libPath))
                ExtractorJar.extractFromJar(library, tempDirPath);
            // load library
            System.load(libPath);
            
        }
        catch (Exception ex)
        {
            Logger.getLogger(LoadLibrary.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        
        return result;
    }
    
    /**
     * NT-Check File exists
     * 
     * @param path
     *            File pathname
     * @return Returns True if file exists, False otherwise.
     */
    public static boolean isFileExist(String path)
    {
        File f = new File(path);
        
        return f.exists();
    }
    
}
