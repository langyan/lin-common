package com.lin.common.ortools;

import com.google.ortools.sat.BoolVar;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.LinearExpr;

public class FlightAircraftScheduling {
    public static void main(String[] args) {
        // 定义航班和飞机数量
        int numFlights = 10;
        int numAircrafts = 5;

        // 创建 Solver
        CpModel model = new CpModel();

        // 创建变量
        BoolVar[][] assignments = new BoolVar[numFlights][numAircrafts];
        for (int i = 0; i < numFlights; i++) {
            for (int j = 0; j < numAircrafts; j++) {
                assignments[i][j] = model.newBoolVar("assignment_" + i + "_" + j);
            }
        }

        // 添加约束条件
        for (int i = 0; i < numFlights; i++) {
            model.addEquality(LinearExpr.sum(assignments[i]), 1);
        }

        for (int j = 0; j < numAircrafts; j++) {
            model.addLessOrEqual(LinearExpr.sum(assignments[j]), 1);
        }

        // 添加其他约束条件

        // 定义目标函数（可选）
        // CpObjective objective = model.newMinimize(...);

        // 创建 Solver 并解决问题
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        // 打印结果
        if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
            // 输出分配结果
            for (int i = 0; i < numFlights; i++) {
                for (int j = 0; j < numAircrafts; j++) {
                    if (solver.booleanValue(assignments[i][j])) {
                        System.out.println("Flight " + i + " assigned to Aircraft " + j);
                    }
                }
            }
            // 输出其他结果
        } else {
            System.out.println("No solution found.");
        }
    }
}
