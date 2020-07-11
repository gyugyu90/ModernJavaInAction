package com.example.modernjava.part4;

import java.util.List;

public class GameUtils {
    public static void paint(List<Resizable> l) {
        l.forEach(r -> {
            r.setAbsoluteSize(42, 42);
            r.draw();
        });
    }
}
