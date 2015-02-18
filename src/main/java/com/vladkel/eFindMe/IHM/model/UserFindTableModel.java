package com.vladkel.eFindMe.IHM.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.vladkel.eFindMe.search.engine.model.User;

public class UserFindTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 4732144004160165466L;
	
	private final String[] entetes = { "Nom","Prenom"};	
	private List<User> usersFind;
	
	public UserFindTableModel(List<User> usersFind)
	{
		this.usersFind = usersFind;
	}
	
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public int getRowCount() {
		return usersFind.size();
	}
	
	public User getRowAt(int rowIndex)
	{
		return usersFind.get(rowIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) 
		{
			case 0:
				return usersFind.get(rowIndex).getName();
			case 1:
				return usersFind.get(rowIndex).getFirstname();
			default:
				throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
			case 1:
				return String.class;
	
			default:
				System.out.println("ERROR");		
				return Object.class;
		}
	}
}