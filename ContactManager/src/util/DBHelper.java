package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:data/contacts.db";

	private static Connection conn = null;

	static {
		try {
			
			Class.forName(DRIVER);
			initDatabase();
			System.out.println("init");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection(URL);
			System.out.println("connection successfully£º"+conn);
			return conn;
		}
		return conn;
	}

	private static void initDatabase() throws IOException {
		File dataBaseFile = new File("data/contacts.db");
		File dataBaseDir = new File("data");
		if(!dataBaseDir.exists()) dataBaseDir.mkdirs();
		if (dataBaseFile.exists()) {
			System.out.println("Database exist");
		} else {
			dataBaseFile.createNewFile();
			try {
				conn = getConnection();

				System.out.println("Opened database successfully");

				java.sql.Statement stmt = conn.createStatement();
				String sqlcontacts = "CREATE TABLE 'contacts' ('id'  TEXT NOT NULL,'name'  TEXT NOT NULL,'sex'  TEXT,'age'  INTEGER,'phoneNum'  TEXT,'QQ'  TEXT,'city'  TEXT,PRIMARY KEY ('id' ASC))";
				String sqlusers = "CREATE TABLE 'users' ('id'  TEXT NOT NULL,'username'  TEXT NOT NULL,'password'  TEXT NOT NULL,'isAdmin'  INTEGER NOT NULL,PRIMARY KEY ('id' ASC),CONSTRAINT 'id' UNIQUE ('id' COLLATE NOCASE ASC),CONSTRAINT 'username' UNIQUE ('username' COLLATE NOCASE ASC) ON CONFLICT FAIL)";
				String sqlInitData = "INSERT INTO 'users' VALUES (0000000000, 'admin', 'admin', 1)";
				stmt.executeUpdate(sqlcontacts);
				stmt.executeUpdate(sqlusers);
				stmt.executeUpdate(sqlInitData);
				stmt.close();
				conn.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			System.out.println("Table created successfully");

		}
	}

}
