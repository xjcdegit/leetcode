package leetcode.middle.hash;/*
 *
 * @Param
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 保证文件名唯一
 */
public class Leetcode1487 {
    public String[] getFolderNames(String[] names) {
        Map<String,Integer> map = new HashMap();
        int n = names.length;
        String[] res = new String[n];
        int i = 0;
        for (String temp:names){
            if(!map.containsKey(temp)){
                res[i++] = temp;
                map.put(temp,1);
            }else {
                int k = map.get(temp);
                while(map.containsKey(addSuffix(temp,k))){
                    k++;
                }
                res[i++] = addSuffix(temp,k);
                map.put(temp,map.get(temp) + 1);
                map.put(addSuffix(temp,k),1);
            }
        }
        return res;
    }

    public String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }
}
