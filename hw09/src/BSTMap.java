import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private BSTNode root;
    private int size;
    private Set<K> keySet;

    public BSTMap() {
        root = null;
        size = 0;
        keySet = new TreeSet<>();
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
        root = put(key, value, root);
        keySet.add(key);
    }

    private BSTNode put(K key, V value, BSTNode node) {
        if (node == null) {
            size += 1;
            return new BSTNode(key, value, null, null);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = put(key, value, node.right);
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(key, value, node.left);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, BSTNode node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return get(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            return get(key, node.left);
        } else {
            return node.value;
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private boolean containsKey(K key, BSTNode node) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) > 0) {
            return containsKey(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            return containsKey(key, node.left);
        } else {
            return true;
        }
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
        root = null;
        size = 0;
        keySet.clear();
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return remove(key, root, root);
    }

    private V remove(K key, BSTNode node, BSTNode parent) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return remove(key, node.right, node);
        } else if (key.compareTo(node.key) < 0) {
            return remove(key, node.left, node);
        } else {
            size -= 1;
            V val = node.value;
            if (node.right == null) {
                // Case 1: node has no children
                // Case 2a: one left child
                removeCase(node, parent, node.left, key);
            } else if (node.left == null) {
                // Case 2b: one right child
                removeCase(node, parent, node.right, key);
            } else {
                // Case 3: two children
                BSTNode predecessor = predecessor(node);
                K predKey = predecessor.key;
                V predVal = predecessor.value;
                remove(predKey); // must be Case 1 or Case 2
                node.key = predKey;
                node.value = predVal;
            }
            return val;
        }
    }

    private void removeCase(BSTNode node, BSTNode parent, BSTNode child, K key) {
        if (parent == node) { // node is root
            root = child;
        } else if (parent.left != null && parent.left.key.equals(key)) { // in case parent has two children
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // "Biggest" in the left subtree
    private BSTNode predecessor(BSTNode root) {
        BSTNode predecessor = root.left;
        while (predecessor.right != null) {
            predecessor = predecessor.right;
        }
        return predecessor;
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
        int index;
        List<K> inOrder;

        KeyIterator() {
            index = 0;
            inOrder = new ArrayList<>();
            inOrder(root);
        }

        void inOrder(BSTNode node) {
            if (node == null) {
                return;
            }
            inOrder(node.left);
            inOrder.add(node.key);
            inOrder(node.right);
        }

        @Override
        public boolean hasNext() {
            return index != inOrder.size();
        }

        @Override
        public K next() {
            return inOrder.get(index++);
        }
    }
}
