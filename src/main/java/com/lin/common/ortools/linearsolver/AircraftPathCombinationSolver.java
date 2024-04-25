package com.lin.common.ortools.linearsolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPSolver.OptimizationProblemType;
import com.google.ortools.linearsolver.MPVariable;

import com.google.ortools.linearsolver.MPVariable;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPSolver.OptimizationProblemType;
import java.util.ArrayList;
import java.util.List;

import com.google.ortools.linearsolver.MPVariable;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPSolver.OptimizationProblemType;
import java.util.ArrayList;
import java.util.List;

public class AircraftPathCombinationSolver {

    private static int constraintCounter = 1;

    public static void main(String[] args) {
        
        Loader.loadNativeLibraries();
        // 定义飞机和路径组合
       
        List<ComposePath> paths1 = generateLargePaths(1000000);
        List<ComposePath> paths2 = generateLargePaths(1000000);
        List<ComposePath> paths3 = generateLargePaths(1000000);

        ComposeAircraft aircraft1 = new ComposeAircraft(paths1);
        ComposeAircraft aircraft2 = new ComposeAircraft(paths2);
        ComposeAircraft aircraft3 = new ComposeAircraft(paths3);
        
        List<ComposeAircraft> aircrafts =Lists.newArrayList(aircraft1,aircraft2,aircraft3);
        // 创建求解器
        MPSolver solver = new MPSolver("AircraftPathCombinationSolver", OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

        // 定义变量
        List<List<MPVariable>> x = new ArrayList<>();
        for (int i = 0; i < aircrafts.size(); i++) {
            List<MPVariable> aircraftVariables = new ArrayList<>();
            for (int j = 0; j < aircrafts.get(i).getPaths().size(); j++) {
                aircraftVariables.add(solver.makeIntVar(0, 1, "x[" + i + "," + j + "]"));
            }
            x.add(aircraftVariables);
        }

        // 定义目标函数
        MPConstraint objective = solver.makeConstraint(0, Integer.MAX_VALUE, "Objective");
        for (List<MPVariable> aircraftVariables : x) {
            for (MPVariable variable : aircraftVariables) {
                objective.setCoefficient(variable, 1);
            }
        }

        // 定义约束条件
        // 每架飞机最多选择一条路径
        for (List<MPVariable> aircraftVariables : x) {
            MPConstraint constraint1 = solver.makeConstraint(0, 1, "Constraint1_" + constraintCounter++);
            for (MPVariable variable : aircraftVariables) {
                constraint1.setCoefficient(variable, 1);
            }
        }

        // 每个节点最多被一架飞机选择
//        for (int j = 0; j < aircrafts.get(0).getPaths().size(); j++) {
//            MPConstraint constraint2 = solver.makeConstraint(0, 1, "Constraint2_" + constraintCounter++);
//            for (int i = 0; i < aircrafts.size(); i++) {
//                constraint2.setCoefficient(x.get(i).get(j), 1);
//            }
//        }

        // 每个路径最多被一架飞机选择
        for (int i = 0; i < aircrafts.size(); i++) {
            for (int j = 0; j < aircrafts.get(i).getPaths().size(); j++) {
                MPConstraint constraint3 = solver.makeConstraint(0, 1, "Constraint3_" + constraintCounter++);
                constraint3.setCoefficient(x.get(i).get(j), 1);
            }
        }

        // 确保选定的路径组合中节点不重复（这里假设节点编号从1开始）
//        for (int node = 1; node <= 10; node++) {
//            MPConstraint constraint4 = solver.makeConstraint(0, 1, "Constraint4_node" + node + "_" + constraintCounter++);
//            for (int i = 0; i < aircrafts.size(); i++) {
//                for (int j = 0; j < aircrafts.get(i).getPaths().size(); j++) {
//                    if (aircrafts.get(i).getPaths().get(j).getPath().contains(node)) {
//                        constraint4.setCoefficient(x.get(i).get(j), 1);
//                    }
//                }
//            }
//        }

        // 求解
        MPSolver.ResultStatus status = solver.solve();

        // 输出结果
        if (status == MPSolver.ResultStatus.OPTIMAL || status == MPSolver.ResultStatus.FEASIBLE) {
            System.out.println("Objective Value: " + solver.objective().value());
            for (int i = 0; i < aircrafts.size(); i++) {
                for (int j = 0; j < aircrafts.get(i).getPaths().size(); j++) {
                    if (x.get(i).get(j).solutionValue() == 1) {
                        System.out.println("Aircraft " + (i + 1) + " selects Path " + (j + 1));
                    }
                }
            }
        } else {
            System.out.println("The problem does not have an optimal solution.");
        }
    }
    private static List<ComposePath> generateLargePaths(int count) {
        List<ComposePath> paths = new ArrayList<>();
        
        Random random=new Random();
        for (int i = 0; i < count; i++) {
            List<Integer> path = new ArrayList<>();
            if(random.nextBoolean()) {
                path.add(random.nextInt(20)); // 简化示例，实际可根据业务需求生成不同的路径
            }
            path.add(random.nextInt(100)); // 简化示例，实际可根据业务需求生成不同的路径
            path.add(random.nextInt(100)); // 简化示例，实际可根据业务需求生成不同的路径
            path.add(random.nextInt(100)); // 简化示例，实际可根据业务需求生成不同的路径
            if(random.nextBoolean()) {
                path.add(random.nextInt(40)); // 简化示例，实际可根据业务需求生成不同的路径
            }
            paths.add(new ComposePath(path));
        }
        return paths;
    }
}

class ComposeAircraft {
    private List<ComposePath> paths;

    public ComposeAircraft(List<ComposePath> paths) {
        this.paths = paths;
    }

    public List<ComposePath> getPaths() {
        return paths;
    }
}

class ComposePath {
    private List<Integer> path;

    public ComposePath(List<Integer> path) {
        this.path = path;
    }

    public List<Integer> getPath() {
        return path;
    }
}




