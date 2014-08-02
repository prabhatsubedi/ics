package com.ejb.shoppingcart.service;

import java.util.List;

import javax.ejb.Local;

import com.ejb.shoppingcart.pojo.Item;

@Local
public interface IItemServiceDAO {
	public void addToList(String item);
	public void removeFromList(Integer item);
	public List<String> itemsFromList();
	
	public Item createOrUpdate(Item item);
	public void remove(Item item);
	public Item find(Object id);
	public List<Item> list();
}
