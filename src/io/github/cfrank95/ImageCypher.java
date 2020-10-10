package io.github.cfrank95;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.Random;

class ImageCypher extends Component {

    // How many columns to multiply by itself
    private int numLocs = 100;
    // Best Way to ensure even number of elements
    private int numCells = (int) Math.pow(numLocs, 2);
    // Array of cells
    private int[] cells;
    // Seed number (Used for Decryption)
    long randSeed = 1;

    // Buffer image to write to
    private BufferedImage newImg;
    // Initialization of variables for image characteristics
    int imgWidth, imgHeight, copyWidth, copyHeight;

    // Read Image and evaluate proportions
    public ImageCypher(URL imageSrc) {
        try {
            newImg = ImageIO.read(imageSrc);
            imgWidth = newImg.getWidth(null);
            imgHeight = newImg.getHeight(null);
        } catch (IOException e) {
            System.out.println("Image could not be read");
            System.exit(1);
        }
        copyWidth = imgWidth /numLocs;
        copyHeight = imgHeight /numLocs;
        cells = new int[numCells];
        for (int i = 0; i < numCells; i++) {
            cells[i] = i;
        }
    }

    // Temporary Cypher using random values
    // Seed value used in decryption method
    void cypher() {
        Random rand = new Random();
        rand.setSeed(randSeed);
        int rowIndex;
        for (int i = 0; i < numCells; i++) {
            while ((rowIndex = rand.nextInt(numLocs)) == i);

            int temp = cells[i];
            cells[i] = cells[rowIndex];
            cells[rowIndex] = temp;
        }
    }

    // Part of Component Class
    public Dimension getPreferredSize() {
        return new Dimension(imgWidth, imgHeight);
    }


    public void paint(Graphics g) {
        // Initializing cell destination values
        int destX, destY;

        // Row for-loop (x = Index x)
        for (int x = 0; x < numLocs; x++) {
            int srcX = x * copyWidth;

            // Column for-loop (y = Index y)
            for (int y = 0; y < numLocs; y++) {
                int srcY = y * copyHeight;
                int cell = cells[x * numLocs + y];
                destX = (cell / numLocs) * copyWidth;
                destY = (cell % numLocs) * copyHeight;

                g.drawImage(newImg,
                        destX, destY, destX + copyWidth, destY + copyHeight,
                        srcX, srcY, srcX + copyWidth, srcY + copyHeight,
                        null);
            }
        }
    }
}
