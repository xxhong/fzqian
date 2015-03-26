package com.xxhong.fzqian.activity;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.xxhong.fzqian.R;
import com.xxhong.fzqian.utils.JsonParser;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFzqActivity extends BaseActivity implements OnClickListener {
	// 用HashMap存储听写结果
	private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
	private Button btVoice;
	private EditText etName;
	// 语音听写对象
	private SpeechRecognizer mIat;
	// 语音听写UI
	private RecognizerDialog mIatDialog;

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
		// 初始化识别对象
		mIat = SpeechRecognizer.createRecognizer(this, mInitListener);
		// 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
		mIatDialog = new RecognizerDialog(this, mInitListener);
		mIat.setParameter(SpeechConstant.ASR_PTT, "0");
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

	private void initView() {
		btVoice = (Button) this.findViewById(R.id.bt_voice);
		etName = (EditText) this.findViewById(R.id.et_name);
	}

	private void initLis() {
		btVoice.setOnClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.okbtn, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_voice:

			break;

		default:
			break;
		}
	}

	public void stop(View v) {
		mIat.stopListening();
	}

	public void start(View v) {
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
			// showTip(error.getPlainDescription(true));
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

		etName.setText(resultBuffer.toString());
		etName.setSelection(etName.length());
	}
}
