package ds.Leetcode;

public class ReverseLinkedList_206 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode trav = head;
        ListNode lastNode = null;
        ListNode temp;
        while (trav != null) {
            temp = trav.next;
            trav.next = lastNode;

            lastNode = trav;
            trav = temp;
        }

        return lastNode;
    }

    public static ListNode reverseListRec(ListNode trav, ListNode prevNode) {
        if (trav == null) return prevNode;
        ListNode node = trav.next;
        trav.next = prevNode;

        return reverseListRec(node, trav);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(56);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(32);
        ListNode listNode6 = new ListNode(15);
        head.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode trav1 = head;
        while (trav1 != null) {
            System.out.print(trav1.val + " ");
            trav1 = trav1.next;
        }

        ListNode ls = reverseListRec(head, null);
//        ListNode ls = reverseList(head);

        System.out.println();
        ListNode trav = ls;
        while (trav != null) {
            System.out.print(trav.val + " ");
            trav = trav.next;
        }
    }
}
