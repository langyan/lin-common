package com.lin.common.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class KnapsackSimulatedAnnealing {

    static double initialTemperature = 1000.0;
    static double coolingRate = 0.003;
    static int iterationsPerTemperature = 100;

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 2, 5));
        items.add(new Item("Item2", 3, 8));
        items.add(new Item("Item3", 5, 13));
        items.add(new Item("Item4", 7, 21));
        items.add(new Item("Item5", 1, 3));

        int knapsackCapacity = 10;

        List<Item> bestCombination = simulatedAnnealing(items, knapsackCapacity);

        System.out.println("Best Combination:");
        for (Item item : bestCombination) {
            System.out.println("Name: " + item.name + ", Weight: " + item.weight + ", Value: " + item.value);
        }
        System.out.println("Total Weight: " + getTotalWeight(bestCombination) + ", Total Value: " + getTotalValue(bestCombination));
    }

    private static List<Item> simulatedAnnealing(List<Item> items, int knapsackCapacity) {
        List<Item> currentSolution = generateRandomSolution(items);
        List<Item> bestSolution = new ArrayList<>(currentSolution);

        double temperature = initialTemperature;

        while (temperature > 1.0) {
            for (int i = 0; i < iterationsPerTemperature; i++) {
                List<Item> newSolution = generateNeighborSolution(currentSolution);

                double currentEnergy = calculateEnergy(currentSolution, knapsackCapacity);
                double newEnergy = calculateEnergy(newSolution, knapsackCapacity);

                if (acceptanceProbability(currentEnergy, newEnergy, temperature) > Math.random()) {
                    currentSolution = new ArrayList<>(newSolution);
                }

                if (getTotalValue(currentSolution) > getTotalValue(bestSolution)) {
                    bestSolution = new ArrayList<>(currentSolution);
                }
            }

            temperature *= 1.0 - coolingRate;
        }

        return bestSolution;
    }

    private static List<Item> generateRandomSolution(List<Item> items) {
        List<Item> solution = new ArrayList<>();
        Random random = new Random();

        for (Item item : items) {
            if (random.nextBoolean()) {
                solution.add(item);
            }
        }

        return solution;
    }

    private static List<Item> generateNeighborSolution(List<Item> currentSolution) {
        List<Item> newSolution = new ArrayList<>(currentSolution);
        Random random = new Random();

        if (!newSolution.isEmpty()) {
            int index = random.nextInt(newSolution.size());
            Item item = newSolution.get(index);

            // Flip the selected item (include or exclude)
            if (random.nextBoolean()) {
                newSolution.remove(item);
            } else {
                newSolution.add(item);
            }
        }

        return newSolution;
    }

    private static double acceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        if (newEnergy > currentEnergy) {
            return 1.0;
        }

        return Math.exp((newEnergy - currentEnergy) / temperature);
    }

    private static double calculateEnergy(List<Item> solution, int knapsackCapacity) {
        int totalWeight = getTotalWeight(solution);
        int penalty = Math.max(0, totalWeight - knapsackCapacity);

        return getTotalValue(solution) - penalty;
    }

    private static int getTotalWeight(List<Item> solution) {
        int totalWeight = 0;
        for (Item item : solution) {
            totalWeight += item.weight;
        }
        return totalWeight;
    }

    private static int getTotalValue(List<Item> solution) {
        int totalValue = 0;
        for (Item item : solution) {
            totalValue += item.value;
        }
        return totalValue;
    }
}
