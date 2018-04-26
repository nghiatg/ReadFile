package ThePackage;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Utils {
	public static ArrayList<String> getDataFromInsert(String line) throws Exception{
		ArrayList<String> rs = new ArrayList<String>();
		ArrayList<String> elements = null;
		StringBuilder sb = new StringBuilder();
		String subLine = line.substring(line.indexOf('(') - 1,line.length() - 2);
//		System.out.println(subLine);
		for(String oneRow : fastSplitStandard(subLine , "),(")) {
//			if(oneRow.contains("10236)_1998_QA93")) {
//				System.out.println(oneRow);
//				break;
//			}
			elements = fastSplit(oneRow.substring(2), "," ,4);
			sb.append(elements.get(0)).append(",").append(elements.get(1)).append("---").append((elements.get(2)));
			rs.add(sb.toString());
			sb.setLength(0);
		}
		
		return rs;
	}
	
	
	public static void fastSplitStandardWithPrint(String line,String sepa,PrintWriter pr) throws Exception {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> elements = null;
		String temp = line;
		int index = temp.indexOf(sepa);
		int count = 0;
		while(temp.contains(sepa)) {
			count++;
			try { 
//				rs.add(temp.substring(0, index));
				elements = fastSplit(temp.substring(2, index),",",4);
				sb.append(elements.get(0)).append("   ").append(elements.get(1)).append("---").append(elements.get(2));
				pr.println(sb.toString());
//				System.out.println(sb.toString());
				
				if(count % 5000 == 0) {
					pr.flush();
				}
				sb.setLength(0);
				temp = temp.substring(index + 1);
				index = temp.indexOf(sepa);
			}catch(Exception e) {}
		}
		pr.flush();
	}
	
	
	
	public static ArrayList<String> fastSplit(String line,String sepa,int limit) {
		ArrayList<String> rs = new ArrayList<String>();
		String temp = line;
		int index = temp.indexOf(sepa);
		while(temp.contains(sepa) && rs.size() < limit) {
//			try {
				rs.add(temp.substring(0, index));
				temp = temp.substring(index + 1);
				index = temp.indexOf(sepa);
//			}catch(Exception e) {}
		}
		return rs;
	}

	public static ArrayList<String> fastSplitStandard(String line,String sepa) {
		ArrayList<String> rs = new ArrayList<String>();
		String temp = line;
		int index = temp.indexOf(sepa);
		while(temp.contains(sepa)) {
//			try { 
				rs.add(temp.substring(0, index));
				temp = temp.substring(index + 1);
				index = temp.indexOf(sepa);
//			}catch(Exception e) {}
		}
		rs.add(temp);
		return rs;
	}
	
	public static String filter(String title) {
		int i = 0;
		int j = title.length() - 1;
		try {
			while(!Character.isLetterOrDigit(title.charAt(i))) {
				++i;
			}
			while(!Character.isLetterOrDigit(title.charAt(j))) {
				--j;
			}
			return title.substring(i, j+1);
		}catch(Exception e) {
			return title;
		}
	}
}
