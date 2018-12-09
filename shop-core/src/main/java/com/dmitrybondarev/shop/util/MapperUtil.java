package com.dmitrybondarev.shop.util;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.CartDto;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private DozerBeanMapper dozerBeanMapper;

    public MapperUtil(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public Product mapProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        dozerBeanMapper.map(productDto, product);
        return product;
    }

    public ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        dozerBeanMapper.map(product, productDto);
        return productDto;
    }

    public ProductDtoRest mapProductDtoToProductDtoRest(ProductDto productDto) {
        ProductDtoRest productDtoRest = new ProductDtoRest();
        dozerBeanMapper.map(productDto, productDtoRest);
        return productDtoRest;
    }


    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEnabled(userDto.isEnabled());
//        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setAddresses(userDto.getAddresses());
        user.setRoles(userDto.getRoles());
        user.setOrders(userDto.getOrders());
        return user;
    }

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEnabled(user.isEnabled());
//        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setAddresses(user.getAddresses());
        userDto.setRoles(user.getRoles());
        userDto.setOrders(user.getOrders());
        return userDto;
    }


    public Order mapOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(null);
        order.setAddress(orderDto.getAddress());
        order.setDateOfOrder(orderDto.getDateOfOrder());
        order.setListOfProducts(orderDto.getListOfProducts());
        order.setStatusOfDelivery(orderDto.getStatusOfDelivery());
        order.setStatusOfPayment(orderDto.getStatusOfPayment());
        order.setTypeOfDelivery(orderDto.getTypeOfDelivery());
        order.setTypeOfPayment(orderDto.getTypeOfPayment());
        order.setUser(orderDto.getUser());
        return order;
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setDateOfOrder(order.getDateOfOrder());
        orderDto.setListOfProducts(order.getListOfProducts());
        orderDto.setStatusOfDelivery(order.getStatusOfDelivery());
        orderDto.setStatusOfPayment(order.getStatusOfPayment());
        orderDto.setTypeOfDelivery(order.getTypeOfDelivery());
        orderDto.setTypeOfPayment(order.getTypeOfPayment());
        orderDto.setUser(order.getUser());
        return orderDto;
    }


    public Address mapAddressDtoToAddress(AddressDto addressDto) {
        Address address = new Address();
        dozerBeanMapper.map(addressDto, address);
        return address;
    }

    public AddressDto mapAddressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        dozerBeanMapper.map(address, addressDto);
        return addressDto;
    }


    public Cart mapCartDtoToCart(CartDto cartDto) {
        Cart cart = new Cart();
        dozerBeanMapper.map(cartDto, cart);
        return cart;
    }

    public CartDto mapCartToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        dozerBeanMapper.map(cart, cartDto);
        return cartDto;
    }
}
