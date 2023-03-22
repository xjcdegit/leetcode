package leetcode.Scheduling5;/*
 *
 * @Param
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Schedule {
    // 遗传算法的参数
    private static final int POPULATION_SIZE = 100;    // 种群大小
    private static final double CROSSOVER_RATE = 0.9;   // 交叉率
    private static final double MUTATION_RATE = 0.1;    // 变异率
    private static final int MAX_GENERATION = 1000;     // 最大迭代次数

    // 排班的数据
    private static final int NUM_OF_SHIFTS = 3;         // 班次数量
    private static final int NUM_OF_DAYS = 7;           // 排班周期（天）
    private static final int NUM_OF_NURSES = 10;        // 护士数量

    // 班次的数据（名称、需要的人数）
    private static final String[] SHIFT_NAMES = {"早班", "晚班", "夜班"};
    private static final int[] SHIFT_REQUIREMENTS = {3, 3, 2};

    // 遗传算法用到的基本操作
    private Random random = new Random();
    private List<ScheduleChromosome> population = new ArrayList<>();

    /**
     * 构造函数，初始化种群
     */
    public Schedule() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new ScheduleChromosome(NUM_OF_SHIFTS, NUM_OF_DAYS, NUM_OF_NURSES));
        }
    }

    /**
     * 遗传算法的主要步骤
     */
    public void solve() {
        int generation = 0;
        while (generation < MAX_GENERATION) {
            // 评估每个染色体的适应度
            for (ScheduleChromosome chromosome : population) {
                chromosome.calculateFitness(SHIFT_NAMES, SHIFT_REQUIREMENTS);
            }

            // 按照适应度从高到低排序
            Collections.sort(population);
            System.out.println("第 " + generation + " 代，最优解（适应度：" + population.get(0).getFitness() + "）：");
            System.out.println(population.get(0));

            // 选择
            List<ScheduleChromosome> parents = new ArrayList<>();
            for (int i = 0; i < POPULATION_SIZE / 2; i++) {
                parents.add(tournamentSelection());
            }

            // 交叉
            for (int i = 0; i < parents.size() - 1; i += 2) {
                if (random.nextDouble() < CROSSOVER_RATE) {
                    crossover(parents.get(i), parents.get(i + 1));
                }
            }

            // 变异
            for (ScheduleChromosome chromosome : population) {
                if (random.nextDouble() < MUTATION_RATE) {
                    chromosome.mutate();
                }
            }

            generation++;
        }

        System.out.println("完成排班：");
        System.out.println(population.get(0));
    }

    /**
     * 锦标赛选择
     */
    private ScheduleChromosome tournamentSelection() {
        ScheduleChromosome chromosome1 = population.get(random.nextInt(POPULATION_SIZE));
        ScheduleChromosome chromosome2 = population.get(random.nextInt(POPULATION_SIZE));

        if (chromosome1.getFitness() > chromosome2.getFitness()) {
            return chromosome1;
        } else {
            return chromosome2;
        }
    }

    /**
     * 单点交叉
     */
    private void crossover(ScheduleChromosome chromosome1, ScheduleChromosome chromosome2) {
        int crossoverPoint = random.nextInt(chromosome1.getGenes().size());

        List<Integer> temp1 = new ArrayList<>(chromosome1.getGenes().subList(0, crossoverPoint));
        temp1.addAll(chromosome2.getGenes().subList(crossoverPoint, chromosome2.getGenes().size()));
        chromosome1.setGenes(temp1);

        List<Integer> temp2 = new ArrayList<>(chromosome2.getGenes().subList(0, crossoverPoint));
        temp2.addAll(chromosome1.getGenes().subList(crossoverPoint, chromosome1.getGenes().size()));
        chromosome2.setGenes(temp2);
    }

    /**
     * 染色体类
     */
    public class ScheduleChromosome implements Comparable<ScheduleChromosome> {
        private List<Integer> genes = new ArrayList<>();
        private double fitness = 0;

        public ScheduleChromosome(int numOfShifts, int numOfDays, int numOfNurses) {
            for (int i = 0; i < numOfShifts * numOfDays; i++) {
                genes.add(random.nextInt(numOfNurses)); // 每个基因值表示一个护士的编号
            }
        }

        public List<Integer> getGenes() {
            return genes;
        }

        public void setGenes(List<Integer> genes) {
            this.genes = genes;
        }

        public double getFitness() {
            return fitness;
        }

        public void calculateFitness(String[] shiftNames, int[] shiftRequirements) {
            // 计算适应度
            double fitness = 0;
            for (int i = 0; i < NUM_OF_SHIFTS; i++) {
                for (int j = 0; j < NUM_OF_DAYS; j++) {
                    int nursesOnDuty = 0;
                    for (int k = 0; k < genes.size(); k++) {
                        if (genes.get(k) == i && k % NUM_OF_SHIFTS == j) {
                            nursesOnDuty++;
                        }
                    }
                    int diff = Math.abs(nursesOnDuty - shiftRequirements[i]);
                    fitness += (NUM_OF_NURSES - diff) * 10;
                }
            }
            this.fitness = fitness;
        }

        public void mutate() {
            // 突变，随机改变一个基因值
            int index = random.nextInt(genes.size());
            genes.set(index, random.nextInt(NUM_OF_NURSES));
        }

        @Override
        public int compareTo(ScheduleChromosome o) {
            if (fitness > o.fitness) {
                return -1;
            } else if (fitness < o.fitness) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < NUM_OF_SHIFTS; i++) {
                sb.append(String.format("%-10s", SHIFT_NAMES[i]));
            }
            sb.append("\n");
            for (int i = 0; i < NUM_OF_DAYS; i++) {
                for (int j = 0; j < NUM_OF_SHIFTS; j++) {
                    int nursesOnDuty = 0;
                    for (int k = 0; k < genes.size(); k++) {
                        if (genes.get(k) == j && k % NUM_OF_SHIFTS == i) {
                            nursesOnDuty++;
                        }
                    }
                    sb.append(String.format("%-10s", nursesOnDuty));
                }
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
//        new Schedule();
        new Schedule().solve();
    }
}

