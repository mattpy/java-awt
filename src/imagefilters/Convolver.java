package imagefilters;

import java.awt.*;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.MemoryImageSource;
import java.util.Hashtable;

public abstract class Convolver implements ImageConsumer, PluginFilter {
    protected int width;
    protected int height;

    protected int[] originalImagePixels;
    protected int[] filteredImagePixels;

    boolean isImageReady = false;

    public abstract void convolve();

    @Override
    public Image filter(Frame frame, Image input) {
        isImageReady = false;
        input.getSource().startProduction(this);
        waitForImage();
        filteredImagePixels = new int[width * height];

        try {
            convolve();
        } catch (Exception e) {
            System.out.println("Convolver failed to convolve");
            e.printStackTrace();
        }

        return frame.createImage(new MemoryImageSource(width, height, filteredImagePixels, 0, width));
    }

    // Synchronized guarantees happens-before relationship
    private synchronized void waitForImage() {
        try {
            while (!isImageReady) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public synchronized void imageComplete(int status) {
        isImageReady = true;
        notifyAll();
    }

    @Override
    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Dummy methods
    @Override
    public void setProperties(Hashtable<?, ?> props) {

    }

    @Override
    public void setColorModel(ColorModel model) {

    }

    @Override
    public void setHints(int hintflags) {

    }
}
