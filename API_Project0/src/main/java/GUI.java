
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;


public class GUI implements ActionListener,ListSelectionListener {

    private JFrame frame = new JFrame();
    private Translator translator;
    private Custom_TextToSpeech textToSpeech;
    private String resultText;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JButton play;
    private JList list;

    public GUI() {

        translator=new Translator("j3l2sWcNRX1e2TWI8LX1LtXKQpfyhZ0v30PiylamuR7r","en-es");
        this.textToSpeech = new Custom_TextToSpeech("iO6feBiPt2pMw0sLAFBtpPub__AE1O3tM_5LMZF-BBn2");
        this.textField= new JTextField("", 24);
        this.button= new JButton("Translate");
        button.setFont(new Font("Calibri", Font.BOLD, 20));
        Icon icon = new ImageIcon("sound.png");
        this.play=new JButton(icon);
        this.play.setPreferredSize(new Dimension(40,40));
        this.play.setRolloverEnabled(true);
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.setOpaque(false);
        play.setRolloverIcon(new ImageIcon("sound_clicked.png"));
        play.setPressedIcon(new ImageIcon("sound.png"));
        String languages[]={"ar-en","en-ar","en-es","en-fr","en-it","en-de"};
        this.list= new JList(languages);
        list.addListSelectionListener( this);
        // the panel with the button and text
        JPanel panel = new JPanel();
        this.label= new JLabel("Translation appears here",SwingConstants.CENTER);
        textField.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        panel.setLayout(new GridLayout(3, 2));
        panel.add(textField);
        panel.add(label);
        panel.add(button);

        panel.add(play);

        panel.add(list);
        button.addActionListener(this);
        play.addActionListener(this);


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
            this.resultText = translator.translate(textField.getText());
            label.setText(resultText);
//            textField.setText("");
        }else{
            if (s.equals("")){
                try {
                    this.textToSpeech.createSpeech(this.resultText);
                    URL url2 = new URL("file:///"+this.textToSpeech.getPath());
                    Clip clip2 = null;
                    try {
                        clip2 = AudioSystem.getClip();
                        AudioInputStream ais2 = AudioSystem.getAudioInputStream(url2);
                        clip2.open(ais2);
                        clip2.start();
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void valueChanged(ListSelectionEvent e)
    {
        //set the text of the label to the selected value of lists
        String lang = (String) this.list.getSelectedValue();
        this.translator.setModelToTrans(lang);
        //"ar-en","en-es","ar-es","en-fr","en-it"
        switch (lang){
            case "en-es":this.textToSpeech.setSpeaker(SynthesizeOptions.Voice.ES_ES_LAURAV3VOICE);break;
            case "en-fr":this.textToSpeech.setSpeaker(SynthesizeOptions.Voice.FR_FR_RENEEV3VOICE);break;
            case "en-it":this.textToSpeech.setSpeaker(SynthesizeOptions.Voice.IT_IT_FRANCESCAV3VOICE);break;
            case "en-ar":this.textToSpeech.setSpeaker(SynthesizeOptions.Voice.AR_AR_OMARVOICE);break;
            case "en-de":this.textToSpeech.setSpeaker(SynthesizeOptions.Voice.DE_DE_DIETERV3VOICE);break;
            default:this.textToSpeech.setSpeaker(SynthesizeOptions.Voice.EN_US_LISAVOICE);
        }

    }
}
