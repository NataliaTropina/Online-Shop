package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.NewAddressDto;
import com.example.demo.todo.dto.OrderDto;
import com.example.demo.todo.dto.OrdersPage;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.exceptions.NotFoundException;
import com.example.demo.todo.models.*;
import com.example.demo.todo.repositories.*;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.AddressesService;
import com.example.demo.todo.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    private final UsersRepository usersRepository;

    private final ProductsRepository productsRepository;

    private final CartRepository cartRepository;

    private final AddressRepository addressRepository;

    private final AddressesService addressesService;


   // @Override
   // @Transactional
   /* public OrderDto createOrder( AuthenticatedUser currentUser, NewAddressDto newAddress) {


        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(() ->

                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                );

        if (user.getAddress() == null){

           Address address = Address.builder()
                   .Country(newAddress.getCountry())
                   .city(newAddress.getCity())
                   .street(newAddress.getStreet())
                   .houseNumber(newAddress.getHouseNumber())
                   .postcode(newAddress.getPostcode())
                   .build();

           addressRepository.save(address);
           user.setAddress(address);

        }

        Cart userCart = currentUser.getUser().getCart();

        if (userCart == null || userCart.getCartDetails().isEmpty()) {
            throw new IllegalArgumentException("User's cart is empty");
        }

        double totalOrderPrice = 0.0;

        for (CartDetails cartDetails : userCart.getCartDetails()) {

            Product product = productsRepository.findById(cartDetails.getProductId())
                    .orElseThrow(() ->
                            new NotFoundException("Product with id <" + cartDetails.getProductId() + "> not found")
                    );
            if (product.getQuantity() < cartDetails.getQuantity()) {
                throw new IllegalArgumentException("Insufficient quantity for product with id <" + product.getId() + ">");
            }

            product.setQuantity(product.getQuantity() - cartDetails.getQuantity());
            productsRepository.save(product);

            double itemPrice = cartDetails.getQuantity() * product.getPrice();

            totalOrderPrice += itemPrice;

        }

        Order order = Order.builder()
                .orderDate(LocalDate.now())
                .cart(userCart)
                .user(user)
                .totalPrice(totalOrderPrice)
                .status((Order.Status.PROCESSING))
                .build();

        order.setCart(userCart);
        userCart.setCartDetails(order.getCart().getCartDetails());

        ordersRepository.save(order);

        cartRepository.deleteById(currentUser.getUser().getCart().getId());

        return OrderDto.from(order);
    }

    */


    @Override
    public OrderDto createOrder(AuthenticatedUser currentUser) {
        // Получение пользователя и создание адреса
        NewAddressDto newAddress = new NewAddressDto();
        User user = getUserWithAddress(currentUser, newAddress);

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

        if (user.getAddress() == null) {

            Address address = Address.builder()
                    .Country(newAddress.getCountry())
                    .city(newAddress.getCity())
                    .street(newAddress.getStreet())
                    .houseNumber(newAddress.getHouseNumber())
                    .build();

            addressRepository.save(address);
            user.setAddress(address);
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

    private Order createOrderObject(User user, double totalOrderPrice) {

        Cart userCart = user.getCart();

        Order order = Order.builder()
                .orderDate(LocalDate.now())
                .status(Order.Status.PROCESSING)
                .totalPrice(countTotalOrderPrice(userCart))
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
    public OrderDto updateOrder(String id, AuthenticatedUser currentUser) {

        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(() ->
                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                );

        Order order = ordersRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Order with id <" + id + "> not found")
                );
        if (user.getRole().equals(User.Role.USER)) {

            order.setCart(currentUser.getUser().getCart());

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
