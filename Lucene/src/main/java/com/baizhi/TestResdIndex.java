package com.baizhi;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2017/6/22.
 */
public class TestResdIndex {
    public static void main(String[] args) throws IOException {
        //1.指定目录
        Directory directory = FSDirectory.open(new File("Lucene/index"));
        //2.获取索引
        IndexReader indexReader = IndexReader.open(directory);
        //3.创建IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //4.创建Query
        TermQuery termQuery = new TermQuery(new Term("author", "张三"));
        //5.查询结果
        TopDocs topDocs = indexSearcher.search(termQuery, Integer.MAX_VALUE);
        int totalHits = topDocs.totalHits;//实际返回的文档数目
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;//获取相似度分数的集合
        for (ScoreDoc s:scoreDocs) {
            float score = s.score;
            int doc = s.doc;//这是文档的id
            Document document = indexSearcher.doc(doc);
            //然后获取document中的信息Fileds
            String id = document.get("id");


        }
        //关闭资源
        indexReader.close();
        indexSearcher.close();
    }
}
