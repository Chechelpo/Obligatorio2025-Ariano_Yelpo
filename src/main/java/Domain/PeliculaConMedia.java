package Domain;

// Clase auxiliar para representar película con media
public class PeliculaConMedia implements Comparable<PeliculaConMedia> {
    private final Pelicula pelicula;
    private final double media;

    public PeliculaConMedia(Pelicula pelicula, double media) {
        this.pelicula = pelicula;
        this.media = media;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public double getMedia() {
        return media;
    }

    @Override
    public int compareTo(PeliculaConMedia otra) {
        return Double.compare(otra.media, this.media); // descendente
    }
}
