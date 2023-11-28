package com.example.demo.todo.runners;

import com.example.demo.todo.models.Address;
import com.example.demo.todo.models.Cart;
import com.example.demo.todo.models.User;
import com.example.demo.todo.repositories.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
//@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InitialDataRunner implements CommandLineRunner {

    UsersRepository usersRepository;

    @Override
    public void run(String... args) {

        User alisher = null;

        Address address = Address.builder()
                .Country("Germany")
                .city("Berlin")
                .street("Hamburgerstr.")
                .houseNumber("55")
                .postcode("22222")
                .build();

        Cart cart = new Cart();


        if (!usersRepository.existsById("1")) {
            User admin = User.builder()
                    .email("admin@ait-tr.de")
                    .role(User.Role.ADMIN)
                    .hashPassword("$2a$10$YijmlwvWMcfIhT2qQOQ7EeRuMiByNjPtKXa78J7Y8z7XZWJJQTDa.") // admin
                    .build();

            alisher = User.builder()
                    .email("alisher@ait-tr.de")
                   // .address(address)
                   // .cart(cart)
                    .role(User.Role.USER)
                    .hashPassword("$2a$10$RVSHTssubxIkoAl3rQ58UedU8sPMM6FZRxg1icrJg07f.MQAMRpDy") // alisher
                    .build();
            usersRepository.save(admin);
            usersRepository.save(alisher);
        }

    }
}
