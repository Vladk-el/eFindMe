package com.vladkel.eFindMe.IHM.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UrlFindTableModel extends AbstractTableModel {

	private final String[] entetes = { "Url", "Mots cles"};	
	private List<UrlFindModel> urlsFind;
	
	public UrlFindTableModel(List<UrlFindModel> urlsFind)
	{
		this.urlsFind = urlsFind;
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public int getRowCount() {
		return urlsFind.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) 
		{
			case 0:
				return urlsFind.get(rowIndex).getUrl();
	
			case 1:
				StringBuilder keywords = new StringBuilder();
				
				for(String key : urlsFind.get(rowIndex).getKeyWords())
				{
					keywords.append(key);
				}
				
				return keywords.toString();
	
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