/**
 * 方法：动态规划
 *
 * 条件：
 *  1. 给出一个0-9数字的字符串，还有一个数字映射字母的关系 1->A, 2->B ... 26->Z
 *  2. 求将数组字符串映射为字母有多少种方式
 *  3. "1" 可以被映射为 "A"， "11" 可以被映射为 "AA" 或 "K"，"111" 可以被映射为 "AAA" 或 "AK" 或 "KA"
 *  3. 0 或 06 不能被映射，但是 "10" 可以被映射为 "J"，"20" 可以被映射为 "T"
 *
 * 思路：
 *  假设当前位置为 i，字符串长度为 m，当前可以映射的方式为 total[i] 种
 *
 *  通过题目可以得知给出的字符串由0-9组成，只有1-26的数字可以被映射为字母
 *    那么就要考虑 0, 1-9, 10-26这几种情况
 *
 *  1. 如果当前数字可以跟前一位合并为 10-26的数字，那么就可以分为两种情况
 *      1) 与前一位合并
 *      2) 不跟前一位合并
 *    由此可以推出：
 *      合并：把当前数字和前一个数字组成一个字母，实际上就是看往前两位的数字有多少种映射方式
 *          则有： total[i-2] 种方式
 *      不合并：不把当前数字和前一个数字组成一个字母，实际上就是看前一位的数字有多少种映射方式
 *          则有： total[i-1] 种方式
 *    那么子问题就是：total[i] = total[i-1] + total[i-2]
 *
 *  2. 但是上一步有一种特殊情况：0 必须跟 1或2 合并才能被映射
 *      所以需要针对0进行判断
 *          1) 当前位置的0，可以和前一位结合成 10 或 20，此时就必须跟前一位合并来映射
 *            那么就要看往前两位的数字有多少种映射方式
 *          子问题就是： total[i] = total[i-2]
 *
 *          2) 不能和前一位结合成 10 或 20
 *          那么说明改字符串不能被翻译，直接返回 0 退出
 *
 *  3. 如果当前位置不是0，且不能跟前一位合并成 10-26，那么就要看往前一位的数字有多少种映射方式
 *      子问题就是： total[i] = total[i-1]
 *
 * 状态数组
 *   s[0] 可以使用 1 作为默认值，s[2] 就有 s[0] + s[-1]
 *      所以状态数组可以定义为  dp[s.length + 1]
 *      dp[0] 和 dp[1] 分别存储 s[-1] s[0] 默认值；后面依次类推，存储解码方法总数
 *
 * 递推方程根据情况判断：
 *  1. s[i] == 0:
 *      s[i-1] in [1, 2]: fn(i) = fn(i-2)
 *      s[i-1] not in [1, 2]: fn(n) = 0
 *  2. (s[i-1] + s[i]) in [11-26]: fn(i) = fn(i-1) + fn(i-2)
 *  3. s[i] 必须单独映射: fn(i) = fn(i-1)
 *
 * 代码如下：
 */
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        char[] charArray = s.toCharArray();
        int[] dp = new int[charArray.length + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= charArray.length; i++) {
            int charIndex = i - 1;
            if (charArray[charIndex] == '0') {
                if (charArray[charIndex - 1] == '1' || charArray[charIndex - 1] == '2') {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            } else if ((charArray[charIndex - 1] == '1' &&  charArray[charIndex] <= '9') || (charArray[charIndex - 1] == '2' &&  charArray[charIndex] <= '6')) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[charArray.length];
    }
}
