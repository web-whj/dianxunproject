package com.gw.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class QKServlet extends HttpServlet {
	public Map<Integer, String> map1 = new LinkedHashMap<Integer, String>();
	public Map<Integer, Double> map2 = new LinkedHashMap<Integer, Double>();
	public Map<Integer, String> map3 = new LinkedHashMap<Integer, String>();
	public Map<Integer, Double> map4 = new LinkedHashMap<Integer, Double>();
	public Map<Integer, Double> map5 = new LinkedHashMap<Integer, Double>();
	public Map<Integer, Double> map6 = new LinkedHashMap<Integer, Double>();
	public Map<Integer, JsonObject> jsonObMap = new LinkedHashMap<Integer, JsonObject>();

	private JsonObject currentArray;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = getServletContext().getRealPath("/upload");
		File file = new File(path+"/qk.json");
		judeFileExists(file);
		rReadJSON();
		String method = req.getParameter("method");
		if (method.equals("list")) {
			list(req, resp);
		} else if (method.equals("top")) {
			readDate();
			top(req, resp);
		}  
	}

	private void rReadJSON() {
		final long timeInterval = 80000;// ����������һ��
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					// ��Ҫ���еĳ���
					response();
					readDate();
					// ------- ends here
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	// ��ȡ����
	public void readDate() {
		try {
			String path = getServletContext().getRealPath("/upload");
			JsonParser parser = new JsonParser(); // ����JSON������
			JsonObject object = (JsonObject) parser.parse(new FileReader(
					path+"/qk.json")); // ����JsonObject����
			JsonArray array = object.get("result").getAsJsonArray(); // �õ�Ϊjson������
			for (int i = 0; i < array.size(); i++) {
				JsonObject subObject = array.get(i).getAsJsonObject();
				map1.put(i, subObject.get("account").getAsString());
				map2.put(i, subObject.get("balance").getAsDouble());
				map3.put(i, subObject.get("mark").getAsString());
				map4.put(i, subObject.get("expend").getAsDouble());
				map5.put(i, subObject.get("income").getAsDouble());
				map6.put(i, subObject.get("tradeNum").getAsDouble());
			}
			/*Set<Integer> set6 = map6.keySet();
			Iterator<Integer> it6 = set6.iterator();
			Integer ie6 = 0;
			while (it6.hasNext()) {
				ie6 = it6.next();
				System.out.println(ie6 + "ֵ��" + map6.get(ie6));
			}*/

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// �ж��ļ��Ƿ����
	private static void judeFileExists(File file) {
		if (file.exists()) {
			System.out.println("file exists");
		} else {
			System.out.println("file not exists, create it ...");
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void response() {
		String line = "";
		String message = "";
		String returnData = "";
		Boolean postState = false;
		BufferedReader bufferedReader = null;
		try {
			URL urlObject = new URL("https://x.miguan.in/otc/rankTopList");
			HttpURLConnection urlConn = (HttpURLConnection) urlObject
					.openConnection();
			urlConn.setDoOutput(true);
			/* �趨���û��� */
			urlConn.setRequestProperty("Pragma:", "no-cache");
			urlConn.setRequestProperty("Cache-Control", "no-cache");
			/* ά�ֳ����� */
			urlConn.setRequestProperty("Connection", "Keep-Alive");
			/* �����ַ��� */
			urlConn.setRequestProperty("Charset", "UTF-8");
			/* �趨�����ʽΪjson */
			urlConn.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			/* ����ʹ��POST�ķ�ʽ���� */
			urlConn.setRequestMethod("POST");
			/* ���ò�ʹ�û��� */
			urlConn.setUseCaches(false);
			/* ����������� */
			urlConn.setDoOutput(true);
			/* ������������ */
			urlConn.setDoInput(true);
			urlConn.connect();
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(
					urlConn.getOutputStream(), "UTF-8");
			outStreamWriter.flush();
			outStreamWriter.close();
			/* ��postʧ�� */
			if ((urlConn.getResponseCode() != 200)) {
				returnData = "{\"jsonStrStatus\":0,\"processResults\":[]}";
				message = "����POSTʧ�ܣ�" + "code=" + urlConn.getResponseCode()
						+ "," + "ʧ����Ϣ��" + urlConn.getResponseMessage();
				// ����BufferedReader����������ȡURL����Ӧ
				InputStream errorStream = urlConn.getErrorStream();
				if (errorStream != null) {
					InputStreamReader inputStreamReader = new InputStreamReader(
							errorStream, "utf-8");
					bufferedReader = new BufferedReader(inputStreamReader);
					while ((line = bufferedReader.readLine()) != null) {
						message += line;
					}
					inputStreamReader.close();
				}
				errorStream.close();
				System.out.println("����ʧ�ܣ�������ϢΪ��" + message);
			} else {
				/* ���ͳɹ����ط��ͳɹ�״̬ */
				postState = true;
				// ����BufferedReader����������ȡURL����Ӧ
				InputStream inputStream = urlConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream, "utf-8");
				bufferedReader = new BufferedReader(inputStreamReader);
				while ((line = bufferedReader.readLine()) != null) {
					message += line;
				}
				returnData = message;
				inputStream.close();
				inputStreamReader.close();
				System.out.println("����POST�ɹ�����������Ϊ��" + message);
				String path = getServletContext().getRealPath("/upload");
				PrintWriter pw = new PrintWriter(path+"/qk.json");
				pw.println(message);
				pw.flush();
				pw.close();
				// �����ݴ�����ļ���
				System.out.println("ִ�гɹ�");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void top(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response();
		readDate();
		req.setAttribute("map1", map1);
		System.out.println("map1��ֵ��--------");
		Set<Integer> set6 = map1.keySet();
		Iterator<Integer> it6 = set6.iterator();
		Integer ie6 = 0;
		while (it6.hasNext()) {
			ie6 = it6.next();
			System.out.println(ie6 + "ֵ��" + map1.get(ie6));
		}
		req.getRequestDispatcher("/WEB-INF/rankinglist.jsp").forward(req, resp);
	}

	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response();
		readDate();
		resp.sendRedirect(req.getContextPath()+"/WEB-INF/query.jsp");
	}

}