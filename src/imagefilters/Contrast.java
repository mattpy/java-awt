package imagefilters;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class Contrast extends RGBImageFilter implements PluginFilter {
    @Override
    public Image filter(Frame frame, Image input) {
        return frame.createImage(new FilteredImageSource(input.getSource(), this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
        int r = 0xff & rgb >> 16;
        r *= (int) ((r > 128) ? 1.2 : 0.8);

        int g = 0xff & rgb >> 8;
        g *= (int) ((g > 128) ? 1.2 : 0.8);

        int b = 0xff & rgb;
        b *= (int) ((b > 128) ? 1.2 : 0.8);

        return 0xff000000 | r << 16 | g << 8 | b;
    }
}
