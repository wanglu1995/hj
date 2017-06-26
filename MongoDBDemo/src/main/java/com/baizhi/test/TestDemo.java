package com.baizhi.test;

import com.baizhi.entity.Order;
import com.baizhi.entity.User;
import com.mongodb.WriteResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2017/6/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:mongodb.xml")
public class TestDemo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insert(){
        //添加方法
        User user = new User("zhangsan",25,new Date());
        mongoTemplate.save(user);
        System.out.println(user);
    }

    @Test
    public void query(){
        //根据id查找一个
        User user = mongoTemplate.findById("59510381f1efac045a989a49", User.class);
        System.out.println(user);
    }

    @Test
    public void queryAll(){
        //查看所有
        List<User> all = mongoTemplate.findAll(User.class);
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void queryByName(){
        Query query = new Query();
        //添加查询条件
        Criteria criteria = new Criteria("name");
        criteria.is("zhangsan");
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void update(){
        Query q = new Query();
        Criteria c = new Criteria("age");
        c.is(21);
        q.addCriteria(c);
        Update update = new Update();
        update.set("name","lisi");
        WriteResult writeResult = mongoTemplate.updateMulti(q, update, User.class);
        System.out.println(writeResult);
    }

    @Test
    public void queryByPage(){
        Query query = new Query();
        query.skip(0);
        query.limit(1);
        List<User> users = mongoTemplate.find(query,User.class);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryBySort(){
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"age"));
        List<User> users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*关系属性的封装*/
    @Test
    public void save(){
        User user = new User("wangwu",23,new Date());
        Order order = new Order("娃哈哈",2.21);
        Order order1 = new Order("可乐",3.0);
        user.setOrders(Arrays.asList(order,order1));

        //首先保存孩子，后保存父亲
        for (Order order2 : user.getOrders()) {
            mongoTemplate.save(order2);
        }
        mongoTemplate.save(user);
    }

    @Test
    public void queryByChild(){
        Query query = new Query();
        Criteria c = new Criteria("name");
        c.is("wangwu");
        query.addCriteria(c);
        List<User> users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.out.println(user);
            List<Order> orders = user.getOrders();
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

}
