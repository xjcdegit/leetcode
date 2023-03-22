package leetcode.middle.sort;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode1233 {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> list = new ArrayList<>();
        list.add(folder[0]);
        for(int i = 1;i < folder.length;i++){
            int pre = list.get(list.size() - 1).length();
            if(!(folder[i].length() > pre && list.get(list.size() - 1).equals(folder[i].substring(0,pre)) && folder[i].charAt(pre) == '/')){
                list.add(folder[i]);
            }
        }
        return list;
    }
}
