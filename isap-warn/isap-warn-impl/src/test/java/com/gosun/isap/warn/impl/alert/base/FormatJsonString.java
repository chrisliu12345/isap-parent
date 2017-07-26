package com.gosun.isap.warn.impl.alert.base;

/**
 * <p>创建时间：2017-6-12 9:40</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class FormatJsonString {

    /**
     * 将 json 字符串格式化
     *
     * @param json json
     * @return 格式化后的 json
     */
    public static String formatJson(String json) {
        if (null == json || json.isEmpty()) {
            return "";
        }
        json = clearEmptyChar(json);
        StringBuilder sb = new StringBuilder();
        char current;
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < json.length(); i++) {
            current = json.charAt(i);
            switch (current) {
                case '"':
                    if (isQuotationMarks(json, i)) {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 去掉 json 中的空字符
     *
     * @param json json
     * @return 去除空字符的 json
     */
    private static String clearEmptyChar(String json) {
        if (null == json || json.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        char current;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < json.length(); i++) {
            current = json.charAt(i);
            switch (current) {
                case '"':
                    if (!isInQuotationMarks) {
                        clearEmptyChar(sb);
                    }
                    if (isQuotationMarks(json, i)) {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    if (!isInQuotationMarks) {
                        clearEmptyChar(sb);
                    }
                    sb.append(current);
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        clearEmptyChar(sb);
                    }
                    sb.append(current);
                    break;
                case ',':
                    if (!isInQuotationMarks) {
                        clearEmptyChar(sb);
                    }
                    sb.append(current);
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * @param string 所在字符串
     * @param index  引号所在位置
     * @return 是否为有效的引号
     */
    private static boolean isQuotationMarks(String string, int index) {
        if (index == 0) {
            return true;
        }
        int count = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (string.charAt(i) == '\\') {
                count++;
            } else {
                break;
            }
        }
        return count % 2 == 0;
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     * @author lizhgb
     * @Date 2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    /**
     * 去除 stringBuilder 中的空字符
     *
     * @param stringBuilder stringBuilder
     */
    private static void clearEmptyChar(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            return;
        }
        int length = stringBuilder.length();
        for (int i = length - 1; i >= 0; i--) {
            char c = stringBuilder.charAt(i);
            if (isEmptyChar(c)) {
                stringBuilder.deleteCharAt(i);
            } else {
                break;
            }
        }
    }

    /**
     * @param c 字符
     * @return 是否为空字符
     */
    private static boolean isEmptyChar(char c) {
        char[] empty = {'\f', '\n', '\r', '\t'};
        for (char emptyChar : empty) {
            if (c == emptyChar) {
                return true;
            }
        }
        return false;
    }
}
