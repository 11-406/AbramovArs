package Classwork2805;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String s = "aplle";
        Map<Character, Long> map = s.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        //Сделал через stream(поток позволяющий преобразовывать данные)

    }
}