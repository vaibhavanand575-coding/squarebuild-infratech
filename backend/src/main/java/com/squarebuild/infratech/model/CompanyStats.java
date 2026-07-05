package com.squarebuild.infratech.model;

public class CompanyStats {
    private final int totalSquareYards;
    private final int totalProjects;
    private final int residentialPlots;
    private final int farmHouses;
    private final int yearFounded;

    public CompanyStats(int totalSquareYards, int totalProjects, int residentialPlots, int farmHouses, int yearFounded) {
        this.totalSquareYards = totalSquareYards;
        this.totalProjects = totalProjects;
        this.residentialPlots = residentialPlots;
        this.farmHouses = farmHouses;
        this.yearFounded = yearFounded;
    }

    public int getTotalSquareYards() { return totalSquareYards; }
    public int getTotalProjects() { return totalProjects; }
    public int getResidentialPlots() { return residentialPlots; }
    public int getFarmHouses() { return farmHouses; }
    public int getYearFounded() { return yearFounded; }
}
