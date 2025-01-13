import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        MyApp app = new MyApp();
        app.setSize(new Dimension(500, 500));
        app.setTitle("Menu Bars");
        app.setVisible(true);
    }
}

class MyApp extends Frame {
    MyApp() {
        setLayout(new FlowLayout());

        Dialog dialog = new Dialog(this, "Hello World", false);
        dialog.setSize(new Dimension(250, 250));
        dialog.setLayout(new FlowLayout());
        dialog.setVisible(true);

        dialog.add(new Label("Press this button to close"));

        Button button = new Button("Close");
        dialog.add(button);
        button.addActionListener(e -> dialog.dispose());

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}