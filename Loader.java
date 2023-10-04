import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Loader
{
    private BlockBlaster loadBB;
    private Python loadPython;
    private Ping loadPing;
    private  HighScore loadHS;

    public void HSLoad() {
        try {
            FileInputStream fis = new FileInputStream("highScore.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadHS = (HighScore) ois.readObject();

            ois.close();
            fis.close();

        } catch (Exception i) {
            i.printStackTrace();
            return;
        }
    }

    public HighScore getLoadedHS()
    {
        return loadHS;
    }

    public void BBLoad() {
        try {
            FileInputStream fis = new FileInputStream("blockBlasterSaver.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadBB = (BlockBlaster) ois.readObject();

            ois.close();
            fis.close();

        } catch (Exception i) {
            i.printStackTrace();
            return;
        }
    }

    public BlockBlaster getLoadedBB()
    {
        return loadBB;
    }

    public void PythonLoad() {
        try {
            FileInputStream fis = new FileInputStream("pythonSaver.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadPython = (Python) ois.readObject();

            ois.close();
            fis.close();

        } catch (Exception i) {
            i.printStackTrace();
            return;
        }
    }

    public Python getLoadedPython()
    {
        return loadPython;
    }

    public void PingLoad() {
        try {
            FileInputStream fis = new FileInputStream("pingSaver.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadPing = (Ping) ois.readObject();

            ois.close();
            fis.close();

        } catch (Exception i) {
            i.printStackTrace();
            return;
        }
    }

    public Ping getLoadedPing()
    {
        return loadPing;
    }

}
