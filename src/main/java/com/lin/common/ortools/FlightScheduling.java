package com.lin.common.ortools;

import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.IntVar;

public class FlightScheduling {

    public static void main(String[] args) {

        Loader.loadNativeLibraries();
        // 航班数量
        int numFlights = 5;
        // 航班起飞时间
        int[] departureTimes = {0, 2, 5, 8, 10};
        // 航班到达时间
        int[] arrivalTimes = {1, 4, 7, 9, 12};
        // 起飞机场
        String[] departureAirports = {"A", "B", "C", "D", "E"};
        // 落地机场
        String[] arrivalAirports = {"B", "C", "D", "E", "F"};
        // 飞机航班分配情况
        int[] planeAssignments = {-1, -1, 1, 1, -1}; // -1表示航班无固定飞机

        // 航班飞机编排问题
        flightScheduling(numFlights, departureTimes, arrivalTimes, departureAirports, arrivalAirports,
            planeAssignments);
    }

    public static void flightScheduling(int numFlights, int[] departureTimes, int[] arrivalTimes,
        String[] departureAirports, String[] arrivalAirports, int[] planeAssignments) {
        // 创建 CP-SAT 模型
        CpModel model = new CpModel();

        // 创建变量：航班起飞时间
        IntVar[] startTimes = new IntVar[numFlights];
        for (int i = 0; i < numFlights; i++) {
            startTimes[i] = model.newIntVar(departureTimes[i], Integer.MAX_VALUE, "start_time_" + i);
        }

        // 添加约束：同一架飞机编排的航班，前序航班和后序航班机场需要衔接
        for (int i = 0; i < numFlights - 1; i++) {
            if (planeAssignments[i] == planeAssignments[i + 1] && planeAssignments[i] != -1) {
                model.addEquality(model.newConstant(departureAirports[i + 1].hashCode()),
                    model.newConstant(arrivalAirports[i].hashCode()));
            }
        }

        // 添加约束：航班飞机分配
        for (int i = 0; i < numFlights; i++) {
            if (planeAssignments[i] != -1) {
                model.addEquality(model.newConstant(planeAssignments[i]), model.newConstant(planeAssignments[i]));
            }
        }

        // 创建求解器并求解
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        // 输出结果
        if (status == CpSolverStatus.OPTIMAL) {
            for (int i = 0; i < numFlights; i++) {
                System.out.println("Flight " + i + " scheduled to start at time " + solver.value(startTimes[i])
                    + ", from " + departureAirports[i] + " to " + arrivalAirports[i] + ", assigned to plane "
                    + (planeAssignments[i] == -1 ? "Any" : planeAssignments[i]));
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
