package imagedemos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

public class CropImageFilterDemo extends Frame {
    private static final int NUM_COLS = 4;
    private static final int NUM_ROWS = 4;

    private final Image[] cell = new Image[NUM_ROWS * NUM_COLS];

    private int tileWidth;
    private int tileHeight;

    public CropImageFilterDemo() {
        setLayout(new FlowLayout());

        try {
            Image image = ImageIO.read(new File("C:\\Users\\caspe\\Pictures\\Screenshots\\Screenshot 2024-07-31 011014.png"));

            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);

            setSize(new Dimension(imageWidth, imageHeight));

            tileWidth = imageWidth / NUM_COLS;
            tileHeight = imageHeight / NUM_ROWS;

            for (int y = 0; y < NUM_ROWS; y++) {
                for (int x = 0; x < NUM_COLS; x++) {
                    CropImageFilter cif = new CropImageFilter(tileWidth * x, tileHeight * y, tileWidth, tileHeight);
                    FilteredImageSource fis = new FilteredImageSource(image.getSource(), cif);
                    int cellIndex = y * NUM_COLS + x;
                    cell[cellIndex] = createImage(fis);
                }
            }

            randomizeCells();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button randomizeButton = new Button("Randomize");
        randomizeButton.addActionListener(e -> {
            randomizeCells();
            repaint();
        });
        add(randomizeButton);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void randomizeCells() {
        int numCells = NUM_ROWS * NUM_COLS;
        for (int i = 0; i < numCells * 2; i++) {
            int randomCellOne = (int) (Math.random() * numCells);
            int randomCellTwo = (int) (Math.random() * numCells);
            Image temp = cell[randomCellOne];
            cell[randomCellOne] = cell[randomCellTwo];
            cell[randomCellTwo] = temp;
        }
    }

    @Override
    public void update(Graphics g) {
        // Prevent automatic screen clearing
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        for (int y = 0; y < NUM_ROWS; y++) {
            for (int x = 0; x < NUM_COLS; x++) {
                g.drawImage(
                        cell[y * NUM_COLS + x],
                        x * tileWidth + getInsets().left,
                        y * tileHeight + getInsets().top,
                        null
                );
            }
        }
    }
}
