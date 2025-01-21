package imagefilters;

import java.awt.Frame;
import java.awt.Image;

public interface PluginFilter {
    Image filter(Frame frame, Image input);
}
