package org.example;

import com.google.ortools.Loader;
import com.google.ortools.sat.*;

import java.util.ArrayList;
import java.util.List;

public class TyreScheduler {
    public static void main(String[] args) {
        Loader.loadNativeLibraries();

        int numMachines = 5;
        int numTyres = 2;
        int tyreBuildTime = 4;

        CpModel model = new CpModel();

        IntVar[] startTimes = new IntVar[numTyres];
        IntVar[] assignedMachines = new IntVar[numTyres];
        IntervalVar[] tyreTasks = new IntervalVar[numTyres];

        int horizon = 8;

        for (int i = 0; i < numTyres; i++) {
            startTimes[i] = model.newIntVar(0, horizon, "start_" + i);
            assignedMachines[i] = model.newIntVar(0, numMachines - 1, "machine_" + i);
            tyreTasks[i] = model.newIntervalVar(startTimes[i], tyreBuildTime, startTimes[i].add(tyreBuildTime).getName(), "task_" + i);
        }

        for (int m = 0; m < numMachines; m++) {
            List<IntervalVar> machineTasks = new ArrayList<>();
            for (int i = 0; i < numTyres; i++) {
                machineTasks.add(tyreTasks[i]);
            }
            model.addNoOverlap(machineTasks);
        }

        IntVar makespan = model.newIntVar(0, horizon, "makespan");
        for (int i = 0; i < numTyres; i++) {
            model.addLessOrEqual(startTimes[i].add(tyreBuildTime), makespan);
        }

        model.minimize(makespan);

        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
            System.out.println("Minimum total time (makespan): " + solver.value(makespan) + " hours");
            for (int i = 0; i < numTyres; i++) {
                System.out.println("Tyre " + i + ": Start = " + solver.value(startTimes[i]) +
                        ", Machine = " + solver.value(assignedMachines[i]));
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
