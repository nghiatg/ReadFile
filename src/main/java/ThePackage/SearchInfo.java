package ThePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cedarsoftware.util.io.JsonWriter;

public class SearchInfo {
	public static void change() throws Exception {
		ArrayList<JSONObject> titles = new ArrayList<JSONObject>();
		BufferedReader br  = new BufferedReader(new FileReader("C:\\Users\\Mr-Tuy\\Downloads\\data.txt"));
		String line = br.readLine();
		int count = 1;
		JSONObject obj = null; 
		while(line != null) {
			if(count % 9 == 1) {
				titles.add(new JSONObject());
				obj = titles.get(titles.size()-1);
				
			}
			obj.put("Origin", "Nhom 4");
			if(count % 9 == 2) {
				obj.put("Url",line);
			}
			if(count % 9 == 3) {
				obj.put("Time",line.substring(line.indexOf(":")+2));
			}
			if(count % 9 == 4) {
				obj.put("Author",line.substring(line.indexOf(":")+2));
			}
			if(count % 9 == 5) {
				obj.put("Title",line.substring(line.indexOf(":")+2));
			}
			
			if(count % 9 == 6) {
				obj.put("Content",line.substring(line.indexOf(":")+2));
			}
			line = br.readLine();
			count++;
		}
//		for(JSONObject o : titles) {
//			System.out.println(JsonWriter.formatJson(o.toString()));
//		}
		br.close();
		JSONArray objs = new JSONArray(titles);
		PrintWriter pr = new PrintWriter("E:\\study\\text\\data.json");
		pr.println(JsonWriter.formatJson(objs.toString()));
		pr.close();
//		System.out.println(JsonWriter.formatJson(objs.toString()));
		
	}

}
