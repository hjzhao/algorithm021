// 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
// 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
// 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
// 注意，一开始你手头没有任何零钱。
// 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
// 示例1
//  输入：[5,5,5,10,20]
//  输出：true
// 示例2
//  输入：[5,5,10]
//  输出：true
// 示例3
//  输入：[10,10]
//  输出：false
// 示例4
//  输入：[5,5,10,10,20]
//  输出：false

/**
 * 题解： 贪心算法
 * 条件：
 *  1. 每杯柠檬水售价5美元，顾客支付分为3中情况：分别是支付 5美元、10美元、20美元
 *  2. 因为最大是支付20美元，所以20美元不能用于给顾客找零，找零只能用 5美元、10美元
 *  3. 开始没钱找零，需要先收领钱，然后再给后面的顾客找零
 * 因为找零要优先选择10美元找零，然后再使用5美元找零，所以可以使用贪心算法
 *
 * 解题思路：
 *  1. 通过两个变量分别记录顾客支付的 5美元、10美元 有多少张
 *  2. 找零优先使用10美元找零，然后再用5美元找零
 *  3. 如果需要10美元找零，则将10美元 -1张，如果需要5美元找零，则将5美元 -1张
 * 返回结果
 *  1. 有一次找零失败，则返回false
 *  2. 如果所有的都找零成功，则返回true
 */

 //第一版实现：
class Solution4_1_1 {
    public boolean lemonadeChange(int[] bills) {
        int fiveDollar = 0;
        int tenDollar = 0;
        for (int i = 0; i < bills.length; i++) {
            //收入5美元不用找零
            if (bills[i] == 5) {
                fiveDollar++;
            }
            // 大于5美元需要找零
            int change = bills[i] - 5;
            if (change > 10 && tenDollar > 0) {
                change = change - 10;
                tenDollar--;
            }
            int changeFive = change / 5;
            if (changeFive > fiveDollar) {
                return false;
            }
            fiveDollar -= changeFive;
            if (bills[i] == 10) {
                tenDollar++;
            }
        }
        return true;
    }
}

//第二版实现：
//参考题解后
//  找零方式分为3中
//      1. 5美元不用找零
//      2. 10美元需要找零 5美元
//      3. 20美元找零 10美元+5美元，或者 3个5美元
//第一版实现中，要多次计算可以找多少张10美元，多少张5美元
//优化后代码如下：
class Solution4_1_2 {
    public boolean lemonadeChange(int[] bills) {
        int fiveDollar = 0;
        int tenDollar = 0;
        for (int i = 0; i < bills.length; i++) {
            //收入5美元不用找零
            if (bills[i] == 5) {
                fiveDollar++;
            } else if (bills[i] == 10) {
                if (fiveDollar == 0) {
                    return false;
                }
                fiveDollar--;
                tenDollar++;
            } else {
                if (tenDollar > 0 && fiveDollar > 0) {
                    tenDollar--;
                    fiveDollar--;
                } else if (fiveDollar >= 3) {
                    fiveDollar -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}