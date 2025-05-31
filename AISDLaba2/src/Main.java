import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        CreateFile.createFile();
        BTree bTree = new BTree(50);
        try(Scanner scanner = new Scanner(new File("SetNumbers.txt"))){
            List<Integer> allNumbers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                allNumbers.add(scanner.nextInt());
            }


            long timeInsert = 0;
            for (int i = 0; i < allNumbers.size(); i++) {
                long before = System.nanoTime();
                bTree.insert(allNumbers.get(i));
                long after = System.nanoTime();
                timeInsert += (after - before);
            }
            System.out.println(timeInsert/allNumbers.size());
            long timeSearch = 0;
            for (int i = 0; i < 100; i++){
                Random random = new Random();
                int number = allNumbers.get(random.nextInt(allNumbers.size()));
                long before = System.nanoTime();
                bTree.search(number);
                long after = System.nanoTime();
                timeSearch += (after - before);
            }
            System.out.println(timeSearch/100);

            long timeRemove = 0;
            for (int i = 0; i < 1000; i++){
                int number = allNumbers.remove(0);
                long before = System.nanoTime();
                bTree.remove(number);
                long after = System.nanoTime();
                timeRemove += (after - before);
            }
            System.out.println(timeRemove/1000);

        }
        catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + e.getMessage());
            }


    }
}