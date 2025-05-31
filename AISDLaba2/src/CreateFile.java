import java.io.*;
import java.util.Random;

public class CreateFile {

    public static void createFile() {
        File file = new File("SetNumbers.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < 10000; i++){
            Random rand = new Random();
            try (FileOutputStream outputStream = new FileOutputStream(file, true)) {
                String s = Integer.toString(rand.nextInt(0, 100000)) + " ";
                outputStream.write(s.getBytes());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
