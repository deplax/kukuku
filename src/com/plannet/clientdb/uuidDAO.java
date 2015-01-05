package com.plannet.clientdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class uuidDAO extends AbstractDAO {

	public uuidDAO(Context context) {
		super(context);
	}

	public long insert(String uuid) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();
		cv.put("uuid", uuid);
		return db.insert("personal", null, cv);
	}

	public void update(String uuid) {
		delete();
		insert(uuid);
	}

	public String select() {
		db = helper.getReadableDatabase();
		Cursor c = db.query("personal", null, null, null, null, null, null);
		c.moveToFirst();
		return c.getString(c.getColumnIndex("uuid"));
	}

	public long delete() {
		db = helper.getWritableDatabase();
		return db.delete("personal", null, null);
	}
}
