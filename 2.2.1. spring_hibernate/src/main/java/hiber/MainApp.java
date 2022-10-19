package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

//      carService.add(new Car("BMW", 3));
//      carService.add(new Car("Ford focus", 2));
//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru",
//              carService.getCarByModelAndSeries("BMW", 3)));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
//              carService.getCarByModelAndSeries("Ford focus", 2)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      Car car = carService.getCarByModelAndSeries("BMW", 3);
      System.out.println(car.getSeries());

      User user = userService.getUserByCar("Ford focus", 2);
      System.out.println(user.toString());

      context.close();
   }
}
