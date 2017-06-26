package com.baizhi;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2017/6/22.
 */
public class TesIndexWriter {
    public static void main(String[] args) throws IOException {
        //1.创建索引
        Directory directory = FSDirectory.open(new File("Lucene/index"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35,analyzer);
        IndexWriter indexWriter = new IndexWriter(directory,config);
        //2.创建Document
        Document document = new Document();
        document.add(new Field("id","1", Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("title","This is a book", Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("author","张三", Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content","一本好书，就是要仔细阅读", Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field("path","E:\\fourth\\finalProject\\Lucene\\src\\main\\java\\com\\baizhi\\This is a book.java", Field.Store.NO, Field.Index.ANALYZED));
        //添加到索引库
        indexWriter.addDocument(document);
        //提交
        indexWriter.commit();
        //关闭资源
        indexWriter.close();

    }
}
