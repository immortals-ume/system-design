package algorithms;

import java.util.HashSet;
import java.util.Set;

public class UnionFind {
    private final int[] parent;
    private final int[] rank;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];

        // Initially, each element is its own parent (own group)
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0; // All ranks start at 0
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);  // 0 to 4

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        System.out.println(uf.connected(0, 2)); // true
        System.out.println(uf.connected(0, 4)); // false
        System.out.println(uf.countDistinctSets()); // 2 groups
    }

    // Find the root of an element with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    // Union two sets by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // Already in the same group
        if (rootX == rootY) {
            return Boolean.FALSE;
        }

        // Union by rank: attach smaller tree under larger
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;  // Only increase rank when both have the same rank
        }

        return Boolean.TRUE;
    }

    // Check if two elements are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    // Optional: get a number of distinct groups (not tracked live here)
    public int countDistinctSets() {
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < parent.length; i++) {
            roots.add(find(i));
        }
        return roots.size();
    }
}
