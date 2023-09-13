package View;

import java.util.Scanner;

import Control.iGetView;


public class View implements iGetView{
    Scanner in = new Scanner(System.in);
    @Override
    // Метод считывающий введённую строку с клавиатуры
    public String prompt(String msg) {
       System.out.print(msg + " -> ");
       return in.nextLine();
    }


    
}
