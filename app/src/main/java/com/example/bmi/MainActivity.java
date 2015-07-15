package com.example.bmi;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText height,weight;
    String h = "",w;
    double shengao,tizhong,result;
    TextView textView;//显示结果
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		height = (EditText) findViewById(R.id.height);
		weight = (EditText) findViewById(R.id.weight);
		textView = (TextView) findViewById(R.id.textview);
		
		SharedPreferences settings = getSharedPreferences("data", 0);
	    //从偏好设置文件中获取上次的身高值，如果不存在，采用默认值
		h = settings.getString("shengao", "");
		//把上次的身高值放到本次身高输入框中
	    height.setText(h);
	    weight.requestFocus();
	}
	
	public void click(View view){
		h = height.getText().toString();
		w = weight.getText().toString();
		//把字符串转化为小数
		if(h.equals("")|| w.equals("")){
			Toast.makeText(getApplicationContext(), 
			"您输入的身高或者体重为空，请重新输入", 
			Toast.LENGTH_SHORT).show();
			return;
		}
		shengao = Double.parseDouble(h);
		tizhong = Double.parseDouble(w);
		//BMI:体重/身高的平方，18~55：标准
		//>25:偏胖；<18：偏瘦
		result = tizhong / (shengao*shengao);
		if (result >= 18 && result <=25) {
			textView.setText("身材标准");
		} else if(result < 18){
			textView.setText("偏瘦");
		} else {
			textView.setText("偏胖");
		}
	}
	//返回键或者home键都会调用此方法
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//获取偏好设置
		SharedPreferences settings = getSharedPreferences("data", 0);
		//保存数据
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putString("shengao", h);
	    editor.commit();

	}

}
