package com.plannet.clientdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context, String DB_NAME, int DB_VERSION) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// onCreate에서 SQLException 잡아줄 필요 없다고 한다. 또한 OpenHelper를 사용하면 close 안 해줘도 된다. 알아서 관리한다.
	// Helper 객체 만들어 getWritableDatabase/getReadableDatabase를 사용할것.
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQLStatements.createPersonalTable);
		db.execSQL(SQLStatements.createUserTable);
		db.execSQL(SQLStatements.createCategoryTable);
		db.execSQL(SQLStatements.createPlanTable);
		db.execSQL("INSERT INTO personal(uuid) VALUES('default');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("DB Upgrade", oldVersion + " to " + newVersion);
		// db.execSQL(""); alter or drop tables here.
		onCreate(db);
	}
}