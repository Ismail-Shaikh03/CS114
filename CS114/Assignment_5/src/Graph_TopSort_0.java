
import java.util.ArrayList;
import java.util.Stack;
import java.util.Dictionary;
import java.util.Hashtable;
//-------------------------------------------------------------
// One Node in the Graph
//
class Node
{
    public Node(String name)
    {
        this.name = name;
    }
    public String name;
    public boolean visited = false;
    public int in_count = 0;
    public ArrayList<Edge> out_edge_list = new ArrayList<>();
    public String toString(){ return name; } //" " + visited + " " + in_count; removes this so in topological sort it only prints out numbers and not true that it's visited
}
//-------------------------------------------------------------
// A Directed Edge in the Graph
//
class Edge
{
    public Edge(String name, double weight, Node from, Node to)
    {
        this.name = name;
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
    public String toString(){ return name + ": "+ from.name + " " + to.name;}
    public String name;
    public double weight;
    public Node from;
    public Node to;
}
//-------------------------------------------------------------
// A Directed Graph of Nodes and Edges
//
class Graph
{
    public ArrayList<Node> node_list = new ArrayList<>();
    public ArrayList<Edge> edge_list = new ArrayList<>();
    Dictionary<String, Node> d_Nodes = new Hashtable<>();
    //----------------------------------------------------------
// Initialize a graph (State before DFS performed)
//
    public void init_graph()
    {
        for(Node n : node_list){
           n.visited=false;
           n.in_count=0;
        }
        for( Edge e : edge_list){
            e.to.in_count+=1;
            e.weight=Double.MAX_VALUE;
        }
    }

    //----------------------------------------------------------
// Add a new edge ( and possibly new nodes) to the graph.
//
    public void add_edge(String name, double weight, String node_name_from, String
            node_name_to)
    {
        Node node_from, node_to;
        if ((node_from = d_Nodes.get(node_name_from)) == null){
            node_list.add(node_from = new Node(node_name_from));
            d_Nodes.put(node_name_from, node_from);
        }
        if ((node_to = d_Nodes.get(node_name_to)) == null){
            node_list.add(node_to = new Node(node_name_to));
            d_Nodes.put(node_name_to, node_to);
        }
        Edge new_edge = new Edge(name, weight, node_from, node_to);
        edge_list.add(new_edge);
        node_from.out_edge_list.add(new_edge);
    }
    //----------------------------------------------------------
// Describe a Graph
//
    public String toString()
    {
        String s = "\nNodes\n---------------\n";
        for(Node n : node_list)
        {
            s += n + "\n";
        }
        s += "\nEdges\n---------------\n";
        for(Edge e : edge_list)
        {
            s += e + "\n";
        }
        return s;
    }
    //----------------------------------------------------------
// Depth First Search (DFS)
//
    public void dfs(String node_name)
    {
        System.out.println("\n\nDFS Source Node: " + node_name + "\n--------------------\n");
        init_graph();
        dfs(d_Nodes.get(node_name));
    }
    private void dfs(Node n)
    {
        System.out.println("Visiting Node " + n);
        n.visited = true;
        for (Edge e : n.out_edge_list)
            if (e.to.visited == false)
            {
                System.out.println("\tFollow Edge " + e);
                dfs(e.to);
                System.out.println("\tReverse Edge " + e);
            }
        System.out.println("Backup From " + n);
        n.visited=false;
    }
    //----------------------------------------------------------
// Topological Sort - Compute all possible topological sorts
// Note: can also use a Stack instead of List
//
    private Stack<Node> visit_list = new Stack<>();
    private int sort_count = 0; // count how many sorts
    private boolean holder=false;
    public boolean top_sort_all()
    {
        System.out.println("Topological Sorts\n----------------------\n");
// initialize
        boolean holder=top_sort_all_rec();
        System.out.println("Done\n");
        return holder;
    }
    private boolean top_sort_all_rec() {
        if (visit_list.size() == node_list.size()) {
            sort_count++;
            System.out.println(sort_count +")"+visit_list);
            holder=true;
        }
        for (Node n : node_list) {
            if (n.visited == false && n.in_count == 0) {
                n.visited = true;
                visit_list.push(n);
                for (Edge e : n.out_edge_list) {
                    e.to.in_count--;
                }
                top_sort_all_rec();
                n.visited = false;
                visit_list.pop();
                for (Edge e : n.out_edge_list) {
                    e.to.in_count++;
                }
            }

        }
        return holder;
    }
//----------------------------------------------------------
// Dijkstra
//
        private Node find_min_node() // find and return min weight unvisited Node
        { // and mark it as visited.
            return null;
        }
        public void dijkstra()
        {
/*
make init_graph() initialize all Nodes as unvisited,
all Node weights to the largest representable
floating point number, and all back pointers to null.
(look up java largest double)
Remember to add weight and back_ptr data members to
the Node class.
set the source Node weight to 0
// alternative while loop using a method that finds, removes
// and returns min weight unvisited node
while ((min_node = find_min_node()) != null)
for each edge e outgoing from min_node
t = to Node of e
if t is not visited and (w = weight of min_node + weight of e) <
weight of t
{
weight of t = w
back pointer of t = min_node
}
*/
        }
    }
    //-------------------------------------------------------------
// Main. Performs DFS for graph in image topo_07.png
//
    class Graph_TopSort_0
    {
        public static void main(String[] args) throws Exception {
            Graph g = new Graph();
            g.add_edge("14", 1.0, "1", "4");
            g.add_edge("15", 1.0, "1", "5");
            g.add_edge("23", 1.0, "2", "3");
            g.add_edge("24", 1.0, "2", "4");
            g.add_edge("34", 1.0, "3", "4");
            g.add_edge("36", 1.0, "3", "6");
            g.add_edge("38", 1.0, "3", "8");
            g.add_edge("45", 1.0, "4", "5");
            g.add_edge("57", 1.0, "5", "7");
            g.add_edge("59", 1.0, "5", "9");
            g.add_edge("67", 1.0, "6", "7");
            g.add_edge("79", 1.0, "7", "9");
            g.add_edge("89", 1.0, "8", "9");
            System.out.println(g);
            g.dfs("1");
            g.dfs("2");
            if (g.top_sort_all()) {
                System.out.print("\nSort Complete\n");
            } else {
                System.out.print("\nGraph Contains a Cycle\n");
            }
        }
    }
/*
Nodes
---------------
1 false
4 false
5 false
2 false
3 false
6 false
8 false
7 false
9 false
Edges
---------------
14: 1 4
15: 1 5
23: 2 3
24: 2 4
34: 3 4
36: 3 6
38: 3 8
45: 4 5
57: 5 7
59: 5 9
67: 6 7
79: 7 9
89: 8 9
DFS Source Node: 1
--------------------
Visiting Node 1
Follow Edge 14: 1 4
Visiting Node 4
Follow Edge 45: 4 5
Visiting Node 5
Follow Edge 57: 5 7
Visiting Node 7
Follow Edge 79: 7 9
Visiting Node 9
Backup From 9
Reverse Edge 79: 7 9
Backup From 7
Reverse Edge 57: 5 7
Backup From 5
Reverse Edge 45: 4 5
Backup From 4
Reverse Edge 14: 1 4
Backup From 1
DFS Source Node: 2
--------------------
Visiting Node 2
Follow Edge 23: 2 3
Visiting Node 3
Follow Edge 34: 3 4
Visiting Node 4
Follow Edge 45: 4 5
Visiting Node 5
Follow Edge 57: 5 7
Visiting Node 7
Follow Edge 79: 7 9
Visiting Node 9
Backup From 9
Reverse Edge 79: 7 9
Backup From 7
Reverse Edge 57: 5 7
Backup From 5
Reverse Edge 45: 4 5
Backup From 4
Reverse Edge 34: 3 4
Follow Edge 36: 3 6
Visiting Node 6
Backup From 6
Reverse Edge 36: 3 6
Follow Edge 38: 3 8
Visiting Node 8
Backup From 8
Reverse Edge 38: 3 8
Backup From 3
Reverse Edge 23: 2 3
Backup From 2
Press Enter to Exit
*/
