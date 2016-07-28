package com.trioly.utils;

import android.annotation.SuppressLint;

import com.baseproject.util.BaseProjectLog;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringUtils {

    private final static String EMPTY_STRING = "";
    private final static String TAG          = StringUtils.class.getSimpleName();

    /**
     * 不为空 返回真
     *
     * @param str 字符串
     * @return 如果字符串不为空且长度大于1 返回真 ，其他返回假
     */
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().equals(EMPTY_STRING);
    }

    /**
     * 如果为空 返回真
     *
     * @param str 字符串
     * @return 如果为空或长度等于零，返回真，其他返回假
     */
    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 去掉空格不为空 返回真
     *
     * @param str 字符串
     * @return 如果字符串不为空且去掉空格长度大于1 返回真 ，其他返回假
     */
    public static boolean isNotTrimBlank(String str) {
        return str != null && !str.trim().equals(EMPTY_STRING);
    }

    /**
     * 判断密码长度是否大于6小于12
     *
     * @param str
     * @return 如果密码包含空格，返回会假，密码长度不够6位和大于12位返回假，其它返回真
     */
    public static boolean checkPwdLength(String str) {
        if (str.contains(" ")) {
            return false;
        }
        if (str.length() >= 6 && str.length() <= 12) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断密码是否大于2种
     *
     * @param str
     * @return
     */
    public static boolean checkPwdType(String str) {
        Pattern p2 = Pattern.compile(
                "^((?=.*?\\d)(?=.*?[A-Za-z])|(?=.*?\\d)(?=.*?[!@#$%^&])|(?=.*?[A-Za-z])(?=" + "" +
                        ".*?[!@#$%^&]))[\\dA-Za-z!@#$%^&]+$");
        Matcher m2 = p2.matcher(str);
        if (!m2.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 去掉空格为空返回真
     *
     * @param str 字符串
     * @return 如果字符串为空或去掉空格长度为0, 返回真，其他返回假
     */
    public static boolean isTrimBlank(String str) {
        return str == null || str.trim().equals(EMPTY_STRING);
    }

    /**
     * 首字母大写
     *
     * @param str 要转换的字符串
     * @return 首字母大写的字符串
     */
    @SuppressLint("DefaultLocale")
    public static String capFirstUpperCase(String str) {
        if (isBlank(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);

    }

    /**
     * 首字母小写
     *
     * @param str 要转换的字符串
     * @return 首字母小写的字符串
     */
    @SuppressLint("DefaultLocale")
    public static String capFirstLowerCase(String str) {
        if (isBlank(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 是否是手机字符串
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str) {
        Pattern p = Pattern.compile("^((\\+?86)|((\\+86)))?1\\d{10}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 是否是身份证号码
     *
     * @param str
     * @return
     */
    public static boolean isIdCardNumber(String str) {
        Pattern p = Pattern.compile(
                "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}" + "" +
                        "([0-9]|[X|x|*])$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 确认密码对比
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isConfirmPassword(String str1, String str2) {
        return str1.equals(str2);
    }

    public static String formatCount(String count) {
        int c = 0;
        try {
            c = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            BaseProjectLog.e(TAG, e.getMessage(), e.getCause());
        }
        return formatCount(c);
    }

    public static String formatCount(int count) {
        if (count > 9999) {
            return String.format("%s万",
                    new BigDecimal(count).divide(new BigDecimal(10000)).setScale(1,
                            RoundingMode.HALF_DOWN).toString());
        } else {
            return String.format("%s", count);
        }
    }

    /**
     * 得到字符串的子字符串
     * Get sub string of original string. Don't consider ArrayIndexOutOfBoundException.
     *
     * @param length 从头部开始子字符串的长度
     *               length of substring from the beginning
     * @param str    初始字符串 original string
     */
    public static String getSubString(String str, int length) {
        if (str == null) {
            return null;
        }
        int strLength = str.length();
        return str.substring(0, Math.min(length, strLength));
    }

    /**
     * 使用正则表达式对指定字符串格式进行检查
     * Match string by regular expression.
     *
     * @param source         指定字符串
     *                       string to match
     * @param regularPattern 正则表达式
     *                       regular expression
     */
    public static boolean validateStringPattern(String source, String regularPattern) {
        Pattern pattern = Pattern.compile(regularPattern);
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }

    /**
     * 将整形数组转换成字符串数组
     *
     * @param intArray 要转化的Integer数组
     * @return 转化后的字符串数组
     */
    public static String[] getStringArrayFromIntArray(int[] intArray) {
        if (intArray == null) {
            return new String[0];
        }

        String[] strArray = new String[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            strArray[i] = String.valueOf(intArray[i]);
        }
        return strArray;
    }

    /**
     * 获取下载文件的扩展名
     *
     * @param downloadUrl 文件下载地址
     */
    public static String getFileSuffix(String downloadUrl) {
        if (!isBlank(downloadUrl)) {
            int index = downloadUrl.lastIndexOf('.');
            return downloadUrl.substring(index, downloadUrl.length());
        }
        return null;
    }


    /**
     * 判断字符串是否为空，为空则defaultText,若defaultText 也为空，则返回“ ”
     *
     * @param text
     * @param defaultText
     * @return
     */
    public static String textIsNull(String text, String defaultText) {
        if (!isNull(text)) {
            return text;
        } else {
            if (!isNull(defaultText)) {
                return " ";
            } else {
                return defaultText;
            }
        }
    }

    public static boolean isNull(String string) {
        if (string == null || "".equals(string)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将字节数组转换为16进制字符串.
     *
     * @param data 进行转换的字节数组
     * @return 16进制的字符串
     */
    public static String byte2HexString(byte[] data) {
        if (null == data) {
            return "";
        }
        StringBuffer checksumSb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String hexStr = Integer.toHexString(0x00ff & data[i]);
            if (hexStr.length() < 2) {
                checksumSb.append("0");
            }
            checksumSb.append(hexStr);
        }
        return checksumSb.toString();
    }

    /**
     * 16进制字符串转换为字节数组.
     *
     * @param digits 16进制的字符串
     * @return 字符数组
     */
    public static byte[] hexStr(String digits) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < digits.length(); i += 2) {
            char c1 = digits.charAt(i);
            if ((i + 1) >= digits.length()) {
                throw new IllegalArgumentException();
            }
            char c2 = digits.charAt(i + 1);
            byte b = 0;
            if ((c1 >= '0') && (c1 <= '9')) {
                b += ((c1 - '0') * 16);
            } else if ((c1 >= 'a') && (c1 <= 'f')) {
                b += ((c1 - 'a' + 10) * 16);
            } else if ((c1 >= 'A') && (c1 <= 'F')) {
                b += ((c1 - 'A' + 10) * 16);
            } else {
                throw new IllegalArgumentException();
            }
            if ((c2 >= '0') && (c2 <= '9')) {
                b += (c2 - '0');
            } else if ((c2 >= 'a') && (c2 <= 'f')) {
                b += (c2 - 'a' + 10);
            } else if ((c2 >= 'A') && (c2 <= 'F')) {
                b += (c2 - 'A' + 10);
            } else {
                throw new IllegalArgumentException();
            }
            baos.write(b);
        }
        return (baos.toByteArray());
    }

    public static String getSigTimeStr(){
        return String.valueOf(System.currentTimeMillis());
    }

    public static String formatPrice(String price) {
        return new BigDecimal(price).divide(new BigDecimal(100)).setScale(2).toString();
    }

    public static String formatPrice(int price, String unit) {
        return new BigDecimal(price).divide(new BigDecimal(100)).setScale(2).toString() + unit;
    }
}
