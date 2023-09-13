package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Control.iGetModel;
import Control.iGetView;

import View.TextRus;
import View.View;

public class ModelToy implements iGetModel {
    iGetView view = new View();
    TextRus text = new TextRus();
    Random rnd = new Random();
    private String fileName;
    public List<Toy> toys = new ArrayList<Toy>();



    public ModelToy(String fileName) {
        this.fileName = fileName;

        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Проводит розыгрыш игрушек
     * 
     * @param toys список игрушек участвующих в розыгрыше
     */
    public void raffle() {
        int sumToys = countToys(toys);
        Toy[] toysForRaffle = genArrayToys(toys, sumToys);
        Toy prizeToy = toysForRaffle[rnd.nextInt(sumToys)];

        System.out.println(text.prizeToy + prizeToy);
        for (Toy toy : toys) {
            if (toy == prizeToy) {
                toy.setInTotal(toy.getInTotal()-1);
                if (toy.getInTotal() == 0) {
                    toy.setFrequency(0);
                }
            }
        }
    
    }

    /**
     * Суммирует "веса" всех ирушек
     * 
     * @param toys список игрушек участвующих в розыгрыше
     * @return
     */
    private int countToys(List<Toy> toys) {
        int sumToys = 0;
        for (Toy toy : toys) {
            sumToys += toy.getFrequency();
        }
        return sumToys;
    }

    /**
     * Создаёт массив для проведения розыгрыша
     * 
     * @param toys    список игрушек участвующих в розыгрыше
     * @param sumToys общей "вес" всех игрушек в розыгрыше
     * @return
     */
    private Toy[] genArrayToys(List<Toy> toys, int sumToys) {
        Toy[] toysForRaffle = new Toy[sumToys];
        int count = 0;
        int idCount = 0;
        for (int i = 0; i < sumToys; i++) {
            if (count < toys.get(idCount).getFrequency()) {
                count++;
                toysForRaffle[i] = toys.get(idCount);
            } else {
                idCount++;
                toysForRaffle[i] = toys.get(idCount);
                count = 1;
            }
        }
        return toysForRaffle;
    }


    /**
     * Извлекает список игрушек из текстового файла
     * 
     */
    public void getAllToys() {
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] param = line.split(";");
                Toy elem = new Toy(Integer.parseInt(param[0]), param[1], Integer.parseInt(param[2]),
                        Integer.parseInt(param[3]));
                toys.add(elem);
                line = reader.readLine();
            }
            reader.close();
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Сохраняен нынешний список игрушек розыгрыша в файл
     */
    public void saveAllToyToFile() {
        try(FileWriter fw = new FileWriter(fileName, false))
        {
            for(Toy toy : toys)
            {
                fw.write(toy.getId() + ";" + toy.getName()+";"+toy.getInTotal()+";"+toy.getFrequency());
                fw.append('\n');
            }
            fw.flush();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
   /**
    * Создаёт игрушку и добавляет её в список
    */
    public void addToy() {
        int id =  Integer.parseInt(view.prompt(text.inputIdToy));
        String name = view.prompt(text.inputToyName);
        int inTotal = Integer.parseInt(view.prompt(text.inputInTotalToy));
        int frequency = Integer.parseInt(view.prompt(text.inputFrequencyToy));
        Toy elem = new Toy(id, name, inTotal, frequency);
        toys.add(elem);
        System.out.println(text.toyAdded);
    }


    /**
     * Печатает весь список игрушек в розыгрыше
     */
    public void printAllToys(){
        System.out.println(text.toysInRaffle);
        for (Toy toy : toys) {
            System.out.println(toy);
        }
        if (toys.size() == 0){
            System.out.println(text.empty);
        }
    }
    
    /**
     * Удаляет выбранную игрушку
     */
    public void dellToy() {
        int id = Integer.parseInt(view.prompt(text.inputIdToy));
        Toy forDel = null;
        boolean availabilityId = false;
        for (Toy toy : toys){
                       
            if(toy.getId() == id){
                System.out.println(toy + text.deleted);
                forDel = toy;
                toys.remove(forDel);
                availabilityId = true;
            }
        }
        
        if (!availabilityId) {
            
            System.out.println(text.idNotFound);
        }
        
    }


    /**
     * Изменяет выбранную игрушку
     */
    public void changeToy(){
        int id = Integer.parseInt(view.prompt(text.inputIdToy));
        boolean availabilityId = false;
        for (Toy toy : toys){
                       
            if(toy.getId() == id){
                toy.setName(view.prompt(text.inputToyName));
                toy.setInTotal(Integer.parseInt(view.prompt(text.inputInTotalToy)));
                toy.setFrequency(Integer.parseInt(view.prompt(text.inputFrequencyToy)));
                System.out.println(toy + text.changed);
                availabilityId = true;
            }
        }
        
        if (!availabilityId) {
            
            System.out.println(text.idNotFound);
        }
    }


}
