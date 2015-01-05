package com.plannet.pages;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.plannet.activity.EditPlanActivity;
import com.plannet.activity.R;
import com.plannet.clientdb.PlanDAO;
import com.plannet.model.Plan;
import com.plannet.others.Utilities;

public class ModelFragment extends Fragment implements PopUpFragment.OnPopUpListener {
	private PlanAdapter adapter;
	private int categoryId;

	public ModelFragment(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.model_page_fragment, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		ArrayList<Plan> planList = new PlanDAO(getActivity()).selectByCategory(categoryId);
		// Collections.reverse(planList); 이렇게 해도 역순으로 출력 가능하다
		adapter = new PlanAdapter(getActivity(), R.layout.plan_listview_item, planList);
		ListView listView = (ListView) getView().findViewById(R.id.plan_listview);
		listView.setAdapter(adapter);

		// 롱클릭 리스너 추가
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
				String title = adapter.getItem(i).getTitle();
				PopUpFragment popUp = new PopUpFragment(i, title);
				popUp.setOnPopUpListener(ModelFragment.this); // 구현한 팝업 리스너를 넣어줌
				popUp.show(getFragmentManager(), "PlanLongClickPopUp");
				return true;
			}
		});
	}

	// ////////////////////////
	// 여기서부터 팝업 리스너 구현
	// ////////////////////////

	@Override
	public void onDelete(int listItemId) {
		// delete plan in DB
		Plan plan = adapter.getPlanList().get(listItemId);
		new PlanDAO(getActivity()).delete(plan.getPid());

		// delete plan in adapter
		adapter.getPlanList().remove(listItemId);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onEdit(int listItemId) {
		// move to edit plan activity
		Plan plan = adapter.getPlanList().get(listItemId);
		Utilities.moveToAnotherActivity(this.getActivity(), EditPlanActivity.class, "pid", plan.getPid(), "title",
				plan.getTitle(), "summary", plan.getSummary());
	}
}