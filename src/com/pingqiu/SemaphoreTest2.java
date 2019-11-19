package com.pingqiu;

// java program to demonstrate
// use of semaphores Locks
import java.util.LinkedList;
import java.util.concurrent.*;

// Driver class
public class SemaphoreTest2 {
    private static class Shared {
        // Create a list shared by producer and consumer
        // Size of list is 2.
        static LinkedList<Integer> list = new LinkedList<>();
        static int capacity = 6;
        static int value = 0;

        public static void produce () {
            System.out.printf("Producer %s checking queue size before add: %d.%n",
                    Thread.currentThread().getName(), list.size());

            // to insert the jobs in the list
            list.add(value);

            System.out.printf("Queue size : %d after %s added value %d.%n%n",
                    list.size(), Thread.currentThread().getName(), value);

            value++;
        }

        public static void consume() {
            System.out.printf("Checking queue size %d  before consume : %s.%n",
                    list.size(), Thread.currentThread().getName());
            if(list.size() <= 0) {
                return;
            }

            //to retrive the first job in the list
            int val = list.removeFirst();

            System.out.printf("Consumer %s consumed value : %d, queue size before consume: %d.%n%n",
                    Thread.currentThread().getName(), val, list.size());
        }
    }


    private static class Producer extends Thread {
        private String threadName;
        private Semaphore sem;
        public Producer(Semaphore sem, String name) {
            super(name);
            this.sem = sem;
            threadName = name;
        }

        public void run() {
            while (true) {
                try {
                    // First, get a permit.
                    System.out.println(threadName + " is waiting for a permit.");

                    // acquiring the lock
                    sem.acquire();

                    System.out.println(threadName + " gets a permit.");

                    // Now, accessing the shared resource.
                    // other waiting threads will wait, until this
                    // thread release the lock
                    Shared.produce();

                    // Now, allowing a context switch -- if possible.
                    // for thread B to execute
                    Thread.sleep(1000);
                } catch (InterruptedException exc) {
                    System.out.println(exc);
                }

                // Release the permit.
                System.out.println(threadName + " releases the permit.");
                sem.release();
            }
        }
    };

    private static class Consumer extends Thread {
        private String threadName;
        private Semaphore sem;

        public Consumer(Semaphore sem, String name) {
            super(name);
            this.sem = sem;
            threadName = name;
        }

        public void run() {
            while(true) {
                try {
                    // First, get a permit.
                    System.out.println(threadName + " is waiting for a permit.");

                    // acquiring the lock
                    sem.acquire();

                    System.out.println(threadName + " gets a permit.");

                    // Now, accessing the shared resource.
                    // other waiting threads will wait, until this
                    // thread release the lock
                    Shared.consume();

                    // Now, allowing a context switch -- if possible.
                    // for thread A to execute
                    Thread.sleep(1000);
                } catch (InterruptedException exc) {
                    System.out.println(exc);
                }
                // Release the permit.
                System.out.println(threadName + " releases the permit.");
                sem.release();
            }
        }
    }

    public static void main(String args[]) throws InterruptedException
    {
        // creating a Semaphore object
        // with number of permits 1
        Semaphore sem = new Semaphore(1);

        // creating two threads with name A and B
        // Note that thread A will increment the count
        // and thread B will decrement the count
        Producer p1 = new Producer(sem, "P-1");
        Producer p2 = new Producer(sem, "P-2");
        Producer p3 = new Producer(sem, "P-3");
        Consumer c1 = new Consumer(sem, "C-1");
        Consumer c2 = new Consumer(sem, "C-2");
        Consumer c3 = new Consumer(sem, "C-3");

        // stating threads A and B
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();

        // waiting for threads A and B
        p1.join();
        p2.join();
        p3.join();
        c1.join();
        c2.join();
        c3.join();

        // count will always remain 0 after
        // both threads will complete their execution
        System.out.println("count: " + Shared.value);
    }
}
