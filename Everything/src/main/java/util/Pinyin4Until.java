package util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/4
 * @Time: 11:09
 */
public class Pinyin4Until {
    /**
     * 中文字符格式
     */
    private static final String CHINESE_PATTERN = "[\\u4E00-\\u9FA5]";
    public static final HanyuPinyinOutputFormat FORMAT;

    static {
        FORMAT = new HanyuPinyinOutputFormat();
        //设置拼音小写
        FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置不带音调
        FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //带V字符
        FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /*判断字符串是否包含中文；*/
    public static boolean containsChinese(String str) {
        return str.matches(".*" + CHINESE_PATTERN + ".*");
    }

   /* public static String[][] get(String hanyu) throws BadHanyuPinyinOutputFormatCombination {
        String[][] result = new String[hanyu.length()][];
        for (int i = 0; i < hanyu.length(); i++) {
            result[i] = PinyinHelper.toHanyuPinyinStringArray(hanyu.charAt(i), FORMAT);
        }
        return result;
    }*/

    public static String[] get(String hanyu) {
        String[] array = new String[2];
        StringBuilder pinyin = new StringBuilder();
        StringBuilder pinyin_first = new StringBuilder();
        for (int i = 0; i < hanyu.length(); i++) {
            String[] pinyins = new String[0];
            try {
                pinyins = PinyinHelper.toHanyuPinyinStringArray(hanyu.charAt(i), FORMAT);
                //中文字符返回的字符串数组，可能为null或者长度为0
                //返回原始的字符
                if (pinyins == null || pinyins.length == 0) {
                    pinyin.append(hanyu.charAt(i));
                    pinyin_first.append(hanyu.charAt(i));
                } else {//可以转换为拼音，只取第一个字符串作为拼音
                    pinyin.append(pinyins[0]);
                    pinyin_first.append(pinyins[0].charAt(0));
                }
            } catch (Exception e) {//出现异常，返回原始的字符串
                pinyin.append(hanyu.charAt(i));
                pinyin_first.append(hanyu.charAt(i));
            }
        }
        array[0] = pinyin.toString();
        array[1] = pinyin_first.toString();
        return array;
    }

    /*
     * 返回所有拼音的排列组合；*/
    public static String[][] get(String hanyu, boolean fullSppell) {
        String[][] array = new String[hanyu.length()][];
        /*StringBuilder pinyin = new StringBuilder();
        StringBuilder pinyin_first = new StringBuilder();*/
        for (int i = 0; i < hanyu.length(); i++) {
            String[] pinyins = new String[0];
            try {
                pinyins = PinyinHelper.toHanyuPinyinStringArray(hanyu.charAt(i), FORMAT);
                if (pinyins == null || pinyins.length == 0) {//a->["a"]
                    array[i] = new String[]{
                            String.valueOf(hanyu.charAt(i))
                    };
                } else {//和->he he huo he hu--去重
/*                    if (fullSppell) {
                        Set<String> set = new HashSet<>();
//                    List<String> Arrays.asList(pinyins);
                        set.addAll(Arrays.asList(pinyins));
                        array[i] = set.toArray(new String[set.size()]);
                    }*/
                    array[i] = unique(pinyins, fullSppell);
                }
            } catch (Exception e) {//出现异常，返回原始的字符串
                array[i] = new String[]{String.valueOf(hanyu.charAt(i))};
            }
        }
        return array;
    }

    /*去重操作*/
    public static String[] unique(String[] pinyins, boolean fullSpell) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < pinyins.length; i++) {
            if (fullSpell) {
                set.add(pinyins[i]);
            } else {
                set.add(String.valueOf(pinyins[i].charAt(0)));
            }
        }
        return set.toArray(new String[set.size()]);
    }

    public static void main(String[] args) {
        /*String[][] pinyins = get("中华人民共和国");
        System.out.println(Arrays.toString(pinyins));*/
      /*  String[] pinyins = get("中华人民共和国");
        System.out.println(Arrays.toString(pinyins));*/
        System.out.println("abc".matches(".*"));
    }
}
