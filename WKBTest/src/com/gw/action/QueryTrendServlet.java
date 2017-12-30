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

public class QueryTrendServlet extends HttpServlet {
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
		File file = new File(path+"/qk1.json");
		judeFileExists(file);
		rReadJSON(req, resp);
		String method = req.getParameter("method");
		if (method.equals("queryDe")) {
			queryDe(req, resp);
		}
	}

	private void rReadJSON(final HttpServletRequest req,
			final HttpServletResponse resp) {
		final long timeInterval = 80000;// ����������һ��
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					// ��Ҫ���еĳ���
					response(req, resp);
					try {
						queryDe(req, resp);
					} catch (ServletException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
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
	// �ж��ļ��Ƿ����
	private void judeFileExists(File file) {
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

	public void response(HttpServletRequest req, HttpServletResponse resp) {
		String line = "";
		String message = "";
		String returnData = "";
		Boolean postState = false;
		BufferedReader bufferedReader = null;
		try {
			URL urlObject = new URL(
					"https://x.miguan.in/otc/queryTrade?address="
							+ req.getParameter("address"));
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
				PrintWriter pw = new PrintWriter(path+"/qk1.json");
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

	private void queryDe(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*response(req, resp);*/
		
		System.out.println("ִ�е��˲�ѯĳ��address����");
		String queryOne = req.getParameter("address");
		try {
			String path = getServletContext().getRealPath("/upload");
			JsonParser parser = new JsonParser(); // ����JSON������
		 
			JsonObject object = (JsonObject) parser.parse(new FileReader(
					path+"/qk1.json")); // ����JsonObject����
			
			System.out.println("code=" + object.get("code").getAsString());
			// //��json����תΪΪString�͵�����
			System.out.println("msg=" + object.get("msg").getAsString());
			// //��json����תΪΪboolean�͵�����
			System.out.println("success=" + object.get("success").getAsString());
			System.out.println("result="+ object.get("result").getAsJsonObject());
			
			
			JsonObject JsonObject = object.get("result").getAsJsonObject(); // �õ�Ϊjson������
			JsonParser parser1 = new JsonParser(); // ����JSON������
			JsonObject object1 = (JsonObject) parser.parse(JsonObject.toString());
			System.out.println("account="+ object1.get("account").getAsJsonObject());
			
			
			JsonObject jsonObject2 = object1.get("account").getAsJsonObject();
			JsonParser parser2 = new JsonParser(); // ����JSON������
			JsonObject object2 = (JsonObject) parser.parse(jsonObject2.toString());
			System.out.println(object2.get("account").getAsString());
			System.out.println(object2.get("income").getAsString());
			System.out.println("balance"+object2.get("balance").getAsString());
			System.out.println("expend"+object2.get("expend").getAsString());
			System.out.println("income"+object2.get("income").getAsString());
			System.out.println("mark"+object2.get("mark").getAsString());
			System.out.println("tradeNum"+object2.get("tradeNum").getAsString());
			String account = object2.get("account").getAsString();
			req.setAttribute("account",account);
			req.setAttribute("tradeNum",object2.get("tradeNum").getAsString());
			
			JsonArray array = object1.get("recordList").getAsJsonArray();
			for (int i = 0; i < array.size(); i++) {

				JsonObject subObject = array.get(i).getAsJsonObject();
				System.out.println("amount="+ subObject.get("amount").getAsString());
				System.out.println("cost"+subObject.get("cost").getAsString());
				
				req.setAttribute("hash", subObject.get("hash").getAsString());
				
				System.out.println("hash"+subObject.get("hash").getAsString());
				System.out.println("id"+subObject.get("id").getAsString());
				System.out.println("mainAccount"+subObject.get("mainAccount").getAsString());
				
				req.setAttribute("mainAccount", subObject.get("mainAccount").getAsString());
				
				System.out.println("tradeAccount"+subObject.get("tradeAccount").getAsString());
				System.out.println("tradeTime"+subObject.get("tradeTime").getAsString());
				System.out.println("type"+subObject.get("type").getAsString());
			}
			System.out.println(object1.get("pages").getAsInt());
			System.out.println(object1.get("total").getAsInt());
			
			
			
			/*
			 * Set<Integer> set6 = map6.keySet(); Iterator<Integer> it6 =
			 * set6.iterator(); Integer ie6 = 0; while (it6.hasNext()) { ie6 =
			 * it6.next(); System.out.println(ie6 + "ֵ��" + map6.get(ie6)); }
			 */

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/WEB-INF/query.jsp").forward(req, resp);
	}
}