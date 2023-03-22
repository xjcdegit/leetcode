package leetcode.scheduling4;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * 该程序采用遗传算法来优化排班方案，其中遗传算法包括三个步骤：评估、交叉和变异。
 *
 * 初始化：程序首先初始化了一个由多个随机生成的候选方案组成的种群。
 *
 * 评估：对于种群中的每个个体，程序计算它的适应性分数（fitness score）。评估函数的实现如下：
 *
 * -对于每个员工，每天最多工作4小时。如果一个员工一天工作时间小于等于4小时，则将其适应性分数加1（即fitScore++)。
 *
 * -对于每个时间点，如果超过一个员工已经工作了4个小时，则将其适应性分数减去超过4小时的总时间（即fitScore -= (workingHours - 4)）。
 *
 * -对于每个员工，一周最多工作40小时。如果超过40小时，则将适应性分数减去超出的总工作时间（即fitScore -= (workingHours - 40)）。
 *
 * 交叉：程序使用简单的单点交叉来产生新的后代个体。具体来说，它随机选择两个个体，然后在一个随机位置将它们拆开，并使用它们的片段来创建两个新的子代。
 *
 * 变异：程序使用小的概率来随机改变每个个体的一些基因。具体来说，每个基因都有10％的概率在变异。
 *
 * 排班结果：程序将以最高适应度得分的个体作为最佳排班方案，并打印其结果。
 *
 * 在以上所给出的代码中，遵照排班规则一、二、三，代表分别为变量MAX_WORKING_HOURS_PER_WEEK、MAX_WORKING_HOURS_PER_DAY、TOTAL_EMPLOYEES，其值为40、4、6。遗传算法运算过程中涉及到许多变量、循环等操作，如有需要，请对照代码进行实践操作。
 */
public class IntelligentScheduling {

    private static final int POPULATION_SIZE = 10;
    private static final int CHROMOSOME_LENGTH = 42;
    private static final int MAX_WORKING_HOURS_PER_WEEK = 40;
    private static final int MAX_WORKING_HOURS_PER_DAY = 4;
    private static final int TIME_SLOT_PER_DAY = 8;
    private static final int TOTAL_EMPLOYEES = 6;

    private static int[][] chromosomePool = new int[POPULATION_SIZE][CHROMOSOME_LENGTH];
    private static int[][] bestChromosome;

    private static int fitnessScore = 0;
    private static int bestFitnessScore = 0;

    private static void initializeChromosomes() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < CHROMOSOME_LENGTH; j++) {
                chromosomePool[i][j] = (int) (Math.random() * TOTAL_EMPLOYEES);
            }
        }
    }

    private static void calculateFitnessScore(int[][] chromosome) {
        int[] workingHoursPerEmployee = new int[TOTAL_EMPLOYEES];
        int[] workingHoursPerDay = new int[TIME_SLOT_PER_DAY];
        int workingHoursPerWeek = 0;

        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            int employeeId = chromosome[0][i];
            workingHoursPerEmployee[employeeId]++;
            workingHoursPerDay[i % TIME_SLOT_PER_DAY]++;
        }

        for (int i = 0; i < TOTAL_EMPLOYEES; i++) {
            int workingHours = workingHoursPerEmployee[i];
            if (workingHours > 0 && workingHours <= TIME_SLOT_PER_DAY) {
                fitnessScore++;
            }
        }

        for (int i = 0; i < TIME_SLOT_PER_DAY; i++) {
            int workingHours = workingHoursPerDay[i];
            if (workingHours > MAX_WORKING_HOURS_PER_DAY) {
                fitnessScore -= (workingHours - MAX_WORKING_HOURS_PER_DAY);
            }
        }

        for (int i = 0; i < CHROMOSOME_LENGTH; i += TIME_SLOT_PER_DAY) {
            int workingHours = 0;
            for (int j = 0; j < TIME_SLOT_PER_DAY; j++) {
                int employeeId = chromosome[0][i + j];
                workingHours += (employeeId == i / TIME_SLOT_PER_DAY) ? 1 : 0;
            }

            workingHoursPerWeek += workingHours;
        }

        if (workingHoursPerWeek > MAX_WORKING_HOURS_PER_WEEK) {
            fitnessScore -= (workingHoursPerWeek - MAX_WORKING_HOURS_PER_WEEK);
        }
    }

    private static void evaluate() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            calculateFitnessScore(new int[][] { chromosomePool[i] });
        }

        if (fitnessScore > bestFitnessScore) {
            bestFitnessScore = fitnessScore;
            bestChromosome = new int[][] { chromosomePool[0] };
        }
    }

    private static void crossover() {
        int[][] newChromosomePool = new int[POPULATION_SIZE][CHROMOSOME_LENGTH];
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            indexes.add(i);
        }
        Collections.shuffle(indexes);

        for (int i = 0; i < POPULATION_SIZE / 2; i++) {
            int index1 = indexes.get(i * 2);
            int index2 = indexes.get(i * 2 + 1);

            int pivot = (int) (Math.random() * CHROMOSOME_LENGTH);
            int[] child1 = new int[CHROMOSOME_LENGTH];
            int[] child2 = new int[CHROMOSOME_LENGTH];

            for (int j = 0; j < CHROMOSOME_LENGTH; j++) {
                child1[j] = (j < pivot) ? chromosomePool[index1][j] : chromosomePool[index2][j];
                child2[j] = (j < pivot) ? chromosomePool[index2][j] : chromosomePool[index1][j];
            }

            newChromosomePool[i * 2] = child1;
            newChromosomePool[i * 2 + 1] = child2;
        }

        chromosomePool = newChromosomePool;

    }

    private static void mutate() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < CHROMOSOME_LENGTH; j++) {
                if (Math.random() < 0.1) {
                    chromosomePool[i][j] = (int) (Math.random() * TOTAL_EMPLOYEES);
                }
            }
        }
    }

    private static void printResult() {
        System.out.println("Best solution found:");
        for (int i = 0; i < CHROMOSOME_LENGTH; i += TIME_SLOT_PER_DAY) {
            System.out.print("Day " + (i / TIME_SLOT_PER_DAY + 1) + ": ");

            for (int j = 0; j < TIME_SLOT_PER_DAY; j++) {
                System.out.print(bestChromosome[0][i + j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        initializeChromosomes();

        for (int i = 0; i < 500; i++) {
            fitnessScore = 0;

            evaluate();

            crossover();

            mutate();
        }

        printResult();
    }
}

