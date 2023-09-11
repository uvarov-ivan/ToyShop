
import java.util.ArrayList;
import java.util.List;

import Model.ModelToy;
import Model.Toy;

public class App {
    public static void main(String[] args) throws Exception {
        
        Toy bear = new Toy(1, "Bear", 1, 3);
        Toy car = new Toy(2, "Car", 1, 7);
        Toy car2 = new Toy(3, "Car2", 1, 5);
        List<Toy> toys = new ArrayList<Toy>();
        toys.add(bear);
        toys.add(car);
        toys.add(car2);
        System.out.println(bear);
        System.out.println(car);
        System.out.println(car2);
     
        Toy prizeToy = ModelToy.raffle(toys);
        System.out.println("\nПризовая игрушка:" + prizeToy);
        for (Toy toy : toys) {
            if (toy == prizeToy) {
                toy.setInTotal(toy.getInTotal()-1);
                if (toy.getInTotal() == 0) {
                    toy.setFrequency(0);
                }
            }
        }
        
        System.out.println(bear);
        System.out.println(car);
        System.out.println(car2);
    }


}
