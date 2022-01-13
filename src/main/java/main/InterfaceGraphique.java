package main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class InterfaceGraphique extends JFrame implements KeyListener {
    private Julia julia;
    private ImageIcon imageicon;
    private JLabel jlabel = new JLabel();
    private JButton button;

    /* JTextField pour les valeurs entrées par l'user */
    private JTextField textReel;
    private JTextField textImag;
    private JTextField textZoom;
    private JTextField textIter;

    private JTextField textHeight;

    private JLabel error; // Afficher l'erreur si l'user n'a pas mis les bonnes valeurs

    public InterfaceGraphique() {

    }

    public InterfaceGraphique(Julia julia, int height, int width) throws InterruptedException {
        super("Fractale");
        this.julia = julia;
        /* Génération de l'ensemble de Julia */
        julia.generateJulia();
        this.setSize(height, width);
        this.setLayout(new FlowLayout());

        try {
            /* "Lecture" de l'image généré par generateJulia() */
            imageicon = new ImageIcon(julia.getImage());
            jlabel.setIcon(imageicon);

            this.add(jlabel);

        } catch (Exception e) {
            e.printStackTrace();

        }

        addKeyListener(this);

        this.setResizable(false);
        this.setVisible(true);

    }

    public void generateIG() {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(800, 500));
        button = new JButton("Envoie");
        button.addActionListener((Event) -> {
            // TODO Auto-generated method stub
            if (Event.getSource() == button) {
                String s = textReel.getText();
                String s2 = textImag.getText();
                String s3 = textZoom.getText();
                String s4 = textIter.getText();
                String s5 = textHeight.getText();
                try {
                    /* Parsing des valeurs des JTextField */
                    double reel = Double.parseDouble(s);
                    double imag = Double.parseDouble(s2);
                    double zoom = Double.parseDouble(s3);
                    int iter = Integer.parseInt(s4);
                    int height = Integer.parseInt(s5);

                    Complexe c = new Complexe(reel, imag);

                    new InterfaceGraphique(Julia.newJulia(c, zoom, iter, height, height), height, height);

                } catch (NumberFormatException | InterruptedException except) {
                    error.setVisible(true);
                }

            }
        });

        /* Ajout JTextField/Bouton */
        textReel = new JTextField("Reel (ex:-1.476)");
        textReel.setPreferredSize(new Dimension(200, 40));
        textImag = new JTextField("Imaginaire (ex:0)");
        textImag.setPreferredSize(new Dimension(200, 40));
        textZoom = new JTextField("Zoom (ex:1)");
        textZoom.setPreferredSize(new Dimension(200, 40));
        textIter = new JTextField("Iteration (ex:200)");
        textIter.setPreferredSize(new Dimension(200, 40));
        textHeight = new JTextField("Taille (ex:1000)");
        textHeight.setPreferredSize(new Dimension(100, 40));
        button.setPreferredSize(new Dimension(200, 40));
        this.add(textReel);
        this.add(textImag);
        this.add(textZoom);
        this.add(textIter);
        this.add(textHeight);
        this.add(button);

        error = new JLabel("Merci de mettre des nombres et des points à la place des virgules.(ex : 0.3)");
        error.setVisible(false);
        this.add(error);
        this.setResizable(false);

        setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /* Fonction pour gérer les entrées clavier */
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyChar() == '+' || e.getKeyChar() == 'k') {
            julia.addZoom();

            generateNewJulia();
        } else if (e.getKeyChar() == '-' || e.getKeyChar() == 'l') {
            julia.lessZoom();

            generateNewJulia();
        } else if (e.getKeyChar() == 'z') {
            julia.toTop();

            generateNewJulia();
        } else if (e.getKeyChar() == 's') {
            julia.toBottom();

            generateNewJulia();
        } else if (e.getKeyChar() == 'q') {
            julia.toLeft();
            generateNewJulia();
        } else if (e.getKeyChar() == 'd') {
            julia.toRight();
            generateNewJulia();
        } else if (e.getKeyChar() == 'g') {
            julia.writeIMG();
        }

    }

    public void generateNewJulia() {

        imageicon = new ImageIcon(julia.getImage());
        jlabel.setIcon(imageicon);
        this.add(jlabel);

    }

}