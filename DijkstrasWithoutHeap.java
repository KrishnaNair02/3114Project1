package prj1;

import java.util.ArrayList;

/**
 * The implementation of Dijkstras shortest path algorithm by using a simple
 * linear search to find the unvisited node with the minimum distance estimate
 * 
 * @author Enter your names here
 * @version 1.1
 */
public class DijkstrasWithoutHeap {

    private int[] distances;
    private boolean[] explored;
    private ArrayList<Integer[]>[] adjEdges;
    
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
     public DijkstrasWithoutHeap(int n, int[][] edges) {
        distances = new int[n];
        explored = new boolean[n];
        adjEdges = new ArrayList[n];
        
        // initialize the arrays set to infinite (-1) and unexplored
        for (int i = 0; i < n; i ++) {
            distances[i] = -1;
            explored[i] = false;
            
            // populate the adjacency list
            adjEdges[i] = new ArrayList<Integer[]>();
            for (int j = 1, index = 0; j <= edges.length; j ++) {
                if (edges[j-1][0] == i+1 || edges[j-1][1] == i+1) {
                    adjEdges[i].add(new Integer[2]);
                    adjEdges[i].get(index)[0] = edges[j-1][0];
                    if (edges[j-1][0] == i+1)
                        adjEdges[i].get(index)[0] = edges[j-1][1];
                    adjEdges[i].get(index)[1] = edges[j-1][2];
                    index ++;
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
        int sourceIndex = source - 1;
        int nextNeighborIndex;
        int currentIndex;
        int exploredNum = 1;
        
        int minIndex;
        int minValue;
        
        // explored the source
        distances[sourceIndex] = 0;
        explored[sourceIndex] = true;
        currentIndex = sourceIndex;
        
        for (int i = 0; i < adjEdges[currentIndex].size(); i ++) {
            nextNeighborIndex = adjEdges[currentIndex].get(i)[0] - 1;
            distances[nextNeighborIndex] = distances[currentIndex] + adjEdges[currentIndex].get(i)[1];
        }
        
        while (exploredNum != explored.length) {
         // populate the binary heap
            
            minIndex = -1;
            minValue = -1;
            for (int i = 0; i < distances.length; i ++) {
                if (distances[i] != -1 && !explored[i] && (minValue == -1 || minValue > distances[i])) {
                    minIndex = i;
                    minValue = distances[i];
                }
            }
                
            if (minIndex == -1)
                break;
            explored[minIndex] = true;
            currentIndex = minIndex;
            exploredNum ++;
            
            for (int i = 0; i < adjEdges[currentIndex].size(); i ++) {
                nextNeighborIndex = adjEdges[currentIndex].get(i)[0] - 1;
                if (distances[nextNeighborIndex] == -1 || distances[nextNeighborIndex] > distances[currentIndex] + adjEdges[currentIndex].get(i)[1]) {
                    distances[nextNeighborIndex] = distances[currentIndex] + adjEdges[currentIndex].get(i)[1];
                }
            }
        }
        
        return distances;
    }

}
