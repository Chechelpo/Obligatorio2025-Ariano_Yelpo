package Domain;

public class ResultadoDirector implements Comparable<ResultadoDirector>{
    private String nombre;
    private int cantidadPeliculas;
    private double mediana;

    public ResultadoDirector(String nombre, int cantidadPeliculas, double mediana) {
        this.nombre = nombre;
        this.cantidadPeliculas = cantidadPeliculas;
        this.mediana = mediana;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadPeliculas() {
        return cantidadPeliculas;
    }

    public void setCantidadPeliculas(int cantidadPeliculas) {
        this.cantidadPeliculas = cantidadPeliculas;
    }

    public double getMediana() {
        return mediana;
    }

    public void setMediana(double mediana) {
        this.mediana = mediana;
    }

    @Override
    public int compareTo(ResultadoDirector otro) {
        return Double.compare(otro.mediana, this.mediana);
    }

}
