public class TreeSort {
    private int iterations; // счетчик итераций сортировки

    public TreeSort() {
        this.iterations = 0;
    }
    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        int count; // для подсчета повторяющихся элементов

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.count = 1;
        }
    }

    private TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        iterations++; // увеличиваем счетчик при каждом сравнении

        if (value == root.value) {
            root.count++;
        } else if (value < root.value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }

    private void inOrderTraversal(TreeNode root, java.util.List<Integer> result) {
        if (root != null) {
            inOrderTraversal(root.left, result);
            for (int i = 0; i < root.count; i++) {
                result.add(root.value);
            }
            inOrderTraversal(root.right, result);
        }
    }

    public int[] sort(int[] arr) {
        iterations = 0; // сбрасываем счетчик перед сортировкой
        TreeNode root = null;

        // Вставляем все элементы в дерево
        for (int value : arr) {
            root = insert(root, value);
        }

        // Собираем отсортированный массив
        java.util.List<Integer> resultList = new java.util.ArrayList<>();
        inOrderTraversal(root, resultList);

        // Преобразуем List в массив
        int[] sortedArray = new int[resultList.size()];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = resultList.get(i);
        }

        return sortedArray;
    }

    public int getIterations() {
        return iterations;
    }

}
