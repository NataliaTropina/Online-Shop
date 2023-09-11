package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.NewOrderDto;
import com.example.demo.todo.dto.NewOrderItemsDto;
import com.example.demo.todo.dto.OrderDto;
import com.example.demo.todo.dto.OrdersPage;
import com.example.demo.todo.exceptions.NotFoundException;
import com.example.demo.todo.models.Order;
import com.example.demo.todo.models.OrderItems;
import com.example.demo.todo.models.Product;
import com.example.demo.todo.models.User;
import com.example.demo.todo.repositories.OrderItemsRepository;
import com.example.demo.todo.repositories.OrdersRepository;
import com.example.demo.todo.repositories.ProductsRepository;
import com.example.demo.todo.repositories.UsersRepository;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    private final UsersRepository usersRepository;

    private final ProductsRepository productsRepository;

    private final OrderItemsRepository orderItemsRepository;
    @Override
    public OrderDto createOrder(NewOrderDto newOrder, AuthenticatedUser currentUser) {


        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(()->

                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                        );


        Order order = Order.builder()
                .orderDate(newOrder.getOrderDate())
                .orderItems(newOrder.getOrderItems())
                .user(user)
                .build();

        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    @Override
    public OrdersPage getAll() {
        List<Order> orders = ordersRepository.findAll();

        return OrdersPage.builder().data(OrderDto.from(orders)).build();
    }

    @Override
    public OrderDto getById(String id) {

        Order order = ordersRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Order with id <" + id + "> not found")
                        );
        return OrderDto.from(order);
    }

    @Override
    public OrderDto updateOrder(String id, AuthenticatedUser currentUser, NewOrderDto newOrder) {

        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(()->
                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                        );

        Order order = ordersRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Order with id <" + id + "> not found")
                        );
        if (user.getRole().equals(User.Role.USER)) {

            order.setOrderItems(newOrder.getOrderItems());
        } else {
            order.setOrderItems(newOrder.getOrderItems());
            order.setOrderDate(newOrder.getOrderDate());
            order.setStatus(newOrder.getStatus());
        }
        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    @Override
    public OrderDto deleteById(String id) {

        Order order = ordersRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Order with id <" + id + "> not found")
                        );

        ordersRepository.deleteById(id);

        return OrderDto.from(order);
    }
}
