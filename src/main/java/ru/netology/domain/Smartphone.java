package ru.netology.domain;

public class Smartphone extends Product {
   private String creator;

    public Smartphone(int id, String name, int price, String creator) {
        super(id, name, price);
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Smartphone(int id, String name, int price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "creator='" + creator + '\'' +
                '}';
    }
}
