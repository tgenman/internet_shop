package com.dmitrybondarev.repo;

// Mock storage for testing

import com.dmitrybondarev.model.Client;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class MockUserDataBase {

    private int counter = 0;

    private Map<Integer, Client> db = new HashMap<>();

    public void addClient(Client client) {
        int id = counter++;
        client.setId(id);
        db.put(id, client);
    }

    public boolean updateClient(Client client) {
        int id = client.getId();
        if (!db.containsKey(id)) return false;
        db.put(id, client);
        return true;
    }

    public Client findClientById(int id) {
        return db.get(id);
    }

    public boolean removeClientById(int id) {
        if (!db.containsKey(id)) return false;
        db.remove(id);
        return true;
    }

    public Collection<Client> getAllClients() {
        return db.values();
    }
}
