package kz.aitu.ADS_assignment_4;

import java.util.*;

public class WeightedGraph<VertexData> {
    private final boolean undirected;
    private Map<VertexData, Vertex<VertexData>> map = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    private Vertex<VertexData> getVertex(VertexData v){
        return map.get(v);
    }

    public void addVertex(VertexData v) {
        if(map.get(v) != null)
            return;
        map.put(v, new Vertex<>(v));
    }

    public void addEdge(VertexData source, VertexData dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops

        Vertex<VertexData> sV = map.get(source);
        Vertex<VertexData> dV = map.get(dest);

        sV.addAdjacentVertex(dV, weight);

        if (undirected)
            dV.addAdjacentVertex(sV, weight);
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<VertexData> v : map.values()) {
            count += v.getAdjacencyList().size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(VertexData v) {
        return map.get(v) != null;
    }

    public boolean hasEdge(VertexData source, VertexData dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).hasEdge(map.get(dest));
    }

    public Iterable<VertexData> adjacencyList(VertexData v) {
        if (!hasVertex(v)) return null;
        return map.get(v).getAdjacencyList();
    }

    public Iterable<Edge<VertexData>> getEdges(VertexData v) {
        if (!hasVertex(v)) return null;
        List<Edge<VertexData>> edges = new LinkedList<>();
        for (VertexData dest : map.get(v).getAdjacencyList()) {
            if(hasVertex(dest))
                edges.add(new Edge<>(v, dest, map.get(v).getEdgeWeight(map.get(dest))));
        }
        return edges;
    }
}
