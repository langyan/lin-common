package com.lin.common.ortools.sat;

import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverSolutionCallback;
import com.google.ortools.sat.IntVar;
import com.google.ortools.sat.LinearExpr;

/** Cryptarithmetic puzzle. */
public final class CpIsFunSat {
    static class VarArraySolutionPrinter extends CpSolverSolutionCallback {
        public VarArraySolutionPrinter(IntVar[] variables) {
            variableArray = variables;
        }

        @Override
        public void onSolutionCallback() {
            for (IntVar v : variableArray) {
                System.out.printf("  %s = %d", v.getName(), value(v));
            }
            System.out.println();
            solutionCount++;
        }

        public int getSolutionCount() {
            return solutionCount;
        }

        private int solutionCount;
        private final IntVar[] variableArray;
    }

    public static void main(String[] args) throws Exception {
        Loader.loadNativeLibraries();
        // Create the model.
        CpModel model = new CpModel();

        int base = 10;
        IntVar c = model.newIntVar(1, base - 1, "C");
        IntVar p = model.newIntVar(0, base - 1, "P");
        IntVar i = model.newIntVar(1, base - 1, "I");
        IntVar s = model.newIntVar(0, base - 1, "S");
        IntVar f = model.newIntVar(1, base - 1, "F");
        IntVar u = model.newIntVar(0, base - 1, "U");
        IntVar n = model.newIntVar(0, base - 1, "N");
        IntVar t = model.newIntVar(1, base - 1, "T");
        IntVar r = model.newIntVar(0, base - 1, "R");
        IntVar e = model.newIntVar(0, base - 1, "E");

        // We need to group variables in a list to use the constraint AllDifferent.
        IntVar[] letters = new IntVar[] {c, p, i, s, f, u, n, t, r, e};

        // Define constraints.
        model.addAllDifferent(letters);

        // CP + IS + FUN = TRUE
        model.addEquality(LinearExpr.weightedSum(new IntVar[] {c, p, i, s, f, u, n, t, r, u, e},
            new long[] {base, 1, base, 1, base * base, base, 1, -base * base * base, -base * base, -base, -1}), 0);

        // Create a solver and solve the model.
        CpSolver solver = new CpSolver();
        VarArraySolutionPrinter cb = new VarArraySolutionPrinter(letters);
        // Tell the solver to enumerate all solutions.
        solver.getParameters().setEnumerateAllSolutions(true);
        // And solve.
        solver.solve(model, cb);

        // Statistics.
        System.out.println("Statistics");
        System.out.println("  - conflicts : " + solver.numConflicts());
        System.out.println("  - branches  : " + solver.numBranches());
        System.out.println("  - wall time : " + solver.wallTime() + " s");
        System.out.println("  - solutions : " + cb.getSolutionCount());
    }

    private CpIsFunSat() {}
}