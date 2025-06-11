package Domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


public class Review {
    private double evaluation;
    private Usuario usuario;
    private Pelicula pelicula;
    private LocalDate fecha;
    //Complete Constructor
    public Review(double evaluation,Usuario usuario,Pelicula pelicula, long timestamp) {
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.evaluation = evaluation;

        // Convertimos el timestamp (segundos) a LocalDate
        this.fecha = Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    //Getters and Setters
    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
}
