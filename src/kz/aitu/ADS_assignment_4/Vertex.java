package kz.aitu.ADS_assignment_4;

import java.util.*;

public class Vertex<V> {
    private static int nodeIndex = 0;
    private final int hash;
    private V data;
    private final Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        hash = nodeIndex++;
        this.adjacentVertices = new HashMap<>();
    }

    public V getData() {
        return data;
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight){
        adjacentVertices.put(destination, weight);
    }

    public boolean hasEdge(Vertex<V> other){
        return adjacentVertices.get(other) != null;
    }

    public List<V> getAdjacencyList(){
        List<V> res = new LinkedList<>();
        for(var i : adjacentVertices.keySet())
            res.add(i.getData());
        return res;
    }

    public Double getEdgeWeight(Vertex<V> other){
        if(!hasEdge(other))
            return null;
        return adjacentVertices.get(other);
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
