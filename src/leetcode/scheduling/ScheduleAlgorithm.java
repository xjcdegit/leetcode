package leetcode.scheduling;/*
 *
 * @Param
 */

import java.util.*;

public class ScheduleAlgorithm {

    private int numShifts; // 班次的数量
    private int numEmployees; // 员工的数量
    private int[][] preferences; // 员工对班次的偏好
    private int populationSize; // 种群数量
    private int maxGenerations; // 最大迭代次数
    private double crossoverRate; // 交叉率
    private double mutationRate; // 变异率

    // 初始化排班算法的各种参数
    public ScheduleAlgorithm(int numShifts, int numEmployees, int[][] preferences, int populationSize, int maxGenerations, double crossoverRate, double mutationRate) {
        this.numShifts = numShifts;
        this.numEmployees = numEmployees;
        this.preferences = preferences;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    // 根据员工对班次的偏好随机生成一个初始种群
    private int[][] generatePopulation() {
        int[][] population = new int[populationSize][numShifts];
        Random random = new Random();
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < numShifts; j++) {
                population[i][j] = random.nextInt(numEmployees);
            }
        }
        return population;
    }

    // 计算一个班次安排的适应度值（这里采用员工的偏好之和作为适应度值）
    private double calculateFitness(int[] schedule) {
        double fitness = 0;
        for (int i = 0; i < numEmployees; i++) {
            for (int j = 0; j < numShifts; j++) {
                if (schedule[j] == i) {
                    fitness += preferences[i][j];
                }
            }
        }
        return fitness;
    }

    // 计算整个种群的适应度值
    private double[] calculateFitnessScores(int[][] population) {
        double[] fitnessScores = new double[populationSize];
        for (int i = 0; i < populationSize; i++) {
            fitnessScores[i] = calculateFitness(population[i]);
        }
        return fitnessScores;
    }

    // 选择一个用于交叉的父代
    private int selectParent(double[] fitnessScores) {
        double totalFitness = 0;
        for (double score : fitnessScores) {
            totalFitness += score;
        }
        double rand = Math.random() * totalFitness;
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
        int[][] population = generatePopulation();
        int[] bestSchedule = population[0];
        double bestFitness = calculateFitness(bestSchedule);
        for (int generation = 0; generation < maxGenerations; generation++) {
            double[] fitnessScores = calculateFitnessScores(population);
            for (int i = 0; i < populationSize; i++) {
                if (fitnessScores[i] > bestFitness) {
                    bestFitness = fitnessScores[i];
                    bestSchedule = population[i];
                }
            }
            crossover(population);
            mutate(population);
        }
        return bestSchedule;
    }
}
