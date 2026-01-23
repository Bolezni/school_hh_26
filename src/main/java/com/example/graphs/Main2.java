package com.example.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main2 {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private void dfs(Node node, Node copy, Node[] visited){
        visited[copy.val]=copy;

        for(Node n:node.neighbors){
            if(visited[n.val]==null){
                Node newNode = new Node(n.val);
                copy.neighbors.add(newNode);
                dfs(n,newNode,visited);
            }else{
                copy.neighbors.add(visited[n.val]);
            }
        }
    }

    public Node recurse(Node node, HashMap<Integer, Node> visited){
        if(visited.containsKey(node.val)){
            return visited.get(node.val);
        }

        Node curr = new Node(node.val);
        visited.put(node.val, curr);
        for(Node n:node.neighbors){
            curr.neighbors.add(recurse(n,visited));
        }
        return curr;
    }

    public Node clone(Node node){
        if(node == null) return null;
        return recurse(node, new HashMap<Integer,Node>());
    }

    public Node cloneGraph(Node node) {
        Node clone = new Node(node.val);
        Node[] visited = new Node[101];
        Arrays.fill(visited,null);
        dfs(node,clone,visited);
        return clone;
    }
}
