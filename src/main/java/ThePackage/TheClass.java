package ThePackage;

public class TheClass {
	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();
		SearchInfo.change();
//		String s1 = "C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\page.sql";
//		String s2 = "C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\2017\\pagelinks.sql";
//		WebMining.lol();
		long end = System.nanoTime();
		System.out.println((end - start)/1000000000/60 + "\tminutes");
		
		
	}
}
