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
		final long timeInterval = 80000;// 两分钟运行一次
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					// 你要运行的程序
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

	// 获取数据
	public void readDate() {
		try {
			String path = getServletContext().getRealPath("/upload");
			JsonParser parser = new JsonParser(); // 创建JSON解析器
			JsonObject object = (JsonObject) parser.parse(new FileReader(
					path+"/qk.json")); // 创建JsonObject对象
			JsonArray array = object.get("result").getAsJsonArray(); // 得到为json的数组
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
				System.out.println(ie6 + "值：" + map6.get(ie6));
			}*/

		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 判断文件是否存在
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
			/* 设定禁用缓存 */
			urlConn.setRequestProperty("Pragma:", "no-cache");
			urlConn.setRequestProperty("Cache-Control", "no-cache");
			/* 维持长连接 */
			urlConn.setRequestProperty("Connection", "Keep-Alive");
			/* 设置字符集 */
			urlConn.setRequestProperty("Charset", "UTF-8");
			/* 设定输出格式为json */
			urlConn.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			/* 设置使用POST的方式发送 */
			urlConn.setRequestMethod("POST");
			/* 设置不使用缓存 */
			urlConn.setUseCaches(false);
			/* 设置容许输出 */
			urlConn.setDoOutput(true);
			/* 设置容许输入 */
			urlConn.setDoInput(true);
			urlConn.connect();
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(
					urlConn.getOutputStream(), "UTF-8");
			outStreamWriter.flush();
			outStreamWriter.close();
			/* 若post失败 */
			if ((urlConn.getResponseCode() != 200)) {
				returnData = "{\"jsonStrStatus\":0,\"processResults\":[]}";
				message = "发送POST失败！" + "code=" + urlConn.getResponseCode()
						+ "," + "失败消息：" + urlConn.getResponseMessage();
				// 定义BufferedReader输入流来读取URL的响应
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
				System.out.println("发送失败！错误信息为：" + message);
			} else {
				/* 发送成功返回发送成功状态 */
				postState = true;
				// 定义BufferedReader输入流来读取URL的响应
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
				System.out.println("发送POST成功！返回内容为：" + message);
				String path = getServletContext().getRealPath("/upload");
				PrintWriter pw = new PrintWriter(path+"/qk.json");
				pw.println(message);
				pw.flush();
				pw.close();
				// 把数据存放在文件中
				System.out.println("执行成功");
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
		System.out.println("map1的值：--------");
		Set<Integer> set6 = map1.keySet();
		Iterator<Integer> it6 = set6.iterator();
		Integer ie6 = 0;
		while (it6.hasNext()) {
			ie6 = it6.next();
			System.out.println(ie6 + "值：" + map1.get(ie6));
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