package com.plannet.clientdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDAO {
	protected SQLiteDatabase db;
	protected DBOpenHelper helper;
	protected ContentValues cv;

	public AbstractDAO(Context context) {
		helper = new DBOpenHelper(context, DBInfo.DB_NAME, DBInfo.DB_VERSION);
	}

	// public abstract long insert();
	// return the row ID of the newly inserted row, or -1 if an error occurred

	// public abstract int update();
	// return the number of rows affected

	// public abstract List<?> select();

	// public abstract long delete();
	// return the number of rows affected if a whereClause is passed in, 0 otherwise.
	// To remove all rows and get a count, pass "1" as the whereClause.
}