package leetcode.LRUCache;/*
 *
 * @Param
 */

public class LRUCacheTest {
    public static void main(String[] args) throws InterruptedException {
        LRUCache cache = new LRUCache(3); // create a cache with capacity 3
        cache.put(1, 10, 1000); // put key 1 with value 10 and expire time 1000 ms
        cache.put(2, 20, 2000); // put key 2 with value 20 and expire time 2000 ms
        cache.put(3, 30, 3000); // put key 3 with value 30 and expire time 3000 ms

        assertEquals(10, cache.get(1)); // get key 1 should return value 10
        assertEquals(20, cache.get(2)); // get key 2 should return value 20
        assertEquals(30, cache.get(3)); // get key 3 should return value 30

        Thread.sleep(1500); // wait for some time

        /*assertEquals(-1, cache.get(1)); // get key 1 should return -1 as it is expired
        assertEquals(20, cache.get(2)); // get key 2 should return value 20 as it is not expired
        assertEquals(30, cache.get(3)); // get key 3 should return value\
        cache.put(4, 40, 4000); // put key 4 with value 40 and expire time 4000 ms

        assertEquals(-1, cache.get(2)); // get key 2 should return -1 as it is evicted due to LRU policy
        assertEquals(30, cache.get(3)); // get key 3 should return value 30 as it is not expired
        assertEquals(40, cache.get(4)); // get key 4 should return value 40 as it is not expired*/
    }

    //assertEquals是一个断言方法，它用来检查两个对象是否相等123。
    // 如果不相等，它会抛出一个AssertionError4。
    // 如果两个对象都是null，它会认为它们相等3。
    // 当您调用assertEquals(object1, object2)时，它会使用该类型对象的equals方法来比较3。
    private static void assertEquals(int i, int i1) {
        if(i != i1){
            throw new AssertionError();
        }
    }
}