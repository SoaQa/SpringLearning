package ui.menu;

import hotelManager.controllers.interfaces.IAdditionalServiceController;
import hotelManager.controllers.interfaces.IClientController;
import hotelManager.controllers.interfaces.IRoomController;
import hotelManager.models.AdditionalService;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.menu.interfaces.IMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component("serviceMenu")
public class ServicesMenu implements IMenu {

    @Autowired
    private IAdditionalServiceController additionalServiceController;
    @Autowired
    private IClientController clientController;
    @Autowired
    private IRoomController roomController;

    private Scanner scanner = new Scanner(System.in);


    @Override
    public void printMenu() {
        System.out.println("1. Просмотр услуг");
        System.out.println("2. Просмотр услуг клиента");
        System.out.println("3. Создать услугу");
        System.out.println("4. Удалить услугу");
        System.out.println("Введите 0 для выхода в главное меню");
    }

    @Override
    public void callMethod(int index) {
       switch (index-1){
           case 0: showAllServices(); break;
           case 1: showClientServices(); break;
           case 2: createAdditionalService(); break;
           case 3: deleteAdditionalService();break;
       }
    }

    private void showAllServices(){
        List<AdditionalService> additionalServices = additionalServiceController.getAdditionalServices();
        if (additionalServices != null && additionalServices.size() != 0){
            for (AdditionalService additionalService:additionalServices
                    ) {

                System.out.println(additionalService.toString());
                }
        }
        else{
            System.out.println("Услуги отсутсвуют!");
        }
    }

    private void showClientServices(){
        HotelClient selectedClient;
        List<HotelClient> allClients = new ArrayList<HotelClient>();

        System.out.println("Не реализовано");
    }


    private void createAdditionalService() throws RuntimeException{
        List<HotelClient> allClients;
        allClients = clientController.getHotelClients();
        String name;
        int cost;
        HotelClient hotelClient;
        AdditionalService additionalService;
        int cDays;
        if (clientController.getHotelClients().size() != 0 ){
            System.out.println("Введите название будущей услуги!");
            name = scanner.nextLine();
            System.out.println("Введите стоимость будущей услуги!");
            cost = scanner.nextInt();
            if (cost < 0){
                throw new RuntimeException("Стоимость меньше нуля!");
            }
            System.out.println("На какое количество дней услуга будет представлена?");
            cDays = scanner.nextInt();

            System.out.println("Выберите клиента: ");
            int i = 1;
            for (HotelClient client: allClients
            ) {

                System.out.println(i + ". " + client.getFirstName());
                i+=1;
            }
            i = scanner.nextInt();
            hotelClient = allClients.get(i-1);
            for (Room selectedRoom: roomController.getRooms()
                 ) {if (selectedRoom.getNRoom() == hotelClient.getRoom().getNRoom()){
                    additionalService = additionalServiceController.createAddService(name, cost, selectedRoom, hotelClient,cDays);
                    roomController.addService(selectedRoom, additionalService);
                    System.out.println("Услуга успешно создана!");
                     break;
            }

            }
        }
    }


    private void deleteAdditionalService(){
        System.out.println("Выберите услугу для удаления!");
        int i = 1;
        int param;
        List<AdditionalService> additionalServices;
        additionalServices = additionalServiceController.getAdditionalServices();
        for (AdditionalService additionalService: additionalServices
             ) {
                System.out.println(i+ ". " + additionalService.toString());
                i+=1;
        }
        param = scanner.nextInt();
        additionalServices.remove(param-1);
        System.out.println("Услуга удалена!");
    }


}

