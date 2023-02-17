package prj1;

/**
 * In this class, we implement the d-ary min-heap data structure
 * 
 * @author Enter your names here
 *
 */
public class MinHeap {
    // The parameter d in the d-ary min-heap
    private int d;
    private int size;

    // The array representation of your min-heap (It is not required to use this)
    private HeapNode[] nodes;
    // The auxiliary array that stores the location of nodes by IDs
    private int[] location;

    /**
     * Constructor
     * 
     * @param n:
     *            maximum number of elements in the min-heap at each time
     * @param d:
     *            parameter d in the d-ary min-heap
     */
    public MinHeap(int n, int d) {
        nodes = new HeapNode[n];
        location = new int[n+1];
        for (int i = 0; i < n+1; i ++)
            location[i] = -1;
        
        this.d = d;
        this.size = 0;
    }


    /**
     * This method inserts a new element with "id" and "value" into the min-heap
     * 
     * @param id
     * @param value
     */
    public void insert(int id, int value) {
        if (size == nodes.length) {
            return;
        }
        nodes[size] = new HeapNode(id, value);
        location[id] = size;
        heapifyup(size);
        size ++;
    }


    /**
     * This method extracts the min value of the heap
     * 
     * @return an array consisting of two integer elements: id of the minimum
     *         element and the value of the minimum element
     * 
     *         So for example, if the minimum element has id = 5 and value = 1,
     *         you should return the array [5, 1]
     */
    public int[] extractMin() {
        int[] minArr = new int[2];
        minArr[0] = nodes[0].getId();
        minArr[1] = nodes[0].getValue();
        location[nodes[size-1].getId()] = location[nodes[0].getId()];
        nodes[0] = nodes[size-1];
        size --;
        heapifyDown(0);
        return minArr;
    }


    /**
     * This method takes an id and a new value newValue for the corresponding
     * node, and updates the data structure accordingly
     * 
     * @param id
     * @param newValue
     */
    public void decreaseKey(int id, int newValue) {
        int index = location[id];
        nodes[index].setValue(newValue);
        heapifyup(index);
    }


    /**
     * This method returns the array representation of heap
     * 
     * @return the array representation of heap
     */
    public int[] getHeap() {
        int[] intNodes = new int[size];
        for (int i = 0; i < size; i++)
        {
            intNodes[i] = nodes[i].getValue();
        }
        return intNodes;
    }


    /**
     * the toString method that returns a string with the values of the heap in
     * the array representation.
     * This method can help you find the issues of your code when you want to
     * debug.
     * 
     * @return string form of the array representation of heap
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.nodes.length; i++) {
            if (nodes[i] != null) {
                sb.append(nodes[i].getValue());
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    
    private void heapifyup(int index) {
        int parent;
        while (index > 0) {
            parent = (index - 1) / d;
            if (nodes[index].getValue() < nodes[parent].getValue()) {
                HeapNode temp = nodes[index];
                nodes[index] = nodes[parent];
                nodes[parent] = temp;
                
                int tempLoc = location[nodes[index].getId()];
                location[nodes[index].getId()] = location[nodes[parent].getId()];
                location[nodes[parent].getId()] = tempLoc;
            }
            else {
                break;
            }
            index = parent;
        }
        return;
    }
    
    public void heapifyDown(int index) {
        HeapNode temp;
        int lowest = (d * index) + 1;
        int arrayLength = size;
        if (lowest >= arrayLength) {
            return;
        }
        for (int remainder = 0; remainder < d; remainder++) {
            int currChild = (d * index) + remainder + 1;
            if (nodes[currChild].getValue() < nodes[lowest].getValue() && currChild < arrayLength) {
                lowest = currChild;
            }
        }
        if (nodes[lowest].getValue() < nodes[index].getValue()) {
            temp = nodes[index];
            nodes[index] = nodes[lowest];
            nodes[lowest] = temp;
            
            int tempLoc = location[nodes[index].getId()];
            location[nodes[index].getId()] = location[nodes[lowest].getId()];
            location[nodes[lowest].getId()] = tempLoc;
            
            heapifyDown(lowest);
        }
    }

}