package Stack;

public class Stack {
    char[] arr;
    int top;
    public int size;

    public Stack(int size) {
        arr = new char[size];
        this.size = size;
        top = -1;
    }

    public boolean add(char elem) {
        if(!isFull()) {
            arr[top+1] = elem;
            top++;
            return true;
        } else {
            return false;
        }
    }

    public char remove() {
        if(!isPust()){
            return arr[top--];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isFull() {
        return top == size-1;
    }

    public boolean isPust() {
        return top == -1;
    }

}
