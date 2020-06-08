package com.example.modernjava.part2;

import java.util.stream.Stream;


public class ParallelStream {

    public static void main(String[] args) {

    }

    public long sequentialSum(long n) {
        long sum = Stream.iterate(1L, i -> i + 1) // 무한 자연수 스트림 생성
                .limit(n) // n개 이하로 제한
                .reduce(0L, Long::sum); // 모든 숫자를 더하는 스트림 리듀싱 연산
        return sum;
    }

    public long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel() // 스트림을 병렬 스트림으로 변환
                .reduce(0L, Long::sum);
    }

}
