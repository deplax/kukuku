package com.plannet.pages;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class PopUpFragment extends DialogFragment {
	private OnPopUpListener listener;
	private int listItemId;
	private String listItemTitle;

	public PopUpFragment(int listItemId, String listItemTitle) {
		this.listItemId = listItemId;
		this.listItemTitle = listItemTitle;
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(listItemTitle);
		String[] selectItems = { "수정", "삭제"};

		builder.setItems(selectItems, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				switch (id) {
				case 0:
					listener.onEdit(listItemId);
					break;
				case 1:
					listener.onDelete(listItemId);
					break;
				}
			}
		});

		return builder.create();
	}

	// ////////////////////////
	// 여기서부터 팝업 리스너 인터페이스 선언
	// ////////////////////////

	public void setOnPopUpListener(OnPopUpListener listener) {
		this.listener = listener;
	}

	public interface OnPopUpListener {
		public void onDelete(int listItemId);

		public void onEdit(int listItemId);
	}
}