package com.shpp.p2p.cs.vmarchenko.assignment6.Assignment6Part3;

/**
 * Implements the basic logic of converting a
 * tone matrix into a sound wave that can be played back.
 */
public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

		for(int row = 0; row < toneMatrix.length; row++) {
            if(toneMatrix[row][column]){
                for(int i = 0; i < samples[row].length; i++) {
                    result[i] += samples[row][i];
                }
            }
        }

        double maxValue = findMaxValue(result);

        normalize(result, maxValue);

        return result;
    }

    /**
     * Normalizes the sound wave between -1 and 1
     *
     * @param array an array of numbers representing a sound wave.
     * @param maxValue maximum value in a sound wave
     */
    public static void normalize(double[] array, double maxValue) {
        // avoid normalization in cases where it is not needed
        if(maxValue > 1){
            for(int i = 0; i < array.length; i++) {
                array[i] /= maxValue;
            }
        }
    }

    /**
     * Method for finding the maximum value in a sound wave
     *
     * @param array an array of numbers representing a sound wave.
     * @return the maximum value in a sound wave.
     */
    public static double findMaxValue(double[] array) {
        double value = 1;
        for(int i = 0; i < array.length; i++) {
            if(Math.abs(array[i]) > value) {
                value = Math.abs(array[i]);
            }
        }
        return value;
    }
}