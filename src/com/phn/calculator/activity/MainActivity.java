package com.phn.calculator.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private char[] num1;
	private char[] num2;
	private char opt = 'c';
	private double result = 0;
	private boolean hasSaveResult =false;
	private static int index1 = 0;
	private static int index2 = 0;
	private StringBuffer stbf = new StringBuffer("0");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initNum();
		setBtn();

	}

	private void initNum() {
		num1 = new char[16];
		num2 = new char[16];
	}

	private void setBtn() {
		Button btn_num_0 = (Button) findViewById(R.id.btn_num_0);
		btn_num_0.setOnClickListener(this);
		Button btn_num_1 = (Button) findViewById(R.id.btn_num_1);
		btn_num_1.setOnClickListener(this);
		Button btn_num_2 = (Button) findViewById(R.id.btn_num_2);
		btn_num_2.setOnClickListener(this);
		Button btn_num_3 = (Button) findViewById(R.id.btn_num_3);
		btn_num_3.setOnClickListener(this);
		Button btn_num_4 = (Button) findViewById(R.id.btn_num_4);
		btn_num_4.setOnClickListener(this);
		Button btn_num_5 = (Button) findViewById(R.id.btn_num_5);
		btn_num_5.setOnClickListener(this);
		Button btn_num_6 = (Button) findViewById(R.id.btn_num_6);
		btn_num_6.setOnClickListener(this);
		Button btn_num_7 = (Button) findViewById(R.id.btn_num_7);
		btn_num_7.setOnClickListener(this);
		Button btn_num_8 = (Button) findViewById(R.id.btn_num_8);
		btn_num_8.setOnClickListener(this);
		Button btn_num_9 = (Button) findViewById(R.id.btn_num_9);
		btn_num_9.setOnClickListener(this);
		Button btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(this);
		Button btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_delete.setOnClickListener(this);
		Button btn_divide = (Button) findViewById(R.id.btn_divide);
		btn_divide.setOnClickListener(this);
		Button btn_multiply = (Button) findViewById(R.id.btn_multiply);
		btn_multiply.setOnClickListener(this);
		Button btn_plus = (Button) findViewById(R.id.btn_plus);
		btn_plus.setOnClickListener(this);
		Button btn_minus = (Button) findViewById(R.id.btn_minus);
		btn_minus.setOnClickListener(this);
		Button btn_equal = (Button) findViewById(R.id.btn_equal);
		btn_equal.setOnClickListener(this);
		Button btn_dot = (Button) findViewById(R.id.btn_dot);
		btn_dot.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_num_0:
			setNum('0');
			break;
		case R.id.btn_num_1:
			setNum('1');
			break;
		case R.id.btn_num_2:
			setNum('2');
			break;
		case R.id.btn_num_3:
			setNum('3');
			break;
		case R.id.btn_num_4:
			setNum('4');
			break;
		case R.id.btn_num_5:
			setNum('5');
			break;
		case R.id.btn_num_6:
			setNum('6');
			break;
		case R.id.btn_num_7:
			setNum('7');
			break;
		case R.id.btn_num_8:
			setNum('8');
			break;
		case R.id.btn_num_9:
			setNum('9');
			break;
		case R.id.btn_clear:
			clear();
			setCalculateVlaue("0");
			break;
		case R.id.btn_delete:
			openHint("暂时还无法使用删除一位功能！");
			break;
		case R.id.btn_plus:
			setOpt('+');
			break;
		case R.id.btn_minus:
			setOpt('-');
			break;
		case R.id.btn_divide:
			setOpt('/');
			break;
		case R.id.btn_multiply:
			setOpt('*');
			break;
		case R.id.btn_equal:
			closeHint();
			getCalculateValue();
			clear();
			break;
		case R.id.btn_dot:
			openHint("暂时还无法使用小数点功能！");
			break;
		}

	}

	private void setOpt(char op) {
		if (!num1IsNull() && opt == 'c') {
			closeHint();
			opt = op;
			setCalculateVlaue(new StringBuffer(String.valueOf(num1) + opt)
					.toString());
		} else if(hasSaveResult){
			opt = op;
			num1 = String.valueOf(result).toCharArray();
			hasSaveResult= false;
			setCalculateVlaue(new StringBuffer(String.valueOf(num1) + opt)
			.toString());
		}else {
			openHint("暂时不支持带符号数运算，请直接输入一个正数！");
		}
	}

	private void setNum(char num) {
		if (opt == 'c') {
			if (index1 <= 5) {
				num1[index1] = num;
				index1++;
				setCalculateVlaue(new StringBuffer(String.valueOf(num1))
						.toString());
				closeHint();
			} else {
				openHint("目前允许计算的数的最大长度为6！");
			}
		} else {
			if (index2 <= 5) {
				num2[index2] = num;
				index2++;
				setCalculateVlaue(new StringBuffer(String.valueOf(num1) + opt
						+ String.valueOf(num2)).toString());
				closeHint();
			} else {
				openHint("目前允许计算的数的最大长度为6！");
			}
		}

	}

	private void openHint(String message) {
		TextView calculate_hint_title = (TextView) findViewById(R.id.calculate_hint_title);
		calculate_hint_title.setVisibility(View.VISIBLE);
		TextView calculate_hint_message = (TextView) findViewById(R.id.calculate_hint_message);
		calculate_hint_message.setText(message);
		calculate_hint_message.setVisibility(View.VISIBLE);
	}

	private void closeHint() {
		TextView calculate_hint_title = (TextView) findViewById(R.id.calculate_hint_title);
		calculate_hint_title.setVisibility(View.INVISIBLE);
		TextView calculate_hint_message = (TextView) findViewById(R.id.calculate_hint_message);
		calculate_hint_message.setVisibility(View.INVISIBLE);
	}

	private void clear() {
		num1 = new char[16];
		num2 = new char[16];
		opt = 'c';
		index1 = 0;
		index2 = 0;
	}

	private void getCalculateValue() {
		if (index2 != 0) {
			getCalcultateValueByOpt();
		} else if (index1 != 0) {
			hasSaveResult = true;
			result = Double.parseDouble(String.valueOf(num1));
			setCalculateVlaue(String.valueOf(num1));
		} else {
			setCalculateVlaue("0");
		}
		clear();
	}

	private void getCalcultateValueByOpt() {
		double num01 = Double.parseDouble(String.valueOf(num1));
		double num02 = Double.parseDouble(String.valueOf(num2));
		boolean divideNumIsNotZero = true;
		switch (opt) {
		case '+':
			result = num01 + num02;
			break;
		case '-':
			result = num01 - num02;
			break;
		case '*':
			result = num01 * num02;
			break;
		case '/':
			if(num02!=0){
				result = (num01 * 1.0) / num02;
			}else{
				divideNumIsNotZero = false;
			}
			break;
		default:
			break;
		}
		if(divideNumIsNotZero){
			hasSaveResult = true;
			result = Double.parseDouble(new java.text.DecimalFormat("#.00000000").format(result));
			clear();
			setCalculateVlaue(String.valueOf(result));
		}else{
			openHint("除数不能为零！");
		}
		
	}

	private void setCalculateVlaue(String str) {
		TextView calculate_value = (TextView) findViewById(R.id.calculate_value);
		calculate_value.setText(str);
	}

	/**
	 * @return
	 */
	private boolean num1IsNull() {
		if (index1 == 0) {
			return true;
		}
		return false;
	}
}
