package com.dmitrybondarev.repo.api;

import com.dmitrybondarev.model.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClientRepo {

    void addClient(Client client);

    Client findByEmail(String email);

    List<Client> getAllClients();
}
