package leetcode.scheduling2;/*
 *
 * @Param
 */

import java.util.*;

public class ScheduleAlgorithm {

    private int numShifts; // 班次数量
    private int numEmployees; // 员工数量
    private int[][] availability; // 员工可用性
    private int minutesPerShift; // 每个班次时长（分钟）
    private int minutesPerMaxConsecutiveShifts; // 最长连续工作时间（分钟）
    private int totalHoursPerWeek; // 每周总工作时间（小时）
    private int populationSize; // 种群大小
    private int maxGenerations; // 最大迭代次数
    private double crossoverRate; // 交叉率
    private double mutationRate; // 变异率

    // 初始化排班算法的各种参数
    public ScheduleAlgorithm(int numShifts, int numEmployees, int[][] availability, int minutesPerShift,
                             int minutesPerMaxConsecutiveShifts, int totalHoursPerWeek, int populationSize,
                             int maxGenerations, double crossoverRate, double mutationRate) {
        this.numShifts = numShifts;
        this.numEmployees = numEmployees;
        this.availability = availability;
        this.minutesPerShift = minutesPerShift;
        this.minutesPerMaxConsecutiveShifts = minutesPerMaxConsecutiveShifts;
        this.totalHoursPerWeek = totalHoursPerWeek;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    // 随机生成一个初始种群
    private int[][] generatePopulation(int populationSize) {
        int[][] population = new int[populationSize][numShifts];
        Random random = new Random();
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < numShifts; j++) {
                population[i][j] = random.nextInt(numEmployees);
            }
        }
        return population;
    }

    // 判断一个班次是否合法
    private boolean isValidShift(int[] schedule, int shiftIndex) {
        int employeeId = schedule[shiftIndex];
        if (availability[employeeId][shiftIndex] == 0) {
            return false;
        }
        int startShiftIndex = Math.max(0, shiftIndex - (minutesPerMaxConsecutiveShifts/minutesPerShift) + 1);
        int endShiftIndex = shiftIndex;
        int consecutiveMinutes = 0;
        for (int i = startShiftIndex; i <= endShiftIndex; i++) {
            if (schedule[i] == employeeId) {
                consecutiveMinutes += minutesPerShift;
                if (consecutiveMinutes > minutesPerMaxConsecutiveShifts) {
                    return false;
                }
            } else {
                consecutiveMinutes = 0;
            }
        }
        return true;
    }

    // 判断一个班次表是否合法
    private boolean isValidSchedule(int[] schedule) {
        int[][] hoursPerEmployee = new int[numEmployees][7];
        for (int i = 0; i < numShifts; i++) {
            if (!isValidShift(schedule, i)) {
                return false;
            }
            int employeeId = schedule[i];
            hoursPerEmployee[employeeId][i/3]++;
        }
        for (int i = 0; i < numEmployees; i++) {
            int totalHours = 0;
            for (int j = 0; j < 7; j++) {
                totalHours += hoursPerEmployee[i][j] * 4;
            }
            if (totalHours > totalHoursPerWeek) {
                return false;
            }
        }
        return true;
    }

    // 计算一个班次表的适应度值（这里采用员工的工作时间之和作为适应度值）
    private int calculateFitness(int[] schedule) {
        int[] hoursPerEmployee = new int[numEmployees];
        for (int i = 0; i < numShifts; i++) {
            int employeeId = schedule[i];
            hoursPerEmployee[employeeId] += minutesPerShift;
        }
        int totalHours = 0;
        for (int i = 0; i < numEmployees; i++) {
            totalHours += hoursPerEmployee[i];
        }
        return totalHours;
    }

    // 计算整个种群的适应度值
    private int[] calculateFitnessScores(int[][] population) {
        int[] fitnessScores = new int[populationSize];
        for (int i = 0; i < populationSize; i++) {
            if (isValidSchedule(population[i])) {
                fitnessScores[i] = calculateFitness(population[i]);
            } else {
                fitnessScores[i] = 0;
            }
        }
        return fitnessScores;
    }

    // 选择一个用于交叉的父代
    private int selectParent(int[] fitnessScores) {
        int totalFitness = 0;
        for (int score : fitnessScores) {
            totalFitness += score;
        }
        int rand = new Random().nextInt(totalFitness);
        for (int i = 0; i < populationSize; i++) {
            rand -= fitnessScores[i];
            if (rand <= 0) {
                return i;
            }
        }
        return 0;
    }

    // 交叉操作
    private void crossover(int[][] population) {
        Random random = new Random();
        for (int i = 0; i < populationSize; i += 2) {
            if (random.nextDouble() < crossoverRate) {
                int parent1Index = selectParent(calculateFitnessScores(population));
                int parent2Index = selectParent(calculateFitnessScores(population));
                int crossoverPoint = random.nextInt(numShifts);
                for (int j = crossoverPoint; j < numShifts; j++) {
                    int temp = population[parent1Index][j];
                    population[parent1Index][j] = population[parent2Index][j];
                    population[parent2Index][j] = temp;
                }
            }
        }
    }

    // 变异操作
    private void mutate(int[][] population) {
        Random random = new Random();
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < numShifts; j++) {
                if (random.nextDouble() < mutationRate) {
                    population[i][j] = random.nextInt(numEmployees);
                }
            }
        }
    }

    // 执行排班算法并返回最佳班次安排
    public int[] run() {
        int[][] population = generatePopulation(populationSize);
        int[] bestSchedule = null;
        int bestFitness = 0;
        for (int generation = 0; generation < maxGenerations; generation++) {
            int[] fitnessScores = calculateFitnessScores(population);
            for (int i = 0; i < populationSize; i++) {
                if (fitnessScores[i] > bestFitness) {
                    bestFitness = fitnessScores[i];
                    bestSchedule = Arrays.copyOf(population[i], numShifts);
                }
            }
            crossover(population);
            mutate(population);
        }
        return bestSchedule;
    }
}
