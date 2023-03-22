package leetcode.easy;/*
 *
 * @Param
 */

import java.util.*;

public class Leetcode2363 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int[] item:items1){
            map.put(item[0],map.getOrDefault(item[0],0) + item[1]);
        }
        for(int[] item:items2){
            map.put(item[0],map.getOrDefault(item[0],0) + item[1]);
        }

        List<List<Integer>> result = new ArrayList<>();
        final Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            List<Integer> integers = new ArrayList<>();
            integers.add(entry.getKey());
            integers.add(entry.getValue());
            result.add(integers);
        }
        Collections.sort(result,(a,b) -> a.get(0) - b.get(0));
        return result;
    }

}
