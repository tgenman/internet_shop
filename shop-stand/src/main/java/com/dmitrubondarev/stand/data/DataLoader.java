package com.dmitrubondarev.stand.data;

import com.dmitrubondarev.stand.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class DataLoader {

    private static final Logger logger = Logger.getLogger(DataLoader.class);
    private ObjectMapper mapper = new ObjectMapper();
    private Client client;
    private static volatile DataLoader loader;

    private DataLoader() {
        client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("admin", "admin"));
    }

    public static DataLoader getInstance() {
        DataLoader localInstance = loader;
        if (localInstance == null) {
            synchronized (DataLoader.class) {
                localInstance = loader;
                if (localInstance == loader) {
                    localInstance = loader = new DataLoader();
                }
            }
        }
        return localInstance;
    }

    public List<Product> getProducts() {
        String response = getResponse("http://truckservice:8080/api/product/list");
        List<Product> products = null;
        try {
            products = mapper.readValue(response, new TypeReference<List<Product>>() {
            });
        } catch (IOException e) {
            logger.error("Error during loading temp shifts data! " + e.getMessage());
        }
        return products;
    }



//    ================ NON-API =============

    private String getResponse(String url) {
        WebResource webResource = client.resource(url);
        return webResource.accept(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class).getEntity(String.class);
    }
}
