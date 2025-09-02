package com.shpp.p2p.cs.vmarchenko.assignment6.Assignment6Part1;

import acm.graphics.*;

import java.awt.*;

/**
 * The program implements a mechanism for hiding information in an image,
 * as well as restoring the same information through the interface.
 */
public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixels = source.getPixelArray();

        int row = pixels[0].length;
        int col = pixels.length;

        boolean[][] result = new boolean[col][row];

        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                Color c = new Color(pixels[i][j]);
                result[i][j] = c.getRed() % 2 != 0;
            }
        }

        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int row = message[0].length;
        int col = message.length;

        int[][] pixels = source.getPixelArray();

        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                Color c = new Color(pixels[i][j]);
                int redComponent = c.getRed();

                if(message[i][j]) {
                    if (c.getRed() % 2 == 0) {
                        redComponent = redComponent + 1;
                    }
                } else {
                    if(c.getRed() % 2 != 0) {
                        redComponent = redComponent - 1;
                    }
                }

                Color updatedColor = new Color(redComponent, c.getGreen(), c.getBlue());
                pixels[i][j] = updatedColor.getRGB();
            }
        }

        return new GImage(pixels);
    }
}