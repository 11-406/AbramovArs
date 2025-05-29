package Array;

public class Array {
    public static void main(String[] args) {
        getNum1();
        getSum();
        DoubleMin();
    }

    public static void getNum1() {
        int number = 10;
        int[] a = {1,2,4,5,6,7,8,9,10};
        boolean[] ls = new boolean[number];

        for(int i = 0; i < a.length-1; i++) {
            ls[a[i]] = true;
        }
        int c = 0;
        for(int k = 0; k < ls.length; k++) {
            if(ls[k] == false) {
                c = k;
            }
        }
        System.out.println(c);
    }

    public static void getSum() {
        int sum = 0;
        int[] a = new int[99];
        int[] fullA = new int[100];
        for(int i = 0; i < a.length; i++) {
            sum +=a[i];
        }
        int n = a.length + 1;
        int sumA =n*(n+1)/100;

        System.out.println(sumA - sum);
    }

    public static void DoubleMin() {
        int[] a = {7,4,8,5};
        int min = a[0];
        int min2 = a[0];
        for(int i = 0; i < a.length; i++) {
            if(a[i] < min) {
                min2 = min;
                min = a[i];
            }
            if(a[i] < min2 || a[i] > min) {
                min2 = a[i];
            }
        }
        System.out.println(min);
        System.out.println(min2);
    }


}