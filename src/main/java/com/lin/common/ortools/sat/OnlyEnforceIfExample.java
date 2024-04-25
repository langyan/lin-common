package com.lin.common.ortools.sat;

import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.Literal;

public class OnlyEnforceIfExample {
    public static void main(String[] args) {

        Loader.loadNativeLibraries();
        // 创建 Solver
        CpModel model = new CpModel();

        // 创建变量
        Literal x = model.newBoolVar("x");
        Literal y = model.newBoolVar("y");

        // 添加条件约束
        model.addEquality(x, y).onlyEnforceIf(y);

        // 创建 Solver 并解决问题
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        // 打印结果
        if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
            System.out.println("Solution found:");
            System.out.println("x = " + solver.value(x));
            System.out.println("y = " + solver.value(y));
        } else {
            System.out.println("No solution found.");
        }
    }
}
