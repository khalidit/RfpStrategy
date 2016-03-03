package com.teamtrade.rfp.dao;

import java.util.Set;

import com.teamtrade.rfp.model.Client;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
public interface ClientDao {
		
	public void addClient(Client client);
    public void updateClient(Client client);
    public Set<Client> getAllClients();
    public Client getClientById(int id);
    public void deleteClient(Client client);
}
