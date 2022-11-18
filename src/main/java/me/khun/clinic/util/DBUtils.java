package me.khun.clinic.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
	
	public static void rollBack(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(AutoCloseable...autoCloseable) {
		for (var a : autoCloseable) {
			try {
				if (a != null) {
					a.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
