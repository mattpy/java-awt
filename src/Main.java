import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        MyApp app = new MyApp();
        app.setSize(new Dimension(300, 300));
        app.setTitle("Java AWT");
        app.setVisible(true);
    }
}

class MyApp extends Frame {
    private int count = 0;

    MyApp() {
        setLayout(new FlowLayout());

        Button button = new Button("Click");
        button.addActionListener((e) -> {
            count++;
            repaint();
        });
        add(button);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(String.format("You clicked the button %d times", count), 20, 100);
    }
}