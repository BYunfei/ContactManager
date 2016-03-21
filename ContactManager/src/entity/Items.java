package entity;

public class Items{
	private String id;
	private String name;
	private String sex;
	private int age;
	private String phoneNum;
	private String QQ;
	private String city;

	public Items() {
	}

	public Items(String id,String name, String sex, int age, String phoneNum, String QQ, String city) {
		if(id==null)
			this.id = "00000000";
		else
			this.id = id;
		if(name==null){
			this.name = phoneNum;
		}else{
			this.name = name;
		}
		
		if(sex==null){
			this.sex = "";
		}else{
			this.sex = sex;
		}
		
		this.age = age;
		
		if(phoneNum == null)
			this.phoneNum = "";
		else
			this.phoneNum = phoneNum;
		this.age = age;
		
		if(QQ == null)
			this.QQ = "";
		else
			this.QQ = QQ;

		this.city = city;
	}

	//判断是否为相同对象
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof Items) {
			Items i = (Items) obj;
			//所有属性相同即判定为相同对象
			if (i.getName().equals(this.getName()) && i.getSex().equals(this.getSex()) && i.getAge() == this.getAge()
					&& i.getPhoneNum().equals(this.getPhoneNum()) && i.getQQ().equals(this.getQQ())
					&& i.getCity()==this.getCity()) {
				return true;
			}
		}
		return false;
	}

	// getter & setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String QQ) {
		this.QQ = QQ;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String[] getAtrributes(){
		String[] atrr = {this.id,this.name,this.sex,this.phoneNum,this.age+"",this.QQ,this.city};
		return atrr;
	}
	
	public String toString(){
		return "姓名："+this.getName()+'\t'+"学号："+this.getId();
	}
}
