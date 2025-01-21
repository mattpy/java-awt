package imagefilters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ImageFilterDemo extends Frame implements ActionListener {
    private final String imageFilepath;

    private Image originalImage;
    private LoadedImage centerImage;

    private Label label;

    private static final String[] filters = {
            "Grayscale", "Invert", "Contrast", "Blur", "Sharpen"
    };

    public ImageFilterDemo(String imageFilepath) {
        this.imageFilepath = imageFilepath;

        addNorthContent();
        addCenterContent();
        addSouthContent();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void loadImage() {
        try {
            File imageFile = new File(imageFilepath);
            originalImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Failed to load image file");
            System.exit(0);
        }
    }

    private void addNorthContent() {
        Panel northPanel = new Panel(new FlowLayout());
        label = new Label("");
        northPanel.add(label);
        add(northPanel, BorderLayout.NORTH);
    }

    private void addCenterContent() {
        loadImage();
        Panel centerPanel = new Panel();
        add(centerPanel, BorderLayout.CENTER);
        centerImage = new LoadedImage(originalImage);
        centerPanel.add(centerImage, BorderLayout.CENTER);
    }

    private void addSouthContent() {
        Panel southPanel = new Panel();
        add(southPanel, BorderLayout.SOUTH);

        Button resetButton = new Button("Reset");
        resetButton.addActionListener(this);
        southPanel.add(resetButton);

        for (String filter : filters) {
            Button filterButton = new Button(filter);
            filterButton.addActionListener(this);
            southPanel.add(filterButton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Reset")) {
            centerImage.set(originalImage);
            label.setText("Normal");
        } else {
            try {
                String fullyQualifiedName = String.join(".", getClass().getPackageName(), action);
                PluginFilter pluginFilter = (PluginFilter) (Class.forName(fullyQualifiedName)).getConstructor().newInstance();
                Image filteredImage = pluginFilter.filter(this, originalImage);
                centerImage.set(filteredImage);
                label.setText("Filtered: " + action);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
