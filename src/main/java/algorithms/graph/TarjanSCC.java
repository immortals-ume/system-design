package algorithms.graph;

import java.util.*;

public class TarjanSCC {
    private final Map<String, List<String>> graph;
    private final Map<String, Integer> ids;      // Discovery time of each node
    private final Map<String, Integer> low;      // Lowest reachable discovery time
    private final Deque<String> stack;
    private final Set<String> onStack;
    private int idCounter = 0;

    public TarjanSCC(Map<String, List<String>> graph) {
        this.graph = graph;
        this.ids = new HashMap<>();
        this.low = new HashMap<>();
        this.stack = new ArrayDeque<>();
        this.onStack = new HashSet<>();
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));
        graph.put("B", List.of("C"));
        graph.put("C", Arrays.asList("A", "D"));
        graph.put("D", List.of("E"));
        graph.put("E", List.of("F"));
        graph.put("F", List.of("D"));

        TarjanSCC tarjan = new TarjanSCC(graph);
        List<List<String>> sccs = tarjan.findSCCs();

        System.out.println("Strongly Connected Components:");
        for (List<String> scc : sccs) {
            System.out.println(scc);
        }
    }

    public List<List<String>> findSCCs() {
        List<List<String>> sccs = new ArrayList<>();
        for (String node : graph.keySet()) {
            if (!ids.containsKey(node)) {
                dfs(node, sccs);
            }
        }
        return sccs;
    }

    private void dfs(String at, List<List<String>> sccs) {
        ids.put(at, idCounter);
        low.put(at, idCounter);
        idCounter++;
        stack.push(at);
        onStack.add(at);

        for (String neighbor : graph.getOrDefault(at, Collections.emptyList())) {
            if (!ids.containsKey(neighbor)) {
                dfs(neighbor, sccs);
                low.put(at, Math.min(low.get(at), low.get(neighbor)));
            } else if (onStack.contains(neighbor)) {
                low.put(at, Math.min(low.get(at), ids.get(neighbor)));
            }
        }

        // Root node found, pop stack to get SCC
        if (ids.get(at).equals(low.get(at))) {
            List<String> scc = new ArrayList<>();
            while (true) {
                String node = stack.pop();
                onStack.remove(node);
                scc.add(node);
                if (node.equals(at)) break;
            }
            sccs.add(scc);
        }
    }
}
