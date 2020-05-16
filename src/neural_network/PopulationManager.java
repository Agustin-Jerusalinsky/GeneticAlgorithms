package neural_network;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class PopulationManager<T extends GeneticIndividual>{
    static int BATCH = 5;
    int elitistNumber = 5;
    int parentsThreshold = 12;
    int generation = 0;
    static boolean LOG = true;
    Map<Integer, GeneticIndividual> playerMap;
    List<GeneticIndividual> individuals;
    static Random random;


    public PopulationManager(Collection<GeneticIndividual> individuals){
        this.individuals= new ArrayList<>(individuals);

    }
    public void setParentsThreshold(int n){parentsThreshold=n;}
    public void setElitistNumber(int n){elitistNumber=n;}
    public void runGeneration(Function<GeneticIndividual,Double> fitnessFunction){
        for(GeneticIndividual individual : individuals){
         individual.setScore(fitnessFunction.apply(individual));
        }
        naturalSelection();
        generation++;
    }
    public void naturalSelection() {
        individuals.sort((p1, p2) -> (int) (p2.score() - p1.score()));
        List<GeneticIndividual> children = individuals.subList(0, elitistNumber);
        generation++;
        for (int i = elitistNumber; i < individuals.size(); i++) {
            GeneticIndividual individual1 = individuals.get(random.nextInt(parentsThreshold));
            GeneticIndividual individual2 = individuals.get(random.nextInt(parentsThreshold));
            children.add(individual1.breed(individual2));
        }
        individuals=children;
        playerMap=new HashMap<>();
        for (GeneticIndividual individual : children) {
            playerMap.put(individual.getId(), individual);
        }
    }

    public static void save(NeuralNetwork n, String path) {
        try {
            FileOutputStream f = new FileOutputStream(new File(path));
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write objects to file
            o.writeObject(n);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("File not found for saving");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error initializing stream");
        }
    }
}
