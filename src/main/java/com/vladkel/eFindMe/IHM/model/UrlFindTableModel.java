package com.vladkel.eFindMe.IHM.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UrlFindTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -6592915932272405128L;
	
	private final String[] entetes = { "Url", "Confiance"};	
	private List<UrlFindModel> urlsFind;
	
	public UrlFindTableModel(List<UrlFindModel> urlsFind)
	{
		this.urlsFind = urlsFind;
	}
	
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public int getRowCount() {
		return urlsFind.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) 
		{
			case 0:
				return urlsFind.get(rowIndex).getUrl();
	
			case 1:
				return urlsFind.get(rowIndex).getTrust();
	
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