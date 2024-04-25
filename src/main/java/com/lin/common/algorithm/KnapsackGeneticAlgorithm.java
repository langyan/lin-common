package com.lin.common.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



class Individual {
    List<Item> genes;
    int fitness;

    public Individual(List<Item> genes) {
        this.genes = genes;
        this.fitness = calculateFitness();
    }

    private int calculateFitness() {
        int totalWeight = 0;
        int totalValue = 0;

        for (Item gene : genes) {
            totalWeight += gene.weight;
            totalValue += gene.value;
        }

        // 惩罚超过背包容量的个体
        int penalty = Math.max(0, totalWeight - KnapsackGeneticAlgorithm.knapsackCapacity);

        return totalValue - penalty;
    }
}

public class KnapsackGeneticAlgorithm {

    static int knapsackCapacity = 10;
    static int populationSize = 50;
    static int generations = 1000;
    static double crossoverRate = 0.7;
    static double mutationRate = 0.01;

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 2, 5));
        items.add(new Item("Item2", 3, 8));
        items.add(new Item("Item3", 5, 13));
        items.add(new Item("Item4", 7, 21));
        items.add(new Item("Item5", 1, 3));

        List<Individual> population = initializePopulation(items);

        for (int generation = 0; generation < generations; generation++) {
            List<Individual> newPopulation = new ArrayList<>();

            while (newPopulation.size() < populationSize) {
                Individual parent1 = selectParent(population);
                Individual parent2 = selectParent(population);

                Individual child = crossover(parent1, parent2);
                mutate(child);

                newPopulation.add(child);
            }

            population = newPopulation;
        }

        Individual bestIndividual = getBestIndividual(population);

        System.out.println("Best Combination:");
        for (Item gene : bestIndividual.genes) {
            System.out.println("Name: " + gene.name + ", Weight: " + gene.weight + ", Value: " + gene.value);
        }
        System.out.println("Total Weight: " + getTotalWeight(bestIndividual) + ", Total Value: " + bestIndividual.fitness);
    }

    private static List<Individual> initializePopulation(List<Item> items) {
        List<Individual> population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            List<Item> genes = generateRandomGenes(items);
            population.add(new Individual(genes));
        }

        return population;
    }

    private static List<Item> generateRandomGenes(List<Item> items) {
        List<Item> genes = new ArrayList<>();
        Random random = new Random();

        for (Item item : items) {
            if (random.nextBoolean()) {
                genes.add(item);
            }
        }

        return genes;
    }

    private static Individual selectParent(List<Individual> population) {
        Random random = new Random();
        int tournamentSize = 5;

        List<Individual> tournament = new ArrayList<>();
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(random.nextInt(population.size())));
        }

        return getBestIndividual(tournament);
    }

    private static Individual getBestIndividual(List<Individual> population) {
        Individual bestIndividual = population.get(0);

        for (Individual individual : population) {
            if (individual.fitness > bestIndividual.fitness) {
                bestIndividual = individual;
            }
        }

        return bestIndividual;
    }

    private static Individual crossover(Individual parent1, Individual parent2) {
        Random random = new Random();
        int crossoverPoint = random.nextInt(parent1.genes.size());

        List<Item> childGenes = new ArrayList<>(parent1.genes.subList(0, crossoverPoint));
        
        for (Item gene : parent2.genes) {
            if (!childGenes.contains(gene)) {
                childGenes.add(gene);
            }
        }

        return new Individual(childGenes);
    }

    private static void mutate(Individual individual) {
        Random random = new Random();

        for (int i = 0; i < individual.genes.size(); i++) {
            if (random.nextDouble() < mutationRate) {
                Item gene = individual.genes.get(i);
                if (random.nextBoolean()) {
                    individual.genes.set(i, gene); // Duplicate the gene
                } else {
                    individual.genes.remove(i); // Remove the gene
                }
            }
        }
    }

    private static int getTotalWeight(Individual individual) {
        int totalWeight = 0;

        for (Item gene : individual.genes) {
            totalWeight += gene.weight;
        }

        return totalWeight;
    }
}
