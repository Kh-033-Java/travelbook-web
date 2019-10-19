package com.Kh033Java.travelbook.travelbook;

import com.Kh033Java.travelbook.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;


public class UserRepositoryTest {

    private SessionFactory factory;

    @Before
    public void init() {
        Configuration conf = new Configuration.Builder().build();
        factory = new SessionFactory(conf, "com.Kh033Java.travelbook.model");
    }

    @After
    public void destroy() {
        factory.close();
    }

    @Test
    public void embeddedSaveTest() throws Exception {
        Session session = factory.openSession();
        User user = new User("Ivan");
        session.save(user);
    }


}
