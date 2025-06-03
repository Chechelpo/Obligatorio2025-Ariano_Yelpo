package Domain;

public class Review {
    private int evaluation;
    private Genero genere;

    //Empty Constructor
    public Review() {
    }

    //Complete Constructor
    public Review(int evaluation, Genero genere) {
        this.evaluation = evaluation;
        this.genere = genere;
    }

    //Getters and Setters
    public Genero getGenere() {
        return genere;
    }

    public void setGenere(Genero genere) {
        this.genere = genere;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
}
