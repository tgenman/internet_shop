package com.dmitrybondarev.repo.api;

import com.dmitrybondarev.model.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClientRepo {

    void addClient(Client client);
}
