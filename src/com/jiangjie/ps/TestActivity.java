package com.jiangjie.ps;

import java.io.File;

import com.jiangjie.utils.BitmapUtils;
import com.jiangjie.utils.PaintConstants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.view.DragEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class TestActivity extends Activity {
	private CheckBox ckTurn;
	private CheckBox ckKeepScale; 
	private CheckBox ckColor;
	private CheckBox ckErase;

	private Button settingsBtn = null;
	private Button saveBtn = null;
	private ImageTouchView switcher = null;

	private Context mContext;
	//状态栏高度
	public static int statusbarHeight;
	public static int tilebarHeight;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 

		ckTurn = (CheckBox)findViewById(R.id.ckTurn);
		ckKeepScale = (CheckBox)findViewById(R.id.ckKeepScale); 
		ckColor = (CheckBox)findViewById(R.id.ckColor);
		ckErase = (CheckBox)findViewById(R.id.ckErase);

		settingsBtn = (Button)findViewById(R.id.settings);
		saveBtn = (Button)findViewById(R.id.save);

		switcher = (ImageTouchView)findViewById(R.id.switcher);
		


		ckTurn.setChecked(PaintConstants.SELECTOR.HAIR_RURN);
		ckKeepScale.setChecked(PaintConstants.SELECTOR.KEEP_SCALE); 
		
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		statusbarHeight = frame.top;
		
		int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
		//statusBarHeight是上面所求的状态栏的高度
		tilebarHeight = contentTop - statusbarHeight;

		ckTurn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				PaintConstants.SELECTOR.HAIR_RURN = isChecked;
			}
		});

		ckKeepScale.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				PaintConstants.SELECTOR.KEEP_SCALE = isChecked;
			}
		});

		ckColor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				PaintConstants.SELECTOR.COLORING = isChecked;
				if(isChecked && PaintConstants.SELECTOR.ERASE){
					PaintConstants.SELECTOR.ERASE = false;
					ckErase.setChecked(false);
				}
				PaintConstants.SELECTOR.KEEP_IMAGE = isChecked;
			}
		});

		ckErase.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				PaintConstants.SELECTOR.ERASE = isChecked;	
				if(isChecked && PaintConstants.SELECTOR.COLORING){
					PaintConstants.SELECTOR.COLORING = false;
					ckColor.setChecked(false);
				}
				PaintConstants.SELECTOR.KEEP_IMAGE = isChecked;
			}
		});

		//设置
		settingsBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(TestActivity.this, SettingsActivity.class);
				startActivity(intent);
			}
		});

		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String path = "/storage/sdcard0";
				//检测文件夹是否存在
				File file = new File(path);
				String filepath = path+File.separator+String.valueOf(System.currentTimeMillis()+".png");
			    BitmapUtils.saveToSdCard(filepath, switcher.cacheBitmap);
				Toast.makeText(TestActivity.this, "文件"+filepath+"保存成功！", 0).show();
			}
		});
		

	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		initStatus();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		initStatus();
	}
	
	private void initStatus(){
		
		if(PaintConstants.SELECTOR.HAIR_RURN){
			ckTurn.setChecked(true);
		}
		
		if(PaintConstants.SELECTOR.KEEP_SCALE){
			ckKeepScale.setChecked(true);
		}
		
		if(PaintConstants.SELECTOR.COLORING){
			ckColor.setChecked(true);
		}
		
		if(PaintConstants.SELECTOR.ERASE){
			ckErase.setChecked(true);
			
		}
	}
}







