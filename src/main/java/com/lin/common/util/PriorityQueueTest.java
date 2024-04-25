package com.lin.common.util;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Use a PriorityQueue to offer and poll elements based on priority.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        int highestPriority = priorityQueue.poll();
        
        System.out.println(highestPriority);
    }

}
