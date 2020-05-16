package neural_network;

import java.io.*;
import java.util.Random;

public class NeuralNetwork implements Serializable {
    static int ids;
    int id;
    double mutationRate = 0.1;
    Layer[] layers;
    int[] architecture;
    static Random random = new Random();
    public NeuralNetwork(double mutationRate,int ... architecture){
        id=ids++;
        this.mutationRate=mutationRate;
        this.architecture = architecture;
        layers = new Layer[architecture.length-1];
        for(int i=0;i<architecture.length-1;i++){
            layers[i] = new Layer(architecture[i+1],architecture[i]);

        }
    }
    
    
    public void randomize(){
        for(int i=0;i<layers.length;i++){
            layers[i].randomizeNodes();
        }
    }

    public double[] feedForward(double ... inputs){
        double[] results = inputs.clone();
        for(int i=0;i<layers.length;i++){
            results=layers[i].calculateNodesValues(results);
        }
        return results;
    }

    //Random Neuron
    public NeuralNetwork breed(NeuralNetwork neuralNetwork){
        NeuralNetwork newNeuralNetwork = new NeuralNetwork(mutationRate,architecture);
        for(int i=0;i<layers.length;i++){
            for(int j=0;j<layers[i].nodes.length;j++){

                if(Math.random() > 0.5){
                    newNeuralNetwork.layers[i].nodes[j] = new Node(layers[i].nodes[j].coefficients.clone());
                }else{
                    newNeuralNetwork.layers[i].nodes[j] = new Node(neuralNetwork.layers[i].nodes[j].coefficients.clone());
                }
                for(int w=0;w<layers[i].nodes[j].coefficients.length;w++){
                    if(Math.random() < mutationRate){

                        newNeuralNetwork.layers[i].nodes[j].coefficients[w] += random.nextGaussian()/10;
                        if(newNeuralNetwork.layers[i].nodes[j].coefficients[w] < -1)newNeuralNetwork.layers[i].nodes[j].coefficients[w] = -1;
                        else if(newNeuralNetwork.layers[i].nodes[j].coefficients[w] > 1)newNeuralNetwork.layers[i].nodes[j].coefficients[w] = 1;
                    }
                }
            }
        }

        return newNeuralNetwork;
    }

    //Random layer and Random Node- vertical split
    public NeuralNetwork breedVerticalSplit(NeuralNetwork neuralNetwork){
        NeuralNetwork newNeuralNetwork = new NeuralNetwork(mutationRate,architecture);
        int layer = random.nextInt(architecture.length-1);
        int node = random.nextInt(neuralNetwork.layers[layer].nodes.length);

        for(int i=0;i<layers.length;i++){
            for(int j=0;j<layers[i].nodes.length;j++){
                if(i<layer && j<node){
                    newNeuralNetwork.layers[i].nodes[j] = new Node(layers[i].nodes[j].coefficients.clone());
                }else{
                    newNeuralNetwork.layers[i].nodes[j] = new Node(neuralNetwork.layers[i].nodes[j].coefficients.clone());
                }
                for(int w=0;w<layers[i].nodes[j].coefficients.length;w++){
                    if(Math.random() < mutationRate){
                        newNeuralNetwork.layers[i].nodes[j].coefficients[w] += random.nextGaussian()/10;
                        if(newNeuralNetwork.layers[i].nodes[j].coefficients[w] < -1)newNeuralNetwork.layers[i].nodes[j].coefficients[w] = -1;
                        else if(newNeuralNetwork.layers[i].nodes[j].coefficients[w] > 1)newNeuralNetwork.layers[i].nodes[j].coefficients[w] = 1;
                    }
                }
            }
        }

        return newNeuralNetwork;
    }

    //Random node per layer- horizontal split
    public NeuralNetwork breedHorizontalSplit(NeuralNetwork neuralNetwork){
        NeuralNetwork newNeuralNetwork = new NeuralNetwork(mutationRate,architecture);
        Random random = new Random();
        Math.random();
        double splitPercent = Math.random();
        for(int i=0;i<layers.length;i++){
            int motherNode = (int) Math.floor(layers[i].nodes.length*splitPercent);
            //int fatherNode = (layers[i].nodes.length -motherNode);

            for(int j=0;j<motherNode;j++){
                newNeuralNetwork.layers[i].nodes[j] =new Node( this.layers[i].nodes[j].coefficients.clone());
            }
            for(int j=motherNode;j<layers[i].nodes.length;j++){
                newNeuralNetwork.layers[i].nodes[j] = new Node(neuralNetwork.layers[i].nodes[j].coefficients.clone());
            }
        }
        //int layer = random.nextInt(architecture.length-1)+1;

        return newNeuralNetwork;
    }
    

}


