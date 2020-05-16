package neural_network;

public interface GeneticIndividual {
    public double score();
    public GeneticIndividual breed(GeneticIndividual individual);
    public int getId();
    public void setScore(Double score);
}
