package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chengjunsen on 2018/10/30.
 */
public class RegUtil {
    public final static String PICTURE_MARKDOWN_REG = "\\!\\[.*?\\]\\((http(s)?:\\/\\/)?(.*?)(jpg|png)(\\s\"(.*?)\")?\\)";
    public final static String TITLE_MARKDOWN_REG = "#{1,}\\s(.*)";
    public final static String REFERENCE_MARKDOWN_REG = ">\\s(.*)";
    public final static String CUTLINE_MARKDOWN_REG = "(-|\\*){3,}$";
    public final static String ORDERED_LIST_MARKDOWN_REG = "[0-9]+.\\s(.*)";
    public final static String DISORDERED_LIST_MARKDOWN_REG = "[0-9]+.\\s(.*)";
    public final static String CHECKBOX_REG = "-\\s\\[x\\]\\s(.*)";
    public final static String UNCHECKBOX_REG = "-\\s\\[\\s\\]\\s(.*)";

    public static boolean match(String regEx, String checkContent) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(checkContent);
        return matcher.matches();
    }

    public static boolean find(String regEx, String checkContent) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(checkContent);
        return matcher.find();
    }

    public static List<String> splits(String regEx, String checkContent) {
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(checkContent);
        int start = 0;
        while(matcher.find()){
            int matchStart = matcher.start();
            if (start < matchStart) {
                results.add(checkContent.substring(start, matchStart));
            }
            results.add(matcher.group());
            start = matcher.end();
        }
        if (start < checkContent.length()) {
            results.add(checkContent.substring(start, checkContent.length()));
        }
        return results;
    }
}
