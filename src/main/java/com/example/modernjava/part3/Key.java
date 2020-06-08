package com.example.modernjava.part3;

// ref: https://dzone.com/articles/hashmap-performance
public class Key implements Comparable<Key> {

    private final int value;

    public Key(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Key o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Key key = (Key) obj;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
