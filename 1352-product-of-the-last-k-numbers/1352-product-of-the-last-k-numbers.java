class ProductOfNumbers {
    ArrayList<Integer> arr;

    public ProductOfNumbers() {
        arr = new ArrayList<>();
    }

    public void add(int num) {
        arr.add(num);
    }

    public int getProduct(int k) {
        int prod = 1;

        for (int i = arr.size() - 1; i >= arr.size() - k; i--) {
            prod *= arr.get(i);
        }

        return prod;
    }
}