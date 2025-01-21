package imagefilters;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class Invert extends RGBImageFilter implements PluginFilter {
    @Override
    public Image filter(Frame frame, Image input) {
        return frame.createImage(new FilteredImageSource(input.getSource(), this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
        int r = 0xff - (rgb >> 16) & 0xff;
        int g = 0xff - (rgb >> 8) & 0xff;
        int b = 0xff - rgb & 0xff;
        return 0xff000000 | r << 16 | g << 8 | b;
    }
}
