import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GameRunner implements ActionListener {
    public static GameRunner gameRunner;
    private JFrame arcade;
    private JButton pythonButton, bbButton, pingButton;
    private Dimension dim;
    public static PythonRunner pythonRunner;
    public static BBRunner bbRunner;
    public static PingRunner pingRunner;
    public static HighScore highScore;
    private JLabel imageLabel, mainMenu, pyHighScore, bbHighScore, pingHighScore, makers, empty, empty2, empty3, empty4, empty5;
    private BoxLayout bl;
    public static Saver hsSaver;
    private Loader hsLoader;

    public GameRunner() {
        hsLoader = new Loader();
        hsSaver = new Saver();
        highScore = new HighScore();

        if (new File("highScore.txt").isFile()){
            hsLoader.HSLoad();
            highScore.setBbHighScore(hsLoader.getLoadedHS().getBbHighScore());
            highScore.setPingHighScore(hsLoader.getLoadedHS().getPingHighScore());
            highScore.setPyHighScore(hsLoader.getLoadedHS().getPyHighScore());
        }else
        {
            highScore.setBbHighScore(0);
            highScore.setPingHighScore(0);
            highScore.setPyHighScore(0);
            hsSaver.SerializeHS(highScore);
        }

        String pyHS = "Current Python High Score: " + highScore.getPyHighScore();
        String bbHS = "Current Block Blaster High Score: " + highScore.getBbHighScore();
        String pingHS = "Current Ping Overall Rallies High Score: " + highScore.getPingHighScore();
        String authors = "Created by Matthew Hiscock, Victoria Petrov, and Jillian Breau";

        dim = Toolkit.getDefaultToolkit().getScreenSize();

        arcade = new JFrame("Arcade");

        arcade.getContentPane().setBackground(Color.BLACK);

        arcade.setSize(600, 600);

        arcade.setLocation(dim.width / 2 - arcade.getWidth() / 2, dim.height / 2 - arcade.getHeight() / 2);

        arcade.setResizable(false);

        bl = new BoxLayout(arcade.getContentPane(), BoxLayout.Y_AXIS);

        arcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        arcade.setLayout(bl);

        imageLabel = new JLabel(new ImageIcon("./arcadeFont.png"));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainMenu = new JLabel("Main Menu");
        mainMenu.setFont(new Font("Monospaced", Font.PLAIN, 25));
        mainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenu.setForeground(Color.GREEN);

        empty = new JLabel("       ");
        empty.setFont(new Font("Monospaced", Font.PLAIN, 30));
        empty.setAlignmentX(Component.CENTER_ALIGNMENT);
        empty.setForeground(Color.GREEN);

        empty2 = new JLabel("       ");
        empty2.setFont(new Font("Monospaced", Font.PLAIN, 25));
        empty2.setAlignmentX(Component.CENTER_ALIGNMENT);
        empty2.setForeground(Color.GREEN);

        empty3 = new JLabel("       ");
        empty3.setFont(new Font("Monospaced", Font.PLAIN, 25));
        empty3.setAlignmentX(Component.CENTER_ALIGNMENT);
        empty3.setForeground(Color.GREEN);

        empty4 = new JLabel("       ");
        empty4.setFont(new Font("Monospaced", Font.PLAIN, 80));
        empty4.setAlignmentX(Component.CENTER_ALIGNMENT);
        empty4.setForeground(Color.GREEN);

        empty5 = new JLabel("       ");
        empty5.setFont(new Font("Monospaced", Font.PLAIN, 10));
        empty5.setAlignmentX(Component.CENTER_ALIGNMENT);
        empty5.setForeground(Color.GREEN);

        pyHighScore = new JLabel(pyHS);
        pyHighScore.setFont(new Font("Monospaced", Font.PLAIN, 15));
        pyHighScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        pyHighScore.setForeground(Color.GREEN);

        pingHighScore = new JLabel(pingHS);
        pingHighScore.setFont(new Font("Monospaced", Font.PLAIN, 15));
        pingHighScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        pingHighScore.setForeground(Color.GREEN);

        bbHighScore = new JLabel(bbHS);
        bbHighScore.setFont(new Font("Monospaced", Font.PLAIN, 15));
        bbHighScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        bbHighScore.setForeground(Color.GREEN);

        makers = new JLabel(authors);
        makers.setFont(new Font("Monospaced", Font.PLAIN, 15));
        makers.setAlignmentX(Component.CENTER_ALIGNMENT);
        makers.setForeground(Color.GREEN);

        pythonButton = new JButton("Play Python");
        pythonButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        pythonButton.addActionListener(this);
        pythonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pythonButton.setBackground(Color.GREEN);

        bbButton = new JButton("Play Block Blaster");
        bbButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        bbButton.addActionListener(this);
        bbButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bbButton.setBackground(Color.GREEN);

        pingButton = new JButton("Play Ping");
        pingButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        pingButton.addActionListener(this);
        pingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pingButton.setBackground(Color.GREEN);

        arcade.add(empty5);

        arcade.add(imageLabel);

        arcade.add(mainMenu);

        arcade.add(empty);

        arcade.add(pythonButton);

        arcade.add(pyHighScore);

        arcade.add(empty2);

        arcade.add(bbButton);

        arcade.add(bbHighScore);

        arcade.add(empty3);

        arcade.add(pingButton);

        arcade.add(pingHighScore);

        arcade.add(empty4);

        arcade.add(makers);

        arcade.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (arg0.getSource() == pythonButton)
        {
            pythonRunner = new PythonRunner();
        }

        if (arg0.getSource() == bbButton)
        {
            bbRunner = new BBRunner();
        }

        if (arg0.getSource() == pingButton)
        {
            pingRunner = new PingRunner();
        }

    }

    public BBRunner getBBRunner()
    {
        return bbRunner;
    }

    public PythonRunner getPythonRunner() { return pythonRunner; }

    public PingRunner getPingRunner() { return pingRunner; }


    public static void main(String[] args)
    {
        new GameRunner();

    }

    public static HighScore getHighScore() {
        return highScore;
    }

    public static Saver getHsSaver()
    {
        return hsSaver;
    }

}
