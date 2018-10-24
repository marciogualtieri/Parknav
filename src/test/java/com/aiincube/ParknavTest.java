package com.aiincube;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(3)
public class ParknavTest {

    @Test
    public void whenFirstExampleFromRequirements_thenAtLeast2Cameras() {
        int sortedSpaces[] = {1, 2, 3, 4, 5};
        assertThat(Parknav.findMinimumNumberOfCameras(1, sortedSpaces), is(2));
    }

    @Test
    public void whenSecondExampleFromRequirements_thenAtLeast3Cameras() {
        int sortedSpaces[] = {2, 4, 5, 6, 7, 9, 11, 12};
        assertThat(Parknav.findMinimumNumberOfCameras(2, sortedSpaces), is(3));
    }

    @Test
    public void whenThirdExampleFromRequirements_thenAtLeast3Cameras() {
        int sortedSpaces[] = {1, 15, 30, 40, 50};
        assertThat(Parknav.findMinimumNumberOfCameras(10, sortedSpaces), is(3));
    }

    @Test
    @Benchmark
    public void whenOneHundredParkingSpaces_thenAtLeast9Cameras() {
        int sortedSpaces[] = IntStream.rangeClosed(1, 100).toArray();
        assertThat(Parknav.findMinimumNumberOfCameras(10, sortedSpaces), is(9));
    }

    @Test
    @Benchmark
    public void whenOneHundredThousandParkingSpaces_thenAtLeast9Cameras() {
        int sortedSpaces[] = IntStream.rangeClosed(1, 100000).toArray();
        assertThat(Parknav.findMinimumNumberOfCameras(10, sortedSpaces), is(9090));
    }

}
