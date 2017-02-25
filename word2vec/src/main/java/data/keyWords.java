package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tfidf.src.org.akgul.TfIdf;

public class keyWords {
	private static List<String> allDocuments = new ArrayList<String>();
	private static BufferedWriter bufw ;
	static{
		try {
			bufw = new BufferedWriter(new FileWriter(new File("C:/D/NLPIR/paper/files/idf.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		List<String> dataPaths = new ArrayList<String>();
		dataPaths.add("C:/D/NLPIR/paper/files/test/Normalize/seg/");
		dataPaths.add("C:/D/NLPIR/paper/files/train/Normalize/seg/");
		getAllDocuments(dataPaths);
		System.out.println("getAllDocuments...");
		saveDocs();
		System.out.println("saveDocs...");
		getIDFSave();
		System.out.println("getIDFSave()...");
	}
	private static void saveDocs() throws IOException {
		BufferedWriter bu = new BufferedWriter(new FileWriter(new File("C:/D/NLPIR/paper/files/doc.txt")));
		Iterator iter = allDocuments.iterator();
		while(iter.hasNext()){
			bu.write((String)iter.next());
			bu.newLine();
			bu.flush();
		}
	}
	/**
	 * 含有map按照value排序：http://www.cnblogs.com/hxsyl/p/3331095.html
	 * @throws IOException
	 */
	private static void getIDFSave() throws IOException {
		TfIdf tfIdf = new TfIdf(allDocuments);
		Map<String, Double> map = tfIdf.idf();
		Set<String> keyWords = map.keySet();
		Iterator iter = keyWords.iterator();
		
//		按照value大小排序
		List<Map.Entry<String,Double>> list = new ArrayList<Map.Entry<String,Double>>(map.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<String,Double>>() {
            //升序排序
            public int compare(Entry<String, Double> o1,
                    Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
            
        });
		for(Map.Entry<String,Double> mapping:list){ 
            bufw.write(mapping.getKey()+":"+mapping.getValue());
			bufw.newLine();
			bufw.flush();
       } 
		
//		不按照value大小排序
//		String key = null;
//		double score = 0;
//		while(iter.hasNext()){
//			key = (String) iter.next();
//			score = map.get(key);
//			System.out.println(key + "\t" + score);
//			bufw.write(key + "\t" + score);
//			bufw.newLine();
//			bufw.flush();
//		}
	}
	private static void getAllDocuments(List<String> dataPaths) throws Exception {
		String line = null;
		int count =0;
		for (String dataPath : dataPaths) {
			File file = new File(dataPath);
			File[] files = file.listFiles();
			for (File f : files) {
				if(!f.isDirectory()){
					BufferedReader bufr = new BufferedReader(new FileReader(f));
					while((line=bufr.readLine())!=null){
						allDocuments.add(line);
						count++;
					}
				}
			}
		}
		System.out.println("count is : " + count);
		System.out.println("allDocuments is prepared,size is : " + allDocuments.size());
	}

}
