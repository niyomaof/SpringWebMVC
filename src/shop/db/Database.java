package shop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	public Connection conn;

	public Database() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "1234");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public boolean close() {
		try {
			conn.close();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean commit() {
		try {
			conn.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean rollback() {
		try {
			conn.rollback();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// return code
	public int add(String sql) {
		int lastId = -1;
		try {
			if (conn != null) {
				Statement stmt = conn.createStatement();
				lastId = stmt.executeUpdate(sql);
				stmt.close();
			}
			return lastId;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lastId;
	}

	// return key of inserted
	public int add(String sql, String[] param) {
		int lastId = -1;
		try {
			if (conn != null) {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql, param);
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs != null && rs.next()) {
					lastId = rs.getInt(1);
				}
				rs.close();
				stmt.close();
			}
			return lastId;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lastId;
	}

	public int update(String sql) {
		int lastId = -1;
		try {
			if (conn != null) {
				Statement stmt = conn.createStatement();
				lastId = stmt.executeUpdate(sql);
				stmt.close();
			}
			return lastId;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lastId;
	}

	public int remove(String sql) {
		int lastId = -1;
		try {
			if (conn != null) {
				Statement stmt = conn.createStatement();
				lastId = stmt.executeUpdate(sql);
				stmt.close();
			}
			return lastId;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lastId;
	}

	public HashMap<String, Object> querySingle(String sql) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
							map.put(rsMetaData.getColumnName(i + 1), rs.getString(i + 1));
						}
					}
				}
				rs.close();
				stmt.close();

				return map;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			;
		}
		return map;
	}

	public ArrayList<HashMap<String, Object>> queryList(String sql) {
		ArrayList<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
		try {
			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						for (int i = 0; i < rsMetaData.getColumnCount(); i++) {
							map.put(rsMetaData.getColumnName(i + 1), rs.getString(i + 1));
						}
						mapList.add(map);
					}
				}
				rs.close();
				stmt.close();

				return mapList;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			;
		}
		return mapList;
	}
}
