package com.lin.common.ortools;
//package com.hnair.c3i.data.ortools;
//
//import com.google.ortools.sat.CpModel;
//import com.google.ortools.sat.CpSolver;
//import com.google.ortools.sat.CpSolverStatus;
//import com.google.ortools.sat.IntVar;
//
//public class FlightSchedulingSat {
//
//    public static void main(String[] args) {
//        // 飞机数量
//        int numPlanes = 3;
//        // 飞机信息：机号、目前飞机所在机场
//        String[] planeIds = {"Plane1", "Plane2", "Plane3"};
//        String[] currentAirports = {"A", "B", "C"};
//        // 航班数量
//        int numFlights = 5;
//        // 航班信息：起飞时间、起飞机场、落地时间、落地机场
//        int[] departureTimes = {0, 2, 5, 8, 10};
//        String[] departureAirports = {"A", "B", "C", "D", "E"};
//        int[] arrivalTimes = {1, 4, 7, 9, 12};
//        String[] arrivalAirports = {"B", "C", "D", "E", "F"};
//        // 机场过站时间
//        int layoverTime = 1;
//
//        // 航班飞机编排问题
//        flightScheduling(numPlanes, planeIds, currentAirports, numFlights, departureTimes, departureAirports,
//            arrivalTimes, arrivalAirports, layoverTime);
//    }
//
//    public static void flightScheduling(int numPlanes, String[] planeIds, String[] currentAirports, int numFlights,
//        int[] departureTimes, String[] departureAirports, int[] arrivalTimes, String[] arrivalAirports,
//        int layoverTime) {
//        // 创建 CP-SAT 模型
//        CpModel model = new CpModel();
//
//        // 创建变量：飞机的航班分配
//        IntVar[] planeAssignments = new IntVar[numFlights];
//        for (int i = 0; i < numFlights; i++) {
//            planeAssignments[i] = model.newIntVar(0, numPlanes - 1, "plane_assignment_" + i);
//        }
//
//        // 添加约束：同一架飞机前序航班的落地机场等于后序航班的起飞机场
//        for (int i = 1; i < numFlights; i++) {
//            for (int j = 0; j < numPlanes; j++) {
//                IntVar previousArrivalAirportIndex = model.newConstant(departureAirports[i - 1].hashCode());
//                IntVar nextDepartureAirportIndex = model.newConstant(arrivalAirports[i].hashCode());
//                model.addEquality(previousArrivalAirportIndex, nextDepartureAirportIndex)
//                    .onlyEnforceIf(model.newEquality(planeAssignments[i - 1], j));
//            }
//        }
//
//        // 添加约束：同一架飞机前序航班的落地时间 + 机场过站时间 < 后序航班的起飞时间
//        for (int i = 1; i < numFlights; i++) {
//            for (int j = 0; j < numPlanes; j++) {
//                IntVar previousArrivalTime = model.newConstant(arrivalTimes[i - 1] + layoverTime);
//                IntVar nextDepartureTime = model.newConstant(departureTimes[i]);
//                model.addLessThan(previousArrivalTime, nextDepartureTime)
//                    .onlyEnforceIf(model.newEquality(planeAssignments[i - 1], j));
//            }
//        }
//
//        // 创建求解器并求解
//        CpSolver solver = new CpSolver();
//        CpSolverStatus status = solver.solve(model);
//
//        // 输出结果
//        if (status == CpSolverStatus.OPTIMAL) {
//            for (int i = 0; i < numFlights; i++) {
//                int assignedPlaneIndex = (int)solver.value(planeAssignments[i]);
//                System.out.println("Flight " + i + ": Plane " + planeIds[assignedPlaneIndex] + ", from "
//                    + departureAirports[i] + " to " + arrivalAirports[i]);
//            }
//        } else {
//            System.out.println("No solution found.");
//        }
//    }
//}
