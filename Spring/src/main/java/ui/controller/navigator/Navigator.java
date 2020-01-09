package ui.controller.navigator;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ui.menu.interfaces.IMenu;
import ui.menu.ClientMenu;
import ui.menu.Menu;
import ui.menu.RoomMenu;
import ui.menu.ServicesMenu;

import java.io.IOException;
import java.util.Scanner;

@Component
@Getter
public class Navigator {
    private static final int SERVICES_MENU =0;
    private static final int ROOMS_MENU = 1;
    private static final int CLIENTS_MENU = 2;
    private static final int MAIN_MENU = 3;
    private IMenu currentMenu;

    @Autowired
    @Qualifier("serviceMenu")
    private IMenu serviceMenu;

    @Autowired
    @Qualifier("roomMenu")
    private IMenu roomMenu;

    @Autowired
    @Qualifier("clientMenu")
    private IMenu clientMenu;

    @Autowired
    @Qualifier("mainMenu")
    private IMenu mainMenu;

    private Scanner scanner = new Scanner(System.in);
    private int param=0;


    public void navigate(int index){
        switch (index){
            case SERVICES_MENU:
                    currentMenu = serviceMenu;
                    currentMenu.printMenu();
                    param = scanner.nextInt();
                    currentMenu.callMethod(param);
                    currentMenu = mainMenu;
                    break;

            case ROOMS_MENU:
                    currentMenu = roomMenu;
                    currentMenu.printMenu();
                    param = scanner.nextInt();
                    currentMenu.callMethod(param);
                    currentMenu = mainMenu;
                    break;

            case CLIENTS_MENU:
                    currentMenu =clientMenu;
                    currentMenu.printMenu();
                    param = scanner.nextInt();
                    currentMenu.callMethod(param);
                    currentMenu = mainMenu;
                    break;

            case MAIN_MENU:
                currentMenu = mainMenu;
                break;
        }
        currentMenu.printMenu();
    }

}
