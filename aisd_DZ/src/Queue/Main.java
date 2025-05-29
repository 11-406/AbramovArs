package Queue;

public class Main {
    public static void main(String[] args){
        Queue<Integer> queue = new Queue<Integer>();
        queue.add(2);
        queue.add(13);
        queue.add(6);
        queue.add(7);
        queue.add(23);
        System.out.println(queue.remove());
    }
}
