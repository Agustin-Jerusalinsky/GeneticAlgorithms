package neural_network;

import java.io.Serializable;

public class Layer implements Serializable {

    Node[] nodes;
    int numberOfInputs;

    Layer(int numberOfNodes,int numberOfInputs){
        nodes = new Node[numberOfNodes];
        this.numberOfInputs = numberOfInputs;
        for(int i=0;i<numberOfNodes;i++){
            nodes[i] = new Node(numberOfInputs);
        }
    }

    public void randomizeNodes(){
        for(int i=0;i<nodes.length;i++){
            nodes[i].randomize();
        }
    }

    public double[] calculateNodesValues(double[] input){
        double[] valueNodes = new double[nodes.length];
        for(int i=0;i<nodes.length;i++){
            valueNodes[i] = nodes[i].calculate(input);
        }
        return valueNodes;
    }
}
