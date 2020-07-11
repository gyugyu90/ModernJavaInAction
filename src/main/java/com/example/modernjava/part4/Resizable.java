package com.example.modernjava.part4;

public interface Resizable extends Drawable {

    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);

}
