package leetcode.scheduling2;/*
 *
 * @Param
 */

public class ScheduleSystem {

    public static void main(String[] args) {
        int numShifts = 21;
        int numEmployees = 5;

        // 员工可用性，1表示可用，0表示不可用
        int[][] availability = {
                {1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0, 1, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1}
        };

        int minutesPerShift = 60; // 一个班次60分钟
        int minutesPerMaxConsecutiveShifts = 4 * 60; // 最长连续工作时间为4小时
        int totalHoursPerWeek = 40; // 每周最多工作40小时
        int populationSize = 100; // 种群大小
        int maxGenerations = 1000; // 最大迭代次数
        double crossoverRate = 0.8; // 交叉率
        double mutationRate = 0.1; // 变异率

        ScheduleAlgorithm algorithm = new ScheduleAlgorithm(numShifts, numEmployees, availability,
                minutesPerShift, minutesPerMaxConsecutiveShifts,
                totalHoursPerWeek, populationSize, maxGenerations,
                crossoverRate, mutationRate);
        int[] bestSchedule = algorithm.run();

        System.out.println("Best schedule:");
        for (int i = 0; i < numShifts; i++) {
            System.out.println("Shift " + (i + 1) + ": Employee " + (bestSchedule[i] + 1));
        }
    }
}

