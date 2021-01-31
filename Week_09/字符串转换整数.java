import java.util.Map;

import javax.swing.text.TabExpander;

/**
 * 解法1： 简单解法
 * 问题：if else 太多
 */
public class Solution_9_2 {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
            continue;
        }

        if (i == s.length()) {
            return 0;
        }

        char first = s.charAt(i);
        int flag = 1;
        if (first == '+') {
            i++;
        } else if (first == '-') {
            i++;
            flag = -1;
        }

        int result = 0;
        for (; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            if (num < 0 || num > 9) {
                break;
            }

            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                System.out.println(result);
                return Integer.MAX_VALUE;
            }

            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }

            result = result * 10 + num * flag;
        }
        return result;
    }
}

/**
 * 参考官方题解
 * 解法2：DFA
 */
public class Solution {
    private int sign = 1;
    private long num = 0;
    private String state = "start";
    private Map<String, String[]> stateTable = new HashMap<>(){{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public int myAtoi(String s) {
        for (int i = 0; i < s.length(); i++) {
            get(s.charAt(i));
        }
        return (int)(num * sign);
    }

    private void get(char c) {
        int col = getCol(c);
        String[] stateArray = stateTable.get(state);
        state = stateArray[col];
        if (state.equals("signed")) {
            sign = c == '+' ? 1 : -1;
        } else if (state.equals("in_number")) {
            num = num * 10 + c - '0';
            num = sign == 1 ? Math.min(num, (long)Integer.MAX_VALUE) : Math.min(num, -(long)Integer.MIN_VALUE);
            System.out.println(num);
        }
    }

    private  int getCol(char c) {
        if (c == ' ') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        }
        int digit = c - '0';
        if (digit >= 0 && digit <= 9) {
            return 2;
        }
        return 3;
    }
}
