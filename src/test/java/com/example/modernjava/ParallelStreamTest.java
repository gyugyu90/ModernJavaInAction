package com.example.modernjava;

import com.example.modernjava.part2.ParallelStream;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ParallelStreamTest {

    @Test
    public void sequentialSum() {
        ParallelStream ps = new ParallelStream();
        assertEquals(ps.sequentialSum(10), 55L);
    }
}
