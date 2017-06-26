package com.baizhi;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2017/6/22.
 */
public class TestIndeCRUD {
    public static void main(String[] args) {

    }

    /**
     * 删除索引
     */
    public static void deleteindex() throws IOException {
        //1.指定目录
        Directory directory = FSDirectory.open(new File("Lucene/index"));
        //2.建立索引库
        IndexReader indexReader = IndexReader.open(directory);
        Term term = new Term("id","1");
        indexReader.deleteDocuments(term);
        //关闭资源
        indexReader.close();
    }
    /**
     * 恢复删除的索引
     */
    public static void undeleteIndex() throws IOException {
        //1.指定目录
        Directory directory = FSDirectory.open(new File("Lucene/index"));
        //2.读取索引库
        IndexReader indexReader = IndexReader.open(directory);
        //3.恢复
        indexReader.undeleteAll();
        //4.关闭资源
        indexReader.close();
    }
    /**
     * 更新索引
     */
    public static void updateIndex(){

    }
    /**
     * 查看文档的信息
     */
    public static void queryInfo(){

    }
}
