package com.dmitrybondarev.service.impl;

import com.dmitrybondarev.model.Client;
import com.dmitrybondarev.model.dto.ClientDto;
import com.dmitrybondarev.model.enums.Role;
import com.dmitrybondarev.repo.api.ClientRepo;
import com.dmitrybondarev.service.api.ClientService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private DozerBeanMapper mapper;

    public List<Client> getAllClients() {
        return clientRepo.getAllClients();
    }

    public boolean registerNewClient(ClientDto clientDto) {

        Client byEmail = clientRepo.findByEmail(clientDto.getEmail());
        if (byEmail != null) return false;

        Client client = new Client();
        mapper.map(clientDto, client);

        client.setActive(true);
        client.setRoles(Collections.singleton(Role.USER));
        clientRepo.addClient(client);
        return true;
    }
}
