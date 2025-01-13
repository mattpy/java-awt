import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        MyApp app = new MyApp();
        app.setSize(new Dimension(300, 300));
        app.setTitle("Menu Bars");
        app.setVisible(true);
    }
}

class MyApp extends Frame {
    MyApp() {
        setLayout(new FlowLayout());

        MenuBar menuBar = new MenuBar();

        Menu section1 = new Menu("File");
        MenuItem open = new MenuItem("Open");
        open.setEnabled(false);
        MenuItem save = new MenuItem("Save");
        MenuItem close = new MenuItem("Close");

        section1.add(open).addActionListener(e -> System.out.println("Opening"));
        section1.add(new MenuItem("-"));
        section1.add(save).addActionListener(e -> System.out.println("Saving"));
        section1.add(new MenuItem(""));
        section1.add(close).addActionListener(e -> System.exit(0));

        Menu section2 = new Menu("Options");
        MenuItem graphics = new MenuItem("Graphics");
        CheckboxMenuItem oc = new CheckboxMenuItem("Occlusion Culling", true);

        section2.add(graphics);
        section2.add(oc);

        menuBar.add(section1);
        menuBar.add(section2);

        setMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}