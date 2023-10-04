import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Saver
{
    public void SerializeHS(HighScore p)
    {
        try {
            File saveFile = new File("highScore.txt");
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos .writeObject(p);
            oos.close();
            fos.close();

        }
        catch(Exception i){
            i.printStackTrace();
        }
    }
    public void SerializeBB(BlockBlaster p)
    {
        try {
            File saveFile = new File("blockBlasterSaver.txt");
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos .writeObject(p);
            oos.close();
            fos.close();

        }
        catch(Exception i){
            i.printStackTrace();
        }
    }

    public void SerializePython(Python p)
    {
        try {
            File saveFile = new File("pythonSaver.txt");
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos .writeObject(p);
            oos.close();
            fos.close();

        }
        catch(Exception i){
            i.printStackTrace();
        }
    }

    public void SerializePing(Ping p)
    {
        try {
            File saveFile = new File("pingSaver.txt");
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos .writeObject(p);
            oos.close();
            fos.close();

        }
        catch(Exception i){
            i.printStackTrace();
        }
    }

}
