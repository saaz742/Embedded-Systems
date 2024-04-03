import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
/*input:
number of tasks                int
task0: periods time            int int
task1: periods time            int int
...
 */
//sara azarnoush 98170668
public class RMS {
    public class Task {
        int id, p, c, r, e;  //id, period, computational time, remaining time, entry time

        Task(int pe, int co, int ide) {
            id = ide;
            p = pe;
            c = co;
            r = co;
        }
    }

    public class queue {
        int t = 0;
        Task lastExecuted = null;
        LinkedList<Task> q = new LinkedList<>();
        int check() throws Exception {
            if (q.isEmpty()) {
                t++;
                return 0;
            }
            t++;
            Task task = q.getFirst();
            if (task.r <= 0)
                throw new Exception();
            task.r--;
            if ((task.r + 1 == task.c) || (lastExecuted != null && task != lastExecuted))
                System.out.println(task.id + " at time " + (t - 1) +" started execution");
            lastExecuted = task;
            if (task.r == 0) {
                if (task.e + task.p >= t) {
                    System.out.println(task.id + " at time " + t + " completed");
                    q.pollFirst();
                    return 2;
                } else {
                    System.out.println(q.getFirst().id + " finished at " + t + " missed it's deadline " + (q.getFirst().e + q.getFirst().p) + ".");
                    q.pollFirst();
                    return -1;
                }
            }
            return 99;
        }

        int add(Task task) {
            if (q.isEmpty()) {
                q.addFirst(task);
                task.e = t;
                return 0;
            }
            if (task.p == q.getFirst().p) { //add copy task to the first task
                q.add(1, task);
                task.e = t;
                return 1;
            }
            if (task.p < q.getFirst().p) {
                boolean tOK= q.getFirst().c == q.getFirst().r;
                q.addFirst(task);
                task.e = t;
                if (tOK)
                    return 2;
                else {
                    System.out.println(q.get(1).id+ " at time " + t + " preempted.");
                    return 3;
                }
            }
            if (task.p > q.getFirst().p) { //add the second task with less priority
                if (q.size() == 1) {
                    q.add(task);
                    task.e = t;
                    return 4;
                }
                for (int i = 1; i < q.size(); i++) { //add task in the middle
                    if (task.p < q.get(i).p) {
                        q.add(i, task);
                        task.e = t;
                        return 5;
                    }
                    if (task.p > q.get(i).p) {//add least prioritized task
                        if (i == q.size() - 1) {
                            q.add(task);
                            task.e = t;
                            return 6;
                        }
                    }
                }
            }
            return 7;
        }
    }

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int[] input) {
        int lc = input[0];
        for (int i = 1; i < input.length; i++) lc = lcm(lc, input[i]);
        return lc;
    }

    public static void main(String[] args) {
        LinkedList<Task> tasks = new LinkedList<>();
        queue q = new RMS().new queue();
        try {
            Scanner s = new Scanner(System.in);
            int n = s.nextInt();
            ArrayList<Integer> periods = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int p = s.nextInt();
                int t = s.nextInt();
                periods.add(p);
                Task task = new RMS().new Task(p, t, i);
                tasks.add(task);
            }
            int[] arr = periods.stream().mapToInt(i -> i).toArray();
            int pLCM = lcm(arr);
            for (int i = 0; ; i++) {
                for (Task t : tasks) {
                    if (i % t.p == 0) {
                        q.add(new RMS().new Task(t.p, t.c, t.id));
                    }
                }
                if (i != 0 && i % pLCM == 0) {
                    int c = 0;
                    if (q.q.size() == tasks.size()) {
                        for (int j = 0; j < q.q.size(); j++) {
                            if (q.q.get(j).c !=q.q.get(j).r ) {
                                c = 1;
                                break;
                            }
                        }
                    }
                    if (c == 0) {
                        System.out.println("end");
                        return;
                    }
                }
                if (q.check() == -1)
                    return;
            }
        } catch (Exception e) {
            System.err.println("err" + e);
        }
    }
}
