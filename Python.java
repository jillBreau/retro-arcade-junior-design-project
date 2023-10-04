import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Python implements Serializable {

    public static final long serialVersionUID = 42L;

    private ArrayList<Point> pythonParts = new ArrayList<>();

    private final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    private int direction = DOWN, score, tailLength, time;

    private Random random;

    private Point head, cherry;

    public Point getHead()
    {
        return head;
    }

    public void setHead(int x, int y)
    {
        head = new Point(x, y);
    }

    public Point getCherry()
    {
        return cherry;
    }

    public void setCherry(int x, int y)
    {
        cherry = new Point(x, y);
    }

    public void changeCherry()
    {
        random = new Random();
        cherry.setLocation(random.nextInt(79), random.nextInt(66));
    }

    public void setTailLength(int length)
    {
        tailLength = length;
    }

    public void incTailLength()
    {
        tailLength++;
    }

    public int getTailLength()
    {
        return tailLength;
    }

    public ArrayList<Point> getPython()
    {
        return pythonParts;
    }

    public void setPythonParts(ArrayList<Point> list)
    {
        pythonParts = list;
    }

    public void addToPython()
    {
        pythonParts.add(new Point(head.x, head.y));
    }

    public void removeFromPython()
    {
        pythonParts.remove(0);
    }

    public void clearPython()
    {
        pythonParts.clear();
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int d)
    {
        if (d == 0)
        {
            direction = UP;
        }
        if (d == 1)
        {
            direction = DOWN;
        }
        if (d == 2)
        {
            direction = LEFT;
        }
        if (d == 3)
        {
            direction = RIGHT;
        }
    }

    public void incScore()
    {
        score += 10;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int sc)
    {
        score = sc;
    }

    public void incTime()
    {
        time++;
    }

    public void setTime(int t)
    {
        time = t;
    }

    public int getTime()
    {
        return time;
    }

}
