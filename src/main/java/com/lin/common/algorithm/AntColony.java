/**
 * 
 */
package com.lin.common.algorithm;

/**
 * @author michaellin
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AntColony {

    private final int numberOfCities;
    private final double[][] distanceMatrix;
    private final double[][] pheromoneMatrix;
    private final int numberOfAnts;
    private final double alpha;
    private final double beta;
    private final double evaporationRate;
    private int[] bestTour;
    private double bestTourLength;

    public AntColony(int numberOfCities, double[][] distanceMatrix, int numberOfAnts,
                     double alpha, double beta, double evaporationRate) {
        this.numberOfCities = numberOfCities;
        this.distanceMatrix = distanceMatrix;
        this.pheromoneMatrix = new double[numberOfCities][numberOfCities];
        this.numberOfAnts = numberOfAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;

        // Initialize pheromone matrix
        for (int i = 0; i < numberOfCities; i++) {
            Arrays.fill(pheromoneMatrix[i], 1.0);
        }

        this.bestTour = new int[numberOfCities + 1];
        this.bestTourLength = Double.POSITIVE_INFINITY;
    }

    public void runAntColony(int numberOfIterations) {
        for (int iteration = 0; iteration < numberOfIterations; iteration++) {
            List<Ant> ants = createAnts();
            distributeAnts(ants);
            updatePheromones(ants);
            updateBestTour(ants);
            evaporatePheromones();
        }
    }

    private List<Ant> createAnts() {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < numberOfAnts; i++) {
            ants.add(new Ant(numberOfCities));
        }
        return ants;
    }

    private void distributeAnts(List<Ant> ants) {
        for (Ant ant : ants) {
            ant.clear();
            ant.visitCity(-1, new Random().nextInt(numberOfCities)); // Choose a random starting city
        }

        for (int step = 0; step < numberOfCities - 1; step++) {
            for (Ant ant : ants) {
                int selectedCity = selectNextCity(ant);
                ant.visitCity(step, selectedCity);
            }
        }

        // Complete the tours by returning to the starting city
        for (Ant ant : ants) {
            ant.visitCity(numberOfCities - 1, ant.getTour().get(0));
        }
    }

    private int selectNextCity(Ant ant) {
        int currentCity = ant.getCurrentCity();
        List<Integer> availableCities = ant.getAvailableCities();

        double[] probabilities = new double[numberOfCities];
        double totalProbability = 0.0;

        for (int city : availableCities) {
            double pheromone = Math.pow(pheromoneMatrix[currentCity][city], alpha);
            double distance = Math.pow(1.0 / distanceMatrix[currentCity][city], beta);

            probabilities[city] = pheromone * distance;
            totalProbability += probabilities[city];
        }

        double randomValue = Math.random() * totalProbability;
        double cumulativeProbability = 0.0;

        for (int city : availableCities) {
            cumulativeProbability += probabilities[city];
            if (cumulativeProbability >= randomValue) {
                return city;
            }
        }

        // This should not happen, but just in case
        return availableCities.get(new Random().nextInt(availableCities.size()));
    }

    private void updatePheromones(List<Ant> ants) {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate);
            }
        }

        for (Ant ant : ants) {
            double pheromoneDeposit = 1.0 / ant.getTourLength();
            for (int i = 0; i < numberOfCities - 1; i++) {
                int city1 = ant.getTour().get(i);
                int city2 = ant.getTour().get(i + 1);
                pheromoneMatrix[city1][city2] += pheromoneDeposit;
                pheromoneMatrix[city2][city1] += pheromoneDeposit;
            }
        }
    }

    private void updateBestTour(List<Ant> ants) {
        for (Ant ant : ants) {
            if (ant.getTourLength() < bestTourLength) {
                bestTourLength = ant.getTourLength();
                System.arraycopy(ant.getTour().toArray(), 0, bestTour, 0, numberOfCities + 1);
            }
        }
    }

    private void evaporatePheromones() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate);
            }
        }
    }

    public int[] getBestTour() {
        return bestTour;
    }

    public double getBestTourLength() {
        return bestTourLength;
    }

    public static void main(String[] args) {
        int numberOfCities = 5;
        double[][] distanceMatrix = {
                {0, 2, 3, 4, 5},
                {2, 0, 6, 7, 8},
                {3, 6, 0, 9, 10},
                {4, 7, 9, 0, 11},
                {5, 8, 10, 11, 0}
        };

        int numberOfAnts = 5;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;
        int numberOfIterations = 100;

        AntColony antColony = new AntColony(numberOfCities, distanceMatrix, numberOfAnts, alpha, beta, evaporationRate);
        antColony.runAntColony(numberOfIterations);

        int[] bestTour = antColony.getBestTour();
        double bestTourLength = antColony.getBestTourLength();

        System.out.println("Best Tour: " + Arrays.toString(bestTour));
        System.out.println("Best Tour Length: " + bestTourLength);
    }
}

class Ant {
    private int numberOfCities;
    private List<Integer> tour;
    private boolean[] visited;
    private double tourLength;

    public Ant(int numberOfCities) {
        this.numberOfCities = numberOfCities;
        this.tour = new ArrayList<>();
        this.visited = new boolean[numberOfCities];
        this.tourLength = 0.0;
    }

    public void visitCity(int step, int city) {
        tour.add(city);
        visited[city] = true;

        if (step > 0) {
            int previousCity = tour.get(step - 1);
            tourLength += Math.sqrt(Math.pow(1.0, 2) + Math.pow(1.0, 2));
        }
    }

    public void clear() {
        tour.clear();
        Arrays.fill(visited, false);
        tourLength = 0.0;
    }

    public List<Integer> getTour() {
        return tour;
    }

    public int getCurrentCity() {
        return tour.get(tour.size() - 1);
    }

    public List<Integer> getAvailableCities() {
        List<Integer> availableCities = new ArrayList<>();
        for (int i = 0; i < numberOfCities; i++) {
            if (!visited[i]) {
                availableCities.add(i);
            }
        }
        return availableCities;
    }

    public double getTourLength() {
        return tourLength;
    }
}

