package ui.menu;

import hotelManager.controllers.interfaces.IAdditionalServiceController;
import hotelManager.controllers.interfaces.IClientController;
import hotelManager.controllers.interfaces.IRoomController;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.services.comparators.CompareClientsByName;
import hotelManager.services.comparators.CompareClientsByRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.menu.interfaces.IMenu;


import java.util.Scanner;

@Component("clientMenu")
public class ClientMenu implements IMenu {

    @Autowired
    private IAdditionalServiceController additionalServiceController;
    @Autowired
    private IClientController clientController;
    @Autowired
    private IRoomController roomController;
    private Scanner scanner = new Scanner(System.in);


    @Override
    public void printMenu() {
        System.out.println("1. Показать всех клиентов.");
        System.out.println("2. Показать услуги клиента.");
        System.out.println("3. Создать клиента.");
        System.out.println("Введите 0 для выхода в главное меню");
    }

    @Override
    public void callMethod(int index) {
        switch (index){
            case 1: printAllClients(); break;
            case 2: printClientServices(); break;
            case 3: createClient(); break;
        }
    }

    private void printAllClients(){
        System.out.println("Выберите тип сортировки");
        System.out.println("1. Соритровать по имени");
        System.out.println("2. Сортировать по дате выселения");
        int param;
        param = scanner.nextInt();
        switch (param){
            case 1:
                for (HotelClient client: clientController.getSortedClients(new CompareClientsByName())
            ) {
                    System.out.println(client.getFirstName());
            }
                break;
            case 2:
                for (HotelClient client: clientController.getSortedClients(new CompareClientsByRD())
                ) {
                    System.out.println(client.getFirstName());
                }
                break;
        }

    }

    private void printClientServices(){System.out.println("Не реализовано");
    }

    private void createClient(){
        String name;
        String surname;
        int age;
        long roomID;
        int cDays;

        System.out.println("Введите имя");
        name = scanner.nextLine();

        System.out.println("Введите фамилию");
        surname = scanner.nextLine();

        System.out.println("Введите возраст");
        age = scanner.nextInt();


        int i = 1;
        for (Room r: roomController.getFreeRooms()
             ) {
            System.out.println(i + ". Комната номер: " + r.getNRoom() + ". Количество звёзд: " + r.getStarsCount() +
                    ". Цена: " + r.getCostPerDay());
            i+=1;

        }
        System.out.println("Выберите комнату для заселения!");
        System.out.println();
        roomID = scanner.nextLong()-1;

        System.out.println("На какое количество дней заселяем?");
        cDays = scanner.nextInt();


        clientController.createHotelClient(name, surname, age, roomID, cDays);
        System.out.println("Клиент " + name + " создан!");
    }


}