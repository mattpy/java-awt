package imagefilters;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class Grayscale extends RGBImageFilter implements PluginFilter {
    @Override
    public Image filter(Frame frame, Image input) {
        return frame.createImage(new FilteredImageSource(input.getSource(), this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
        int r = 0xff & (rgb >> 16);
        int g = 0xff & (rgb >> 8);
        int b = 0xff & rgb;
        int l = (int) (0.2126f * r + 0.7152f * g + 0.0722f * b);
        return 0xff000000 | l << 16 | l << 8 | l;
    }
}
