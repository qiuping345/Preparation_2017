package com.pingqiu;

import java.util.LinkedList;

public class ProducerConsumer {
    private static class ProducerRunnable implements Runnable {
        private PC pc = null;

        public ProducerRunnable(PC pc) {
            this.pc = pc;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    pc.produce();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    ;

    private static class ConsumerRunnable implements Runnable {
        private PC pc = null;

        public ConsumerRunnable(PC pc) {
            this.pc = pc;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    pc.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final PC pc = new PC();

        // Create producer thread
        Thread p1 = new Thread(new ProducerRunnable(pc), "producer-1");
        Thread p2 = new Thread(new ProducerRunnable(pc), "producer-2");
        Thread p3 = new Thread(new ProducerRunnable(pc), "producer-3");
        Thread[] producers = new Thread[]{p1, p2, p3};

        // Create consumer thread
        Thread c1 = new Thread(new ConsumerRunnable(pc), "consumer-1");
        Thread c2 = new Thread(new ConsumerRunnable(pc), "consumer-2");
        Thread c3 = new Thread(new ConsumerRunnable(pc), "consumer-3");
        Thread[] consumers = new Thread[]{c1, c2, c3};

        // Start both threads
        for (Thread producer : producers) {
            producer.start();
        }
        for (Thread consumer : consumers) {
            consumer.start();
        }

        for (Thread producer : producers) {
            producer.join();
        }
        for (Thread consumer : consumers) {
            consumer.join();
        }
    }

    // This class has a list, producer (adds items to list
    // and consumber (removes items).
    public static class PC {
        // Create a list shared by producer and consumer
        // Size of list is 2.
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 6;
        int value = 0;

        // Function called by producer thread
        public void produce() throws InterruptedException {
            synchronized (PC.this) {
                // producer thread waits while list
                // is full
                while (list.size() == capacity) {
                    wait();
                }

                System.out.printf("Producer %s checking queue size before add: %d.%n",
                        Thread.currentThread().getName(), list.size());

                // to insert the jobs in the list
                list.add(value);

                System.out.printf("Queue size : %d after %s added value %d.%n%n",
                        list.size(), Thread.currentThread().getName(), value);

                value++;

                // notify other threads
                notify();

                // makes the working of program easier
                // to  understand
                Thread.sleep(1000);
            }
        }

        // Function called by consumer thread
        public void consume() throws InterruptedException {
            synchronized (this) {
                // consumer thread waits while list
                // is empty
                while (list.size() == 0) {
                    wait();
                }

                System.out.printf("Checking queue size %d  before consumer : %s.%n",
                        list.size(), Thread.currentThread().getName());

                //to retrive the first job in the list
                int val = list.removeFirst();

                System.out.printf("Consumer %s consumed value : %d, queue size before consume: %d.%n%n",
                        Thread.currentThread().getName(), val, list.size());

                // Wake up other threads
                notify();

                // and sleep
                Thread.sleep(1000);
            }
        }
    }
}