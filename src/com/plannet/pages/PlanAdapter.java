package com.plannet.pages;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.plannet.activity.R;
import com.plannet.model.Plan;

public class PlanAdapter extends ArrayAdapter<Plan> {

	private Context context;
	private int layoutResourceId;
	private ArrayList<Plan> planList;

	public ArrayList<Plan> getPlanList() {
		return planList;
	}

	public PlanAdapter(Context context, int resource, ArrayList<Plan> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutResourceId = resource;
		this.planList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflator = ((Activity) context).getLayoutInflater();
			row = inflator.inflate(layoutResourceId, parent, false);
		}

		TextView title = (TextView) row.findViewById(R.id.plan_title);
		title.setText(planList.get(position).getTitle());

		return row;
	}
}
