using System;
using System.Collections.Generic;

interface IGraph {
    int SizeV { get; }
    int SizeE { get; }
    void AddEdge(string a, string b);
    void RemoveEdge(string a, string b);
    List <string> GetNeighbors(string s);
    void PrintGraph(); // wypisuje liste sasiedztwa - macierz jest nieczytelna 
};

class GraphList : IGraph {
    int size_e;
    int size_v;

    Dictionary<string,List<string>> neighbors;
    
    public GraphList(int n) {
        size_v = n;
        size_e = 0;
        neighbors = new Dictionary<string,List<string>>();
    }

    public int SizeE {
        get { return size_e; }
    }
    public int SizeV {
        get { return size_v; }
    }

    public void AddEdge(string a, string b) {
        if(!neighbors.ContainsKey(a)) neighbors.Add(a,new List<string>());
        if(!neighbors.ContainsKey(b)) neighbors.Add(b,new List<string>());

        neighbors[a].Add(b);
        neighbors[b].Add(a);
        size_e++;
    }
    public void RemoveEdge(string a, string b) {
        if(!neighbors.ContainsKey(a) || !neighbors.ContainsKey(b)) 
            throw new Exception("Proba usuniecia nieistniejacej krawedzi!");
        neighbors[a].Remove(b);
        neighbors[b].Remove(a);
        size_e--;
    }
    public List<string> GetNeighbors(string s) {
        if(!neighbors.ContainsKey(s)) 
            throw new Exception("Odwolanie do nieistniejacego wierzcholka!");
        return neighbors[s];
    }
    public void PrintGraph() {
        foreach(KeyValuePair<string,List<string>> p in neighbors) {
            Console.Write("{0}: ",p.Key);
            Console.WriteLine(string.Join(", ", p.Value));
        }
    }
};

class GraphMatrix : IGraph {
    int size_v;
    int size_e;
    bool[,] matrix;

    Dictionary<string,int> VertexId;
    Dictionary<int,string> VertexLabel;

    int GetId(string a) {
        return VertexId.ContainsKey(a) ? VertexId[a] : VertexId.Count;
    }

    public GraphMatrix(int n) {
        size_v = n;
        size_e = 0;
        matrix = new bool[n,n];
        VertexId = new Dictionary<string,int>();
        VertexLabel = new Dictionary<int,string>();
    }

    public int SizeV {
        get { return size_v; }
    }
    public int SizeE {
        get { return size_e; }
    }
    public void AddEdge(string a, string b) {
        int id_a = GetId(a);
        if(id_a == VertexId.Count) {
            VertexId.Add(a,id_a);
            VertexLabel.Add(id_a,a);
        }

        int id_b = GetId(b);
        if(id_b == VertexId.Count) {
            VertexId.Add(b,id_b);
            VertexLabel.Add(id_b,b);
        }

        matrix[id_a,id_b] = matrix[id_b,id_a] = true;
        size_e++;
    }
    public void RemoveEdge(string a, string b) {
        int id_a = GetId(a);
        int id_b = GetId(b);

        if(id_a == VertexId.Count || id_b == VertexId.Count) 
            throw new Exception("Proba usuniecia nieistniejacej krawedzi!");
        matrix[id_a,id_b] = matrix[id_b,id_a] = false;
        size_e--;
    }
    public List<string> GetNeighbors(string s) {
        int x = GetId(s);
        if(x == VertexId.Count) 
            throw new Exception("Odwolanie do nieistniejacego wierzcholka!");

        List<string> l = new List<string>();
        for(int i = 0; i < size_v; i++)
            if(matrix[x,i]) l.Add(VertexLabel[i]);
        return l;
    }
    public void PrintGraph() {
        for(int i = 0; i < size_v; i++)
            if(VertexLabel.ContainsKey(i)) {
                Console.Write("{0}: ", VertexLabel[i]);
                Console.WriteLine(string.Join(", ", GetNeighbors(VertexLabel[i])));
            }
    }
};

class MyRandom : Random{
    public int RandomInt() {
        return Next();
    }
    public string RandomString() {
        int l = (Next()%8)+1;
        string res = "";
        while(l --> 0) res += (char)((Next()%0x3d)+0x40);
        return res;
    }
};

class GraphOperations {
    public static IGraph RandomGraph(IGraph g, int n, int e) {
        MyRandom r = new MyRandom();      
        List<string> vertex = new List<string>();
        
        if(g is GraphList) g = new GraphList(n);
        else if(g is GraphMatrix) g = new GraphMatrix(n);

        for(int i = 0; i < n; i++) 
            vertex.Add(r.RandomString());
        while(e --> 0) 
            g.AddEdge(vertex[r.RandomInt()%n], vertex[r.RandomInt()%n]);

        return g;
    }
    public static List<string> ShortestPath(IGraph g, string a, string b) { //BFS
        List<string> res = new List<string>();
        HashSet<string> visited = new HashSet<string>();
        Dictionary<string,string> prev = new Dictionary<string,string>();
        Queue<string> q = new Queue<string>();

        q.Enqueue(a);
        visited.Add(a);

        while(q.Count > 0) {
            string s = q.Dequeue();
            if(s == b) break;
            List<string> neighbors = g.GetNeighbors(s);
            
            foreach(string x in neighbors)
                if(!visited.Contains(x)) {
                    prev.Add(x,s);
                    visited.Add(x);
                    q.Enqueue(x);
                }
        }
        for(string i = b; prev.ContainsKey(i); i = prev[i]) res.Add(i);

        if(res.Count > 0 || a == b) res.Add(a);
        res.Reverse();
        return res;
    }
}

class Program {
    public static void Main() {
        GraphList G = new GraphList(10);
        //GraphMatrix G = new GraphMatrix(10);

        G.AddEdge("babcia","jadzia");
        G.AddEdge("jadzia","basia");
        G.AddEdge("basia","stasia");
        G.AddEdge("stasia","jadzia");
        Console.WriteLine(G.SizeE);

        G.PrintGraph();

        Console.WriteLine(string.Join(", ", G.GetNeighbors("babcia")));
        Console.WriteLine(string.Join(", ", G.GetNeighbors("jadzia")));
        Console.WriteLine(string.Join(", ", GraphOperations.ShortestPath(G,"babcia","stasia")));
        Console.WriteLine(string.Join(", ", GraphOperations.ShortestPath(G,"basia","babcia")));

        G.RemoveEdge("jadzia","stasia");
        Console.WriteLine(string.Join(", ", GraphOperations.ShortestPath(G,"babcia","stasia")));
        G.RemoveEdge("jadzia","basia");
        Console.WriteLine(string.Join(", ", GraphOperations.ShortestPath(G,"basia","babcia")));
        Console.WriteLine(string.Join(", ", GraphOperations.ShortestPath(G,"basia","basia")));
        Console.WriteLine(G.SizeE);

        try {
            G.RemoveEdge("kasia","babcia");
        } catch(Exception e) {
            Console.WriteLine(e);
        }
        try {
            G.GetNeighbors("dziadek");
        } catch(Exception e) {
            Console.WriteLine(e);
        }

        G = (GraphList)GraphOperations.RandomGraph(G,8,12);
        //G = (GraphMatrix)GraphOperations.RandomGraph(G,8,12);
        G.PrintGraph();
    }
};