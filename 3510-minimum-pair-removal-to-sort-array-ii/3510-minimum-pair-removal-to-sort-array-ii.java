class Node {

    long value;
    int left;
    Node prev;
    Node next;

    Node(int value, int left) {
        this.value = value;
        this.left = left;
    }
}

class PQItem implements Comparable<PQItem> {

    Node first;
    Node second;
    long cost;

    PQItem(Node first, Node second, long cost) {
        this.first = first;
        this.second = second;
        this.cost = cost;
    }

    @Override
    public int compareTo(PQItem other) {
        if (this.cost == other.cost) {
            return this.first.left - other.first.left;
        }
        return this.cost < other.cost ? -1 : 1;
    }
}

public class Solution {

    public int minimumPairRemoval(int[] nums) {
        PriorityQueue<PQItem> pq = new PriorityQueue<>();
        boolean[] merged = new boolean[nums.length];

        int decreaseCount = 0;
        int count = 0;
        Node head = new Node(nums[0], 0);
        Node current = head;

        for (int i = 1; i < nums.length; i++) {
            Node newNode = new Node(nums[i], i);
            current.next = newNode;
            newNode.prev = current;
            pq.offer(
                new PQItem(current, newNode, current.value + newNode.value)
            );
            if (nums[i - 1] > nums[i]) {
                decreaseCount++;
            }
            current = newNode;
        }

        while (decreaseCount > 0) {
            PQItem item = pq.poll();
            Node first = item.first;
            Node second = item.second;
            long cost = item.cost;

            if (
                merged[first.left] ||
                merged[second.left] ||
                first.value + second.value != cost
            ) {
                continue;
            }
            count++;
            if (first.value > second.value) {
                decreaseCount--;
            }

            Node prevNode = first.prev;
            Node nextNode = second.next;
            first.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = first;
            }

            if (prevNode != null) {
                if (prevNode.value > first.value && prevNode.value <= cost) {
                    decreaseCount--;
                } else if (
                    prevNode.value <= first.value && prevNode.value > cost
                ) {
                    decreaseCount++;
                }

                pq.offer(new PQItem(prevNode, first, prevNode.value + cost));
            }

            if (nextNode != null) {
                if (second.value > nextNode.value && cost <= nextNode.value) {
                    decreaseCount--;
                } else if (
                    second.value <= nextNode.value && cost > nextNode.value
                ) {
                    decreaseCount++;
                }

                pq.offer(new PQItem(first, nextNode, cost + nextNode.value));
            }

            first.value = cost;
            merged[second.left] = true;
        }

        return count;
    }
}