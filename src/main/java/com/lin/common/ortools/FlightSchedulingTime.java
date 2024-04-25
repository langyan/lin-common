package com.lin.common.ortools;

import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.IntVar;
import com.google.ortools.sat.LinearExpr;
import com.google.ortools.sat.Literal;

public class FlightSchedulingTime {
    public static void main(String[] args) {

        Loader.loadNativeLibraries();
        // 创建 Solver
        CpModel model = new CpModel();

        // 定义航班数量和飞机数量
        int numFlights = 10;
        int numAircrafts = 5;

        // 创建变量
        Literal[][] assignments = new Literal[numFlights][numAircrafts];
        IntVar[][] startTimes = new IntVar[numFlights][numAircrafts];
        IntVar[][] endTimes = new IntVar[numFlights][numAircrafts];

        // 初始化变量
        for (int i = 0; i < numFlights; i++) {
            for (int j = 0; j < numAircrafts; j++) {
                assignments[i][j] = model.newBoolVar("assignment_" + i + "_" + j);
                startTimes[i][j] = model.newIntVar(0, 10000, "start_" + i + "_" + j);
                endTimes[i][j] = model.newIntVar(0, 10000, "end_" + i + "_" + j);
            }
        }

        // 添加约束条件
        // 确保每个航班只能分配到一个飞机
        for (int i = 0; i < numFlights; i++) {
            model.addEquality(LinearExpr.sum(assignments[i]), 1);
        }

        // 确保每架飞机在任何给定时刻只能执行一个航班
        for (int j = 0; j < numAircrafts; j++) {
            model.addLessOrEqual(LinearExpr.sum(assignments[j]), 1);
        }

        // 确保同一架飞机执飞的航班时间不重叠
        for (int j = 0; j < numAircrafts; j++) {
            for (int i = 0; i < numFlights - 1; i++) {
                IntVar flight1End = endTimes[i][j];
                IntVar flight2Start = startTimes[i + 1][j];
                model.addLessOrEqual(flight1End, flight2Start).onlyEnforceIf(assignments[i][j]);
            }
        }

        // 添加其他约束条件，如起飞时间、降落时间等

        // 创建 Solver 并解决问题
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        // 打印结果
        if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
            // 输出航班编排方案
            for (int i = 0; i < numFlights; i++) {
                for (int j = 0; j < numAircrafts; j++) {
                    if (solver.booleanValue(assignments[i][j])) {
                        long startTime = solver.value(startTimes[i][j]);
                        long endTime = solver.value(endTimes[i][j]);
                        System.out.println("Flight " + i + " assigned to Aircraft " + j + " (Start Time: " + startTime
                            + ", End Time: " + endTime + ")");
                    }
                }
            }
            // 输出其他结果
        } else {
            System.out.println("No solution found.");
        }
    }
}
