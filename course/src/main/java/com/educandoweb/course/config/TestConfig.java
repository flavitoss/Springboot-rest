package com.educandoweb.course.config;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null,"Maria","maria@gmail.com","343284732897487324872", "123456");
        User u2 = new User(null,"Carlos","carlos@gmail.com","343287324872", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.DELIVERED);
        Order o2 = new Order(null, Instant.parse("2019-07-21T14:53:07Z"), u2, OrderStatus.DELIVERED);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:53:07Z"), u1, OrderStatus.DELIVERED);

        Category c1 = new Category(null, "Eletronics");
        Category c2 = new Category(null, "Books");
        Category c3 = new Category(null, "Computers");

        Product p1 = new Product(null,"The lord of the rings", "Lorem ipsum dolor sit amet,asdsafg",90.5,"");
        Product p2 = new Product(null,"Smart TV", "Lorem ipsum dolor sit amet,asdsafg",2190.0,"");
        Product p3 = new Product(null,"MacBook Pro", "Lorem ipsum dolor sit amet,asdsafg",1250.0,"");
        Product p4 = new Product(null,"PC gamer", "Lorem ipsum dolor sit amet,asdsafg",1200.0,"");
        Product p5 = new Product(null,"Ruby on rails", "Lorem ipsum dolor sit amet,asdsafg",100.99,"");

        userRepository.saveAll(Arrays.asList(u1,u2));
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));
        categoryRepository.saveAll(Arrays.asList(c1,c2,c3));

        p1.getCategories().add(c2);
        p2.getCategories().add(c1);
        p3.getCategories().add(c3);
        p4.getCategories().add(c3);
        p5.getCategories().add(c2);

        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

    }
}
