import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class FileWriter {
    public static final int INT_SIZE = 4;
    public static final int NUMBER_OF_ELEMENTS = 3;
    public static final int BUFFER_SIZE = INT_SIZE*NUMBER_OF_ELEMENTS;
    public static final int SLEEP_TIME = 1000;
    public static final File FILE = new File("file.dat");
    public static final int LAST_WRITE = 1;
    public static final int LAST_READ = 0;
    public static final Random random = new Random();
    public static int firstValue;
    public static int secondValue;
    public static IntBuffer intBuffer;



    public static void write() throws IOException {
       intBuffer = map(FILE);
        while(true){
            intBuffer.rewind();
            int check = intBuffer.get();
            if(check == LAST_WRITE) {
                sleep();
                System.out.println("reading hasnt finished yet");
            }
            else if(check == LAST_READ){
                firstValue = random.nextInt();
                secondValue = random.nextInt();
                intBuffer.rewind();
                intBuffer.put(LAST_WRITE);
                intBuffer.put(firstValue);
                intBuffer.put(secondValue);
                intBuffer.rewind();
                sleep();
            }
            else return;
        }
    }

        public static IntBuffer map(File file) {
        IntBuffer intBuffer = null;
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                FileChannel channel = raf.getChannel();
                MappedByteBuffer buf;
                buf = channel.map(
                        FileChannel.MapMode.READ_WRITE,
                        0,
                        BUFFER_SIZE);
                intBuffer = buf.asIntBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return intBuffer;

        }

    public static  void sleep(){
        try{
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            write();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }






}
