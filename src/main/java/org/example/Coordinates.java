package org.example;

import java.util.Objects;

public class Coordinates {
    private final int n;
    private final int m;

    public Coordinates(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public Coordinates(Coordinates coordinates) {
        this.n = coordinates.getN();
        this.m = coordinates.getM();
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return n == that.n && m == that.m;
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, m);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "n=" + n +
                ", m=" + m +
                '}';
    }
}
