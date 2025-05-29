package Tree;

public class Tree {
    public static void main(String[] args) {

        Node r = new Node(1);
        r.setLeft(new Node(2));
        r.setRight(new Node(3));
        r.getLeft().setLeft(new Node(4));
        r.getLeft().setRight(new Node(5));

        Traversal.order(r);

    }
}