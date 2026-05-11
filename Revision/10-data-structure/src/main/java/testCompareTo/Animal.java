package testCompareTo;

public class Animal implements Comparable<Animal>{
    private String name;
    private String owner;

    @Override
    public int compareTo(Animal o) {
        return this.name.compareTo(o.getName());
    }

    public Animal(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return name;
    }
}
