package neural_network;

import java.io.Serializable;
import java.util.Random;

public class Node implements Serializable {

    double[] coefficients;
    double value;
    static Random random = new Random();


    Node(int numInputs){
        coefficients = new double[numInputs+1];
    }

    Node(double[] coefficients){
        this.coefficients = coefficients;
    }


    public double calculate(double[] inputs){
        double sum = 0;
        for(int i=0;i<coefficients.length-1;i++){
            sum += coefficients[i]*inputs[i];
        }
        sum += coefficients[coefficients.length-1];
        return activationFunction(sum);
    }

    public void randomize(){

        for(int i=0;i<coefficients.length;i++){
            coefficients[i] = random.nextGaussian()/1.15;
            if(coefficients[i] < -1)coefficients[i] = -1;
            else if(coefficients[i] > 1)coefficients[i] = 1;
        }
    }

    public double activationFunction(double sum){
        return Math.tanh(sum);
    }

}
