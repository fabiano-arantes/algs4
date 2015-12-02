/*
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import java.util.LinkedList;  
import java.util.Arrays;    

class shortestPath
{
    int ancestor;
    int length;
}

public class SAP {
    private int depth[];
    private Digraph G;
    private int root;
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        if (G == null)
        {
            throw new java.lang.NullPointerException("Ivalid digraph");
        }
        
        this.G = G;
    }
    
    private boolean isValid(int v) 
    {
        return (v >= 0 && v <= this.G.V() - 1);
    }
    
    private boolean isValid(Iterable<Integer> v) 
    {
        if(v == null)
        {
            return false;
        }
        
        for (Integer integer : v) 
        {
            if (!isValid(integer)) 
            {
                return false;
            }
        }

        return true;
    }
    
    private shortestPath findShortestPath(int v, int w)
    {
        if (!isValid(v))
        {
            throw new java.lang.NullPointerException("Invalid v parameter");
        }
        
        if (!isValid(w))
        {
            throw new java.lang.NullPointerException("Ivalid w parameter");
        }
        
        Queue<Integer> q = new Queue<Integer>();
        boolean marked[] = new boolean[G.V()];
        int distTo[] = new int[G.V()];
        
        q.enqueue(v);
        q.enqueue(w);
        
        marked[v] = true;
        marked[w] = true;
        distTo[v] = 0;
        distTo[w] = 0;        
        
        while(!q.isEmpty())
        {
            int a = q.dequeue();
            for(int b: G.adj(a))
            {
                if(marked[b])
                {
                    int length = distTo[b] + distTo[a] + 1;
                    shortestPath sp = new shortestPath();
                    
                    sp.ancestor = b;
                    sp.length = length;
                    
                    return sp;
                }
                
                distTo[b] = distTo[a] + 1;
                marked[b] = true;
                q.enqueue(b);
            }
        }
 
        return null;
    }

    private shortestPath findShortestPath(Iterable<Integer> v, Iterable<Integer> w)
    {
         if (!isValid(v))
        {
            throw new java.lang.NullPointerException("Invalid v parameter");
        }
        
        if (!isValid(w))
        {
            throw new java.lang.NullPointerException("Ivalid w parameter");
        }
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);
        int shortestPath = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        
        LinkedList<Integer> ancestors = new LinkedList<Integer>();
        
        for(int i = 0; i < G.V(); ++i)
        {
            if(bfsv.hasPathTo(i) && (bfsw.hasPathTo(i)))
            {
                ancestors.add(i);
            }
        }
        
        for(int p: ancestors)
        {
            int path = bfsv.distTo(p) + bfsw.distTo(p);
            
            if(path < shortestPath)
            {
                shortestPath = path;
                shortestAncestor = p;
            }
        }
        
        shortestPath sp = new shortestPath();
 
        sp.ancestor = shortestAncestor;
        if(shortestPath != Integer.MAX_VALUE)
        {
            sp.length = shortestPath;        
        }
        else
        {
            sp.length = -1;
        }
        
        return sp;
    }
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    { 
        shortestPath sp = findShortestPath(v, w);
                
        if(sp != null)
        {
            return sp.length;
        }
        
        return -1;
    }
    
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {  
        shortestPath sp = findShortestPath(v, w);
                
        if(sp != null)
        {
            return sp.ancestor;
        }
   
        return -1;
    }
    
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        return findShortestPath(v, w).length;
    }
    
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        return findShortestPath(v, w).ancestor;
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        
        StdOut.println(G.toString());
        
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
*/

//----- old mode without BFS

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import java.util.LinkedList;  
import java.util.Arrays;    

class shortestPath
{
    int ancestor;
    int length;
}

public class SAP {
    private int depth[];
    private Digraph G;
    private int root;
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        if (G == null)
        {
            throw new java.lang.NullPointerException("Ivalid digraph");
        }
        
        this.G = G;
    }
    
    private boolean isValid(int v) 
    {
        return (v >= 0 && v <= this.G.V() - 1);
    }
    
    private boolean isValid(Iterable<Integer> v) 
    {
        if(v == null)
        {
            return false;
        }
        
        for (Integer integer : v) 
        {
            if (!isValid(integer)) 
            {
                return false;
            }
        }

        return true;
    }
    
    private shortestPath findShortestPath(int v, int w)
    {
        if (!isValid(v))
        {
            throw new java.lang.NullPointerException("Invalid v parameter");
        }
        
        if (!isValid(w))
        {
            throw new java.lang.NullPointerException("Ivalid w parameter");
        }
        
        Queue<Integer> q = new Queue<Integer>();
        boolean marked[] = new boolean[G.V()];
        int distTo[] = new int[G.V()];
        
        q.enqueue(v);
        q.enqueue(w);
        
        marked[v] = true;
        marked[w] = true;
        distTo[v] = 0;
        distTo[w] = 0;        
        
        while(!q.isEmpty())
        {
            int a = q.dequeue();
            for(int b: G.adj(a))
            {
                if(marked[b])
                {
                    int length = distTo[b] + distTo[a] + 1;
                    shortestPath sp = new shortestPath();
                    
                    sp.ancestor = b;
                    sp.length = length;
                    
                    return sp;
                }
                
                distTo[b] = distTo[a] + 1;
                marked[b] = true;
                q.enqueue(b);
            }
        }
 
        return null;
    }
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    { 
        if (!isValid(v))
        {
            throw new java.lang.NullPointerException("Invalid v parameter");
        }
        
        if (!isValid(w))
        {
            throw new java.lang.NullPointerException("Ivalid w parameter");
        }
        
        shortestPath sp = findShortestPath(v, w);
                
        if(sp != null)
        {
            return sp.length;
        }
        
        return -1;
    }
    
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        if (!isValid(v))
        {
            throw new java.lang.NullPointerException("Invalid v parameter");
        }
        
        if (!isValid(w))
        {
            throw new java.lang.NullPointerException("Ivalid w parameter");
        }
        
        shortestPath sp = findShortestPath(v, w);
                
        if(sp != null)
        {
            return sp.ancestor;
        }
   
        return -1;
    }
    
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        if (!isValid(v))
        {
            throw new java.lang.NullPointerException("Invalid v parameter");
        }
        
        if (!isValid(w))
        {
            throw new java.lang.NullPointerException("Ivalid w parameter");
        }
        
        int shortestLen = G.V() + 1;
        int len = -1;
        
        for(int iv: v)
        {
            for(int iw: w)
            {
                len = length(iv, iw);
                if((len > 0) && (len < shortestLen))
                {
                    shortestLen = len;
                }
            }
        }
        
        if(shortestLen < G.V() + 1)
        {
            return shortestLen;
        }
        
        return -1;
    }
    
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        if (!isValid(v))
        {
            throw new java.lang.NullPointerException("Invalid v parameter");
        }
        
        if (!isValid(w))
        {
            throw new java.lang.NullPointerException("Ivalid w parameter");
        }
        
        int ancestor;
        int len;
        shortestPath sp;
        shortestPath maxsp = new shortestPath();
        
        maxsp.length = G.V() + 1;
        maxsp.ancestor = -1;
        
        for(int iv: v)
        {
            for(int iw: w)
            {
                sp = findShortestPath(iv, iw);

                if((sp.length > 0) && (sp.length < maxsp.length))
                {
                   maxsp.length = sp.length;
                   maxsp.length = sp.ancestor;
                }
            }
        }

        return maxsp.ancestor;
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        
        StdOut.println(G.toString());
        
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}