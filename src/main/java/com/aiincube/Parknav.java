package com.aiincube;

import java.util.HashSet;
import java.util.Set;

class Parknav {

    static int findMinimumNumberOfCameras(int cameraRange, int[] parkingSpaces) {
        int camerasUsed[] = parkingSpaces.clone();
        int numberOfCamerasUsed = parkingSpaces.length;

        for(int spaceIndex=0; spaceIndex < parkingSpaces.length; spaceIndex++) {
            Set<Integer> spaceCoverage = getSpaceCoverage(spaceIndex, cameraRange, parkingSpaces);
            Set<Integer> neighborsCoverage = getNeighborsCoverage(cameraRange, parkingSpaces, camerasUsed, spaceIndex);

            if(spaceCoverageIsCoveredByNeighbors(spaceCoverage, neighborsCoverage)) {
                camerasUsed[spaceIndex] = -1;
                numberOfCamerasUsed--;
            }
        }
        return numberOfCamerasUsed;
    }

    private static boolean spaceCoverageIsCoveredByNeighbors(Set<Integer> spaceCoverage, Set<Integer> neighborsCoverage) {
        return neighborsCoverage.containsAll(spaceCoverage);
    }

    private static Set<Integer> getNeighborsCoverage(int cameraRange, int[] parkingSpaces, int[] cameras, int spaceIndex) {
        Set<Integer> neighborsCoverage = new HashSet<>();

        for(int neighborIndex : getSpaceCoverageIndexes(spaceIndex, cameraRange, parkingSpaces)) {
            if(spaceHasACamera(cameras[neighborIndex]) && neighborIndex != spaceIndex) {
                neighborsCoverage.addAll(getSpaceCoverage(neighborIndex, cameraRange, parkingSpaces));
            }
        }
        return neighborsCoverage;
    }

    private static boolean spaceHasACamera(int camera) {
        return camera > 0;
    }

    private static Set<Integer> getSpaceCoverage(int spaceIndex, int cameraRange, int[] parkingSpaces) {
        Set<Integer> coverage = new HashSet<>();

        for(int i : getSpaceCoverageIndexes(spaceIndex, cameraRange, parkingSpaces)){
            coverage.add(parkingSpaces[i]);
        }
        return coverage;
    }

    private static Set<Integer> getSpaceCoverageIndexes(int spaceIndex, int cameraRange, int[] parkingSpaces) {
        Set<Integer> indexes = new HashSet<>();

        addLowerNeighborsCoverageIndexes(spaceIndex, cameraRange, parkingSpaces, indexes);
        addUpperNeighborsCoverageIndexes(spaceIndex, cameraRange, parkingSpaces, indexes);
        return indexes;
    }

    private static void addUpperNeighborsCoverageIndexes(int spaceIndex, int cameraRange, int[] parkingSpaces, Set<Integer> indexes) {
        int i = spaceIndex;

        while(i < parkingSpaces.length && parkingSpaces[i] <= parkingSpaces[spaceIndex] + cameraRange) {
            indexes.add(i);
            i++;
        }
    }

    private static void addLowerNeighborsCoverageIndexes(int spaceIndex, int cameraRange, int[] parkingSpaces, Set<Integer> indexes) {
        int i = spaceIndex;

        while(i >= 0 && parkingSpaces[i] >= parkingSpaces[spaceIndex] - cameraRange) {
            indexes.add(i);
            i--;
        }
    }

}
