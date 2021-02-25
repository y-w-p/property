package com.ywp.test;


import com.ywp.dao.UserDao;
import com.ywp.dao.VisitorDao;
import com.ywp.entity.User;
import com.ywp.entity.Visitor;
import com.ywp.services.Impl.UserServiceImpl;
import com.ywp.services.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试类
 */
public class test {


    @Test
    public void test1() throws IOException {

        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");


        //创建SqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //创建SqlSession对象
        SqlSession session = factory.openSession();

        //获取代理对象
        UserDao dao= session.getMapper(UserDao.class);

        //调用方法
        List<User> list = dao.findUserByName("ywp");
        for(User user: list){
            System.out.println(user);
        }

        //关闭资源
        session.close();
        in.close();

    }

    @Test
       public void test2() throws IOException {

           //加载配置文件
           InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");


           //创建SqlSessionFactory工厂对象
           SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

           //创建SqlSession对象
           SqlSession session = factory.openSession();

           //获取代理对象
           VisitorDao dao= session.getMapper(VisitorDao.class);

           //调用方法
        Integer id = dao.visitorRegistered("ywp2", "123456", "13679876262", "赣A12345");

//        List<Visitor> visitors = dao.findVisitorByName("hhh");
//        for(Visitor visitor : visitors){
//            System.out.println(visitor);
//        }

        System.out.println(id);
           //关闭资源
           session.close();
           in.close();

       }




}
