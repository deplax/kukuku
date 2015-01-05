package com.plannet.pages;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

// PagerAdapter 클래스에 대한 설명:
// TabListener -> 탭을 선택했을 때 일어나는 일
// OnPageChangeListener -> 페이지 바뀔 때 (탭 터치든 페이지 스와이프든)
// FragmentPagerAdapter -> 페이저 어댑터, fragment를 어댑트한다
// PagerAdapter는 이것들을 통합관리해줌

public class PagerAdapter extends FragmentPagerAdapter implements TabListener, OnPageChangeListener {
	private final ActionBar tabActionBar;
	private final ViewPager pager;
	private final ArrayList<TabInfo> tabInfoList = new ArrayList<TabInfo>();

	// TabInfo 내부 클래스 - 각각의 탭에 대한 fragment 클래스 정보를 담아둠
	static final class TabInfo {
		final Class<?> targetClass;

		TabInfo(Class<?> targetClass) {
			this.targetClass = targetClass;
		}
	}

	public PagerAdapter(FragmentActivity activity, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		this.pager = pager;
		this.tabActionBar = activity.getActionBar();

		this.pager.setAdapter(this);
		this.pager.setOnPageChangeListener(this);
	}

	public void addTab(ActionBar.Tab tab, Class<?> targetClass, int cid) {
		TabInfo tabInfo = new TabInfo(targetClass);
		tab.setTabListener(this); // PagerAdapter를 탭 리스너로 지정해줌
		tab.setTag(cid); // 각각의 탭의 태그 정보 안에 cid를 넣어줌

		int tabNumber = tabInfoList.size(); // 들어있는 탭 개수가 새롭게 들어갈 탭의 번호가 됨
		// 추가설명: 추가된 탭이 하나도 없었다면 새로 들어갈 탭의 번호는 0번이다

		tabInfoList.add(tabNumber, tabInfo); // PagerAdapter의 tabInfoList에 지정된 번호로 넣어줌
		tabActionBar.addTab(tab); // 실제 액션바에 이 새로 들어온 탭을 추가
		notifyDataSetChanged(); // 이 부분은 초기화 이후에 탭을 동적으로 추가하기 위해

		// ////////////////////////
		// 번호에 대한 설명
		// ////////////////////////
		// ActionBar.Tab의 postion은 0부터 시작한다
		// Page의 position 역시 0부터 시작한다
		// tabInfoList도 0부터 시작한다
		// ////////////////////////
		// 서로 상응되는
		// page의 position 값과,
		// tab의 position 값과,
		// tabInfoList에서의 인덱스 값은 일치하고 있다
	}

	// ////////////////////////
	// 여기서부터 FragmentPagerAdapter 구현 메서드
	// ////////////////////////

	@Override
	public int getCount() {
		// 현재 PagerAdapter가 관리하고 있는 fragment 개수가 몇개인지 알려줌
		return tabInfoList.size();
	}

	@Override
	public Fragment getItem(int position) {
		// 페이저 각각의 위치에 대해 fragment를 인스턴스로 생성해줌
		int cid = (Integer) tabActionBar.getTabAt(position).getTag(); // 여기에서 cid 추출해서 ModelFragment 생성할 때 넣어준다
		return new ModelFragment(cid);
	}

	// ////////////////////////
	// 여기서부터 OnPageChangeListener 구현 메서드
	// ////////////////////////

	@Override
	public void onPageSelected(int position) {
		// 페이지가 선택되었을 때 해당 위치 탭을 선택되었다고 파란색으로 표현해준다
		tabActionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	// ////////////////////////
	// 여기서부터 TabListener 구현 메서드
	// ////////////////////////

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// 탭이 선택되었을 때 해당 위치 페이지를 보여줌
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
	}
}