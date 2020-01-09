package ui.menu;

import hotelManager.controllers.ClientControllerImpl;
import hotelManager.controllers.RoomControllerImpl;
import hotelManager.controllers.interfaces.IClientController;
import hotelManager.controllers.interfaces.IRoomController;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.services.comparators.CompareRoomsRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ui.menu.interfaces.IMenu;
import utils.MyAppContext;

import java.util.List;
import java.util.Scanner;

@Component("roomMenu")
public class RoomMenu implements IMenu {

    @Autowired
    private IClientController clientController;
    @Autowired
    private IRoomController roomController;

    private Scanner scanner = new Scanner(System.in);


    @Override
    public void printMenu() {
        System.out.println("1. Вывести все комнаты.");
        System.out.println("2. Вывести все свободные комнаты.");
        System.out.println("3. Вывести все занятые комнаты.");
        System.out.println("4. Вывести комнаты которые будут свободны через...");
        System.out.println("5. Показать предыдущих клиентов.");
        System.out.println("6. Заселить в комнату клиента.");
        System.out.println("Введите 0 для выхода в главное меню");
    }

    @Override
    public void callMethod(int index) {
        switch (index){
            case 1: printRooms(); break;
            case 2: printFreeRooms();break;
            case 3: printBusyRooms();break;
            case 4: printRoomsWillBeFreeAfter();break;
            case 5: printHistorycalCLients();break;
            case 6: putInRoom();break;
        }
    }

    private void printHistorycalCLients() {
        int i = 1;
        List<Room> rooms = roomController.getRooms();
        rooms.sort((Room o1, Room o2)->o1.getNRoom()-o2.getNRoom());
        for (Room r: rooms
             ) {
            System.out.println(i + ". Комната номер - " + r.getNRoom());
            i++;
        }
        System.out.println("Введите номер комнаты");
        i = scanner.nextInt();

        System.out.println(roomController.getHistoricalClients(rooms.get(i-1)));
    }

    private void printRooms(){
        System.out.println("Выберите тип сортировки!");
        System.out.println("1. По стоимости");
        System.out.println("2. По количеству звёзд");
        System.out.println("3. По вместимости");

        int i;
        int param;
        param = scanner.nextInt();
        switch (param-1){
            case 0:
                System.out.println(roomController.getSortedRooms((Room r1, Room r2)->r1.getCostPerDay() - r2.getCostPerDay()));break;
            case 1:
                System.out.println(roomController.getSortedRooms((Room r1, Room r2)->r1.getStarsCount() - r2.getStarsCount()));break;
            case 2:
                System.out.println(roomController.getSortedRooms((Room r1, Room r2)->r1.getMaximumOccupancy() - r2.getMaximumOccupancy()));break;


        }
    }

    private void printFreeRooms(){
        System.out.println("Выберите тип сортировки!");
        System.out.println("1. По стоимости");
        System.out.println("2. По количеству звёзд");
        System.out.println("3. По вместимости");

        int param;
        param = scanner.nextInt();
        switch (param-1){
            case 0:
                System.out.println(roomController.getSortedFreeRooms((Room r1, Room r2)->r1.getCostPerDay() - r2.getCostPerDay()));break;
            case 1:
                System.out.println(roomController.getSortedFreeRooms((Room r1, Room r2)->r1.getStarsCount() - r2.getStarsCount()));break;
            case 2:
                System.out.println(roomController.getSortedFreeRooms((Room r1, Room r2)->r1.getMaximumOccupancy() - r2.getMaximumOccupancy()));break;

        }
    }

    private void printBusyRooms(){
        System.out.println("Выберите тип сортировки!");
        System.out.println("1. По стоимости");
        System.out.println("2. По количеству звёзд");
        System.out.println("3. По вместимости");
        System.out.println("4. По дате освобождения");

        int param;
        param = scanner.nextInt();
        switch (param-1){
            case 0:
                System.out.println(roomController.getSortedBusyRooms((Room r1, Room r2)->r1.getCostPerDay() - r2.getCostPerDay()));break;
            case 1:
                System.out.println(roomController.getSortedBusyRooms((Room r1, Room r2)->r1.getStarsCount() - r2.getStarsCount()));break;
            case 2:
                System.out.println(roomController.getSortedBusyRooms((Room r1, Room r2)->r1.getMaximumOccupancy() - r2.getMaximumOccupancy()));break;
            case 3:
                System.out.println(roomController.getSortedBusyRooms(new CompareRoomsRD()));break;//прост длинно получается, так как то красивее

        }
    }


    private void printRoomsWillBeFreeAfter() throws RuntimeException{
        System.out.println("Через сколько дней?");
        int days;
        days = scanner.nextInt();
        if (days<0){
            throw new RuntimeException("Введено отрицательное число!");
        }
        System.out.println(roomController.getRoomsWillBeFreeAfter(days));
    }

    private void putInRoom() throws RuntimeException{
        HotelClient selectedClient;
        Room selectedRoom;
        List<HotelClient> allClients;
        List<Room> freeRooms;
        freeRooms = roomController.getFreeRooms();
        allClients = clientController.getHotelClients();

        int i = 1;
        if (allClients.size() != 0) {
            System.out.println("Выберите клиента: ");
            for (HotelClient client : allClients
            ) {
                System.out.println(i + ". " + client.getFirstName());
                i += 1;
            }
            int param = scanner.nextInt();
            if (param<0){
                throw new RuntimeException("Введено отрицательное число!");
            }
            selectedClient = allClients.get(param-1);

            System.out.println("Выберите комнату: ");

            for (Room room : freeRooms
            ) {
                System.out.println(i + ". " + room.toString());
                i += 1;
            }
            param = scanner.nextInt();
            if (param<0){
                throw new RuntimeException("Введено отрицательное число!");
            }
            selectedRoom = freeRooms.get(param-1);

            System.out.println("На какое количество дней заселяем?");
            param = scanner.nextInt();
            if (param<0){
                throw new RuntimeException("Введено отрицательное число!");
            }

            roomController.putIn(selectedRoom, selectedClient, param);
            System.out.println("Клиент заселён!");
        }


    }
}