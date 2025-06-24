package Interfaces;

public interface MyList<K> extends Iterable<K> {
    void add(K o);

    int getSize();
}
