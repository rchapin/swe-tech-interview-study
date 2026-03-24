package com.codility;

// you can also use imports, for example:
import java.util.*;

public class Solution {

    static Map<String, Node> AIRPORTS = getAirports();
    static Map<String, Node> getAirports() {
        Node sfo = new Node("SFO");
        Node lax = new Node("LAX");
        Node den = new Node("DEN");
        Node atl = new Node("ATL");
        Node ord = new Node("ORD");
        Node bal = new Node("BAL");
        sfo.destinations.add(lax);
        lax.destinations.add(den);
        den.destinations.add(atl);
        den.destinations.add(ord);
        ord.destinations.add(bal);

        Map<String, Node> retVal = new HashMap<>();
        retVal.put("SFO", sfo);
        retVal.put("LAX", lax);
        retVal.put("DEN", den);
        retVal.put("ATL", atl);
        retVal.put("ORD", ord);
        retVal.put("BAL", bal);
        return retVal;
    }

    static String findPath(String start, String end) {
        if (!AIRPORTS.containsKey(start) || !AIRPORTS.containsKey(end)) {
            return "";
        }

        Deque<Node> queue = new ArrayDeque<>();
        Node startNode = AIRPORTS.get(start);
        queue.add(startNode);
        Node finalDest = null;

        while (queue.size() > 0) {
            Node curNode = queue.remove();  // SFO
            for (Node childNode : curNode.destinations) {
                System.out.printf("curNode=%s%n", curNode.value);
                
                // For each child, add the path so far from the parent
                // including the curNode/parent to keep track of our whole path.
                for (String existingPath : curNode.path) {
                    childNode.path.add(existingPath);
                }
                
                System.out.printf("childNode.path=%s%n", childNode.path);
                // Add the curNode to the childs path
                childNode.path.add(curNode.value);
                System.out.printf("childNode.path=%s%n", childNode.path);


                // Check if we have arrived at our destination
                if (childNode.value.equals(end)) {
                    finalDest = childNode;
                    break;
                }

                System.out.printf("Adding child=%s%n", childNode.value);
                queue.add(childNode);

            } 
        }

        if (finalDest == null) {
            return "";
        }

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < finalDest.path.size(); i++) {
            if (i < finalDest.path.size() - 2) {
                // Add the current, plus the next step as a trip pair
                buf.append("[" + finalDest.path.get(i) + ", " + finalDest.path.get(i+1) + "], ");
            } else {
                // I'm at the end, add the last one, plus me.
                buf.append(" [" + finalDest.path.get(i) + ", " + finalDest.value + "]");                
            }
        }
        System.out.println(buf);
        return buf.toString();
    }

    public static void main(String [] args) {
        findPath("SFO", "BAL");
        findPath("SFO", "YYZ");
    }

    public static class Node {
        String value;
        List<Node> destinations;
        List<String> path;

        public Node(String value) {
            destinations = new ArrayList<>();
            path = new ArrayList<>();
            this.value = value;
        }
    }
}


