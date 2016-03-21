package entity;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactsList {
	private ArrayList<Items> contacts;

	public ContactsList(){
		this.contacts = new ArrayList<Items>();
	}
	
	public ContactsList(ArrayList<Items> list){
		this.contacts = (ArrayList<Items>)list.clone();
	}

	//getter & setter
	public ArrayList<Items> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Items> contacts) {
		this.contacts = contacts;
	}
	
	//获取联系人个数
	public int getSize(){
		return this.contacts.size();
	}

	//添加联系人
	public boolean addContactIntoList(Items item){
		this.contacts.add(item);
		return true;
	}

	//删除联系人
	public boolean removeContactFromList(Items item){
		if(contacts.contains(item)){
			this.contacts.remove(item);
		}
		return true;
	}
	
	class SortByName implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			
			return 0;
		}
		
	}


}
