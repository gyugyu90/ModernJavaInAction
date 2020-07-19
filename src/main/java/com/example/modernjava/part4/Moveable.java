package com.example.modernjava.part4;

public interface Moveable {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    default void moveHorizontally(int distance) {
        setX(getY() + distance);
    }

    default void moveVertically(int distance) {
        setY(getY() + distance);
    }

}
