package org.chocan.entities;

public abstract class ClientInfo {

    private String name;
    private int number; //9 digits
    private Coordinate coordinate;


    public ClientInfo(String name, int number, Coordinate coordinate){
        this.coordinate = coordinate;
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
