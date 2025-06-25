package Domain;

// Clase auxiliar para ordenar géneros por conteo
public class GeneroConConteo implements Comparable<GeneroConConteo> {
    private final String genero;
    private final int conteo;

    public GeneroConConteo(String genero, int conteo) {
        this.genero = genero;
        this.conteo = conteo;
    }

    public String getGenero() {
        return genero;
    }

    public int getConteo() {
        return conteo;
    }

    @Override
    public int compareTo(GeneroConConteo otro) {
        return Integer.compare(otro.conteo, this.conteo); // descendente
    }
}
