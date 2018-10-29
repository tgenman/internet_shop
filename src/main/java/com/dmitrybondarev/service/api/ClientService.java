package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.Client;

import java.util.Collection;

public interface ClientService {

    Collection<Client> getAllClients();

    void registerNewClient(String name, String familyName, String email, String password);
}
