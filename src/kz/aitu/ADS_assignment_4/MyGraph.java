package kz.aitu.ADS_assignment_4;

import java.util.*;

public class MyGraph<VertexData> {
    private final boolean undirected;
    private final Map<VertexData, Vertex<VertexData>> map = new HashMap<>();

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(VertexData v) {
        if (hasVertex(v))
            return;

        map.put(v, new Vertex<>(v));
    }

    public void addEdge(VertexData source, VertexData dest) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops

        Vertex<VertexData> sV = map.get(source);
        Vertex<VertexData> dV = map.get(dest);

        sV.addAdjacentVertex(dV, 1);

        if (undirected)
            dV.addAdjacentVertex(sV, 1);
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
        return map.containsKey(v);
    }

    public boolean hasEdge(VertexData source, VertexData dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).hasEdge(map.get(dest));
    }

    public List<VertexData> adjacencyList(VertexData v) {
        if (!hasVertex(v)) return null;
        return map.get(v).getAdjacencyList();
    }
}

