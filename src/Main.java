import imagefilters.ImageFilterDemo;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ImageFilterDemo a = new ImageFilterDemo("C:\\Users\\caspe\\Pictures\\Screenshots\\Screenshot 2024-07-31 011014.png");

        a.setSize(new Dimension(500, 500));
        a.setTitle("Learn AWT");
        a.setVisible(true);
    }
}

