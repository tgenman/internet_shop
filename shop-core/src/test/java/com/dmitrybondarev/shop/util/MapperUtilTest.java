package com.dmitrybondarev.shop.util;

import com.dmitrybondarev.shop.model.Address;
import com.dmitrybondarev.shop.model.Cart;
import com.dmitrybondarev.shop.model.Product;
import com.dmitrybondarev.shop.model.User;
import com.dmitrybondarev.shop.model.dto.AddressDto;
import com.dmitrybondarev.shop.model.dto.CartDto;
import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.dto.UserDto;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapperUtilTest {

    private MapperUtil mapperUtil;

    public MapperUtilTest() {
        this.mapperUtil = new MapperUtil(new DozerBeanMapper());
    }

    @Test
    public void testMapProductDtoToProduct() {
//      Values
        long id = 1;
        String title = "1";
        String category = "1";
        int price = 1;
        int quantity = 1;
        int volume = 1;
        int weight = 1;
        String filename = "1";
        boolean active = true;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("color", "1");
        parameters.put("insurance", "1");

//      Input
        ProductDto input = new ProductDto();
        input.setId(id);
        input.setTitle(title);
        input.setCategory(category);
        input.setPrice(price);
        input.setQuantity(quantity);
        input.setVolume(volume);
        input.setWeight(weight);
        input.setParameters(parameters);
        input.setFilename(filename);
        input.setActive(active);

//      Run
        Product output = mapperUtil.mapProductDtoToProduct(input);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, (long) output.getId());
        Assert.assertEquals(title, output.getTitle());
        Assert.assertEquals(category, output.getCategory());
        Assert.assertEquals(price, output.getPrice());
        Assert.assertEquals(quantity, output.getQuantity());
        Assert.assertEquals(volume, output.getVolume());
        Assert.assertEquals(weight, output.getWeight());
        Assert.assertEquals(parameters.get("color"), output.getParameters().get("color"));
        Assert.assertEquals(parameters.get("insurance"), output.getParameters().get("insurance"));
        Assert.assertEquals(filename, output.getFilename());
        Assert.assertEquals(active, output.isActive());
    }

    @Test
    public void testMapProductToProductDto() {
//      Values
        long id = 1;
        String title = "1";
        String category = "1";
        int price = 1;
        int quantity = 1;
        int volume = 1;
        int weight = 1;
        String filename = "1";
        boolean active = true;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("color", "1");
        parameters.put("insurance", "1");

//      Input
        Product input = new Product();
        input.setId(id);
        input.setTitle(title);
        input.setCategory(category);
        input.setPrice(price);
        input.setQuantity(quantity);
        input.setVolume(volume);
        input.setWeight(weight);
        input.setParameters(parameters);
        input.setFilename(filename);
        input.setActive(active);

//      Run
        ProductDto output = mapperUtil.mapProductToProductDto(input);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, (long) output.getId());
        Assert.assertEquals(title, output.getTitle());
        Assert.assertEquals(category, output.getCategory());
        Assert.assertEquals(price, output.getPrice());
        Assert.assertEquals(quantity, output.getQuantity());
        Assert.assertEquals(volume, output.getVolume());
        Assert.assertEquals(weight, output.getWeight());
        Assert.assertEquals(parameters.get("color"), output.getParameters().get("color"));
        Assert.assertEquals(parameters.get("insurance"), output.getParameters().get("insurance"));
        Assert.assertEquals(filename, output.getFilename());
        Assert.assertEquals(active, output.isActive());
    }


    @Test
    public void testMapProductDtoToProductDtoRest() {
//      Values
        long id = 1;
        String title = "1";
        String category = "1";
        int price = 1;
        int volume = 1;
        int weight = 1;
        String filename = "1";
        boolean active = true;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("color", "1");
        parameters.put("insurance", "1");

//      Input
        ProductDto input = new ProductDto();
        input.setId(id);
        input.setTitle(title);
        input.setCategory(category);
        input.setPrice(price);
        input.setVolume(volume);
        input.setWeight(weight);
        input.setParameters(parameters);
        input.setFilename(filename);
        input.setActive(active);

//      Run
        ProductDtoRest output = mapperUtil.mapProductDtoToProductDtoRest(input);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, (long) output.getId());
        Assert.assertEquals(title, output.getTitle());
        Assert.assertEquals(category, output.getCategory());
        Assert.assertEquals(price, output.getPrice());
        Assert.assertEquals(volume, output.getVolume());
        Assert.assertEquals(weight, output.getWeight());
        Assert.assertEquals(parameters.get("color"), output.getParameters().get("color"));
        Assert.assertEquals(parameters.get("insurance"), output.getParameters().get("insurance"));
        Assert.assertEquals(filename, output.getFilename());
        Assert.assertEquals(active, output.isActive());
    }



    @Test
    public void testMapUserToUserDto() {
//      Values
        Long id = 1L;
        String email = "1";
        boolean enabled = true;
        List<String> roles = Collections.singletonList("USER");
        String firstName = "1";
        String lastName = "1";
        Date dateOfBirth = new Date();
        Set<Address> addresses = new HashSet<>();
        Cart cart = new Cart();
        Address address1 = new Address();
        Address address2 = new Address();
        address1.setId(10L);
        address2.setId(20L);
        addresses.add(address1);
        addresses.add(address2);
        cart.setId(1L);

//      Input
        User input = new User();
        input.setId(id);
        input.setEmail(email);
        input.setEnabled(enabled);
        input.setRoles(roles);
        input.setFirstName(firstName);
        input.setLastName(lastName);
        input.setDateOfBirth(dateOfBirth);
        input.setCart(cart);
        input.setAddresses(addresses);

//      Run
        UserDto output = mapperUtil.mapUserToUserDto(input);
        CartDto cartDto = mapperUtil.mapCartToCartDto(cart);
        AddressDto addressDto1 = mapperUtil.mapAddressToAddressDto(address1);
        AddressDto addressDto2 = mapperUtil.mapAddressToAddressDto(address2);


//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, output.getId());
        Assert.assertEquals(email, output.getEmail());
        Assert.assertEquals(enabled, output.isEnabled());
        Assert.assertEquals(roles, output.getRoles());
        Assert.assertEquals(firstName, output.getFirstName());
        Assert.assertEquals(lastName, output.getLastName());
        Assert.assertEquals(dateOfBirth, output.getDateOfBirth());
        Assert.assertEquals(cartDto, output.getCartDto());
        Assert.assertTrue(output.getAddressDtos().contains(addressDto1));
        Assert.assertTrue(output.getAddressDtos().contains(addressDto2));
    }

    @Test
    public void testMapUserDtoToUser() {
//      Values
        Long id = 1L;
        String email = "1";
        boolean enabled = true;
        List<String> roles = Collections.singletonList("USER");
        String firstName = "1";
        String lastName = "1";
        Date dateOfBirth = new Date();
        Set<AddressDto> addressesDto = new HashSet<>();
        CartDto cartDto = new CartDto();
        AddressDto addressDto1 = new AddressDto();
        AddressDto addressDto2 = new AddressDto();
        addressDto1.setId(10L);
        addressDto2.setId(20L);
        addressesDto.add(addressDto1);
        addressesDto.add(addressDto2);
        cartDto.setId(1L);

//      Input
        UserDto input = new UserDto();
        input.setId(id);
        input.setEmail(email);
        input.setEnabled(enabled);
        input.setRoles(roles);
        input.setFirstName(firstName);
        input.setLastName(lastName);
        input.setDateOfBirth(dateOfBirth);
        input.setCartDto(cartDto);
        input.setAddressDtos(addressesDto);

//      Run
        User output = mapperUtil.mapUserDtoToUser(input);
        Cart cart = mapperUtil.mapCartDtoToCart(cartDto);
        Address address1 = mapperUtil.mapAddressDtoToAddress(addressDto1);
        Address address2 = mapperUtil.mapAddressDtoToAddress(addressDto2);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, output.getId());
        Assert.assertEquals(email, output.getEmail());
        Assert.assertEquals(enabled, output.isEnabled());
        Assert.assertEquals(roles, output.getRoles());
        Assert.assertEquals(firstName, output.getFirstName());
        Assert.assertEquals(lastName, output.getLastName());
        Assert.assertEquals(dateOfBirth, output.getDateOfBirth());
        Assert.assertEquals(cart, output.getCart());
        Assert.assertTrue(output.getAddresses().contains(address1));
        Assert.assertTrue(output.getAddresses().contains(address2));
    }


//    @Test
//    public void testMapOrderDtoToOrder() {
//
//    }
//
//    @Test
//    public void mapOrderToOrderDto() {
//
//    }

    @Test
    public void mapAddressDtoToAddress() {
//      Values
        long id = 1;
        String country = "1";
        String city = "1";
        int postalCode = 1;
        String street = "1";
        String building = "1";
        String flat = "1";

//      Input
        AddressDto input = new AddressDto();
        input.setId(id);
        input.setCountry(country);
        input.setCity(city);
        input.setPostalCode(postalCode);
        input.setStreet(street);
        input.setBuilding(building);
        input.setFlat(flat);

//      Run
        Address output = mapperUtil.mapAddressDtoToAddress(input);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, (long) output.getId());
        Assert.assertEquals(country, output.getCountry());
        Assert.assertEquals(city, output.getCity());
        Assert.assertEquals(postalCode, output.getPostalCode());
        Assert.assertEquals(street, output.getStreet());
        Assert.assertEquals(building, output.getBuilding());
        Assert.assertEquals(flat, output.getFlat());
    }

    @Test
    public void mapAddressToAddressDto() {
//      Values
        long id = 1;
        String country = "1";
        String city = "1";
        int postalCode = 1;
        String street = "1";
        String building = "1";
        String flat = "1";

//      Input
        Address input = new Address();
        input.setId(id);
        input.setCountry(country);
        input.setCity(city);
        input.setPostalCode(postalCode);
        input.setStreet(street);
        input.setBuilding(building);
        input.setFlat(flat);

//      Run
        AddressDto output = mapperUtil.mapAddressToAddressDto(input);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, (long) output.getId());
        Assert.assertEquals(country, output.getCountry());
        Assert.assertEquals(city, output.getCity());
        Assert.assertEquals(postalCode, output.getPostalCode());
        Assert.assertEquals(street, output.getStreet());
        Assert.assertEquals(building, output.getBuilding());
        Assert.assertEquals(flat, output.getFlat());
    }

    @Test
    public void mapCartDtoToCart() {
//      Values
        long id = 1;
        String sessionId = "1";
        Map<Product, Integer> content = new HashMap<>();
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setTitle("one");
        product2.setTitle("two");
        content.put(product1, 1);
        content.put(product2, 2);

//      Input
        Cart input = new Cart();
        input.setId(id);
        input.setSessionId(sessionId);
        input.setContent(content);

//      Run
        CartDto output = mapperUtil.mapCartToCartDto(input);
        ProductDto productDto1 = mapperUtil.mapProductToProductDto(product1);
        ProductDto productDto2 = mapperUtil.mapProductToProductDto(product2);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, (long) output.getId());
        Assert.assertEquals(sessionId, output.getSessionId());
        Assert.assertEquals(1,  (int) output.getContentDto().get(productDto1));
        Assert.assertEquals(2,  (int) output.getContentDto().get(productDto2));
    }

    @Test
    public void mapCartToCartDto() {
//      Values
        long id = 1;
        String sessionId = "1";
        Map<ProductDto, Integer> content = new HashMap<>();
        ProductDto productDto1 = new ProductDto();
        ProductDto productDto2 = new ProductDto();
        productDto1.setTitle("one");
        productDto2.setTitle("two");
        content.put(productDto1, 1);
        content.put(productDto2, 2);

//      Input
        CartDto input = new CartDto();
        input.setId(id);
        input.setSessionId(sessionId);
        input.setContentDto(content);

//      Run
        Cart output = mapperUtil.mapCartDtoToCart(input);
        Product product1 = mapperUtil.mapProductDtoToProduct(productDto1);
        Product product2 = mapperUtil.mapProductDtoToProduct(productDto2);

//      Assert
        Assert.assertNotNull(output);
        Assert.assertEquals(id, (long) output.getId());
        Assert.assertEquals(sessionId, output.getSessionId());
        Assert.assertEquals(1,  (int) output.getContent().get(product1));
        Assert.assertEquals(2,  (int) output.getContent().get(product2));
    }
}