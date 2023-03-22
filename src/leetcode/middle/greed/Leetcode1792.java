package leetcode.middle.greed;/*
 *
 * @Param
 */

import java.util.PriorityQueue;

/**
 * 求最大平均通过率
 */
public class Leetcode1792 {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a,b) -> {
            long vala = (long)b[1] * (b[1] + 1) * (a[1] - a[0]);
            long valb = (long)a[1] * (a[1] + 1) * (b[1] - b[0]);
            if(vala == valb){
                return 0;
            }
            return vala < valb ? 1 : -1;
        });
        for(int[] c:classes){
            queue.offer(new int[]{c[0],c[1]});
        }

        for(int i = 0;i < extraStudents;i++){
            int[] temp = queue.poll();
            queue.offer(new int[]{temp[0] + 1,temp[1] + 1});
        }
        double res = 0;
        for(int i = 0;i < classes.length;i++){
            int[] poll = queue.poll();
            res += 1.0* poll[0]/poll[1];
        }
        return  res/ classes.length;
    }
}
