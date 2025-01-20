import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        App a = new App();

        a.setSize(new Dimension(500, 500));
        a.setTitle("Learn AWT");
        a.setVisible(true);
    }
}

class App extends Frame {
    private static final int NUM_COLS = 8;
    private static final int NUM_ROWS = 4;

    private final Image[] cell = new Image[NUM_ROWS * NUM_COLS];

    private int tileWidth;
    private int tileHeight;

    App() {
        setLayout(new FlowLayout());

        try {
            Image image = ImageIO.read(new File("image.png"));

            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);

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