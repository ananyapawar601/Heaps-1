/*
Problem1 
Kth largest in Array (https://leetcode.com/problems/kth-largest-element-in-an-array/)
*/

/*
Min Heap

Time Complexity (TC): O(nlogk)
Each insertion and deletion operation in a priority queue (min-heap) takes O(logk), and we perform this for each of the n elements.
Space Complexity (SC): O(k)
The heap stores at most k elements at any time.
Explanation in Three Lines
This code finds the kth largest element in an array using a min-heap of size k. It iterates through all numbers, adding each to the heap and removing the smallest whenever the heap exceeds size k,
ensuring that only the top k largest elements remain. Finally, the root of the heap (smallest among the k largest elements) is returned as the answer.
*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min-heap to store the k largest elements
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Iterate through all numbers in the array
        for (int num : nums) {
            pq.add(num); // Add the current number to the heap (log k time)
            
            // If heap size exceeds k, remove the smallest element
            if (pq.size() > k) {
                pq.poll();  // Remove the smallest element (log k time)
            }
        }
        
        // The top of the heap is the kth largest element
        return pq.peek();
    }
}


/*
Max Heap

Time Complexity (TC): O(nlogn)
Each insertion and deletion operation in the max-heap takes O(logn), and we do this for all n elements.
Space Complexity (SC): O(n)
The heap can store up to n elements in the worst case.

Explanation in Three Lines
This code finds the kth largest element by maintaining a max-heap with all elements sorted in descending order. It removes elements once the heap size exceeds n−k and 
tracks the smallest removed element as the kth largest. The result is stored in re, which is returned at the end.
*/

class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        // Max-heap to store all elements in descending order
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        int n = nums.length;
        int re = Integer.MAX_VALUE; // Variable to store the kth largest element
        
        // Iterate through all numbers in the array
        for (int num : nums) {
            pq.add(num); // Add the current number to the max-heap (log n time)
            
            // When heap size exceeds (n-k), remove elements to get the kth largest
            if (pq.size() > n - k) {
                re = Math.min(re, pq.poll());  // Remove and track kth largest (log n time)
            }
        }
        
        // The kth largest element is stored in re
        return re;
    }
}