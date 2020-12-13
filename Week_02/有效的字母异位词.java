
//方法1， hash
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        //hash
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int j = 0; j < t.length(); j++) {
            Character c = t.charAt(j);
            int count = map.getOrDefault(c, 0) - 1;
            if (count < 0) {
                return false;
            }
            map.put(c, count);
        }
        return true;
    }
}

//方法2 对两个字符串进行排序，然后比较是否相等