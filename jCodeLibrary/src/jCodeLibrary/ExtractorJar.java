/**
 * @author jsmith
 *         Created: Feb 12, 2022 6:46:58 PM
 *         State: Feb 12, 2022 6:46:58 PM - initial
 */
package jCodeLibrary;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 *
 */
public class ExtractorJar
{
    
    public static String getJarFilePath(Class c)
    {
        try
        {
            return new File(c.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(ExtractorJar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void extractFromJar(String location, String destDir) throws IOException
    {
        try (JarFile jar = new JarFile(getJarFilePath(ExtractorJar.class)))
        {
            Enumeration<JarEntry> enumEntries = jar.entries();
            while (enumEntries.hasMoreElements())
            {
                JarEntry file = (JarEntry) enumEntries.nextElement();
                // System.out.println(file.getName());
                if ((file.getName().equals(location.split("/")[0] + "/") || file.getName().equalsIgnoreCase(location)))
                {
                    File f = new java.io.File(destDir + File.separator + file.getName());
                    if (file.isDirectory())
                    { // if its a directory, create it
                        f.mkdir();
                        continue;
                    }
                    try (java.io.InputStream is = jar.getInputStream(file); // get
                                                                            // the
                                                                            // input
                                                                            // stream
                            java.io.FileOutputStream fos = new java.io.FileOutputStream(f))
                    {
                        while (is.available() > 0)
                        {  // write contents of 'is' to 'fos'
                            fos.write(is.read());
                        }
                    }
                }
            }
        }
        
    }
    
}
