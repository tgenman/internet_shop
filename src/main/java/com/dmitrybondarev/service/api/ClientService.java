package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.Client;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ClientService {

    Collection<Client> getAllClients();

    void registerNewClient(String name, String familyName, String email, String password);
}
