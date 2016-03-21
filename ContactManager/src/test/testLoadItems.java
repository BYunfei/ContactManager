package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ItemsDAO;
import entity.ContactsList;
import entity.Items;

public class testLoadItems {
	public static void main(String[] args) {
		ItemsDAO itemsdao = null;
		try {
			itemsdao = new ItemsDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Items> itemsList = itemsdao.getAllItems();

//		for(int i=0;i<contactsList.getSize();i++){
//			System.out.print(contactsList.getContacts().get(i).getName()+'\t');
//			System.out.print(contactsList.getContacts().get(i).getSex()+'\t');
//			System.out.print(contactsList.getContacts().get(i).getAge()+""+'\t');
//			System.out.print(contactsList.getContacts().get(i).getPhoneNum()+'\t');
//			System.out.println();
//		}
		ArrayList<Items> resultsList = itemsdao.serachByName("ÕÅÈý");
//		for(int i=0;i<resultsList.size();i++){
//			Items item = resultsList.get(i);
//			System.out.println(item.getName()+'\t'+item.getPhoneNum());
//		}
//		Items item = new Items("Mike", "ÄÐ", 18, "19847987998", "34857890", 198237);
//		Items item2 = new Items("Mike", "ÄÐ", 0, "123124432", "", 0);
//
//		itemsdao.saveItems(item);
//		itemsdao.saveItems(item2);

		resultsList = itemsdao.serachByName("Mike");
		itemsdao.deleteItemByID("1");
		itemsList = itemsdao.getAllItems();
		for(int i=0;i<itemsList.size();i++){
			Items item = itemsList.get(i);
			System.out.println(item.getId());
			System.out.println(item.getId()+""+'\t'+item.getName()+'\t'+item.getPhoneNum());
		}
		
	}
}
