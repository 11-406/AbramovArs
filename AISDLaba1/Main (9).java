import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start(){

        CreateFile createFile = new CreateFile();
        createFile.fillFile();
        TreeSort treeSort = new TreeSort();
        try(Scanner scanner = new Scanner(new File("fileNumber.txt"))){
            List<Integer> allNumbers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                allNumbers.add(scanner.nextInt());
            }
            int currentIndex = 0;
            for(int i = 0; i < 60; i++){
                int size = createFile.getList()[i];
                int[] numbers = new int[size];
                for (int j = 0; j < size && currentIndex < allNumbers.size(); j++) {
                    numbers[j] = allNumbers.get(currentIndex);
                    currentIndex++;
                }
                long time = System.nanoTime();
                int[] sorted = treeSort.sort(numbers);
                long time2 = System.nanoTime();
                System.out.println("Количество элементов " + size + " время: " + (time2 - time) + " количество итераций: " + treeSort.getIterations());

            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }

}
