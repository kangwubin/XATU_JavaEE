package util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.Arrays;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/4
 * @Time: 10:52
 */
public class Pinyin4Until_1 {
    public static void test(String hanyu) throws Exception {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //设置拼音小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置不带音调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //带V字符
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
//        String[][] result = new String[hanyu.length()][];
        for (int i = 0; i < hanyu.length(); i++) {
            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(hanyu.charAt(i), format);
//            result[i] = PinyinHelper.toHanyuPinyinStringArray(hanyu.charAt(i), format);
            System.out.println(Arrays.toString(pinyins));
        }
    }

    public static void main(String[] args) throws Exception {
        test("康武斌");
    }
}
