package org.acc.word2vec.demo;

import java.io.FileNotFoundException;
import java.util.List;

import org.acc.word2vec.core.Word2VecUtils;
import org.deeplearning4j.models.word2vec.Word2Vec;

/**
 * Created by zhaoyy on 2016/12/19.
 */
public class RestoreDemo {

    public static void main(String[] args) throws FileNotFoundException {
        Word2Vec word2Vec = Word2VecUtils
                .restore("D:/NLPIR/word2vec/tenbigfileSegment.bin");
        /*double[] ds =  word2Vec.getWordVector("西游记");
        for (int i = 0; i < ds.length; i++) {
        	System.out.print(ds[i]+" ");
		}*/
        System.out.println("______________________");
        List<String> list= word2Vec.similarWordsInVocabTo("西游记", 0.5);
        for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
        System.out.println(word2Vec.wordsNearest("西游记", 10));
        System.out.println(word2Vec.wordsNearest("阅读", 10));
        System.out.println(word2Vec.wordsNearest("读者", 10));
        System.out.println(word2Vec.wordsNearest("杂技", 10));
    }
}
