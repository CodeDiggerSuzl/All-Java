package com.suzl.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Suz1
 * @date 2020/4/2 12:34 上午
 */
public class ABADemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(25, 0);

        new Thread(() -> {

            System.out.println("a1 -> " + reference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(reference.compareAndSet(25, 26,
                    reference.getStamp(), reference.getStamp() + 1));

            System.out.println("a2 -> " + reference.getStamp());

            // 改回去
            System.out.println(reference.compareAndSet(26, 25,
                    reference.getStamp(), reference.getStamp() + 1));

            System.out.println("a3 -> " + reference.getStamp());
        }, "a").start();

        new Thread(() -> {
            int stamp = reference.getStamp();
            System.out.println("b1 -> " + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(25, 26, stamp, stamp + 1));
            System.out.println("b2 -> " + reference.getStamp());
        }, "b").start();
    }
}
