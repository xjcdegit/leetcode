package leetcode.middle.sort;/*
 *
 * @Param
 */

import java.util.Arrays;

/**
 * 给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直区域 的宽度。
 *
 * 垂直区域 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直区域 为宽度最大的一个垂直区域。
 *
 * 请注意，垂直区域 边上 的点 不在 区域内。
 */
public class Leetcode1637 {
    public static int maxWidthOfVerticalArea(int[][] points) {
        /*Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1] = o2[1];
                }
                return o1[0] - o2[0];
            }
        });*/
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        for(int i = 0;i < points.length;i++){
            System.out.println(points[i][0]);
        }
        return 0;
    }
}
