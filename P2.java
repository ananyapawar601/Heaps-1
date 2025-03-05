/*
Min Heap

Time Complexity (TC): O(Nlogk) Each insertion and extraction from the priority queue takes O(logk), and we do this for all N nodes across k lists.
Space Complexity (SC): O(k) The heap stores at most k elements at any time.
Explanation in Three Lines
This code merges k sorted linked lists into one sorted list using a min-heap. It adds the heads of all lists into the heap, repeatedly extracts the smallest node, 
and inserts its next node into the heap until all nodes are processed. The merged list is built using a dummy node, and its head is returned as the result.
*/


class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Min-heap to store the heads of all linked lists in sorted order
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Add the head of each linked list to the priority queue
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head); // Add non-null heads to the heap
            }
        }

        // Dummy node to build the merged linked list
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        // Process nodes in ascending order
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();   // Extract the smallest node (log k)
            curr.next = minNode;
            curr = curr.next;

            // Add the next node from the extracted list to the heap
            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        // Return the merged sorted list
        return dummy.next;
    }
}

/*
merges k sorted linked lists one by one using a two-way merge function

Time Complexity (TC): O(kN) The mergeKLists function merges one list at a time using the merge function, which takes O(N) for two lists.
Since we merge k lists sequentially, the worst case results in O(kN).
Space Complexity (SC): O(1)
Merging is done in-place without extra space, apart from a few pointers.
Explanation in Three Lines
This code merges k sorted linked lists one by one using a two-way merge function. It iterates through the list array, merging each list into an accumulating result, maintaining sorted order. 
The merging process runs in O(kN), making it less efficient than a heap-based approach but simpler in implementation.
*/

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Create a dummy node to start merging
        ListNode merged = new ListNode(Integer.MIN_VALUE);
        
        // Iterate through each linked list in the array
        for (ListNode li : lists) {
            if (li == null) continue; // Skip null lists
            merged = merge(merged, li); // Merge the current list with the merged list
        }

        // Return the merged list, skipping the initial dummy node
        return merged.next;
    }

    private ListNode merge(ListNode p1, ListNode p2) {
        // Dummy node to build the merged linked list
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        // Merge the two lists in sorted order
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                curr.next = p1;
                p1 = p1.next;
            } else {
                curr.next = p2;
                p2 = p2.next;
            }
            curr = curr.next;
        }

        // Attach remaining nodes from either list
        if (p2 != null) {
            curr.next = p2;
        }
        if (p1 != null) {
            curr.next = p1;
        }

        // Return the merged list
        return dummy.next;
    }
}
