package SimpleTask;

import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        double n = Math.sqrt(p);
        for(int i = 3; i*i<(n+0.5); i+=2){
            if(p%i==0){
                System.out.println( false);
            }
        }
        System.out.println( true);
    }
}
