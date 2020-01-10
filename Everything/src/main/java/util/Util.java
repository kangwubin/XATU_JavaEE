package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/4
 * @Time: 16:24
 */
public class Util {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String[] SIZE_NAMES = {"B", "KB", "MB", "GB"};
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    /*文件大小转换为KB,MB....*/
    public static String parseSize(Long size) {
        //String[] sizeNames = {"B", "KB", "MB", "GB"};
        int n = 0;
        while (size >= 1024) {
            size = size / 1024;
            n++;
        }
        return size + SIZE_NAMES[n];
    }

    /*日期类型的转化*/
    public static String parseDate(Long lastModified) {
        //DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        return DATE_FORMAT.format(new Date(lastModified));
    }
}
