package leetcode.middle.hash;/*
 *
 * @Param
 */

import java.util.*;

public class Leetcode1604 {
    private Map<String, List<Integer>> map = new HashMap<>();
    public static void main(String[] args) {
        String[] keyName = {"daniel","daniel","daniel","luis","luis","luis","luis"};
        String[] keyTime = {"10:00","10:40","11:00","09:00","11:00","13:00","15:00"};

        List<String> strings = new Leetcode1604().alertNames(keyName, keyTime);
        for(String key:strings){
            System.out.println(key);
        }
    }

    public  List<String> alertNames(String[] keyName, String[] keyTime) {
        int hour,minute;

        for(int i = 0;i < keyName.length;i++){
            String time = keyTime[i];
            map.putIfAbsent(keyName[i],new ArrayList<Integer>());
            hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            map.get(keyName[i]).add(hour * 60 + minute);
        }
        Set<String> keySet = map.keySet();
        List<String> res = new ArrayList<String>();
        for(String key:keySet){
            List<Integer> list = map.get(key);
            Collections.sort(list);
            for(int i = 2;i < list.size();i++){
                int time1 = list.get(i - 2),time2 = list.get(i);
                int different = time2 - time1;
                if(different <= 60){
                    res.add(key);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
