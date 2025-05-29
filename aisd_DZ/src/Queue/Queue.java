package Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

public class Queue <T>{
    private Deque <T> st1, st2;
    public Queue(){
        st1 = new ArrayDeque<>();
        st2 = new ArrayDeque<>();
    }

    public void add(T num){
        st1.add(num);
    }

    public T remove(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        else if (!st2.isEmpty()){
            return st2.pop();
        }else{
            reverse();
            return st2.pop();
        }
    }

    private void reverse(){
        while(!st1.isEmpty()){
            st2.add(st1.remove());
        }
    }

    public boolean isEmpty(){
        return(st1.isEmpty()) && (st2.isEmpty());
    }

}