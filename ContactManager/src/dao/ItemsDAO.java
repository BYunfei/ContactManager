package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.transform.Result;

import util.DBHelper;
import entity.Items;

public class ItemsDAO {
	Connection conn;

	public ItemsDAO() throws SQLException {
		conn = DBHelper.getConnection();
	}

	public ArrayList<Items> getAllItems() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> itemsList = new ArrayList<Items>();
		try {
			stmt = conn.prepareStatement("SELECT * FROM contacts;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Items i = new Items();
				i.setId(rs.getString("id"));
				i.setName(rs.getString("name"));
				i.setSex(rs.getString("sex"));
				i.setAge(rs.getInt("age"));
				i.setPhoneNum(rs.getString("phoneNum"));
				i.setQQ(rs.getString("QQ"));
				i.setCity(rs.getString("city"));
				itemsList.add(i);
			}
			return itemsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 根据联系人编号获取联系人对象
	public Items getItemByID(String id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM contacts WHERE id=" + id + ";");
			rs = stmt.executeQuery();
			Items item = new Items();
			if (rs.next()) {
				item.setId(rs.getString("id"));
				item.setName(rs.getString("name"));
				item.setSex(rs.getString("sex"));
				item.setAge(rs.getInt("age"));
				item.setPhoneNum(rs.getString("phoneNum"));
				item.setQQ(rs.getString("QQ"));
				item.setCity(rs.getString("city"));
				return item;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 根据姓名查找联系人
	public ArrayList<Items> serachByName(String name) {
		ArrayList<Items> resultsList = new ArrayList<Items>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM contacts WHERE name='" + name + "'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Items i = new Items();
				i.setId(rs.getString("id"));
				i.setName(rs.getString("name"));
				i.setSex(rs.getString("sex"));
				i.setAge(rs.getInt("age"));
				i.setPhoneNum(rs.getString("phoneNum"));
				i.setQQ(rs.getString("QQ"));
				i.setCity(rs.getString("city"));
				resultsList.add(i);
			}
			return resultsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 根据学号查找联系人
	public ArrayList<Items> searchByID(String id) {
		ArrayList<Items> resultsList = new ArrayList<Items>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM contacts WHERE id = '" + id + "'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Items i = new Items();
				i.setId(rs.getString("id"));
				i.setName(rs.getString("name"));
				i.setSex(rs.getString("sex"));
				i.setAge(rs.getInt("age"));
				i.setPhoneNum(rs.getString("phoneNum"));
				i.setQQ(rs.getString("QQ"));
				i.setCity(rs.getString("city"));
				resultsList.add(i);
			}
			return resultsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 根据地区查找联系人
	public ArrayList<Items> searchByCity(String city) {
		ArrayList<Items> resultsList = new ArrayList<Items>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM contacts WHERE city = '" + city + "'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Items i = new Items();
				i.setId(rs.getString("id"));
				i.setName(rs.getString("name"));
				i.setSex(rs.getString("sex"));
				i.setAge(rs.getInt("age"));
				i.setPhoneNum(rs.getString("phoneNum"));
				i.setQQ(rs.getString("QQ"));
				i.setCity(rs.getString("city"));
				resultsList.add(i);
			}
			return resultsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 保存新的联系人
	public boolean saveItems(Items item) {
		try {
			String values = "'" + item.getId() + "','" + item.getName() + "','" + item.getSex() + "'," + item.getAge()
					+ ",'" + item.getPhoneNum() + "','" + item.getQQ() + "','" + item.getCity() + "'";
			String columns = "id," + "name," + "sex," + "age," + "phoneNum," + "QQ," + "city";
			System.out.println("INSERT INTO contacts (" + columns + ") VALUES (" + values + ");");
			PreparedStatement stmt = this.conn
					.prepareStatement("INSERT INTO contacts (" + columns + ") VALUES (" + values + ");");
			if (stmt.execute())
				return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// 修改联系人
	public boolean updateItems(Items item) {
		try {
			String values = "'" + item.getName() + "','" + item.getSex() + "'," + item.getAge() + ",'"
					+ item.getPhoneNum() + "','" + item.getQQ() + "'," + item.getCity();
			String columns = "name," + "sex," + "age," + "phoneNum," + "QQ," + "birth";
			PreparedStatement stmt = this.conn.prepareStatement("UPDATE contacts SET name='" + item.getName()
					+ "',sex='" + item.getSex() + "',age=" + item.getAge() + ",phoneNum='" + item.getPhoneNum()
					+ "',QQ='" + item.getQQ() + "',city='" + item.getCity() + "' WHERE id='" + item.getId() + "'");
			if (stmt.execute())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// 根据ID删除已有联系人
	public boolean deleteItemByID(String id) {
		try {
			String sql = "DELETE FROM contacts WHERE id=" + id;
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (stmt.execute())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// 根据筛选器筛选
	public ArrayList<Items> getItemsByFilter(FilterHelper filter) {
		// 定义 筛选条件 字符串
		String filterString = filter.getSQL();
		String sql;
		if (!filterString.equals(""))
			sql = "SELECT * FROM contacts WHERE " + filterString;
		else
			sql = "SELECT * FROM contacts";
		System.out.println("SQL: " + sql);
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Items> resultsList = new ArrayList<Items>();
			while (rs.next()) {
				System.out.println(rs.getRow());
				Items i = new Items();
				i.setId(rs.getString("id"));
				i.setName(rs.getString("name"));
				i.setSex(rs.getString("sex"));
				i.setAge(rs.getInt("age"));
				i.setPhoneNum(rs.getString("phoneNum"));
				i.setQQ(rs.getString("QQ"));
				i.setCity(rs.getString("city"));
				resultsList.add(i);
			}
			return resultsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 判断联系人是否存在
	public boolean isItemExits(String id) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("SELECT * FROM contacts WHERE id = " + id + " ;");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// 根据年龄筛选
	public ArrayList<Items> supplyByAge(int upper, int lower) {
		String sql = "SELECT * FROM \"contacts\" WHERE age>=" + lower + " AND age<=" + upper;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Items> resultsList = new ArrayList<Items>();
			while (rs.next()) {
				System.out.println(rs.getRow());
				Items i = new Items();
				i.setId(rs.getString("id"));
				i.setName(rs.getString("name"));
				i.setSex(rs.getString("sex"));
				i.setAge(rs.getInt("age"));
				i.setPhoneNum(rs.getString("phoneNum"));
				i.setQQ(rs.getString("QQ"));
				i.setCity(rs.getString("city"));
				resultsList.add(i);
			}
			return resultsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
