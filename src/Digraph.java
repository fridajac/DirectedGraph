import edu.princeton.cs.algs4.Bag;

import java.util.ArrayList;

public class Digraph {

    private int V; // number of vertices in this digraph
    private ArrayList<String> words;
    private int E; //number of edges in this digraph
    private Bag<Integer>[] adj; // adj[v] = adjacency list for vertex v
    private int[] indegree;

    /**
     * Initializes an empty digraph with V vertices.
     *
     * @param nodes
     */
    public Digraph(ArrayList<String> nodes) {
        if (nodes.size() <= 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        V = nodes.size();
        this.words = nodes;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        addEdges();
    }

    private void addEdges() {
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (i != j) {
                    if(checkForEdge(words.get(j), words.get(i).substring(1,5))){
                        addEdge(i, j);
                    }
                }
            }
        }
    }

    private boolean checkForEdge(String s1, String s2) {
        ArrayList<Character> chars = new ArrayList<>();
        for(int i = 0; i < s1.length(); i++) {
            chars.add(s1.charAt(i));
        }
        for(int j = 0; j < s2.length(); j++){
            char c = s2.charAt(j);
            if(chars.contains(c)) {
                chars.remove(chars.indexOf(c));
            }
            else{
                return false;
            }
        }
        return true;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public int getNodePosition(String word) {
        return words.indexOf(word);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int outdegree(int v) {
        return adj[v].size();
    }

    public int indegree(int v) {
        return indegree[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }
}
