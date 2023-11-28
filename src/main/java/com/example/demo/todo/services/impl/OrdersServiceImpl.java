package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.*;
import com.example.demo.todo.exceptions.NotFoundException;
import com.example.demo.todo.models.*;
import com.example.demo.todo.repositories.*;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    private final UsersRepository usersRepository;

    private final ProductsRepository productsRepository;

    private final CartRepository cartRepository;

    @Override
    public OrderDto createOrder(AuthenticatedUser currentUser) {
        // Получение пользователя и создание адреса

        User user = getUserWithAddress(currentUser, new NewAddressDto());


        // Проверка корзины пользователя
        checkUserCart(user);

        // Обработка элементов корзины
        double totalOrderPrice = countTotalOrderPrice(user.getCart());

        // Создание заказа
        Order order = createOrderObject(user, totalOrderPrice);

        // Сохранение заказа и корректная обработка данных

        Cart userCart = user.getCart();
        saveOrderAndHandleData(userCart);

        return OrderDto.from(order);

    }

    private User getUserWithAddress (AuthenticatedUser currentUser, NewAddressDto newAddress){

        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(()-> new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found"));

        if (user.getAddresses() == null || user.getAddresses().isEmpty()) {
            throw new IllegalStateException("User address is missing. Cannot create order without an address.");
        }

        return user;
    }


    private void checkUserCart (User user){

        if (user.getCart() == null || user.getCart().getCartDetails() == null || user.getCart().getCartDetails().isEmpty()){
            throw new NotFoundException("Cart is Empty");
        }
    }

    private double countTotalOrderPrice (Cart userCart){

        double totalOrderPrice = 0.0;

        List<CartDetails> cartDetailsList = userCart.getCartDetails();

        for (CartDetails cartDetails : cartDetailsList) {

            Product product = productsRepository.findById(cartDetails.getProductId())
                    .orElseThrow(() ->
                            new NotFoundException("Product with id <" + cartDetails.getProductId() + "> not found")
                    );
            if (product.getQuantity() < cartDetails.getQuantity()){
                throw new IllegalArgumentException("Insufficient quantity for product with id <" + product.getId() + ">");
            }
            product.setQuantity(product.getQuantity() - cartDetails.getQuantity());
            productsRepository.save(product);

            double itemPrice = cartDetails.getQuantity() * product.getPrice();

            totalOrderPrice += itemPrice;
        }

        return totalOrderPrice;
    }

    private Order createOrderObject(User user, double totalPrice) {

        Cart userCart = user.getCart();

        Order order = Order.builder()
                .orderDate(LocalDate.now())
                .status(Order.Status.PROCESSING)
                .totalPrice(totalPrice)
                .cartDetails(userCart.getCartDetails())
                .user(user)
                .build();

        ordersRepository.save(order);

        return order;
    }

    private void saveOrderAndHandleData(Cart userCart){

        userCart.getCartDetails().clear();
        cartRepository.save(userCart);
    }

    @Override
    public OrdersPage getAll() {
        List<Order> orders = ordersRepository.findAll();

        return OrdersPage.builder().data(OrderDto.from(orders)).build();
    }

    @Override
    public OrderDto getById(String id) {

        Order order = ordersRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Order with id <" + id + "> not found")
                );
        return OrderDto.from(order);
    }

    @Override
    public OrderDto updateOrder(String id, AuthenticatedUser currentUser, NewOrderDto newOrder) {

        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(() ->
                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                );

        Order order = ordersRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Order with id <" + id + "> not found")
                );
        if (user.getRole().equals(User.Role.USER) && order.getStatus() == Order.Status.PROCESSING) {

            order.setCartDetails(newOrder.getCartDetails());
            order.setOrderDate(LocalDate.now());

            ordersRepository.save(order);
        }

        return OrderDto.from(order);
    }

        @Override
        public OrderDto deleteById (String id){

            Order order = ordersRepository.findById(id)
                    .orElseThrow(() ->
                            new NotFoundException("Order with id <" + id + "> not found")
                    );

            ordersRepository.deleteById(id);

            return OrderDto.from(order);
        }


}
