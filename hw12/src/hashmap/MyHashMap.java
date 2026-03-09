package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author 0bs1dian
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size; // the number of elements in the map
    private double loadFactor;
    private final int DEFAULT_CAPACITY = 16;
    private final double DEFAULT_LOAD_FACTOR = 0.75;

    /** Constructors */
    public MyHashMap() {
        size = 0;
        loadFactor = DEFAULT_LOAD_FACTOR;
        buckets = new Collection[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        size = 0;
        loadFactor = DEFAULT_LOAD_FACTOR;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        size = 0;
        this.loadFactor = loadFactor;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[index].add(new Node(key, value));
        size += 1;
        double currLoadFactor = 1.0 * size / buckets.length;
        if (currLoadFactor >= loadFactor) {
            resize();
        }
    }

    private void resize() {
        Collection<Node>[] newBuckets = new Collection[buckets.length * 2];
        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = createBucket();
        }
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                int index = Math.floorMod(node.key.hashCode(), newBuckets.length);
                newBuckets[index].add(node);
            }
        }
        buckets = newBuckets;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        buckets = new Collection[buckets.length];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                keySet.add(node.key);
            }
        }
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                V val = node.value;
                buckets[index].remove(node);
                size -= 1;
                return val;
            }
        }
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        int indexOfBucket;
        int indexOfKey;
        int cnt;
        ArrayList<K> keys;

        KeyIterator() {
            indexOfBucket = 0;
            indexOfKey = 0;
            cnt = 0;
            keys = new ArrayList<>();
            for (Node node : buckets[indexOfBucket]) {
                keys.add(node.key);
            }
        }

        @Override
        public boolean hasNext() {
            // If we use indexOfBucket != buckets.length,
            // we can go out-of-bound
            return cnt != size;
        }

        @Override
        public K next() {
            while (indexOfKey == keys.size()) { // use loop in case empty bucket
                indexOfKey = 0;
                indexOfBucket += 1;
                keys.clear();
                for (Node node : buckets[indexOfBucket]) {
                    keys.add(node.key);
                }
            }
            cnt += 1;
            return keys.get(indexOfKey++);
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (String key : map) {
            System.out.println(key);
        }

        for (int i = 0; i < 10; i++) {
            map.put("hi" + i, i);
        }
        for (String key : map) {
            System.out.print(key + " ");
        }
        System.out.println();

        map.put("hi10", 10);
        map.put("hi11", 11);
        map.put("hi12", 12);
        for (String key : map) {
            System.out.print(key + " ");
        }
        System.out.println();

        map.remove("hi2");
        map.remove("hi5");
        map.remove("hi11");
        for (String key : map) {
            System.out.print(key + " ");
        }
        System.out.println();
    }
}
