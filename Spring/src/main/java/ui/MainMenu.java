package ui;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ui.controller.navigator.Navigator;
import utils.MyAppContext;


import java.io.IOException;
import java.util.Scanner;

@Slf4j
@Getter
@Setter
public class MainMenu {

    public static void main(String[] args) throws IOException, IllegalAccessException {
        log.info("Сервис запущен!");
        AnnotationConfigApplicationContext context = MyAppContext.getApplicationContext();


        System.out.println("Добро пожаловать в электронного администратора гостинницы!");
        log.info("Добро пожаловать в электронного администратора гостинницы!");
        System.out.println("Выюерите меню:");

        Navigator navigator = context.getBean(Navigator.class);
        navigator.navigate(3);


        int param;
        Scanner scanner = new Scanner(System.in);
        while (true){
            param = scanner.nextInt();
            if (param == 0){
                log.info("Завершение работы.");
                break;
            }
            navigator.navigate(param - 1);
        }

    }

}
