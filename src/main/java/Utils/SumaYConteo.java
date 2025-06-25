package Utils;

// Clase auxiliar para acumular suma y cantidad
public class SumaYConteo {
    private double suma = 0;
    private double cantidad = 0;

    //Funcion que sirve para agragar el valor de la review y aumentar el contador de cantidad
    public void agregar(double valor) {
        suma += valor;
        cantidad++;
    }

    public double media() {
        return cantidad == 0 ? 0 : (double) suma / cantidad;
    }

    public double getCantidad() {
        return cantidad;
    }
}
