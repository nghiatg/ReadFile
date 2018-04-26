package ThePackage;

public class ForConcurrency {
	public static void getStructureByThread() throws Exception {
		long start = System.nanoTime();
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					WebMining.getStructure("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\page.sql");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				try {
					WebMining.getStructure("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\pagelinks.sql");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();

		long end = System.nanoTime();
		System.out.println("\n\n---------------\n\n");
		System.out.println((end-start)/1000000000/60);
	}
	
	public static void countLineByThread() throws Exception {
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					WebMining.countLine("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\page.sql");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				try {
					WebMining.countLine("C:\\Users\\Mr-Tuy\\Downloads\\wiki_data\\pagelinks.sql");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		t1.start();
		t2.start();
		
	}

}
