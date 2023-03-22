package leetcode.scheduling;/*
 *
 * @Param
 */

public class ScheduleSystem {

    public static void main(String[] args) {

        int numShifts = 7;
        int numEmployees = 10;
        int[][] preferences = {
                {3, 2, 2, 3, 3, 2, 1},
                {2, 3, 3, 2, 2, 1, 1},
                {1, 1, 1, 2, 2, 3, 3},
                {2, 2, 2, 1, 1, 2, 2},
                {1, 1, 1, 1, 1, 2, 2},
                {1, 2, 1, 3, 1, 2, 2},
                {1, 2, 1, 3, 1, 2, 2},
                {1, 2, 1, 3, 1, 2, 2},
                {1, 2, 1, 3, 1, 2, 2},
                {1, 2, 1, 3, 1, 2, 2}
        };
        int populationSize = 50;
        int maxGenerations = 1000;
        double crossoverRate = 0.8;
        double mutationRate = 0.1;
        ScheduleAlgorithm algorithm = new ScheduleAlgorithm(numShifts, numEmployees, preferences, populationSize, maxGenerations, crossoverRate, mutationRate);
        int[] bestSchedule = algorithm.run();
        System.out.println("Best schedule:");
        for (int i = 0; i < numShifts; i++) {
            System.out.println("Shift " + (i + 1) + ": Employee " + (bestSchedule[i] + 1));
        }


    }


}

