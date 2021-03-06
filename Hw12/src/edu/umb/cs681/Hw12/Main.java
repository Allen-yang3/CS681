package edu.umb.cs681.Hw12;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();

        Directory dr1 = new Directory(null, "C: Windows", 1, date);
        Directory dr2 = new Directory(dr1, "C: Users", 3, date);
        Directory dr3 = new Directory(dr1, "Allen", 5, date);

        File f1 = new File(dr2, "frontend.js", 2, date);
        File f2 = new File(dr2, "runnable.java", 4, date);
        File f3 = new File(dr2, "tennis.py", 6, date);

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(() -> {

                System.out.println(dr1.getName());
                System.out.println(dr2.getName());
                System.out.println(dr3.getName());

                System.out.println(f1.getName());
                System.out.println(f2.getName());
                System.out.println(f3.getName());

                System.out.println(dr1.getTotalSize());
                System.out.println(dr2.getTotalSize());
                System.out.println(dr3.getTotalSize());

                System.out.println(f1.getParent().getParent().countChildren());
                System.out.println(f3.getParent().getParent().countChildren());
                System.out.println(dr3.getParent().countChildren());
            }));
        }

        for (Thread t : threads) {
            try {
                t.start();
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}