package Model;

public class Toy {
    
    private int id;
    private String name;
    private int inTotal;
    private int frequency;




    /**
     * 
     * @param id ID игрушки
     * @param name  Название игрушки
     * @param inTotal Общее количество данных игрушек в розыгрыше
     * @param frequency Частота выпадания данной игрушки (её вес)
     */
    public Toy(int id, String name, int inTotal, int frequency) {
        this.id = id;
        this.name = name;
        this.inTotal = inTotal;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInTotal() {
        return inTotal;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setInTotal(int inTotal){
        this.inTotal = inTotal;
    }

    @Override
    public String toString() {
        return "\nName = " + name + "; ID = " + this.id + "; In total = " + inTotal + "; Prize frequency=" + frequency + ";";
    }


    
}

