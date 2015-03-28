package com.xxhong.fzqian.activity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.xxhong.fzqian.FzqApp;
import com.xxhong.fzqian.R;
import com.xxhong.fzqian.net.RequestServerData;
import com.xxhong.fzqian.utils.DesException;
import com.xxhong.fzqian.utils.JsonParser;
import com.xxhong.fzqian.utils.RequestParamsUtil;
import com.xxhong.fzqian.utils.UiUtil;
import com.xxhong.fzqian.utils.domain.Account;
import com.xxhong.fzqian.utils.domain.UserInfo;
import com.xxhong.lib.uitl.TextUtil;

public class AddFzqActivity extends BaseActivity implements OnClickListener {
	
	public static void  startThis(){
		
	}
	
	// 用HashMap存储听写结果
	private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
	private Button btVoiceName, btVoiceMoney, btVoicePhone, btVoiceDesc;
	private EditText etName, etMoney, etCause, etTime, etPhone, etDesc;
	// 语音听写对象
	private SpeechRecognizer mIat;
	// 语音听写UI
	private RecognizerDialog mIatDialog;

	public enum EditStatus {
		NAME, MONEY, PHONE, DESC;
	}

	EditStatus mEditStaus;
	//1 送礼之人  0收礼之人
	private int userType;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.add_fzq_fragment_view);
		init();
		initView();
		initLis();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		setTitle("添加随礼人");
	}

	private void init() {
		userType = getIntent().getIntExtra("userTpey", 1);//默认送礼之人
		// 初始化识别对象
		mIat = SpeechRecognizer.createRecognizer(this, mInitListener);
		// 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
		mIatDialog = new RecognizerDialog(this, mInitListener);
		mIat.setParameter(SpeechConstant.ASR_PTT, "0");
	}

	private void initView() {
		btVoiceName = (Button) this.findViewById(R.id.bt_voice_name);
		btVoiceMoney = (Button) this.findViewById(R.id.bt_voice_money);
		btVoicePhone = (Button) this.findViewById(R.id.bt_voice_phone);
		btVoiceDesc = (Button) this.findViewById(R.id.bt_voice_desc);
		etName = (EditText) this.findViewById(R.id.et_name);
		etMoney = (EditText) this.findViewById(R.id.et_money);
		etCause = (EditText) this.findViewById(R.id.et_cause);
		etTime = (EditText) this.findViewById(R.id.et_time);
		etPhone = (EditText) this.findViewById(R.id.et_phone);
		etDesc = (EditText) this.findViewById(R.id.et_desc);

	}

	private InitListener mInitListener = new InitListener() {

		@Override
		public void onInit(int code) {
			if (code != ErrorCode.SUCCESS) {
				Toast.makeText(AddFzqActivity.this, "error  code" + code, 0)
						.show();
			}
		}
	};

	private void initLis() {
		etName.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					btVoiceName.setVisibility(View.VISIBLE);
					mEditStaus = EditStatus.NAME;
				} else {
					btVoiceName.setVisibility(View.GONE);
				}
			}
		});
		etMoney.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					btVoiceMoney.setVisibility(View.VISIBLE);
					mEditStaus = EditStatus.MONEY;
				} else {
					btVoiceMoney.setVisibility(View.GONE);
				}
			}
		});
		etPhone.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					btVoicePhone.setVisibility(View.VISIBLE);
					mEditStaus = EditStatus.PHONE;
				} else {
					btVoicePhone.setVisibility(View.GONE);
				}
			}
		});
		etDesc.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					btVoiceDesc.setVisibility(View.VISIBLE);
					mEditStaus = EditStatus.DESC;
				} else {
					btVoiceDesc.setVisibility(View.GONE);
				}
			}
		});

		btVoiceName.setOnClickListener(this);
		btVoiceMoney.setOnClickListener(this);
		btVoicePhone.setOnClickListener(this);
		btVoiceName.setOnClickListener(this);
		btVoiceDesc.setOnClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			finish();
		}
		if(itemId==R.id.ok){
			doAddUserInfo();
		}
		return super.onOptionsItemSelected(item);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.okbtn, menu);
		return super.onCreateOptionsMenu(menu);
	}
	/**
	 * 添加收礼之人
	 */
	public void doAddUserInfo(){
		String name = etName.getText().toString().trim();
		String money = etMoney.getText().toString().trim();
		String cause = etCause.getText().toString().trim();
		String time = etTime.getText().toString().trim();
		String phone = etPhone.getText().toString().trim();
		String desc = etDesc.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			if(userType==1){//送礼之人
				UiUtil.showToast("请输入送礼之人");
			}else{
				UiUtil.showToast("请输入收礼之人");
			}
			return;
		}
		if(TextUtils.isEmpty(money)){
			UiUtil.showToast("请输入礼金");
			return;
		}
		if(TextUtils.isEmpty(cause)){
			UiUtil.showToast("请输入原因");
			return;
		}
		String uuid = UUID.randomUUID()+"";
		UserInfo userInfo = new UserInfo(uuid, name, money, cause, time, desc, phone, userType, "0");
		if(Account.getInstance().isLogin()){
			RequestParams userInfo2params;
			try {
				userInfo2params = RequestParamsUtil.userInfo2params(userInfo);
			} catch (DesException e) {
				UiUtil.showToast("加密传输失败");
				e.printStackTrace();
				return;
			}
			RequestServerData.addUserInfo(userInfo2params, new RequestCallBack<String>() {
				
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					UiUtil.showToast("添加成功");
				}
				
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					UiUtil.showToast("添加失败");
				}
			});
		}else{
			try {
				FzqApp.mDb.save(userInfo);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_voice_name:
			startVoice();
			break;
		case R.id.bt_voice_money:
			startVoice();
			break;
		case R.id.bt_voice_phone:
			startVoice();
			break;
		case R.id.bt_voice_desc:
			startVoice();
			break;
		default:
			break;
		}
	}

	public void stop(View v) {
		mIat.stopListening();
	}

	public void startVoice() {
		mIatResults.clear();
		mIatDialog.setListener(recognizerDialogListener);
		mIatDialog.show();
	}

	/**
	 * 听写UI监听器
	 */
	private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {
		public void onResult(RecognizerResult results, boolean isLast) {
			printResult(results);
		}

		/**
		 * 识别回调错误.
		 */
		public void onError(SpeechError error) {
			UiUtil.showToast(error.getPlainDescription(true));
		}

	};

	private void printResult(RecognizerResult results) {
		String text = JsonParser.parseIatResult(results.getResultString());

		String sn = null;
		// 读取json结果中的sn字段
		try {
			JSONObject resultJson = new JSONObject(results.getResultString());
			sn = resultJson.optString("sn");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mIatResults.put(sn, text);

		StringBuffer resultBuffer = new StringBuffer();
		for (String key : mIatResults.keySet()) {
			String value = mIatResults.get(key);
			resultBuffer.append(value);
		}
		if (mEditStaus == EditStatus.NAME) {
			etName.setText(resultBuffer.toString());
			etName.setSelection(etName.length());
		} else if (mEditStaus == EditStatus.MONEY) {
			etMoney.setText(resultBuffer.toString());
			etMoney.setSelection(etMoney.length());
		} else if (mEditStaus == EditStatus.PHONE) {
			etPhone.setText(resultBuffer.toString());
			etPhone.setSelection(etPhone.length());
		} else if (mEditStaus == EditStatus.DESC) {
			etDesc.setText(resultBuffer.toString());
			etDesc.setSelection(etDesc.length());
		}
	}
}
