package com.tuodfh.algorithm.chaintable;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author tdj
 * 2022/4/10 0010
 * 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表
 */
public class MergeKAscLinklists {

    /**
     * 注意入场数组里每个对象都是一个链表的头部
     * 解题思路就是利用优先级队列，实际是堆
     * 优先级队列会自动将里面的数据排好序，默认升序
     * @param lists 链表数组
     * @return 合并后的链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new ListNodeComparator1());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                priorityQueue.add(lists[i]);
            }
        }
        if (priorityQueue.isEmpty()) {
            return null;
        }
        ListNode head = priorityQueue.poll();
        ListNode pre = head;
        if (head.next != null) {
            priorityQueue.add(head.next);
        }
        while (!priorityQueue.isEmpty()) {
            ListNode listNode = priorityQueue.poll();
            pre.next = listNode;
            pre = listNode;
            if (listNode.next != null) {
                priorityQueue.add(listNode.next);
            }
        }
        return head;
    }

    class ListNodeComparator1 implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }

    }

}
