package org.example.Practica3;
import java.util.*;

public class Graph<V> {
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<V>());
            return true;
        }
        return false;
    }

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    public Set<V> obtainAdjacents(V v) {
        Set<V> adjacents = adjacencyList.get(v);
        if (adjacents != null) {
            return adjacents;
        } else {
            throw new NoSuchElementException("Vertex " + v + " not found");
        }
    }

    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V v : adjacencyList.keySet()) {
            sb.append(v.toString() + ": ");
            for (V adj : adjacencyList.get(v)) {
                sb.append(adj.toString() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<V> onePath(V v1, V v2) {
        Map<V, V> parentMap = new HashMap<>();
        Queue<V> bfsQueue = new LinkedList<>();
        bfsQueue.offer(v1);
        parentMap.put(v1, null);
        while (!bfsQueue.isEmpty()) {
            V curr = bfsQueue.poll();
            if (curr.equals(v2)) {
                List<V> path = new ArrayList<>();
                V node = v2;
                while (node != null) {
                    path.add(0, node);
                    node = parentMap.get(node);
                }
                return path;
            }
            for (V neighbor : obtainAdjacents(curr)) {
                if (!parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, curr);
                    bfsQueue.offer(neighbor);
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "E");
        System.out.println(graph.toString());
        List<String> path = graph.onePath("A", "E");
        System.out.println(path != null ? path.toString() : "No path found");
    }
}

