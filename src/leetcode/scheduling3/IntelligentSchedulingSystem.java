package leetcode.scheduling3;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IntelligentSchedulingSystem {
    private static Random random = new Random();
    // 定义常量
    private static final int POPULATION_SIZE = 100; // 种群大小
    private static final double MUTATION_RATE = 0.01; // 变异概率
    private static final int TOURNAMENT_SIZE = 5; // 锦标赛大小
    private static final int NUM_ELITE_SCHEDULES = 1; // 淘汰数量
    private static final int NUM_WORKING_HOURS = 40; // 每周工作小时数
    private static final int NUM_SHIFTS = 7; // 班次数
    private static final int NUM_EMPLOYEES = 2; // 员工数
    private static final int MAX_HOURS_PER_SHIFT = 4; // 每个班次的最大工作小时数
    private static final int MIN_HOURS_PER_SHIFT = 2; // 每个班次的最小工作小时数
    private static final int MAX_CONSECUTIVE_HOURS_PER_EMPLOYEE = 4; // 员工最长连续工作小时数

    // 定义班次类
    private static class Shift {
        int shiftID;
        int requiredEmployees;

        Shift(int shiftID, int requiredEmployees) {
            this.shiftID = shiftID;
            this.requiredEmployees = requiredEmployees;
        }
    }

    // 初始化班次列表
    private static List<Shift> initializeShifts() {
        List<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift(1, 2));
        shifts.add(new Shift(2, 2));
        shifts.add(new Shift(3, 1));
        shifts.add(new Shift(4, 1));
        shifts.add(new Shift(5, 1));
        shifts.add(new Shift(6, 1));
        shifts.add(new Shift(7, 1));
        return shifts;
    }

    // 定义班次表类
    private static class Schedule {
        List<Integer> employeeAssignments;
        int fitness = 0;

        Schedule() {
            employeeAssignments = new ArrayList<>(Collections.nCopies(NUM_SHIFTS, 0));
            Random random = new Random();
            for (int i = 0; i < NUM_SHIFTS; i++) {
                if (random.nextBoolean()) {
                    employeeAssignments.set(i, random.nextInt(NUM_EMPLOYEES) + 1);
                }
            }
        }

        // 计算适应度函数
        void calculateFitness() {
            int[] hoursWorked = new int[NUM_EMPLOYEES];
            int[] consecutiveHoursWorked = new int[NUM_EMPLOYEES];
            int totalHoursWorked = 0;
            int totalShiftsAssigned = 0;
            int consecutiveShiftsWorked = 0;

            for (int i = 0; i < NUM_SHIFTS; i++) {
                int employeeID = employeeAssignments.get(i);
                Shift shift = initializeShifts().get(i);

                if (employeeID != 0) {
                    hoursWorked[employeeID - 1] += shift.shiftID;
                    totalHoursWorked += shift.shiftID;
                    totalShiftsAssigned++;

                    if (i > 0 && employeeAssignments.get(i - 1) == employeeID) {
                        consecutiveHoursWorked[employeeID - 1] += shift.shiftID;
                        consecutiveShiftsWorked++;
                    } else {
                        consecutiveHoursWorked[employeeID - 1] = shift.shiftID;
                        consecutiveShiftsWorked = 1;
                    }

                    if (hoursWorked[employeeID - 1] > NUM_WORKING_HOURS) {
                        fitness -= (hoursWorked[employeeID - 1] - NUM_WORKING_HOURS) * 2;
                    }

                    if (consecutiveHoursWorked[employeeID - 1] > MAX_CONSECUTIVE_HOURS_PER_EMPLOYEE) {
                        fitness -= (consecutiveHoursWorked[employeeID - 1] / MAX_CONSECUTIVE_HOURS_PER_EMPLOYEE) * 2;
                    }
                }
            }

            int remainingHours = NUM_WORKING_HOURS - totalHoursWorked;
            if (remainingHours > 0) {
                fitness += remainingHours * 2;
            }

            if (totalShiftsAssigned < NUM_SHIFTS) {
                fitness -= (NUM_SHIFTS - totalShiftsAssigned) * 2;
            }

            if (consecutiveShiftsWorked > 1) {
                fitness -= (consecutiveShiftsWorked / 2) * 2;
            }
        }

        // 输出排班结果
        void printSchedule() {
            Shift shift = null;
            for (int i = 0; i < NUM_SHIFTS; i++) {
                shift = initializeShifts().get(i);
                System.out.println("Shift " + shift.shiftID + ": " + (employeeAssignments.get(i) > 0 ? "Employee " + employeeAssignments.get(i) : "Unassigned"));
            }
            System.out.println();
        }

        // 获取班次表的字符串表示
        @Override
        public String toString() {
            return employeeAssignments.toString();
        }
    }

    // 定义种群类
    private static class Population {
        List<Schedule> schedules;

        Population(int size) {
            schedules = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                schedules.add(new Schedule());
            }
        }

        // 对种群中所有班次表计算适应度函数
        void calculateFitness() {
            for (Schedule schedule : schedules) {
                schedule.calculateFitness();
            }
            Collections.sort(schedules, (schedule1, schedule2) -> schedule2.fitness - schedule1.fitness);
        }
    }

    // 遗传算法
    public static void geneticAlgorithm() {
        Random random = new Random();
        Population population = new Population(POPULATION_SIZE);

        population.calculateFitness();

        int generationCounter = 0;

        while (population.schedules.get(0).fitness > 0 && generationCounter < 1000) {
            Population newPopulation = new Population(0);

            for (int i = 0; i < NUM_ELITE_SCHEDULES; i++) {
                newPopulation.schedules.add(population.schedules.get(i));
            }

            while (newPopulation.schedules.size() < POPULATION_SIZE) {
                Schedule parent1 = selectTournamentPopulation(population);
                Schedule parent2 = selectTournamentPopulation(population);
                Schedule child = crossover(parent1, parent2);
                mutate(child);
                newPopulation.schedules.add(child);
            }

            population = newPopulation;
            population.calculateFitness();

            System.out.println("Generation: " + generationCounter + " Fitness: " + population.schedules.get(0).fitness);
            generationCounter++;
        }

        if (population.schedules.get(0).fitness == 0) {
            System.out.println("Solution found in generation " + generationCounter + ":");
            population.schedules.get(0).printSchedule();
        } else {
            System.out.println("Solution found in generation " + generationCounter + ":");
            population.schedules.get(0).printSchedule();
            //System.out.println("No solution found after " + generationCounter + " generations");
        }
    }

    // 锦标赛选择函数
    private static Schedule selectTournamentPopulation(Population population) {
        Population tournament = new Population(0);
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            tournament.schedules.add(population.schedules.get(random.nextInt(POPULATION_SIZE)));
        }
        tournament.calculateFitness();
        return tournament.schedules.get(0);
    }

    // 遗传算法交叉函数
    private static Schedule crossover(Schedule parent1, Schedule parent2) {
        Schedule child = new Schedule();
        Random random = new Random();
        for (int i = 0; i < NUM_SHIFTS; i++) {
            if (random.nextDouble() > 0.5) {
                child.employeeAssignments.set(i, parent1.employeeAssignments.get(i));
            } else {
                child.employeeAssignments.set(i, parent2.employeeAssignments.get(i));
            }
        }
        return child;
    }

    // 遗传算法变异函数
    private static void mutate(Schedule schedule) {
        Random random = new Random();
        for (int i = 0; i < NUM_SHIFTS; i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                schedule.employeeAssignments.set(i, random.nextInt(NUM_EMPLOYEES) + 1);
            }
        }
    }

    // 主函数
    public static void main(String[] args) {
        geneticAlgorithm();
    }
}
