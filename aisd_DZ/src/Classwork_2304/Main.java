package Classwork_2304;

public class Main {

    public static void main(String[] args) {
        System.out.println(nod(7,3));

    }
    public static int nod(int a, int b) {

        if(b!=0) {
            return nod(b,a%b);
        } else {
            return a;
        }

    }
}
