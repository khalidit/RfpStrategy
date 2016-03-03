package com.teamtrade.rfp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamtrade.rfp.dao.ClientDao;
import com.teamtrade.rfp.model.Client;

/**
 * @author Khalid.ALIANNE
 *
 */
@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public Client getClientById(int id) {
		return clientDao.getClientById(id);
	}

	@Override
	public void addClient(Client client) {
		clientDao.addClient(client);
	}

	@Override
	public void updateClient(Client client) {
		Client c = getClientById(client.getClientId());
		if(c != null){
			c.setCompany(client.getCompany());
			c.setRfps(client.getRfps());
		}
	}

	@Override
	public void deleteClientById(int id) {
		Client client = new Client(id);
		clientDao.deleteClient(client);
	}

	@Override
	public Set<Client> getAllClients() {
		return clientDao.getAllClients();
	}
	
	

}
