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
	
	//��ȡ��ϵ�˸���
	public int getSize(){
		return this.contacts.size();
	}

	//�����ϵ��
	public boolean addContactIntoList(Items item){
		this.contacts.add(item);
		return true;
	}

	//ɾ����ϵ��
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
