package com.shpp.p2p.cs.vmarchenko.assignment6.Assignment6Part3;

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
        double maxValue = Double.MIN_VALUE;

		for(int row = 0; row < toneMatrix.length; row++) {
            if(toneMatrix[row][column]){
                for(int i = 0; i < samples[row].length; i++) {
                    result[i] += samples[row][i];
                    if (Math.abs(result[i]) > maxValue) {
                        maxValue = Math.abs(result[i]);
                    }
                }
            }
        }

        for(int i = 0; i < result.length; i++) {
            result[i] /= maxValue;
        }

        return result;
    }
}
