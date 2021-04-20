package com.example.hashmap;

import java.util.Objects;

public class HashMap<K, V> {
    final private double loadFactor = 0.75;
    private int capacity = 16;
    private int threshold = (int) (capacity * loadFactor);
    private Node<K, V>[] busket;
    

    public HashMap(int capacity) {
        this.capacity = capacity;
        threshold = (int) (capacity * loadFactor);
        busket = (Node<K, V>[]) new Node[capacity];
    }

    int indexFor(int hashKey) {
        return hashKey & capacity;
    }

    public HashMap() {
        threshold = (int) (capacity * loadFactor);
        busket = (Node<K, V>[]) new Node[capacity];
    }

    public void add(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value, null);
        int indexAdd = indexFor(newNode.hashKey);
        if (busket[indexAdd] == null){
            addNewNode(newNode, indexAdd);
            return;
        }
        if(busket[indexAdd].hashKey == newNode.hashKey && (busket[indexAdd].key == newNode.key)){
            addNewNode(newNode, indexAdd);
            return;
        }
        addNextNode(newNode, indexAdd);
    }

    private void addNewNode(Node<K, V> newNode, int index) {
        busket[index] = newNode;
    }

    private void addNextNode(Node<K, V> newNode, int index){
        Node curNode = busket[index];
        while(curNode.next != null){
            curNode = curNode.next;
        }
        curNode.next = newNode;
    }

    public void printMap(){

    }

    class Node<K, V> {
        final int hashKey;
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashKey = setHash(key);
        }

        private int setHash(K key) {
            final int prime = 31;
            int result = 1;
            return prime * result + key.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hashKey == node.hashKey &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
