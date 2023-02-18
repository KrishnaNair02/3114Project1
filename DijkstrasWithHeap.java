package prj1;

import java.util.ArrayList;

/**
 * 
 * The implementation of Dijkstras shortest path algorithm by using
 * min-heaps
 * 
 * @author Enter your names here
 */
public class DijkstrasWithHeap {
    
    private int[] distances;
    private boolean[] explored;
    private MinHeap biMH;
    private int[][] edges;
    private ArrayList<Integer>[] adjEdges;

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
    @SuppressWarnings("unchecked")
    public DijkstrasWithHeap(int n, int[][] edges) {
        distances = new int[n];
        explored = new boolean[n];
        biMH = new MinHeap(edges.length , 2);
        this.edges = edges;
        adjEdges = new ArrayList[n];
        
        // initialize the arrays set to infinite (-1) and unexplored
        for (int i = 0; i < n; i ++) {
            distances[i] = -1;
            explored[i] = false;
            
            // populate the adjacency list
            adjEdges[i] = new ArrayList<Integer>();
            for (int j = 1; j <= edges.length; j ++) {
                if (edges[j-1][0] == i+1 || edges[j-1][1] == i+1) {
                    adjEdges[i].add(j);
                }
            }
        }
                   
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
        int[] minEdge;
        int sourceIndex = source - 1;
        int nextIndex;
        int nextNeighborIndex;
        int currentIndex;
        
        // explored the source
        distances[sourceIndex] = 0;
        explored[sourceIndex] = true;
        currentIndex = sourceIndex;
        
        do {
         // populate the binary heap
            for (int i = 0; i < adjEdges[currentIndex].size(); i ++) {
                biMH.insert(adjEdges[currentIndex].get(i), edges[adjEdges[currentIndex].get(i) - 1][2]);
                nextNeighborIndex = edges[adjEdges[currentIndex].get(i) - 1][1] - 1;
                if (nextNeighborIndex == currentIndex)
                    nextNeighborIndex = edges[adjEdges[currentIndex].get(i) - 1][0] - 1;
                if (distances[nextNeighborIndex] == -1 || distances[nextNeighborIndex] > distances[currentIndex] + edges[adjEdges[currentIndex].get(i) - 1][2]) {
                    distances[nextNeighborIndex] = distances[currentIndex] + edges[adjEdges[currentIndex].get(i) - 1][2];
                    
                }
            }
            
            do {
                minEdge = biMH.extractMin();
                nextIndex = edges[minEdge[0] - 1][1] - 1;
                if (nextIndex == currentIndex)
                    nextIndex = edges[minEdge[0] - 1][0] - 1;
            }
            while (explored[nextIndex] && biMH.getSize() > 0);
                
            explored[nextIndex] = true;
            currentIndex = nextIndex;
        }
        while (biMH.getSize() != 0);
        
        return distances;
    }

}
