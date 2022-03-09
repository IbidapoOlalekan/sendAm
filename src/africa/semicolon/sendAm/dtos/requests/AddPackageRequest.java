package africa.semicolon.sendAm.dtos.requests;

import africa.semicolon.sendAm.data.models.PackageDescription;

public class AddPackageRequest {
    private String name;
    private double weightInGrammes;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightInGrammes() {
        return weightInGrammes;
    }

    public void setWeightInGrammes(double weightInGrammes) {
        this.weightInGrammes = weightInGrammes;
    }
}
