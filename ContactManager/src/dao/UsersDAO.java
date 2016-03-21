package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper;
import entity.Items;
import entity.Users;

public class UsersDAO {
	private String adminWord = "hahaha";
	
	public UsersDAO(){
	}
	
	// 从数据库中获取所有用户的列表
	public ArrayList<Users> getAllUsers() {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			Connection conn = DBHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Users u = new Users();
				u.setId(rs.getString("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setIsAdmin(rs.getInt("isAdmin") == 1 ? true : false);
				usersList.add(u);
			}
			return usersList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 根据用户编号获取用户对象
	public Users getUserByID(String id) {
		try {
			Connection conn = DBHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id='" + id + "'");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Users u = new Users();
				u.setId(rs.getString("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setIsAdmin(rs.getInt("isAdmin") == 1 ? true : false);
				return u;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Users getUserByUsername(String username) {
		try {
			Connection conn = DBHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username='" + username + "'");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Users u = new Users();
				u.setId(rs.getString("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setIsAdmin(rs.getInt("isAdmin") == 1 ? true : false);
				System.out.println(rs.getInt("isAdmin") == 1 ? true : false);
				return u;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 判断用户是否存在
	public boolean isUserExist(String username) {
		try {
			Connection conn = DBHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = " + username + " ;");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean isUserExist(Users u) {
		try {
			Connection conn = DBHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = '" + u.getUsername() + "' OR id = '"+u.getId()+"'");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// 判断账号密码是否正确
	public boolean isUserCorrect(Users u) {
		try {
			Connection conn = DBHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = '" + u.getUsername()
					+ "'" + " AND password = '" + u.getPassword() + "';");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// 添加用户
	public boolean addUser(Users user, Items item) {
		try {
			ItemsDAO itemsdao = new ItemsDAO();
			if (itemsdao.getItemByID(item.getId()) == null) {
				itemsdao.saveItems(item);
			}
			Connection conn = DBHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.isAdmin() ? 1 : 0);//如果是管理员则传入1，否则0
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除用户
	public boolean deleteUserBy(String id) {
		try {
			Connection conn = DBHelper.getConnection();
			String sql = "DELETE FROM users WHERE id=" + id;
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (stmt.execute())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	//筛选是否管理员
	public ArrayList<Users> filterAdmin(boolean isAdmin){
		int num = isAdmin?1:0;
		try {
			Connection conn = DBHelper.getConnection();
			String sql = "SELECT * FROM users WHERE isAdmin =" + num;
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Users> usersList = new ArrayList<Users>();
			while (rs.next()) {
				Users u = new Users();
				u.setId(rs.getString("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setIsAdmin(rs.getInt("isAdmin") == 1 ? true : false);
				usersList.add(u);
			}
			return usersList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isAdminCorrect(String s) {
		if (s.equals(adminWord))
			return true;
		return false;
	}
	
}
