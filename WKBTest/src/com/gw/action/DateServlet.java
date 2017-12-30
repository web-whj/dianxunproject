package com.gw.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

public class DateServlet extends HttpServlet {
	public Map<Integer, Double> map = new  LinkedHashMap<Integer, Double>();
	public Map<Integer, Double> map1 = new LinkedHashMap<Integer, Double>();
	public Map<Integer, Object> map2 = new LinkedHashMap<Integer, Object>();
	public Map<Integer, String> map3 = new LinkedHashMap<Integer, String>();
	public Map<Integer, Integer> map4 = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer> map5 = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer> map6 = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer> map7 = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer> map8 = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer> map9 = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer> map10 = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, String> map11 = new LinkedHashMap<Integer, String>();
	 
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
		/*  File file = new File("e:/test.json");*/
		  String path = getServletContext().getRealPath("/upload");
		  File file = new File(path+"/test.json");
			File file1 = new File(path+"/test1.json");
	        judeFileExists(file);
	        judeFileExists(file1);
	        rReadJSON();
	  
		String method = req.getParameter("method");
		if(method == null || method.equals("")){
			toIndex(req,resp);
		}	 
		else if(method.equals("Info")){
			toXQ(req,resp);
		}
	}
	
	  private void toXQ(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  readDate();
		 getData();
		  req.setAttribute("map11", map11);
		  req.setAttribute("map8", map8); 
		  req.getRequestDispatcher("/WEB-INF/tendency.jsp").forward(req, resp);	
	}

	public void toIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// TODO Auto-generated method stub
			getData();
			getNum();
			readDate();
			readNum();
	      	req.setAttribute("map", map);
	        req.setAttribute("map1", map1);
	        req.setAttribute("map2", map2);
	        req.setAttribute("map3", map3);
	        req.setAttribute("map4", map4);
	        req.setAttribute("map5", map5);
	        req.setAttribute("map6", map6);
	        req.setAttribute("map7", map7);
	        req.setAttribute("map8", map8); 
	        req.setAttribute("map9", map9);
	        req.setAttribute("map10", map10);
	        req.setAttribute("map11", map11);
	      
	        req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	public void kcstats(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  req.getRequestDispatcher("/WEB-INF/tendency.jsp").forward(req, resp);
	}

	public  void getData() {
	        StringBuffer sb = new StringBuffer();
	        // Constructs a string buffer with no characters in it and an initial
	        // capacity of 16 characters.
 
	        try {
	            URL url = new URL("https://x.miguan.in/otc/v7/monitorRecordList?orderBy=turnover");
	            URLConnection conn = url.openConnection();
	            InputStream is = conn.getInputStream();
	            Scanner sc = new Scanner(is, "UTF-8");
	            // String content ="";
	            // StringBuffer sb = new StringBuffer();
	            while (sc.hasNextLine()) {
	                sb.append(sc.nextLine()).append("\r\n");// 把当前行内容存放在StringBuffer
	                                                        // sb里 ,append（）附加）
	                // content+=sc.nextLine();
	                // System.out.println(content+=sc.nextLine());
	            }
	            sc.close();
	            is.close();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // 把数据存放在文件中
	        try {
	            //// Creates a new PrintWriter, without automatic line flushing,
	            //// with the specified file name.
	        	String path = getServletContext().getRealPath("/upload");
	            PrintWriter pw = new PrintWriter(path+"/test.json");
	            pw.println(sb.toString());
	            pw.flush();
	            pw.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        System.out.println("执行成功");
	    }
	  public void getNum() {
	        StringBuffer sb = new StringBuffer();
	        // Constructs a string buffer with no characters in it and an initial
	        // capacity of 16 characters.

	        try {
	            URL url = new URL(" https://x.miguan.in/otc/marketHistoryList");
	            URLConnection conn = url.openConnection();
	            InputStream is = conn.getInputStream();
	            Scanner sc = new Scanner(is, "UTF-8");
	            // String content ="";
	            // StringBuffer sb = new StringBuffer();
	            while (sc.hasNextLine()) {
	                sb.append(sc.nextLine()).append("\r\n");// 把当前行内容存放在StringBuffer
	                                                        // sb里 ,append（）附加）
	                // content+=sc.nextLine();
	                // System.out.println(content+=sc.nextLine());
	            }
	            sc.close();
	            is.close();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // 把数据存放在文件中
	        try {
	            //// Creates a new PrintWriter, without automatic line flushing,
	            //// with the specified file name.
	        	String path = getServletContext().getRealPath("/upload");
	            PrintWriter pw = new PrintWriter(path+"/test1.json");
	            pw.println(sb.toString());
	            pw.flush();
	            pw.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        System.out.println("执行成功");
	    }
	 
	        // 判断文件是否存在
	        public static void judeFileExists(File file) {

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
	        
	        public  void rReadJSON() {
	        	final long timeInterval = 8000;// 两分钟运行一次
	      		Runnable runnable = new Runnable() {
	      			public void run() {
	      				while (true) {
	      					//你要运行的程序
	      					getData();
	      					getNum();
	      					readDate();
	      					readNum();
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
	         //获取数据
	         public void  readDate() {
	        	  try {
	        		  String path = getServletContext().getRealPath("/upload");
	                  JsonParser parser=new JsonParser();  //创建JSON解析器
	                  JsonObject object=(JsonObject) parser.parse(new FileReader(path+"/test.json"));  //创建JsonObject对象
	                 // System.out.println("code="+object.get("code").getAsString()); //将json数据转为为String型的数据
	                //  System.out.println("msg="+object.get("msg").getAsBoolean()); //将json数据转为为boolean型的数据
	                   
	                  JsonArray array=object.get("result").getAsJsonArray();    //得到为json的数组
	                  for(int i=0;i<array.size();i++){
	                      //System.out.println(i+"---------------");
	                      
	                      JsonObject subObject=array.get(i).getAsJsonObject();
	                      
	                      if(subObject.get("buy").isJsonNull()){
	                     	// System.out.println("buy="+subObject.get("buy").getAsJsonNull());
	                     }else{
	                     	// System.out.println("buy="+subObject.get("buy").getAsInt());
	                     }
	                     /* System.out.println("buy="+subObject.get("buy").getAsInt());*/
	                     // System.out.println("change="+subObject.get("change").getAsString());
	                     // System.out.println("cnyPrice="+subObject.get("cnyPrice").getAsDouble());
	                     // System.out.println("createTime="+subObject.get("createTime").getAsInt());
	                     // System.out.println("change="+subObject.get("change").getAsString());
	                     // System.out.println("mark="+subObject.get("mark").getAsString());
	                      if(subObject.get("sell").isJsonNull()){
	                       //	 System.out.println("sell="+subObject.get("sell").getAsJsonNull());
	                       }else{
	                      	// System.out.println("sell="+subObject.get("sell").getAsInt());
	                       }
	                     Object change=0.0;
	                     
	                   //   System.out.println("status="+subObject.get("status").getAsString());
	                      if(subObject.get("change").isJsonNull()){
	                    	  change=subObject.get("change").getAsJsonNull();
	                        	// System.out.println("change="+subObject.get("change").getAsJsonNull());
	                        }else{
	                        	change=subObject.get("change").getAsDouble();
	                      	  //	System.out.println("change="+subObject.get("change").getAsDouble()); 
	                        }
	                     
	                    //  System.out.println("turnover="+subObject.get("turnover").getAsString()); 
	                      
	                      
	                      JsonObject json = subObject.get("dict").getAsJsonObject();
	                   //   System.out.println("code="+json.get("code").getAsString());
	                      if(json.get("name").isJsonNull()){
		                //       	 System.out.println("name="+json.get("name").getAsJsonNull());
		                       }else{
		                  //    	 System.out.println("name="+json.get("name").getAsString());
		                       }
	                      //System.out.println("name="+json.get("name").getAsString());
	                    /*  System.out.println("rate="+json.get("rate").getAsString());
	                      System.out.println("status="+json.get("status").getAsString());
	                      System.out.println("url="+json.get("url").getAsString());
	                      System.out.println("urls="+json.get("urls").getAsString());
	                      */
	                      
	                      
	                      map.put(i, subObject.get("cnyPrice").getAsDouble());
	                      map1.put(i, subObject.get("turnover").getAsDouble());
	                      map3.put(i, json.get("name").getAsString());
	                      map2.put(i, change);
	                      
	                  }
	                
	          		 /*  Set<Integer> set = map3.keySet();
	                                Iterator<Integer> it = set.iterator();
	                                while(it.hasNext()){
	                              	  Integer ie=it.next();
	                              	  //取到键的值
	                              	   System.out.println(ie+"值："+map3.get(ie)); 
	                              	 // System.out.println(i);
	                        } 
	                   */
	              } catch (JsonIOException e) {
	                  e.printStackTrace();
	              } catch (JsonSyntaxException e) {
	                  e.printStackTrace();
	              } catch (FileNotFoundException e) {
	                  e.printStackTrace();
	              }
	          }
	         //获取数据
	         public void  readNum() {
	        	  try {
	        		  String path = getServletContext().getRealPath("/upload");
	                  JsonParser parser=new JsonParser();  //创建JSON解析器
	                  JsonObject object=(JsonObject) parser.parse(new FileReader(path+"/test1.json"));  //创建JsonObject对象
	                /*  System.out.println("code="+object.get("code").getAsString()); //将json数据转为为String型的数据
	                  System.out.println("msg="+object.get("msg").getAsBoolean()); //将json数据转为为boolean型的数据
	                 */  
	                  JsonArray array=object.get("result").getAsJsonArray();    //得到为json的数组
	                  for(int i=0;i<array.size();i++){
	                    //  System.out.println(i+"---------------");
	                      
	                      JsonObject subObject=array.get(i).getAsJsonObject();
	                    /*  System.out.println("averageBandWidth="+subObject.get("averageBandWidth").getAsInt());
	                      System.out.println("averageDisk="+subObject.get("averageDisk").getAsInt());
	                      System.out.println("averageOnlineTime="+subObject.get("averageOnlineTime").getAsInt());
	                      System.out.println("blockNum="+subObject.get("blockNum").getAsInt());
	                      System.out.println("createTime="+subObject.get("createTime").getAsInt());
	                      System.out.println("day="+subObject.get("day").getAsString());
	                      System.out.println("id="+subObject.get("id").getAsInt());
	                      */
	                      Object newPrice=0.0;
	                     
	                      if(subObject.get("newPrice").isJsonNull()){
	                    	  newPrice=subObject.get("newPrice").getAsJsonNull();
	                        	// System.out.println("newPrice="+subObject.get("newPrice").getAsJsonNull());
	                        }else{
	                        	newPrice=subObject.get("newPrice").getAsDouble();
	                      	  //	System.out.println("newPrice="+subObject.get("newPrice").getAsDouble()); 
	                        }
	                     
	                    /*  System.out.println("topBandWidth="+subObject.get("topBandWidth").getAsInt()); 
	                      System.out.println("topDisk="+subObject.get("topDisk").getAsInt()); 
	                      System.out.println("topWkb="+subObject.get("topWkb").getAsInt()); 
	                      
	                      System.out.println("wkbAdd="+subObject.get("wkbAdd").getAsInt()); 
	                      System.out.println("wkbNum="+subObject.get("wkbNum").getAsInt()); 
	                      */
	                      map4.put(i, subObject.get("averageBandWidth").getAsInt());  //人均贷款
	                      map5.put(i, subObject.get("averageDisk").getAsInt()); 
	                      map6.put(i, subObject.get("averageOnlineTime").getAsInt()); 
	                      map7.put(i, subObject.get("blockNum").getAsInt()); 
	                      
	                      map8.put(i, subObject.get("topWkb").getAsInt()); 
	                      map9.put(i, subObject.get("wkbAdd").getAsInt()); 
	                      map10.put(i, subObject.get("wkbNum").getAsInt()); 
	                      map11.put(i, subObject.get("day").getAsString()); 
	                  }
	                
	          		/*   Set<Integer> set = map2.keySet();
	                                Iterator<Integer> it = set.iterator();
	                                while(it.hasNext()){
	                              	  Integer ie=it.next();
	                              	  //取到键的值
	                              	   System.out.println(ie+"值："+map2.get(ie)); 
	                              	 // System.out.println(i);
	                        } */
	                   
	              } catch (JsonIOException e) {
	                  e.printStackTrace();
	              } catch (JsonSyntaxException e) {
	                  e.printStackTrace();
	              } catch (FileNotFoundException e) {
	                  e.printStackTrace();
	              }
	          }
	         
	  public Map<String,Integer> getCalcScore(int dk, int cu,int online){
		  Map<String, Integer> maps = new HashMap<String, Integer>();
		  
		  return maps;
	  }
	          
}
