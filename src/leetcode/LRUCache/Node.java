package leetcode.LRUCache;/*
 *
 * @Param
 */

// A node structure for doubly linked list
public class Node {
    int key; // key of the cache entry
    int value; // value of the cache entry
    long expire; // 过期时间戳
    Node prev, next; // pointers to previous and next nodes

    // Constructor
    public Node(int key, int value, long expire) {
        this.key = key;
        this.value = value;
        this.expire = expire;
    }
}
