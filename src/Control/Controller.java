package Control;

import View.TextRus;

public class Controller {
    private TextRus text = new TextRus();
    private iGetModel model;
    private iGetView view;


    public Controller(iGetModel model, iGetView view) {
        this.model = model;
        this.view = view;
    }

    public void run(){
        model.getAllToys();
        model.printAllToys();
        Command com = Command.NONE;
        boolean getNewIteration = true;
        while(getNewIteration){
            String command = view.prompt(text.commands);
            com = Command.valueOf(command.toUpperCase());
            switch(com){
                case EXIT:
                    getNewIteration=false;
                    System.out.println(text.exit);
                    break;
                case NEW:
                    model.addToy();
                    break;
                case RAFFLE:
                    model.raffle();
                    break;
                case CHANGE:
                    model.changeToy();
                    break;
                case SAVE:
                    model.saveAllToyToFile();
                    break;    
                case DELETE:
                    model.dellToy();
                    break;
                case PRINT:
                    model.printAllToys();
                    break;
                case NONE:
                    break; 
            }

        }
    }
    
}
