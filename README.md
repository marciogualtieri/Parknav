# Parknav Puzzle

## Overview

This project implements the solution for the puzzle specified in [these requirements](./requirements/Interview%20Puzzle.pdf).

## Running Tests

To run tests, execute the following command on a terminal:

    ./gradlew clean test --info

This project is not deployable. It's only executable through tests.

>***Note that one of the test cases takes an input of 100,000 parking spaces, thus the complete test suite takes a couple of seconds to complete.***

## Algorithm Design & Analysis

Here's a description of my algorithm:

- Initially I assume that to cover all parking spaces, I need a camera per space, thus I make a copy of the array of parking spaces to a new array of cameras.

- Then for each camera in the array, I check if the camera's coverage is completely covered by the cameras of its neighbors (neighbors depend on camera's range).

- If that's the case for any camera in the array, I simply remove the camera.

- At the end I return the length of the input array minus the number of removed cameras.

The algorithm time complexity would be ` O(N * (2 * <Camera Range>))` , but given that `<Camera Range>` is somewhat small, we may simply consider it `O(N)`.

Ignoring temporary variables used to compute and check coverage (`Set<Int>` for instance) and that we need to make a copy of the input (cameras array), the space complexity is `O(N)` as well.

## Performance

I'm using [JMH Benchmarks](http://openjdk.java.net/projects/code-tools/jmh/) to benchmark performance.

In the unit tests, you will find one test case for 100 parking spaces and another for 100,000 parking spaces, as well as the examples from the requirements.

After running tests, you will find the test reports in your project directory at `build/reports/tests/test/index.html`.

In the HTML report, navigate to `com.aiincube.generated	> ParknavTest_jmhType` and you will see the benchmark results.

Running on my own machine, I get the following:

|Test	                                                |Duration|Result |
|-------------------------------------------------------|--------|-------|
|whenFirstExampleFromRequirements_thenAtLeast2Cameras   |0s      |passed |
|whenOneHundredParkingSpaces_thenAtLeast9Cameras        |0.003s  |passed |
|whenOneHundredThousandParkingSpaces_thenAtLeast9Cameras|3.800s  |passed |
|whenSecondExampleFromRequirements_thenAtLeast3Cameras  |0.001s  |passed |
|whenThirdExampleFromRequirements_thenAtLeast3Cameras   |0s      |passed |

For 100 parking spaces, takes about 3 milliseconds.

For 100,000 parking spaces, takes about 3.8 seconds.
