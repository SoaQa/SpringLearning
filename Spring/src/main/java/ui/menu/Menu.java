package ui.menu;

import org.springframework.stereotype.Component;
import ui.menu.interfaces.IMenu;

@Component("mainMenu")
public class Menu implements IMenu {

    @Override
    public void printMenu() {
        System.out.println("1. Меню услуг");
        System.out.println("2. Меню комнат");
        System.out.println("3. Меню посетителей");
        System.out.println("Введите 0 для выхода из программы!");
    }

    @Override
    public void callMethod(int index) throws RuntimeException{
        throw new RuntimeException("Вызываемые методы отсутвуют!");
    }


}
