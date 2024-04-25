package com.lin.common.ortools;

import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.IntVar;

/**
 * 我们先来看一个简单的问题示例，其中有：
 * 
 * x、y 和 z 这三个变量，每个变量可取以下值：0、1 或 2。
 * 
 * 一项限制条件：x != y
 */
/** Minimal CP-SAT example to showcase calling the solver. */
public final class SimpleSatProgram {
    public static void main(String[] args) throws Exception {
        Loader.loadNativeLibraries();
        // Create the model.
        CpModel model = new CpModel();

        // Create the variables.
        int numVals = 3;

        IntVar x = model.newIntVar(0, numVals - 1, "x");
        IntVar y = model.newIntVar(0, numVals - 1, "y");
        IntVar z = model.newIntVar(0, numVals - 1, "z");

        // Create the constraints.
        model.addDifferent(x, y);

        // Create a solver and solve the model.
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
            System.out.println("x = " + solver.value(x));
            System.out.println("y = " + solver.value(y));
            System.out.println("z = " + solver.value(z));
        } else {
            System.out.println("No solution found.");
        }
    }

    private SimpleSatProgram() {}
}