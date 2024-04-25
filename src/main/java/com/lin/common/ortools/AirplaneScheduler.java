package com.lin.common.ortools;

public class AirplaneScheduler {
    // static {
    // System.loadLibrary("jniortools");
    // }
    //
    // private static final double[][] COSTS = {
    // // Insert your costs here. This is just an example.
    // {10, 15, 10}, {15, 10, 20}, {5, 8, 7}, {12, 9, 15}, {8, 11, 14}};
    //
    // public static void main(String[] args) throws Exception {
    // int numFlights = COSTS.length;
    // int numPlanes = COSTS[0].length;
    //
    // MPSolver solver = new MPSolver("AirplaneScheduler", MPSolver.CBC_MIXED_INTEGER_PROGRAMMING);
    // ArrayList<ArrayList<IntVar>> x = new ArrayList<>();
    //
    // // Variables
    // for (int i = 0; i < numFlights; ++i) {
    // x.add(new ArrayList<>());
    // for (int j = 0; j < numPlanes; ++j) {
    // x.get(i).add(solver.makeIntVar(0, 1, String.format("x[%d,%d]", i, j)));
    // }
    // }
    //
    // // Constraint: each flight is assigned exactly one plane.
    // for (int i = 0; i < numFlights; ++i) {
    // solver.add(solver.sum(x.get(i).toArray(new IntVar[0])).equal(1));
    // }
    //
    // // TODO: Add other constraints (e.g., ensure plane-to-flight compatibility)
    //
    // // Objective: minimize total cost.
    // IntVar objective = solver.makeIntVar(0, 100000, "objective");
    // solver.add(objective.equals(solver.sumFlattenedArrays(x, COSTS)));
    //
    // // TODO: Add other elements to the objective function if needed.
    //
    // // Solve the problem and print the solution.
    // solver.minimize(objective, 1);
    //
    // if (solver.solve()) {
    // for (int i = 0; i < numFlights; ++i) {
    // for (int j = 0; j < numPlanes; ++j) {
    // System.out.printf("x[%d,%d] = %d\n", i, j, (int)x.get(i).get(j).solutionValue());
    // }
    // }
    // } else {
    // System.out.println("No solution found.");
    // }
    // }
}