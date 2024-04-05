import java.util.*;

public class DialsAlg {
	static final int INF = Integer.MAX_VALUE;
	private int V; 
	private ArrayList<ArrayList<Tuple> > adj;

	public DialsAlg(int v) // Constructor
	{
		this.V = v;
		this.adj = new ArrayList<ArrayList<Tuple> >();
		for (int i = 0; i < v; i++)
			this.adj.add(new ArrayList<Tuple>());
	}

	public void AddEdge(int u, int v, int w)
	{
		adj.get(u).add(new Tuple(v, w));
		adj.get(v).add(new Tuple(u, w));
	}

	public void shortestPath(int src, int W)
	{
		int[] dist = new int[V];

		Arrays.fill(dist, INF);

		ArrayList<Integer>[] B = new ArrayList[W * V + 1];
		for (int i = 0; i < W * V + 1; i++)
			B[i] = new ArrayList<Integer>();

		B[0].add(src);
		dist[src] = 0;

		int idx = 0;
		while (true) {
			while (B[idx].size() == 0 && idx < W * V)
				idx++;

			if (idx == W * V)
				break;

			int u = B[idx].get(0);
			B[idx].remove(0);

			for (Tuple i : adj.get(u)) {
				int v = i.v;
				int weight = i.w;

				int du = dist[u];
				int dv = dist[v];

				if (dv > du + weight) {
					dist[v] = du + weight;
					dv = dist[v];

					B[dv].add(0, v);
				}
			}
		}

		System.out.println("Vertex Distance from Source");
		for (int i = 0; i < V; ++i)
			System.out.println(i + "\t\t" + dist[i]);
	}

	static class Tuple {
		int v, w;
		Tuple(int v, int w)
		{
			this.v = v;
			this.w = w;
		}
	}
	public static void main(String[] args)
	{
		int V = 5;
		DialsAlg g = new DialsAlg(V);

		g.AddEdge(0, 1, 2);
		g.AddEdge(0, 3, 1);
		g.AddEdge(1, 2, 3);
		g.AddEdge(1, 3, 2);
		g.AddEdge(3, 4, 4);
		g.AddEdge(4, 2, 1);

		g.shortestPath(0, 4);
	}
}
