package com.ejb.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.shoppingcart.pojo.Item;
import com.ejb.shoppingcart.service.IItemServiceDAO;

@Stateful
public class ItemServiceDAO implements IItemServiceDAO{
	List<String> items = new ArrayList<String>();
	
	@PersistenceContext(name = "EmployeDB")
	private EntityManager em;
	
	@Override
	public void addToList(String item) {
		items.add(item);
	}

	@Override	
	public void removeFromList(Integer item) {
		items.remove(item);
		
	}

	
	@Override
	public List<String> itemsFromList() {
		// TODO Auto-generated method stub
		return this.items;
	}
	
	@Override
	public Item createOrUpdate(Item item) {
		return em.merge(item);
	}

	@Override
	public void remove(Item item) {
		em.remove(item);
		
	}

	@Override
	public Item find(Object id) {
		return em.find(Item.class, id);
	}

	@Override
	public List<Item> list() {
		return null;
	}

}
