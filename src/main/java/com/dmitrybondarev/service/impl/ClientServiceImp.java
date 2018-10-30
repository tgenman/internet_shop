package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.Client;
import com.dmitrybondarev.repo.api.ClientRepo;
import com.dmitrybondarev.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public Collection<Client> getAllClients() {
        return null;
//        return mockUserDataBase.getAllClients();
    }

    public void registerNewClient(String name, String familyName, String email, String password) {
        Client client = new Client();
        client.setName(name);
        client.setFamilyName(familyName);
        client.setEmail(email);
        client.setPassword(password);
        clientRepo.addClient(client);
    }
}
