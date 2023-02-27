package prj1;

/**
 * The implementation of Dijkstras shortest path algorithm by using a simple
 * linear search to find the unvisited node with the minimum distance estimate
 * 
 * @author Enter your names here
 * @version 1.1
 */
public class DijkstrasWithoutHeap {
	
	private int size;
	private int[][] edges;
	private int[][] graph;
	
    /**
     * Constructor of the class
     * 
     * @param n:
     *            number of nodes of the graph
     * @param edges:
     *            the set of edges of the graph. Each row of 'edges' is in the
     *            form of [u, v, w], which means that there is an edge between u
     *            and v with weight w. So edges[i][0] and edges[i][1] are the
     *            end-points of the i-th edge and edges[i][2] is its weight
     */
    public DijkstrasWithoutHeap(int n, int[][] edges) {
        size = n;
        this.edges = edges;
    }


    /**
     * This method computes and returns the distances of all nodes of the graph
     * from the source node
     * 
     * @param source
     * 
     * @return an array containing the distances of the nodes of the graph from
     *         source. Element i of the returned array represents the distance
     *         of node i from the source
     */
    public int[] run(int source) {
    	graph = new int[size][size];
        for (int i = 0; i < size; i++) {
        	for (int j = 0; j < edges.length; j++) {
        		if (edges[j][0] == i + 1) {
            			graph[i][edges[j][1] - 1] = edges[j][2];
            			graph[edges[j][1] - 1][i] = edges[j][2];
            		}
        	}
        }
		/*
		 * for (int i = 0; i < size; i++) { for (int j = 0; j < size; j++) {
		 * System.out.println(graph[i][j] + " "); } System.out.println("\n"); }
		 */
        int distArray[] = new int[size];
        Boolean processed[] = new Boolean[size];
        for (int i = 0; i < size; i++) {
        	distArray[i] = Integer.MAX_VALUE;
        	processed[i] = false;
        }
        distArray[source - 1] = 0;
        for (int j = 0; j < size - 1; j++) {
        	int minVal = Integer.MAX_VALUE;
        	int minInd = -1;
        	for (int l = 0; l < size; l++) {
        		if (processed[l] == false && distArray[l] <= minVal) {
        			minVal = distArray[l];
        			minInd = l;
        		}
        	}
        	int lowInd = minInd;
        	processed[lowInd] = true;
        	for (int k = 0; k < size; k++) {
        		if (!processed[k] && distArray[lowInd] != Integer.MAX_VALUE && graph[lowInd][k] != 0 
        				&& distArray[lowInd] + graph[lowInd][k] < distArray[k]) { 
        			distArray[k] = distArray[lowInd] + graph[lowInd][k];
        		}
        	}
        }
        for (int i = 0; i < distArray.length; i++) {
        	if (distArray[i] == Integer.MAX_VALUE) {
        		distArray[i] = -1;
        	}
        }
        return distArray;
    }
}
