import java.io.File;
import java.nio.IntBuffer;

public class FileReader {
    public static final File FILE = new File("file.dat");
    public static final int LAST_WRITE = 1;
    public static final int LAST_READ = 0;
    public static int firstValue;
    public static int secondValue;




    public static void read(){
        IntBuffer buffer = FileWriter.map(FILE);

        while(true){
            buffer.rewind();
            int check = buffer.get();
            if(check == LAST_READ){
                FileWriter.sleep();
                System.out.println("writing not finished yet");
            }
            else if(check == LAST_WRITE){
                firstValue = buffer.get();
                secondValue = buffer.get();
                System.out.println(firstValue+secondValue);
                buffer.rewind();
                buffer.put(LAST_READ);
                FileWriter.sleep();
            }
            else return;
        }




    }

    public static void main(String[] args) {
        read();
    }
}
