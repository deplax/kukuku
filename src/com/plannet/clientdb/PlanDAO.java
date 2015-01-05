package com.plannet.clientdb;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.plannet.model.Plan;

public class PlanDAO extends AbstractDAO {

	private String[] columns = { "pid", "uid", "cid", "title", "summary", "complete", "private" };

	public PlanDAO(Context context) {
		super(context);
	}

	public long insert(int uid, int cid, String title, String summary, boolean isComplete, boolean isPrivate) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("uid", uid);
		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("complete", isComplete);
		cv.put("private", isPrivate);

		return db.insert("plan", null, cv);
	}

	public long insert(int cid, String title, String summary) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);

		return db.insert("plan", null, cv);
	}

	public int update(int targetPid, int uid, int cid, String title, String summary, boolean isComplete,
			boolean isPrivate) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("uid", uid);
		cv.put("cid", cid);
		cv.put("title", title);
		cv.put("summary", summary);
		cv.put("complete", isComplete);
		cv.put("private", isPrivate);

		return db.update("plan", cv, "pid=?", new String[] { Integer.toString(targetPid) });
	}

	public int update(int targetPid, String title, String summary) {
		db = helper.getWritableDatabase();
		cv = new ContentValues();

		cv.put("title", title);
		cv.put("summary", summary);

		return db.update("plan", cv, "pid=?", new String[] { Integer.toString(targetPid) });
	}

	public ArrayList<Plan> select() {
		ArrayList<Plan> result = new ArrayList<Plan>();
		db = helper.getReadableDatabase();
		Cursor c = db.query("plan", null, null, null, null, null, null);

		while (c.moveToNext()) {
			int pid = c.getInt(c.getColumnIndex("pid"));
			int uid = c.getInt(c.getColumnIndex("uid"));
			int cid = c.getInt(c.getColumnIndex("cid"));
			String title = c.getString(c.getColumnIndex("title"));
			String summary = c.getString(c.getColumnIndex("summary"));
			boolean isComplete = c.getInt(c.getColumnIndex("complete")) > 0;
			boolean isPrivate = c.getInt(c.getColumnIndex("private")) > 0;

			Plan p = new Plan(pid, uid, cid, title, summary, isComplete, isPrivate);
			result.add(p);
		}

		return result;
	}

	public ArrayList<Plan> selectByCategory(int targetCid) {
		String[] params = { Integer.toString(targetCid) }; // cid 파라미터를 String[] 안에 넣어줌

		ArrayList<Plan> result = new ArrayList<Plan>();
		db = helper.getReadableDatabase();
		Cursor c = db.query("plan", columns, "cid=?", params, null, null, null);
		// 마지막 parameter로 "pid DESC" 넣어주면 역순으로 출력됨

		while (c.moveToNext()) {
			int pid = c.getInt(c.getColumnIndex("pid"));
			int uid = c.getInt(c.getColumnIndex("uid"));
			int cid = c.getInt(c.getColumnIndex("cid"));
			String title = c.getString(c.getColumnIndex("title"));
			String summary = c.getString(c.getColumnIndex("summary"));
			boolean isComplete = c.getInt(c.getColumnIndex("complete")) > 0;
			boolean isPrivate = c.getInt(c.getColumnIndex("private")) > 0;

			Plan p = new Plan(pid, uid, cid, title, summary, isComplete, isPrivate);
			result.add(p);
		}

		return result;
	}

	public long delete(int pid) {
		db = helper.getWritableDatabase();
		return db.delete("plan", "pid=?", new String[] { Integer.toString(pid) });
	}
}