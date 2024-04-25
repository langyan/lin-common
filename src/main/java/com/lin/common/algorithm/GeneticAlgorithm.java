package com.lin.common.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class IndividualItem {
    String chromosome;
    int fitness;

    public IndividualItem(String chromosome) {
        this.chromosome = chromosome;
        calculateFitness();
    }

    public void calculateFitness() {
        // 在这个简单的例子中，假设适应度就是二进制字符串的十进制表示
        fitness = Integer.parseInt(chromosome, 2);
    }
    public String toString() {
        return "{chromosome="+chromosome+",fitness="+fitness+ "}";
        
    }
}

public class GeneticAlgorithm {

    static int populationSize = 10;
    static double crossoverRate = 0.7;
    static double mutationRate = 0.01;
    static int chromosomeLength = 10;
    static int generations = 50;
 
    public static void main(String[] args) {
        List<IndividualItem> population = initializePopulation();
        for (int generation = 0; generation < generations; generation++) {
            List<IndividualItem> newPopulation = new ArrayList<>();

            while (newPopulation.size() < populationSize) {
                IndividualItem parent1 = selectParent(population);
                IndividualItem parent2 = selectParent(population);

                IndividualItem child = crossover(parent1, parent2);
                mutate(child);

                newPopulation.add(child);
            }

            population = newPopulation;

            // 打印每一代中适应度最高的个体
            IndividualItem bestIndividual = getBestIndividual(population);
            System.out.println("Generation " + generation + ": Best Fitness = " + bestIndividual.fitness+" chromosome ="+bestIndividual.chromosome);
        }
        System.out.println(population);
    }

    private static List<IndividualItem> initializePopulation() {
        List<IndividualItem> population = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < populationSize; i++) {
            StringBuilder chromosome = new StringBuilder();
            for (int j = 0; j < chromosomeLength; j++) {
                chromosome.append(random.nextInt(2));
            }
            population.add(new IndividualItem(chromosome.toString()));
        }

        return population;
    }

    private static IndividualItem selectParent(List<IndividualItem> population) {
        Random random = new Random();
        int tournamentSize = 2;

        List<IndividualItem> tournament = new ArrayList<>();
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(random.nextInt(population.size())));
        }

        return getBestIndividual(tournament);
    }

    private static IndividualItem crossover(IndividualItem parent1, IndividualItem parent2) {
        Random random = new Random();
        int crossoverPoint = random.nextInt(chromosomeLength);

        String childChromosome = parent1.chromosome.substring(0, crossoverPoint) +
                parent2.chromosome.substring(crossoverPoint);
        return new IndividualItem(childChromosome);
    }

    private static void mutate(IndividualItem individual) {
        Random random = new Random();

        StringBuilder mutatedChromosome = new StringBuilder(individual.chromosome);
        for (int i = 0; i < chromosomeLength; i++) {
            if (random.nextDouble() < mutationRate) {
                mutatedChromosome.setCharAt(i, (mutatedChromosome.charAt(i) == '0') ? '1' : '0');
            }
        }

        individual.chromosome = mutatedChromosome.toString();
        individual.calculateFitness();
    }

    private static IndividualItem getBestIndividual(List<IndividualItem> population) {
        IndividualItem bestIndividual = population.get(0);

        for (IndividualItem individual : population) {
            if (individual.fitness > bestIndividual.fitness) {
                bestIndividual = individual;
            }
        }

        return bestIndividual;
    }
}
