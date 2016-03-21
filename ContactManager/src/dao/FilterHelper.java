package dao;

//筛选用的筛选器，根据获取的信息创建查询条件的SQL语句
public class FilterHelper {
	private int lowerAge;
	private int upperAge;
	private boolean isMale;
	private boolean isFeMale;
	private String location;

	public FilterHelper() {

	}

	public FilterHelper(int lowerAge, int upperAge, boolean isMale, boolean isFeMale, String location) {
		super();
		this.lowerAge = lowerAge;
		this.upperAge = upperAge;
		this.isMale = isMale;
		this.isFeMale = isFeMale;
		this.location = location;
	}

	public int getLowerAge() {
		return lowerAge;
	}

	public void setLowerAge(int lowerAge) {
		this.lowerAge = lowerAge;
	}

	public int getUpperAge() {
		return upperAge;
	}

	public void setUpperAge(int upperAge) {
		this.upperAge = upperAge;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public boolean isFeMale() {
		return isFeMale;
	}

	public void setFeMale(boolean isFeMale) {
		this.isFeMale = isFeMale;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAgeFilter() {
		String filterUp = " age<= " + upperAge;
		String filterDown = " age>= " + lowerAge;
		String filter="";
		System.out.println(upperAge+" "+lowerAge);
		if (lowerAge != -1) {
			if (upperAge != -1) {
				filter = filterUp + " AND " + filterDown;
			}else
				filter = filterDown;
		} else {
			if (upperAge != -1)
				filter = filterUp;
		}
		return filter;
	}

	public String getSexFilter() {
		String filter = "";
		if (isFeMale) {
			filter += "sex = '女'";
			if (isMale)
				filter +=( " OR " + "sex = '男'");
		}else
			if (isMale)
				filter += " sex = '男'";
		return filter;
	}

	public String getLocationFilter() {
		location = location.replace('，', ',');
		String[] locations = location.split(",");
		String filter = "";
		if (location.length()!=0) {
			for (int i = 0; i < locations.length; i++) {
				filter += "city = '"+locations[i]+"' OR ";
			}
			filter = filter.substring(0, filter.length()-3);
		}
		return filter;
	}
	
	public String getSQL(){
		String filter = "";
		if(!getAgeFilter().equals("")){
			filter += " ( "+getAgeFilter()+" )";
		}
		if(!getSexFilter().equals("")){
			filter += " AND ("+getSexFilter()+" )";
		}
		if(!getLocationFilter().equals("")){
			filter += " AND ("+getLocationFilter()+" )";
		}
		if(filter.length()>5&&filter.substring(0, 4).equals(" AND")){
			filter = filter.substring(4);
		}
		return filter;
	}
	
	
	public static void main(String[] args) {
		FilterHelper fh = new FilterHelper(-1,-1,false,false,"");
		
		System.out.println(fh.getAgeFilter()+" "+ fh.getSexFilter()+" "+fh.getLocationFilter());
		System.out.println(fh.getSQL());
		System.out.println();
	}
}
