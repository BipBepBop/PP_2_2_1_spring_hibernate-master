package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "lastname1", "email1@gmail.com");
      User user2 = new User("User2", "lastname2", "email2@gmail.com");
      User user3 = new User("User3", "lastname3", "email@gmail.com");


      Car car1 = new Car("car1", 1);
      Car car2 = new Car("car2", 2);
      Car car3 = new Car("car3", 3);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("car1", 1));
      System.out.println();

      try {
         User nouser = userService.getUserByCar("car4", 4);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println();
      }

      context.close();
   }
}
