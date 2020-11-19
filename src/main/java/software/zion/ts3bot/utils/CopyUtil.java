package software.zion.ts3bot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Zion
 * @created 18/11/2020 - 15:59
 * @project ts3querybot
 */
public class CopyUtil {
    /**
     *
     * @param srcPath
     * @param outFile
     */
    public static void copyOutOfJarFile(String srcPath, File outFile) {
        try {
            InputStream inputStream = CopyUtil.class.getResourceAsStream(srcPath);
            FileOutputStream outputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
