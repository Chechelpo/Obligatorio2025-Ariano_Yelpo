package Semantics;

/***
 * Wrapper class for Strings <br>
 * Implements: compareTo, equals, toString
 */
public class NotBlankString implements Comparable<NotBlankString> {
    private final String value;
    public NotBlankString(String value) {
        if(value == null || value.isBlank()) throw new IllegalArgumentException("Blank or null string");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o instanceof NotBlankString) {
            return this.value.equals(((NotBlankString)o).value);
        }
        return false;
    }

    @Override
    public int compareTo(NotBlankString o) {
        return value.compareTo(o.value);
    }
}
