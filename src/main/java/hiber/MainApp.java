package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

  public static void main(String[] args) throws SQLException {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

    UserService userService = context.getBean(UserService.class);

    userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Car1", 123)));
    userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Car2", 456)));
    userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Car3", 789)));
    userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Car4", 012)));

    List<User> users = userService.listUsers();
    for (User user : users) {
      System.out.println("Id = " + user.getId());
      System.out.println("First Name = " + user.getFirstName());
      System.out.println("Last Name = " + user.getLastName());
      System.out.println("Email = " + user.getEmail());
      System.out.println();
    }
    Car car1 = new Car("Toyota", 777);
    userService.add(new User("Ivan", "Petrov", "ivan@petrov.ru", car1));
    System.out.println(userService.getUserByCar(car1));
    context.close();
  }
}
