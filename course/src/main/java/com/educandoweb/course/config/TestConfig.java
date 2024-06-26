package com.educandoweb.course.config;

import com.educandoweb.course.entities.*;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.*;
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

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null,"Maria","maria@gmail.com","343284732897487324872", "123456");
        User u2 = new User(null,"Carlos","carlos@gmail.com","343287324872", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
        Order o2 = new Order(null, Instant.parse("2019-07-21T14:53:07Z"), u2, OrderStatus.WAITING_PAYMENT);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:53:07Z"), u1, OrderStatus.WAITING_PAYMENT);

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

        OrderItem oi1 = new OrderItem(o1,p1,2,p1.getPrice());
        OrderItem oi2 = new OrderItem(o1,p3,1,p3.getPrice());
        OrderItem oi3 = new OrderItem(o2,p3,2,p3.getPrice());
        OrderItem oi4 = new OrderItem(o3,p5,2,p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"),o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);

    }
}
