package com.lin.common.ortools;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

public class Main {
    static {
        Loader.loadNativeLibraries();
    }

    public static void main(String[] args) {
        // Create the linear solver
        MPSolver solver = MPSolver.createSolver("GLOP");

        // Define the variables
        int numFlights = 2;
        int numPlanes = 2;
        MPVariable[][] x = new MPVariable[numFlights][numPlanes];
        for (int i = 0; i < numFlights; ++i) {
            for (int j = 0; j < numPlanes; ++j) {
                x[i][j] = solver.makeIntVar(0, 1, "x[" + i + "," + j + "]");
            }
        }

        // Define the objective
        MPObjective objective = solver.objective();
        for (int i = 0; i < numFlights; ++i) {
            for (int j = 0; j < numPlanes; ++j) {
                objective.setCoefficient(x[i][j], 1);
            }
        }
        objective.setMinimization();

        // Create the constraints
        for (int i = 0; i < numFlights; ++i) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int j = 0; j < numPlanes; ++j) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }
        for (int j = 0; j < numPlanes; ++j) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int i = 0; i < numFlights; ++i) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }
        solver.solve();

        for (int i = 0; i < numFlights; ++i) {
            for (int j = 0; j < numPlanes; ++j) {
                System.out.println("x[" + i + "," + j + "] = " + x[i][j].solutionValue());
            }
        }
    }
}