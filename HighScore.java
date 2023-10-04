import java.io.Serializable;

public class HighScore implements Serializable {

    public static final long serialVersionUID = 1L;

    private int bbHighScore;
    private int pyHighScore;
    private int pingHighScore;

    public int getBbHighScore() {
        return bbHighScore;
    }

    public void setBbHighScore(int bbHighScore) {
        this.bbHighScore = bbHighScore;
    }

    public int getPyHighScore() {
        return pyHighScore;
    }

    public void setPyHighScore(int pyHighScore) {
        this.pyHighScore = pyHighScore;
    }

    public int getPingHighScore() {
        return pingHighScore;
    }

    public void setPingHighScore(int pingHighScore) {
        this.pingHighScore = pingHighScore;
    }
}
