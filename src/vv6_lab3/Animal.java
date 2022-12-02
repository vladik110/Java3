package bys6_lab3;

public class Animal {
    int id;            // Код записи
    String nameAnimal;  // Название животного
    String colorAnimal;   // Цвет животного

    public Animal() {
        this.id = 0;
        this.nameAnimal = "";
        this.colorAnimal = "";
    }

    public Animal(String nameAnimal, String colorAnimal) {
        this.id = 0;
        this.nameAnimal = nameAnimal;
        this.colorAnimal = colorAnimal;
    }

    public Animal(String nameAnimal) {
        this.id = 0;
        this.nameAnimal = nameAnimal;
        
    }

    public int getId() {
        return id;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public String getColorAnimal() {
        return colorAnimal;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
    }

    public void setColorAnimal(String colorAnimal) {
        this.colorAnimal = colorAnimal;
    }


    @Override
    public String toString() {
        return String.format("Название животного - %s, Цвет - %s", nameAnimal, colorAnimal);
    }
}
