import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiInput extends JFrame {
    public GuiInput() {
        setTitle("GUI Input");
        setPreferredSize(new Dimension(400, 400));
        TextField tf = new TextField();
        add(tf);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        setVisible(true);
    }
}
