package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Client;

@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {

	@Override
	public Client getClientById(int id) {
		return getByKey(id);
	}
	
	@Override
	public void addClient(Client client) {
		persist(client);
	}

	@Override
	public void updateClient(Client client) {
		update(client);
	}

	@Override
	public void deleteClient(Client client) {
		delete(client);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Client> getAllClients() {
		Criteria criteria = createEntityCriteria();
		return new HashSet<>((List<Client>) criteria.list());
	}

}
