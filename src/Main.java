import imagefilters.ImageFilterDemo;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ImageFilterDemo a = new ImageFilterDemo("photo.jpgg");

        a.setSize(new Dimension(500, 500));
        a.setTitle("Learn AWT");
        a.setVisible(true);
    }
}

