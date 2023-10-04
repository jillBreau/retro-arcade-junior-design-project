import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class BlockBlaster implements Serializable {

    public static final long serialVersionUID = 32L;

    private int bricks[][];

    private final int LEFT = 0, RIGHT = 1;

    private int direction = LEFT, score, time;

    private int level, totalBricks, col, row;

    private Point ball, paddle;

    private int ballXdir;
    private int ballYdir;

    public void setLevel(int L)
    {
        level = L;
    }

    public int getLevel()
    {
        return level;
    }

    public void setBallXdir(int dir)
    {
        ballXdir = dir;
    }

    public int getBallXdir()
    {
        return ballXdir;
    }

    public void setBallYdir(int dir)
    {
        ballYdir = dir;
    }

    public int getBallYdir()
    {
        return ballYdir;
    }


    public void setBricksOG(int row, int col)
    {
        bricks = new int[row][col];
        for (int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                bricks[i][j] = 1;
            }
        }

    }

    public void eraseBrick(int i, int j)
    {
        bricks[i][j] = 0;
    }

    public int getOneBrick(int i, int j)
    {
        return bricks[i][j];
    }

    public void setBricks(int[][] input){
        bricks = input;
    }

    public int[][] getBricks(){
        return bricks;
    }


    public void setCol(int c){
        col = c;
    }

    public int getCol(){
        return col;
    }

    public void setRow(int r){
        row = r;
    }

    public int getRow(){
        return row;
    }

    public Point getBall()
    {
        return ball;
    }

    public void setBall(int x, int y)
    {
        ball = new Point(x, y);
    }

    public void decTotalBricks()
    {
        totalBricks--;
    }

    public void setTotalBricks(int colMulRow)
    {
        totalBricks = colMulRow;
    }

    public int getTotalBricks()
    {
        return totalBricks;
    }


//    public ArrayList<Point> getBricks()
//    {
//        return bricks;
//    }
//
//    public void setBricks(ArrayList<Point> list)
//    {
//        bricks = list;
//    }
//
//    public void addToBricks()
//    {
//        bricks.add(new Point()); /////// coordinates??
//    }
//
//    public void clearBricks()
//    {
//        bricks.clear();
//    }


    public void setPaddle(int x, int y)
    {
        paddle = new Point(x, y);
    }

    public Point getPaddle()
    {
        return paddle;
    }


    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int d)
    {
        if (d == 0)
        {
            direction = LEFT;
        }
        if (d == 1)
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
