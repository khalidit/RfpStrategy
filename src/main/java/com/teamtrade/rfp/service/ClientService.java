package com.teamtrade.rfp.service;

import java.util.Set;

import com.teamtrade.rfp.model.Client;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
public interface ClientService {	 
	
	Client getClientById(int id);
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClientById(int id);
    Set<Client> getAllClients(); 
 }
