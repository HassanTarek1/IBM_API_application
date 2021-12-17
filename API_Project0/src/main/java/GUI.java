import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {

    private JFrame frame = new JFrame();
    private Translator translator;
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public GUI() {

        translator=new Translator("j3l2sWcNRX1e2TWI8LX1LtXKQpfyhZ0v30PiylamuR7r","en-it");
        this.textField= new JTextField("", 24);
        this.button= new JButton("Translate");
        // the panel with the button and text
        JPanel panel = new JPanel();
        this.label= new JLabel("Translation appears here",SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        panel.setLayout(new GridLayout(3, 1));
        panel.add(textField);
        panel.add(button);
        panel.add(label);
        button.addActionListener(this);


        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // process the button clicks


    // create one Frame
    public static void main(String[] args) {
        new GUI();
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Translate")){
            String result = translator.translate(textField.getText());
            label.setText(result);
            textField.setText("");
        }
    }
}
