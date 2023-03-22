package leetcode.timer;/*
 *
 * @Param
 */

import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler {
    public static void main(String[] args) {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 执行任务代码
                System.out.println("任务执行了");
            }
        };

        Timer timer = new Timer();
        // 任务在 5 秒后执行
        timer.schedule(task, 5000L);
    }
}


