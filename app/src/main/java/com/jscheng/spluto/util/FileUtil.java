package util;

import java.io.*;

/**
 * Created by chengjunsen on 2018/10/30.
 */
public class FileUtil {

    public static String readFile(String path) {
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            String line;
            reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void overwiteFile(String path, String text) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
