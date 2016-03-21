package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Items;
import entity.Users;
import view.ListPanel;

public class RuntimeData {
	private static Users user;
	private static ArrayList<Items> itemsList;
	private static ListPanel listPanel;

	public static ListPanel getListPanel() {
		return listPanel;
	}

	public static void setListPanel(ListPanel lp) {
		listPanel = lp;
	}

	static {
		ItemsDAO itemsdao;
		try {
			itemsdao = new ItemsDAO();
			itemsList = itemsdao.getAllItems();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Users getUser() {
		return user;
	}

	public static void setUser(Users user) {
		RuntimeData.user = user;
	}

	public static ArrayList<Items> getItemsList() {
		return itemsList;
	}

	public static void setItemsList(ArrayList<Items> itemsList) {
		RuntimeData.itemsList = itemsList;
	}

}
