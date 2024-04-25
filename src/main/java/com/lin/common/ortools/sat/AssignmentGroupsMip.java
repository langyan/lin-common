package com.lin.common.ortools.sat;

import java.util.stream.IntStream;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

/** MIP example that solves an assignment problem. */
public class AssignmentGroupsMip {
    public static void main(String[] args) {
        Loader.loadNativeLibraries();
        // Data
        double[][] costs = {{90, 76, 75, 70, 50, 74}, {35, 85, 55, 65, 48, 101}, {125, 95, 90, 105, 59, 120},
            {45, 110, 95, 115, 104, 83}, {60, 105, 80, 75, 59, 62}, {45, 65, 110, 95, 47, 31},
            {38, 51, 107, 41, 69, 99}, {47, 85, 57, 71, 92, 77}, {39, 63, 97, 49, 118, 56}, {47, 101, 71, 60, 88, 109},
            {17, 39, 103, 64, 61, 92}, {101, 45, 83, 59, 92, 27},};
        int numWorkers = costs.length;
        int numTasks = costs[0].length;

        final int[] allWorkers = IntStream.range(0, numWorkers).toArray();
        final int[] allTasks = IntStream.range(0, numTasks).toArray();

        // Allowed groups of workers:
        int[][] group1 = {
            // group of worker 0-3
            {2, 3}, {1, 3}, {1, 2}, {0, 1}, {0, 2},};

        int[][] group2 = {
            // group of worker 4-7
            {6, 7}, {5, 7}, {5, 6}, {4, 5}, {4, 7},};

        int[][] group3 = {
            // group of worker 8-11
            {10, 11}, {9, 11}, {9, 10}, {8, 10}, {8, 11},};

        // Solver
        // Create the linear solver with the SCIP backend.
        MPSolver solver = MPSolver.createSolver("SCIP");
        if (solver == null) {
            System.out.println("Could not create solver SCIP");
            return;
        }

        // Variables
        // x[i][j] is an array of 0-1 variables, which will be 1
        // if worker i is assigned to task j.
        MPVariable[][] x = new MPVariable[numWorkers][numTasks];
        for (int worker : allWorkers) {
            for (int task : allTasks) {
                x[worker][task] = solver.makeBoolVar("x[" + worker + "," + task + "]");
            }
        }

        // Constraints
        // Each worker is assigned to at most one task.
        for (int worker : allWorkers) {
            MPConstraint constraint = solver.makeConstraint(0, 1, "");
            for (int task : allTasks) {
                constraint.setCoefficient(x[worker][task], 1);
            }
        }
        // Each task is assigned to exactly one worker.
        for (int task : allTasks) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int worker : allWorkers) {
                constraint.setCoefficient(x[worker][task], 1);
            }
        }

        // Create variables for each worker, indicating whether they work on some task.
        MPVariable[] work = new MPVariable[numWorkers];
        for (int worker : allWorkers) {
            work[worker] = solver.makeBoolVar("work[" + worker + "]");
        }

        for (int worker : allWorkers) {
            // MPVariable[] vars = new MPVariable[numTasks];
            MPConstraint constraint = solver.makeConstraint(0, 0, "");
            for (int task : allTasks) {
                // vars[task] = x[worker][task];
                constraint.setCoefficient(x[worker][task], 1);
            }
            // solver.addEquality(work[worker], LinearExpr.sum(vars));
            constraint.setCoefficient(work[worker], -1);
        }

        // Group1
        MPConstraint constraintG1 = solver.makeConstraint(1, 1, "");
        for (int i = 0; i < group1.length; ++i) {
            // a*b can be transformed into 0 <= a + b - 2*p <= 1 with p in [0,1]
            // p is True if a AND b, False otherwise
            MPConstraint constraint = solver.makeConstraint(0, 1, "");
            constraint.setCoefficient(work[group1[i][0]], 1);
            constraint.setCoefficient(work[group1[i][1]], 1);
            MPVariable p = solver.makeBoolVar("g1_p" + i);
            constraint.setCoefficient(p, -2);

            constraintG1.setCoefficient(p, 1);
        }
        // Group2
        MPConstraint constraintG2 = solver.makeConstraint(1, 1, "");
        for (int i = 0; i < group2.length; ++i) {
            // a*b can be transformed into 0 <= a + b - 2*p <= 1 with p in [0,1]
            // p is True if a AND b, False otherwise
            MPConstraint constraint = solver.makeConstraint(0, 1, "");
            constraint.setCoefficient(work[group2[i][0]], 1);
            constraint.setCoefficient(work[group2[i][1]], 1);
            MPVariable p = solver.makeBoolVar("g2_p" + i);
            constraint.setCoefficient(p, -2);

            constraintG2.setCoefficient(p, 1);
        }
        // Group3
        MPConstraint constraintG3 = solver.makeConstraint(1, 1, "");
        for (int i = 0; i < group3.length; ++i) {
            // a*b can be transformed into 0 <= a + b - 2*p <= 1 with p in [0,1]
            // p is True if a AND b, False otherwise
            MPConstraint constraint = solver.makeConstraint(0, 1, "");
            constraint.setCoefficient(work[group3[i][0]], 1);
            constraint.setCoefficient(work[group3[i][1]], 1);
            MPVariable p = solver.makeBoolVar("g3_p" + i);
            constraint.setCoefficient(p, -2);

            constraintG3.setCoefficient(p, 1);
        }

        // Objective
        MPObjective objective = solver.objective();
        for (int worker : allWorkers) {
            for (int task : allTasks) {
                objective.setCoefficient(x[worker][task], costs[worker][task]);
            }
        }
        objective.setMinimization();

        // Solve
        MPSolver.ResultStatus resultStatus = solver.solve();

        // Print solution.
        // Check that the problem has a feasible solution.
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE) {
            System.out.println("Total cost: " + objective.value() + "\n");
            for (int worker : allWorkers) {
                for (int task : allTasks) {
                    // Test if x[i][j] is 0 or 1 (with tolerance for floating point
                    // arithmetic).
                    if (x[worker][task].solutionValue() > 0.5) {
                        System.out.println(
                            "Worker " + worker + " assigned to task " + task + ".  Cost: " + costs[worker][task]);
                    }
                }
            }
        } else {
            System.err.println("No solution found.");
        }
    }

    private AssignmentGroupsMip() {}
}