package com.plannet.clientdb;

public class SQLStatements {
	public final static String createPersonalTable = "CREATE TABLE IF NOT EXISTS personal("
			+ "uuid TEXT"
			+ ");";
	
	public final static String createUserTable = "CREATE TABLE IF NOT EXISTS user("
			+ "uid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "name TEXT"
			+ ");";

	public final static String createCategoryTable = "CREATE TABLE IF NOT EXISTS category("
			+ "cid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "uid INTEGER,"
			+ "name TEXT NOT NULL,"
			+ "FOREIGN KEY(uid) REFERENCES user(uid)"
			+ ");";

	public final static String createPlanTable = "CREATE TABLE IF NOT EXISTS plan("
			+ "pid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ "uid INTEGER,"
			+ "cid INTEGER,"
			+ "title TEXT NOT NULL,"
			+ "summary TEXT,"
			+ "complete NUMERIC,"
			+ "private NUMERIC,"
			+ "FOREIGN KEY(uid) REFERENCES user(uid)"
			+ "FOREIGN KEY(cid) REFERENCES category(cid)"
			+ ");";
}