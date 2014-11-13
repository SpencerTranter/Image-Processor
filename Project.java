// Initial source-code for ImageProcessor.java
// Given as part of Assignment #6, CSC 110, UVic, Fall 2014
// Originally created by Mike Zastre, last edited by Melanie Tory, Nov. 2014

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class Project {
  public static void main (String[] args) {
    Scanner console = new Scanner (System.in);
    System.out.print("Which picture would you like to use? : ");
    String fileImage = console.nextLine();
    int[][] fileImageArray = readGrayscaleImage(fileImage);
    System.out.print("What would you like the file to be called? : ");
    String fileFinal = console.nextLine();
    writeGrayscaleImage(fileFinal, fileImageArray);

  }

    // THIS METHOD MAY BE CALLED, BUT MUST NOT BE MODIFIED!
    // This method reads an image file.
    // expects one parameter: a filename of an image file to be read
    // returns a 2D array of ints representing grayscale values in the input image
    public static int[][] readGrayscaleImage(String filename) {
        int [][] result = null; //create the array
        try {
            File imageFile = new File(filename);    //create the file
            BufferedImage image = ImageIO.read(imageFile);
            int height = image.getHeight();
            int width  = image.getWidth();
            result = new int[height][width];        //read each pixel value
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = image.getRGB(x, y);
                    result[y][x] = rgb & 0xff;
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Problems reading file named " + filename);
            System.exit(-1);
        }
        return result;  //once we're done filling it, return the new array
    }


    // THIS METHOD MAY BE CALLED, BUT MUST NOT BE MODIFIED!
    // This method creates an output image based on an array of ints and writes it to a file.
    // expects two parameters: a filename for the image file that will be created
    //      and a 2D array of ints that will be converted into the image
    public static void writeGrayscaleImage(String filename, int[][] array) {
        int width = array[0].length;
        int height = array.length;

        try {
            BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);    //create the image

            //set all its pixel values based on values in the input array
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = array[y][x];
                    rgb |= rgb << 8;
                    rgb |= rgb << 16;
                    image.setRGB(x, y, rgb);
                }
            }

            //write the image to a file
            File imageFile = new File(filename);
            ImageIO.write(image, "jpg", imageFile);
        }
        catch (IOException ioe) {
            System.err.println("Problems writing file named " + filename);
            System.exit(-1);
        }
    }
}
