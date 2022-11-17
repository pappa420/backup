import java.util.Scanner;

class FCFS {
    // Function to find the waiting time for all
    // processes
    static void findWaitingTime(int processes[], int n,
            int bt[], int wt[]) {
        // waiting time for first process is 0
        wt[0] = 0;
        // calculating waiting time
        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }
    }

    // Function to calculate turn around time
    static void findTurnAroundTime(int processes[], int n,
            int bt[], int wt[], int tat[]) {
        // calculating turn around time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }

    // Function to calculate average time
    void findavgTime(int processes[], int n, int bt[]) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;
        // Function to find waiting time of all processes
        findWaitingTime(processes, n, bt, wt);
        // Function to find turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);
        // Display processes along with all details
        System.out.printf("Processes Burst time Waiting"
                + " time Turn around time\n");
        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.printf(" %d ", (i + 1));
            System.out.printf(" %d ", bt[i]);
            System.out.printf(" %d", wt[i]);
            System.out.printf(" %d\n", tat[i]);
        }
        float s = (float) total_wt / (float) n;
        int t = total_tat / n;
        System.out.printf("Average waiting time = %f", s);
        System.out.printf("\n");
        System.out.printf("Average turn around time = %d ", t);
    }
}

// Shortest Remaining Time First(SJF preemptive)
class Process {
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time

    public Process(int pid, int bt, int art) {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}

class SJF {
    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(Process proc[], int n, int wt[]) {
        int rt[] = new int[n];
        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;
        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
        // Process until all processes gets
        // completed
        while (complete != n) {
            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++) {
                if ((proc[j].art <= t) &&
                        (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }
            if (check == false) {
                t++;
                continue;
            }
            // Reduce remaining time by one
            rt[shortest]--;
            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;
            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {
                // Increment complete
                complete++;
                check = false;
                // Find finish time of current
                // process
                finish_time = t + 1;
                // Calculate waiting time
                wt[shortest] = finish_time -
                        proc[shortest].bt -
                        proc[shortest].art;
                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Process proc[], int n,
            int wt[], int tat[]) {
        // calculating turn around time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    // Method to calculate average time
    void findavgTime(Process proc[], int n) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;
        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n, wt);
        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n, wt, tat);
        // Display processes along with all
        // details
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time");
        // Calculate total waiting time and
        // total turn around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + proc[i].pid + "\t\t"
                    + proc[i].bt + "\t\t " + wt[i]
                    + "\t\t" + tat[i]);
        }
        System.out.println("Average waiting time = " +
                (float) total_wt / (float) n);
        System.out.println("Average turn around time = " +
                (float) total_tat / (float) n);
    }
}

class RR {
    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(int processes[], int n,
            int bt[], int wt[], int quantum) {
        // Make a copy of burst times bt[] to store remaining
        // burst times.
        int rem_bt[] = new int[n];
        for (int i = 0; i < n; i++)
            rem_bt[i] = bt[i];
        int t = 0; // Current time
        // Keep traversing processes in round robin manner
        // until all of them are not done.
        while (true) {
            boolean done = true;
            // Traverse all processes one by one repeatedly
            for (int i = 0; i < n; i++) {
                // If burst time of a process is greater than 0
                // then only need to process further
                if (rem_bt[i] > 0) {
                    done = false; // There is a pending process
                    if (rem_bt[i] > quantum) {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t += quantum;
                        // Decrease the burst_time of current process
                        // by quantum
                        rem_bt[i] -= quantum;
                    }
                    // If burst time is smaller than or equal to
                    // quantum. Last cycle for this process
                    else {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t = t + rem_bt[i];
                        // Waiting time is current time minus time
                        // used by this process
                        wt[i] = t - bt[i];
                        // As the process gets fully executed
                        // make its remaining burst time = 0
                        rem_bt[i] = 0;
                    }
                }
            }
            // If all processes are done
            if (done == true)
                break;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(int processes[], int n,
            int bt[], int wt[], int tat[]) {
        // calculating turn around time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
    }

    // Method to calculate average time
    void findavgTime(int processes[], int n, int bt[],
            int quantum) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;
        // Function to find waiting time of all processes
        findWaitingTime(processes, n, bt, wt, quantum);
        // Function to find turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);
        // Display processes along with all details
        System.out.println("Processes " + " Burst time " +
                " Waiting time " + " Turn around time");
        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + (i + 1) + "\t\t" + bt[i] + "\t " +
                    wt[i] + "\t\t " + tat[i]);
        }
        System.out.println("Average waiting time = " +
                (float) total_wt / (float) n);
        System.out.println("Average turn around time = " +
                (float) total_tat / (float) n);
    }
}

class Priority {
    void priority(String processes[], int n, int burstTime[], int priority[]) {
        int numberOfProcess = n;
        int temp;
        String temp2;
        // Sorting process & burst time by priority
        for (int i = 0; i < numberOfProcess - 1; i++) {
            for (int j = 0; j < numberOfProcess - 1; j++) {
                if (priority[j] > priority[j + 1]) {
                    temp = priority[j];
                    priority[j] = priority[j + 1];
                    priority[j + 1] = temp;
                    temp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp;
                    temp2 = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp2;
                }
            }
        }
        // TAT - Turn Around Time
        int TAT[] = new int[numberOfProcess + 1];
        int waitingTime[] = new int[numberOfProcess + 1];
        // Calculating Waiting Time & Turn Around Time
        for (int i = 0; i < numberOfProcess; i++) {
            TAT[i] = burstTime[i] + waitingTime[i];
            waitingTime[i + 1] = TAT[i];
        }
        // WT = waiting Time
        int totalWT = 0;
        int totalTAT = 0;
        double avgWT;
        double avgTAT;
        // Print Table
        System.out.println("Process BT WT TAT");
        for (int i = 0; i < numberOfProcess; i++) {
            System.out.println(processes[i] + " " + burstTime[i] + " " + waitingTime[i] + " " + (TAT[i]));
            totalTAT += (waitingTime[i] + burstTime[i]);
            totalWT += waitingTime[i];
        }
        avgWT = totalWT / (double) numberOfProcess;
        avgTAT = totalTAT / (double) numberOfProcess;
        System.out.println("\n Average Wating Time: " + avgWT);
        System.out.println(" Average Turn Around Time: " + avgTAT);
    }
}

 class All {
    public static void main(String[] args) {
        FCFS fcfs = new FCFS();
        SJF sjf = new SJF();
        RR rr = new RR();
        Priority pr = new Priority();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n The available algorithms are: ");
            System.out.println("1. FCFS");
            System.out.println("2. SJF");
            System.out.println("3. RR");
            System.out.println("4. Priority");
            System.out.println("5. Exit");
            System.out.println("Choose your algorithm: ");
            int algo = scan.nextInt();
            if (algo == 1) {
                System.out.println("Enter the number of processes: ");
                int n = scan.nextInt();
                int[] processes = new int[n];
                // Burst time of all processes
                int[] burst_time = new int[n];
                System.out.println("Enter the processes: ");
                for (int i = 0; i < n; i++) {
                    processes[i] = scan.nextInt();
                }
                System.out.println("Enter the Burst time for he processes: ");
                for (int i = 0; i < n; i++) {
                    burst_time[i] = scan.nextInt();
                }
                fcfs.findavgTime(processes, n, burst_time);
            } else if (algo == 2) {
                System.out.println("Enter the number of processes: ");
                int n = scan.nextInt();
                int[] processes = new int[n];
                int[] burst_time = new int[n];
                int[] arr_time = new int[n];
                System.out.println("Enter the processes: ");
                for (int i = 0; i < n; i++) {
                    processes[i] = scan.nextInt();
                }
                System.out.println("Enter the Burst Time of the processes: ");
                for (int i = 0; i < n; i++) {
                    burst_time[i] = scan.nextInt();
                }
                System.out.println("Enter the Arrival Time of the processes: ");
                for (int i = 0; i < n; i++) {
                    arr_time[i] = scan.nextInt();
                }
                Process[] proc = new Process[n];
                for (int i = 0; i < n; i++) {
                    Process ind_pr = new Process(processes[i], burst_time[i],
                            arr_time[i]);
                    proc[i] = ind_pr;
                }
                sjf.findavgTime(proc, proc.length);
            } else if (algo == 3) {
                int quantum;
                System.out.println("Enter the number of processes: ");
                int n = scan.nextInt();
                int[] processes = new int[n];
                // Burst time of all processes
                int[] burst_time = new int[n];
                System.out.println("Enter the processes: ");
                for (int i = 0; i < n; i++) {
                    processes[i] = scan.nextInt();
                }
                System.out.println("Enter the Burst time for the processes: ");
                for (int i = 0; i < n; i++) {
                    burst_time[i] = scan.nextInt();
                }
                quantum = scan.nextInt();
                rr.findavgTime(processes, n, burst_time, quantum);
            } else if (algo == 4) {
                System.out.println("Enter the number of processes: ");
                int n = scan.nextInt();
                String processes[] = new String[n];
                int burstTime[] = new int[n];
                int priority[] = new int[n];
                int p = 1;
                for (int i = 0; i < n; i++) {
                    processes[i] = "P" + p;
                    p++;
                }
                System.out.print("Enter the Burst time for the processes: ");
                for (int i = 0; i < n; i++) {
                    burstTime[i] = scan.nextInt();
                }
                System.out.print("Enter Priority for the processes: ");
                for (int i = 0; i < n; i++) {
                    priority[i] = scan.nextInt();
                }
                pr.priority(processes, n, burstTime, priority);
            } else if (algo == 5) {
                System.out.println("Exiting the code...");
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
        scan.close();
    }
}
