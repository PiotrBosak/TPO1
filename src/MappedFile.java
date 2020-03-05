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

public class MappedFile {
    public static final int INT_SIZE = 4;
    public static final int NUMBER_OF_ELEMENTS = 3;
    public static final int BUFFER_SIZE = INT_SIZE*NUMBER_OF_ELEMENTS;
    public static final int SLEEP_TIME = 1000;
    public static final File FILE = new File("file.dat");
    public static final int LAST_WRITE = 1;
    public static final int LAST_READ = 0;
    public static final Random random = new Random();

    public static void map() throws IOException {
        RandomAccessFile file = new RandomAccessFile(FILE,"rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer buf;
        buf = channel.map(
                FileChannel.MapMode.READ_WRITE,
                0,
                BUFFER_SIZE);
        IntBuffer intBuffer = buf.asIntBuffer();
        while(true){
            intBuffer.rewind();
            int check = intBuffer.get();
            if(check == LAST_WRITE)
                sleep();
            else if(check == LAST_READ){
                intBuffer.rewind();
                intBuffer.put(LAST_WRITE);
                intBuffer.put()
            }
        }








    }

    public static  void sleep(){
        try{
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }






}
