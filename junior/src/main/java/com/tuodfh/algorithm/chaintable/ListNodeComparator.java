package com.tuodfh.algorithm.chaintable;

import java.util.Comparator;

/**
 * @author tdj
 * 2022/4/10 0010
 * 链表比较器
 */
public class ListNodeComparator implements Comparator<ListNode> {

    public ListNodeComparator() {
    }

    @Override
    public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
    }
}
