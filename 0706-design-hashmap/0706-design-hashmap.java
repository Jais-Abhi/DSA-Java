class MyHashMap {

    private Integer[] list;
    public MyHashMap() {
        list = new Integer [1000001];
    }
    
    public void put(int key, int value) {
        list[key] = value;
    }
    
    public int get(int key) {
        if(list[key] != null) return list[key];
        return -1;
    }
    
    public void remove(int key) {
        list[key] = null;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */