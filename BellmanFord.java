import java.util.Arrays;

public class BellmanFord {
    public static void bellmanFord(int[][] graph, int source) {
        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    if (graph[j][k] != 0 && dist[j] != Integer.MAX_VALUE && dist[j] + graph[j][k] < dist[k]) {
                        dist[k] = dist[j] + graph[j][k];
                    }
                }
            }
        }

        for (int j = 0; j < V; j++) {
            for (int k = 0; k < V; k++) {
                if (graph[j][k] != 0 && dist[j] != Integer.MAX_VALUE && dist[j] + graph[j][k] < dist[k]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        int source = 0;
        bellmanFord(graph, source);
    }

}
