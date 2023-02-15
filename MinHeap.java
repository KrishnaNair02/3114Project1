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
    // The auxiliary array that stores the location of nodes by ids
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
        location = new int[n];
        for (int i = 0; i < n; i ++)
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
        nodes[size] = new HeapNode(id, value);
        int index = this.heapifyup(size);
        location[id] = index;
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
        nodes[0] = nodes[size];
        heapifyDown(0);
    	return minArr;
        return null;
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
        index = this.heapifyup(index);
        location[id] = index;
    }


    /**
     * This method returns the array representation of heap
     * 
     * @return the array representation of heap
     */
    public int[] getHeap() {
        int[] intNodes = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++)
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
    
    private int heapifyup(int indexInit) {
        int index = indexInit;
        int parent;
        while (index > 0) {
            parent = (index - 1) / d;
            if (nodes[index].getValue() < nodes[parent].getValue()) {
                HeapNode temp = nodes[index];
                nodes[index] = nodes[parent];
                nodes[parent] = temp;
            }
            else {
                break;
            }
            index = parent;
        }
        return index;
    }
    
    public void heapifyDown(int location) {
    	HeapNode temp;
    	int lowest = (d * location) + 1;
    	int arrayLength = nodes.length;
    	if (d*location + 1 > arrayLength) {
    		return;
    	}
    	for (int remainder = 0; remainder < d; remainder++) {
    		int currChild = (d * location) + remainder + 1;
    		if (nodes[currChild].getValue() < nodes[lowest].getValue()) {
    			lowest = currChild;
    		}
    	}
    	if (nodes[lowest].getValue() < nodes[location].getValue()) {
    		temp = nodes[location];
    		nodes[location] = nodes[lowest];
    		nodes[lowest] = temp;
    		heapifyDown(lowest);
    	}
    }

}
