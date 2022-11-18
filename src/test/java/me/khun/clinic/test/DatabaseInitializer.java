package me.khun.clinic.test;

import java.sql.SQLException;

import javax.sql.DataSource;

public class DatabaseInitializer {

	public static void truncateTables(DataSource dataSource, String ... tableName) {
		
		var sql = "TRUNCATE TABLE %s";
		try (
			var connection = dataSource.getConnection();
			var stmt  = connection.createStatement()
		) {
			stmt.addBatch("SET FOREIGN_KEY_CHECKS = 0");
			
			for (var table : tableName) {
				stmt.addBatch(sql.formatted(table));
			}
			
			stmt.addBatch("SET FOREIGN_KEY_CHECKS = 1");
			stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
