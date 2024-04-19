import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextSenderGUI extends JFrame {
    private JTextArea textArea;
    private JButton button1, button2, button3;

    public TextSenderGUI() {
        setTitle("Text Sender");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initComponents();
        addComponents();

        setVisible(true);
    }

    private void initComponents() {
        textArea = new JTextArea();
        button1 = new JButton("Send Text to App 1");
        button2 = new JButton("Send Text to App 2");
        button3 = new JButton("Send Text to App 3");

        button1.addActionListener(new ButtonListener());
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void addComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();
            String textToSend = textArea.getText();

        }
    }


}
