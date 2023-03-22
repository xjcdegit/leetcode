package leetcode.hard.queue;/*
 *
 * @Param
 */

//随机产生数字并传递给一个方法。你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。
//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

import java.util.PriorityQueue;

public class streamMedian {
    PriorityQueue<Integer> queMin;//升序
    PriorityQueue<Integer> queMax;//降序
    /** initialize your data structure here. */
    public streamMedian() {
        queMin = new PriorityQueue<>((a,b)->(b - a));
        queMax = new PriorityQueue<>((a,b)->(a - b));
    }

    public void addNum(int num) {
        if(queMin.isEmpty() || num <= queMin.peek()){
            queMin.offer(num);
            if(queMax.size() + 1 < queMin.size()){
                queMax.offer(queMin.poll());
            }
        }else{
            queMax.offer(num);
            if(queMax.size() > queMin.size()){
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if(queMin.size() > queMax.size()){
            return 1.0*queMin.peek();
        }
        return (queMin.peek() + queMax.peek())*1.0/2;
    }
}
