package Domain;

public class Review {
    private double evaluation;

    //Empty Constructor
    public Review() {
    }

    //Complete Constructor
    public Review(double evaluation) {
        this.evaluation = evaluation;
    }

    //Getters and Setters
    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
}
