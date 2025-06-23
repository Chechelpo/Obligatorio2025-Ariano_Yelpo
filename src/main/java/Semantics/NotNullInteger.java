package Semantics;

/***
 * Wrapper class with semantic check ( value!= 0 ) <br>
 * Implements:
 * Comparable, Equals, toString
 */
public class NotNullInteger implements Comparable<NotNullInteger> {
    private int value;

    public NotNullInteger(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("Value cannot be zero");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("Value cannot be zero");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof NotNullInteger) {
            return this.value == ((NotNullInteger) obj).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(NotNullInteger o) {
        return Integer.compare(this.value, o.value);
    }
}
