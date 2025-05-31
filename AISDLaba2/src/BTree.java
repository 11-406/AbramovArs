class BTreeNode {
    int[] keys;
    int t;
    BTreeNode[] children;
    int n;
    boolean isLeaf;

    BTreeNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }


    public BTreeNode search(int key, int[] operations) {
        int i = binarySearch(key, operations);
        if (i < n && keys[i] == key) {
            operations[0]++;
            return this;
        }
        if (isLeaf) {
            return null;
        }
        operations[0]++;
        return children[i].search(key, operations);
    }


    private int binarySearch(int key, int[] operations) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            operations[0]++;
            if (keys[mid] == key) {
                return mid;
            } else if (keys[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    public void insertNonFull(int key, int[] operations) {
        int i = binarySearch(key, operations);

        if (isLeaf) {

            System.arraycopy(keys, i, keys, i + 1, n - i);
            keys[i] = key;
            n++;
            operations[0]++;
        } else {

            operations[0]++;
            if (children[i].n == 2 * t - 1) {
                splitChild(i, children[i], operations);
                if (key > keys[i]) {
                    operations[0]++;
                    i++;
                }
            }
            children[i].insertNonFull(key, operations);
        }
    }


    public void splitChild(int i, BTreeNode y, int[] operations) {
        operations[0]++;
        BTreeNode z = new BTreeNode(y.t, y.isLeaf);
        z.n = t - 1;

        System.arraycopy(y.keys, t, z.keys, 0, t - 1); // Копирование не считается
        if (!y.isLeaf) {
            System.arraycopy(y.children, t, z.children, 0, t);
        }

        y.n = t - 1;
        System.arraycopy(children, i + 1, children, i + 2, n - i);
        children[i + 1] = z;
        System.arraycopy(keys, i, keys, i + 1, n - i);
        keys[i] = y.keys[t - 1];
        n++;
    }


    public void remove(int key, int[] operations) {
        int idx = binarySearch(key, operations);

        if (idx < n && keys[idx] == key) {
            operations[0]++;
            if (isLeaf) {
                removeFromLeaf(idx, operations);
            } else {
                removeFromNonLeaf(idx, operations);
            }
        } else {
            if (isLeaf) return;
            operations[0]++;
            if (children[idx].n < t) {
                fill(idx, operations);
            }
            children[idx].remove(key, operations);
        }
    }

    private void removeFromLeaf(int idx, int[] operations) {
        System.arraycopy(keys, idx + 1, keys, idx, n - idx - 1);
        n--;
        operations[0]++;
    }

    private void removeFromNonLeaf(int idx, int[] operations) {
        int key = keys[idx];
        operations[0]++;
        if (children[idx].n >= t) {
            int pred = getPredecessor(idx, operations);
            keys[idx] = pred;
            children[idx].remove(pred, operations);
        } else if (children[idx + 1].n >= t) {
            int succ = getSuccessor(idx, operations);
            keys[idx] = succ;
            children[idx + 1].remove(succ, operations);
        } else {
            merge(idx, operations);
            children[idx].remove(key, operations);
        }
    }

    private int getPredecessor(int idx, int[] operations) {
        BTreeNode cur = children[idx];
        while (!cur.isLeaf) {
            operations[0]++;
            cur = cur.children[cur.n];
        }
        return cur.keys[cur.n - 1];
    }

    private int getSuccessor(int idx, int[] operations) {
        BTreeNode cur = children[idx + 1];
        while (!cur.isLeaf) {
            operations[0]++;
            cur = cur.children[0];
        }
        return cur.keys[0];
    }

    private void borrowFromNext(int idx, int[] operations) {
        operations[0]++;
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];


        child.keys[child.n] = keys[idx];
        if (!child.isLeaf) {
            child.children[child.n + 1] = sibling.children[0];
        }


        keys[idx] = sibling.keys[0];


        System.arraycopy(sibling.keys, 1, sibling.keys, 0, sibling.n - 1);
        if (!sibling.isLeaf) {
            System.arraycopy(sibling.children, 1, sibling.children, 0, sibling.n);
        }

        child.n++;
        sibling.n--;
    }

    private void fill(int idx, int[] operations) {
        if (idx != 0 && children[idx - 1].n >= t) {
            borrowFromPrev(idx, operations);
        } else if (idx != n && children[idx + 1].n >= t) {
            borrowFromNext(idx, operations);
        } else {
            merge(idx, operations);
        }
    }

    private void borrowFromPrev(int idx, int[] operations) {
        operations[0]++;
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx - 1];

        System.arraycopy(child.keys, 0, child.keys, 1, child.n);
        child.keys[0] = keys[idx - 1];
        if (!child.isLeaf) {
            System.arraycopy(child.children, 0, child.children, 1, child.n + 1);
            child.children[0] = sibling.children[sibling.n];
        }
        keys[idx - 1] = sibling.keys[sibling.n - 1];
        child.n++;
        sibling.n--;
    }

    private void merge(int idx, int[] operations) {
        operations[0]++;
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];

        child.keys[t - 1] = keys[idx];
        System.arraycopy(sibling.keys, 0, child.keys, t, sibling.n);
        if (!child.isLeaf) {
            System.arraycopy(sibling.children, 0, child.children, t, sibling.n + 1);
        }
        System.arraycopy(keys, idx + 1, keys, idx, n - idx - 1);
        System.arraycopy(children, idx + 2, children, idx + 1, n - idx - 1);
        child.n += sibling.n + 1;
        n--;
    }
}

public class BTree {
    private BTreeNode root;
    private int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public int search(int key) {
        int[] operations = {0};
        if (root != null && root.search(key, operations) != null) {
            return operations[0];
        }
        return -1;
    }

    public int insert(int key) {
        int[] operations = {0};
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
            operations[0] = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root, operations);
                int i = (key > s.keys[0]) ? 1 : 0;
                operations[0]++;
                s.children[i].insertNonFull(key, operations);
                root = s;
            } else {
                root.insertNonFull(key, operations);
            }
        }
        return operations[0];
    }

    public int remove(int key) {
        int[] operations = {0};
        if (root == null) return -1;
        if (root.search(key, new int[1]) == null) return -1;

        root.remove(key, operations);
        if (root.n == 0) {
            root = root.isLeaf ? null : root.children[0];
        }
        return operations[0];
    }
}