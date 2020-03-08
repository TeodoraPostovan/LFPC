

import java.util.*;

class Relation {
    private int to;
    private String value;

    Relation(int to, String value) {
        this.to = to;
        this.value = value;
    }

    public int getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }
}

class Node {
    private int index;
    private List<Relation> relations;
    private boolean fin;

    Node(int index, boolean fin) {
        this.fin = fin;
        this.index = index;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public boolean isFin() {
        return fin;
    }
}

class Graph {
    private List<Node> nodes;

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}

public class Main {

    public static boolean checkPath(Graph graph) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        List<String> letters = Arrays.asList(word.split(""));
        int letterIndex = 0, relation=0, node=0;
        NODE:while (node != graph.getNodes().size()) {
            List<String> values = new ArrayList<>();
            for (Relation tempRel:graph.getNodes().get(node).getRelations()) {
                values.add(tempRel.getValue());
            }
            if (values.contains(letters.get(letterIndex))) {
                while (relation < graph.getNodes().get(node).getRelations().size()) {
                    Relation current = graph.getNodes().get(node).getRelations().get(relation);
                    if (current.getValue().equals(letters.get(letterIndex))) {
                        node = current.getTo();
                        letterIndex++;
                        relation = 0;
                        if (letterIndex>=letters.size()) {
                            break NODE;
                        }
                    } else {
                        relation++;
                    }
                }
            } else {
                letterIndex=-1;
                break NODE;
            }
        }
        return letterIndex>=letters.size()-1 && graph.getNodes().get(node).isFin();
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        Node q0 = new Node(0, false);
        Relation q0q0b = new Relation(0, "b");
        Relation q0q1d = new Relation(1, "d");
        q0.setRelations(Arrays.asList(q0q0b, q0q1d));

        Node q1 = new Node(1, false);
        Relation q1q1a = new Relation(1, "a");
        Relation q1q3b = new Relation(3, "b");
        Relation q1q2d = new Relation(2, "d");
        q1.setRelations(Arrays.asList(q1q1a, q1q3b, q1q2d));

        Node q2 = new Node(2, false);
        Relation q2q2c = new Relation(2, "c");
        Relation q2q3a = new Relation(3, "a");
        q2.setRelations(Arrays.asList(q2q2c, q2q3a));

        Node q3 = new Node(3, true);
        q3.setRelations(Arrays.asList());

        graph.setNodes(Arrays.asList(q0, q1, q2, q3));

        System.out.println(checkPath(graph));
    }
}