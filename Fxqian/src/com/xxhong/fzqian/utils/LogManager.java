package com.xxhong.fzqian.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;


/**
 * 这个是发送日志和保存日志的管理着
 * 
 * @author long
 * 
 */
@SuppressWarnings("deprecation")
public class LogManager {



	private static LogManager logManager;


	public static LogManager getInstance() {
		if (logManager == null) {
			synchronized (LogManager.class) {
				if (logManager == null) {
					logManager = new LogManager();
				}
			}
		}

		return logManager;
	}




	/**
	 * 保存日志
	 * 
	 * @param ex
	 */
	@SuppressLint("SimpleDateFormat")
	public void saveLog(Throwable ex, Context context) {
		// Date date = new Date();

		File dir = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			String packageName = context.getPackageName();
			dir = new File(Environment.getExternalStorageDirectory().getPath()
					+ "/"+packageName);
		} else {
			dir = new File(Environment.getDataDirectory().getPath()
					+ "/xxhong");
		}

		try {
			if (!dir.exists())
				dir.mkdirs();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String str = sw.toString();

			final StringBuffer sb = new StringBuffer();
			sb.append("报错时间:"
					+ new Date(System.currentTimeMillis()).toLocaleString()
					+ "\n");
			sb.append("crashTime:" + System.currentTimeMillis() + "\n\n");
			// 错误日志
			sb.append("\n\n");
			sb.append(str);
			sb.append("\n\n");
			// 把下次要发送的日志先保存
			File sendLog = new File(dir, "sendLog.txt");
			FileOutputStream sendFos = new FileOutputStream(sendLog, true);
			sendFos.write(sb.toString().getBytes());
			sendFos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 保存日志
	 * 
	 * @param ex
	 */
	@SuppressLint("SimpleDateFormat")
	public void saveSDcardLog(Context context, String log) {
		Date date = new Date();
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dayStr = dayFormat.format(date);

		File dir = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			dir = new File(Environment.getExternalStorageDirectory().getPath()
					+ "/pushemail");
		} else {
			dir = new File(Environment.getDataDirectory().getPath()
					+ "/pushemail");
		}

		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, dayStr + ".txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			StringBuffer sb = new StringBuffer();

			// 错误日志
			sb.append("\n\n");
			sb.append(log);
			sb.append("\n\n");

			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write(sb.toString().getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
