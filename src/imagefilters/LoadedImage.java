package imagefilters;

import java.awt.*;

public class LoadedImage extends Canvas {
    private Image image;

    public LoadedImage(Image img) {
        set(img);
    }

    public void set(Image img) {
        image = img;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (image == null) {
            g.drawString("No image", 10, 30);
        } else {
            g.drawImage(image, 0, 0, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(this), image.getHeight(this));
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
