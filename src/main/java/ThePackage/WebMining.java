package ThePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class WebMining {
	public static void countLine(String file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		int count = 0;
		while(line != null) {
			count ++;
			line = br.readLine();
//			if(count % 50000 == 0) {
//				System.out.println(count);
//			}
		}
		System.out.println(count);
		br.close();
	}
	
	public static void getStructure(String file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		StringBuilder sb = new StringBuilder();
		int mode = 0;
		while(line != null) {
			if(line.toLowerCase().contains("create table")) {
				mode = 1;
			}
			if(mode == 1) {
				sb.append(line).append("\n");
			}
			if(mode == 1 && line.length() == 0) {
				mode = 0;
				sb.append("\n\n");
				break;
			}
			line = br.readLine();
		}
		sb.append("\n\n---\n\n");
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void idk(String file) throws Exception {
		PrintWriter pr = new PrintWriter("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\pages.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while(line != null) {
			if(line.toLowerCase().contains("insert into")) {
//				System.out.println(line.substring(0,500));
//				break;
				
				
				for(String s : Utils.getDataFromInsert(line)) {
//					System.out.println(s);
					pr.println(s);
				}
				pr.flush();
			}
			line = br.readLine();
		}
		br.close();
		pr.close();
	}
	
	public static void idk2(String file) throws Exception {
		PrintWriter pr = new PrintWriter("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\pagelinks.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		String subLine;
		while(line != null) {
			if(line.toLowerCase().contains("insert into")) {
//				System.out.println(line.substring(0,500));
//				break;
				
				
				subLine = line.substring(line.indexOf('(') - 1,line.length() - 2);
				Utils.fastSplitStandardWithPrint(subLine, "),(",pr);
			}
			line = br.readLine();
		}
		br.close();
		pr.close();
	}
	
	public static HashMap<String,String> storeToMap(String file) throws Exception {
		HashMap<String,String> pages = new HashMap<String,String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		int index;
		int count = 0;
		while(line != null) {
			count++;
			index = line.indexOf(',');
			if(count < 3500000) {
				pages.put(line.substring(0, index),line.substring(index + 1));
			}else {
				break;
			}
			line = br.readLine();
			
		}
		br.close();
		return pages;
	}
	
	public static void toOneFormat() throws Exception { 
		HashMap<String,String> pages = storeToMap("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\pages.txt");
		System.out.println("Size : " + pages.size() + "\t ---> Done map");
		PrintWriter pr = new PrintWriter("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\links2.txt");
//		PrintWriter pr2 = new PrintWriter("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\pagelinks_left.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\pagelinks_left.txt"));
		String line = br.readLine();
		int index;
		int count = 0;
		while(line != null) {
			index = line.indexOf("   ");
			if(pages.containsKey(line.substring(index+3))) {
				pr.println(line.substring(0, index) + "\t" + pages.get(line.substring(index + 3)));
			}else {
//				pr2.println(line);
			}
			if(count % 5000 == 0 ) {
				if(count % 150000 == 0) {
					System.out.println(count);
				}
				pr.flush();
//				pr2.flush();
			}
			count++;
			line = br.readLine();
		}
//		pr2.close();
		pr.close();
		br.close();
	}
	
	public static void lol() throws Exception {
		HashMap<String,String> pages = storeToMap("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\pages.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Mr-Tuy\\Downloads\\sort_rank.txt"));
		String line = br.readLine();
		while(line != null) {
			if(pages.containsKey(line.split("\t")[1])) {
				System.out.println(line.split("\t")[0] + "\t" + pages.get(line.split("\t")[1]));
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public static void concat() throws Exception  {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\links.txt"));
		PrintWriter pr = new PrintWriter("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\allLinks.txt");
		String line = br.readLine();
		int count = 1;
		while(line != null) {
			pr.println(line);
			line = br.readLine();
			count++;
			if(count % 15000 == 0) {
				pr.flush();
			}
		}
		pr.flush();
		br.close();
		
		br = new BufferedReader(new FileReader("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\links2.txt"));
		line = br.readLine();
		count = 1;
		while(line != null) {
			pr.println(line);
			line = br.readLine();
			count++;
			if(count % 15000 == 0) {
				pr.flush();
			}
		}
		pr.flush();
		br.close();
		pr.close();
	}
	
	public static void insert() throws Exception {
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String user = "root";
		String password = "";

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(url, user, password);
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\allLinks.txt"));
		String line = br.readLine();
		int count = 0;
		StringBuilder sb = new StringBuilder();
		String initiate = "insert into link_indx values ";
		sb.append(initiate);
		while(line != null) {
			sb.append(createRow(line));
			if(count < 1000) {
				sb.append(",");
			}
			if(count == 1000) {
				try {
					conn.createStatement().executeUpdate(sb.toString());
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.out.println(sb.toString());
					break;
				}
				count = 0;
				sb.setLength(initiate.length());
			}
			line = br.readLine();
			count++;
			
		}
		br.close();
		
	}
	public static String createRow(String raw) {
		int index = raw.indexOf("\t");
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(raw.substring(0, index)).append(",").append(raw.substring(index + 1)).append(")");
		return sb.toString();
		
	}
	
	public static void idk() throws Exception {
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String user = "root";
		String password = "";

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(url, user, password);
		PrintWriter pr = new PrintWriter("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\withns\\link_db.txt");

		ArrayList<Integer> from = new ArrayList<Integer>();
		ArrayList<Integer> to = new ArrayList<Integer>();
		ArrayList<Integer> store = new ArrayList<Integer>();
		
		int start = 79681;
		from.add(start);
		int loop = 1;
		ResultSet rs;
		while(loop  < 4) {
			System.out.println(from.size());
			for(int i : from) {
				System.out.println(i);
				rs = conn.createStatement().executeQuery("select * from link_indx where fromid = " + i);
				while(rs.next()) {
					if(!to.contains(rs.getInt(2)) && !store.contains(rs.getInt(2))) {
						to.add(rs.getInt(2));
					}
					pr.println(i + " \t" + rs.getInt(2));
				}
			}
			pr.flush();
			store.addAll(from);
			from = to;
			to = new ArrayList<Integer>();
			System.out.println("End loop : " + loop + "\n\n");
			loop++;
		}
		pr.close();
		conn.close();
		
	}
	
	
}
