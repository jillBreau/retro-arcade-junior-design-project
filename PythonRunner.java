import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class PythonRunner implements ActionListener, KeyListener
{

    private Python python;

    private JFrame jframe;

    private PythonRP renderpanel;

    private Timer timer = new Timer(20, this);

    public static final int SCALE = 10;

    private int ticks = 0;

    private Random random;

    public static boolean over, paused, started, newG, load;

    public static Dimension dim;

    private Saver ps;
    private Loader pl;


    public PythonRunner()
    {
        jframe = new JFrame("Python");
        dim = Toolkit.getDefaultToolkit().getScreenSize();

        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setSize(805,700);
        jframe.setLocation(dim.width/2 - jframe.getWidth()/2, dim.height/2 - jframe.getHeight()/2 );

        jframe.add(renderpanel = new PythonRP());

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.addKeyListener(this);

        python = new Python();

        ps = new Saver();

        pl = new Loader();

        startGame();

    }

    public Python getPython()
    {
        return python;
    }




    public void startGame()
    {
        if (newG) {
            over = false;
            paused = false;
            python.setScore(0);
            python.setTime(0);
            python.setTailLength(5);
            python.setDirection(1);
            random = new Random();
            python.clearPython();
            python.setHead(random.nextInt(30) + 25, random.nextInt(35));
            python.setCherry(random.nextInt(79), random.nextInt(66));

            timer.start();
        }
        if (load)
        {
            loadGame();
        }

    }

    public void loadGame()
    {
        pl.PythonLoad();

        over = false;
        paused = false;

        python.setScore(pl.getLoadedPython().getScore());
        python.setTime(pl.getLoadedPython().getTime());
        python.setTailLength(pl.getLoadedPython().getTailLength());
        python.setDirection(pl.getLoadedPython().getDirection());
        random = new Random();
        python.setPythonParts(pl.getLoadedPython().getPython());
        python.setHead(pl.getLoadedPython().getHead().x, pl.getLoadedPython().getHead().y);
        python.setCherry(pl.getLoadedPython().getCherry().x, pl.getLoadedPython().getCherry().y);
               
        timer.start();
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (started)
        {
            renderpanel.repaint();
            ticks++;

            if (ticks % 2 == 0 && python.getHead() != null && !over && !paused && started ) {
                python.incTime();

                python.addToPython();

                if (python.getDirection() == 0) { // Check for UP
                    if (python.getHead().y - 1 >= 0 && noTailAt(python.getHead().x, python.getHead().y - 1))
                    {
                         python.setHead(python.getHead().x, python.getHead().y - 1);
                    }
                    else
                    {
                        if(getPython().getScore() > GameRunner.getHighScore().getPyHighScore())
                        {
                            GameRunner.getHighScore().setPyHighScore(getPython().getScore());
                            GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                        }
                        over = true;
                    }
                }

                if (python.getDirection() == 1) { //Check for DOWN
                    if (python.getHead().y + 1 < 66 && noTailAt(python.getHead().x, python.getHead().y + 1))
                    {
                        python.setHead(python.getHead().x, python.getHead().y + 1);
                    }
                    else
                    {
                        if(getPython().getScore() > GameRunner.getHighScore().getPyHighScore())
                        {
                            GameRunner.getHighScore().setPyHighScore(getPython().getScore());
                            GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                        }
                        over = true;
                    }
                }

                if (python.getDirection() == 2) { //Check for LEFT
                    if (python.getHead().x - 1 >= 0 && noTailAt(python.getHead().x - 1, python.getHead().y))
                    {
                        python.setHead(python.getHead().x - 1, python.getHead().y);
                    }
                    else
                    {
                        if(getPython().getScore() > GameRunner.getHighScore().getPyHighScore())
                        {
                            GameRunner.getHighScore().setPyHighScore(getPython().getScore());
                            GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                        }
                        over = true;
                    }
                }

                if (python.getDirection() == 3) { // Check for RIGHT
                    if (python.getHead().x + 1 < 79 && noTailAt(python.getHead().x + 1, python.getHead().y))
                    {
                        python.setHead(python.getHead().x + 1, python.getHead().y);
                    }
                    else
                    {
                        if(getPython().getScore() > GameRunner.getHighScore().getPyHighScore())
                        {
                            GameRunner.getHighScore().setPyHighScore(getPython().getScore());
                            GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                        }
                        over = true;
                    }
                }

                if (python.getPython().size() > python.getTailLength())
                {
                    python.removeFromPython();
                }

                if (python.getCherry() != null) {
                    if (python.getHead().equals(python.getCherry())) {
                        python.incScore();
                        python.incTailLength();
                        python.changeCherry();
                    }
                }
            }
        }
    }

    public boolean noTailAt(int x, int y)
    {
        for (Point point : python.getPython())
        {
            if (point.equals(new Point(x, y)))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int i = e.getKeyCode();

        if (i == KeyEvent.VK_LEFT && python.getDirection() != 2)
        {
            python.setDirection(2);
        }
        if (i == KeyEvent.VK_RIGHT && python.getDirection() != 3)
        {
            python.setDirection(3);
        }
        if (i == KeyEvent.VK_UP && python.getDirection() != 0)
        {
            python.setDirection(0);
        }
        if (i == KeyEvent.VK_DOWN && python.getDirection() != 1)
        {
            python.setDirection(1);
        }
        if (i == KeyEvent.VK_SPACE)
        {
            if (over) {
                newG = true;
                load = false;
                startGame();
            }
            else
                paused = !paused;
        }
        if (i == KeyEvent.VK_N)
        {
            if (!started)
            {
                started = true;
                load = false;
                newG = true;
                startGame();
            }
        }
        if (i == KeyEvent.VK_S)
        {
            if (paused)
            {
                ps.SerializePython(python);
            }
        }
        if (i == KeyEvent.VK_L)
        {
            if(!started || over)
            {
                started = true;
                newG = false;
                load = true;
                loadGame();
            }
        }

    }


    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }


}
