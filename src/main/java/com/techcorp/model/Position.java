package com.techcorp.model;

// Reprezentuje stanowiska w firmie wraz z bazowymi wynagrodzeniami i poziomami hierarchii.
public enum Position {
    PREZES(25_000, 1),
    WICEPREZES(18_000, 2),
    MANAGER(12_000, 3),
    PROGRAMISTA(8_000, 4),
    STAZYSTA(3_000, 5);

    private final double baseSalary;
    private final int level;

    Position(double baseSalary, int level) {
        this.baseSalary = baseSalary;
        this.level = level;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public int getLevel() {
        return level;
    }
}
