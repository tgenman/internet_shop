package com.dmitrybondarev.service.api;

import com.dmitrybondarev.model.Client;
import com.dmitrybondarev.model.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ClientService {

    Collection<Client> getAllClients();

    boolean registerNewClient(ClientDto clientDto);
}
