package com.example.modernjava.part4;

import java.util.Arrays;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        List<Resizable> resizableShapes = Arrays.asList(new Ellipse());
        GameUtils.paint(resizableShapes);
    }
}
