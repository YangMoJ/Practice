package com.yang;

/**
 * @author yd
 * @version 1.0
 * @date 2022/11/1 16:36
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class test01 {

    public static void main(String[] args) {
        String str = "Fe2(SO4)3";

        System.out.println(countElement(str));
        System.out.println("吃屎去吧");
        System.out.println("吃屎去吧");
        System.out.println("master test");


    }

    public static String countElement(String param) {
        //进行字符串拼接
        StringBuilder ans = new StringBuilder();
        //记录元素和其对应的次数
        HashMap<String, Integer> element = new HashMap(16);
        //按顺序记录元素
        List<String> lst = new ArrayList();
        //按顺序记录每次元素在此刻出现的次数
        List<Integer> lstNum = new ArrayList();
        //存储括号开始的位置
        Stack<Integer> s = new Stack();
        for (int i = 0; i < param.length(); ++i) {
            //大写英文
            if (param.charAt(i) >= 'A' && param.charAt(i) <= 'Z') {
                int tmp = i;
                ++i;
                //判断++i是否为小写字母
                while (i < param.length() && (param.charAt(i) <= 'z' && param.charAt(i) >= 'a')) {
                    ++i;
                }
                String str = param.substring(tmp, i);
                //判斷是否存在這個key
                if (!element.containsKey(str)) {
                    element.put(str, 0);
                }
                lst.add(str);
                //判斷i位是否為數字
                if (i < param.length() && (param.charAt(i) >= '0' && param.charAt(i) <= '9')) {
                    int sum = 0;
                    while (i < param.length() && (param.charAt(i) >= '0' && param.charAt(i) <= '9')) {

                        sum = sum * 10 + param.charAt(i) - '0';
                        ++i;
                    }
                    Integer value = element.get(str);
                    element.put(str, value + sum);
                    lstNum.add(sum);
                } else {
                    Integer value = element.get(str);
                    element.put(str, value + 1);
                    lstNum.add(1);
                }
                --i;//回档，不然会跳过一个字符
            } else if (param.charAt(i) == '(') {
                //记录下括号开始的元素下标
                s.push(lst.size());
            } else if (param.charAt(i) == ')') {
                //表示一个括号结束了可以进行计算了
                ++i;
                int sum;
                if (i < param.length() && (param.charAt(i) >= '0' && param.charAt(i) <= '9')) {
                    sum = 0;
                    while (i < param.length() && (param.charAt(i) >= '0' && param.charAt(i) <= '9')) {
                        sum = sum * 10 + param.charAt(i) - '0';
                        ++i;
                    }
                } else {
                    continue;
                }
                sum--;
                for (int j = s.pop(); j < lst.size(); ++j) {
                    Integer value = element.get(lst.get(j));
                    element.put(lst.get(j), value + sum * lstNum.get(j));
                    Integer li = lstNum.get(j);
                    li = li + sum * li;
                    lstNum.set(j, li);
                }
                --i;//回档，不然会跳过一个字符
            }
        }
        for (int i = 0; i < lst.size(); ++i) {
            if (element.containsKey(lst.get(i))) {
                //元素拼接一次就够了，其他地方出现的相同元素就不管它了。
                Integer value = element.get(lst.get(i));
                ans.append(lst.get(i) + value);
                element.remove(lst.get(i));
            }
        }
        return ans.toString();
    }

}
