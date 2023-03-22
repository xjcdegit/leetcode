package leetcode.LRUCache;/*
 *
 * @Param
 */

import java.util.HashMap;
import java.util.Map;

// A class for LRU cache with expiration
public class LRUCache {
    private int capacity; // maximum capacity of the cache
    private Map<Integer, Node> map; // hash table for fast lookup
    private Node head, tail; // head and tail pointers for doubly linked list

    // Constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0, 0);
        tail = new Node(0, 0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Get the value of a key from cache if it exists and not expired
    public int get(int key) {
        long currTime = System.currentTimeMillis(); // get current time in milliseconds
        if (map.containsKey(key)) { // if key exists in hash table
            Node node = map.get(key);
            if (node.expire > currTime) { // if node is not expired
                moveToHead(node); // move node to head of list as most recently used
                return node.value;
            } else { // if node is expired
                removeNode(node); // remove node from list
                map.remove(key);  // remove key-value pair from hash table
                return -1;
            }
        } else {
            return -1;
        }
    }

    // Put a key-value pair into cache with a given expire time
    public void put(int key, int value, long expire) {
        long currTime = System.currentTimeMillis();  // get current time in milliseconds
        if (map.containsKey(key)) {  // if key already exists in hash table
            Node node = map.get(key);
            node.value = value;  // update value
            node.expire = currTime + expire;  // update expire time
            moveToHead(node);  // move node to head of list as most recently used
        } else {  // if key does not exist in hash table
            Node node = new Node(key, value, currTime + expire);
            addNode(node);   // add node to head of list as most recently used
            map.put(key, node);  // add key-value pair to hash table

            if (map.size() > capacity) {   // if cache is full
                Node last = tail.prev;   // get the least recently used node from tail of list
                removeNode(last);   // remove it from list
                map.remove(last.key);   // remove it from hash table
            }
        }
    }

    // Add a new node to the head of the list
    private void addNode(Node node) {
        Node next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    // Remove an existing node from the list
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    // Move an existing node to the head of the list
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }
}