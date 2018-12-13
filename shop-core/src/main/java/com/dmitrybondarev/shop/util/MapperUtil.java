package com.dmitrybondarev.shop.util;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Category;
import com.dmitrybondarev.shop.model.Order;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.CartDto;
import com.dmitrybondarev.shop.model.dto.CategoryDto;
import com.dmitrybondarev.shop.model.dto.OrderDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MapperUtil {

    private DozerBeanMapper dozerBeanMapper;

    public MapperUtil(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Loggable
    public Product mapProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        dozerBeanMapper.map(productDto, product);

        return product;
    }

    @Loggable
    public ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        dozerBeanMapper.map(product, productDto);

        productDto.setCategory(
                this.mapCategoryToCategoryDto(product.getCategory()));
        return productDto;
    }

    @Loggable
    public ProductDtoRest mapProductDtoToProductDtoRest(ProductDto productDto) {
        ProductDtoRest productDtoRest = new ProductDtoRest();
        dozerBeanMapper.map(productDto, productDtoRest);

        productDtoRest.setCategory(
                productDto.getCategory().toString());
        return productDtoRest;
    }

    @Loggable
    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        dozerBeanMapper.map(userDto, user);

        if (userDto.getCartDto() == null || userDto.getAddresses() == null) return user;

        user.setCart(this.mapCartDtoToCart(userDto.getCartDto()));

        user.setAddresses(
                userDto.getAddresses().stream()
                        .map(this::mapAddressDtoToAddress)
                        .collect(Collectors.toSet()));

        return user;
    }

    @Loggable
    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        dozerBeanMapper.map(user, userDto);

        if (user.getCart() == null || user.getAddresses() == null) return userDto;

        userDto.setCartDto(this.mapCartToCartDto(user.getCart()));

        userDto.setAddresses(
                user.getAddresses().stream()
                        .map(this::mapAddressToAddressDto)
                        .collect(Collectors.toSet()));

        return userDto;
    }

    @Loggable
    public Order mapOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        dozerBeanMapper.map(orderDto, order);

        if (orderDto.getUser() == null) return order;
        User user = this.mapUserDtoToUser(orderDto.getUser());
        order.setUser(user);


        if (orderDto.getListOfProducts() == null) return order;
        Map<ProductDto, Integer> listOfProductDtos = orderDto.getListOfProducts();
        Map<Product, Integer> listOfProducts = new HashMap<>();
        for (Map.Entry<ProductDto, Integer> entry : listOfProductDtos.entrySet()) {
            listOfProducts.put(
                    this.mapProductDtoToProduct(entry.getKey()),
                    entry.getValue());
        }

        order.setListOfProducts(listOfProducts);

        return order;
    }

    @Loggable
    public OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        dozerBeanMapper.map(order, orderDto);

        if (order.getListOfProducts() == null || order.getUser() == null) return orderDto;

        UserDto userDto = this.mapUserToUserDto(order.getUser());

        Map<Product, Integer> listOfProducts = order.getListOfProducts();
        Map<ProductDto, Integer> listOfProductDtos = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : listOfProducts.entrySet()) {
            listOfProductDtos.put(
                    this.mapProductToProductDto(entry.getKey()),
                    entry.getValue());
        }

        orderDto.setUser(userDto);
        orderDto.setListOfProducts(listOfProductDtos);

        return orderDto;
    }

    @Loggable
    public Address mapAddressDtoToAddress(AddressDto addressDto) {
        Address address = new Address();
        dozerBeanMapper.map(addressDto, address);
        return address;
    }

    @Loggable
    public AddressDto mapAddressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        dozerBeanMapper.map(address, addressDto);
        return addressDto;
    }

    @Loggable
    public Cart mapCartDtoToCart(CartDto cartDto) {
        Cart cart = new Cart();
        dozerBeanMapper.map(cartDto, cart);

        Map<ProductDto, Integer> contentDto = cartDto.getContent();
        if (contentDto == null) return cart;

        Map<Product, Integer> content = new HashMap<>();
        for (Map.Entry<ProductDto, Integer> entry : contentDto.entrySet()) {
            content.put(
                    this.mapProductDtoToProduct(entry.getKey()),
                    entry.getValue());
        }

        cart.setProducts(content);

        return cart;
    }

    @Loggable
    public CartDto mapCartToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        dozerBeanMapper.map(cart, cartDto);

        Map<Product, Integer> content = cart.getProducts();
        if (content == null) return cartDto;

        Map<ProductDto, Integer> contentDto = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : content.entrySet()) {
            contentDto.put(
                    this.mapProductToProductDto(entry.getKey()),
                    entry.getValue());
        }

        cartDto.setContent(contentDto);

        return cartDto;
    }

    @Loggable
    public Category mapCategoryDtoToCategory(CategoryDto categoryDTO) {
        Category category = new Category();
        dozerBeanMapper.map(categoryDTO, category);
        return category;
    }

    @Loggable
    public CategoryDto mapCategoryToCategoryDto(Category category) {
        CategoryDto categoryDTO = new CategoryDto();
        dozerBeanMapper.map(category, categoryDTO);
        return categoryDTO;
    }
}
