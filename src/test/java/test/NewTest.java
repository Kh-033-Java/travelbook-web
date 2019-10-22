package test;

import com.Kh033Java.travelbook.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;

public class NewTest {

    private SessionFactory factory;

    @Before
    public void init() {
        Configuration conf = new Configuration.Builder()
                .uri("bolt://localhost:7687")
                .build();
        factory = new SessionFactory(conf, "dev.model");
    }

    @After
    public void destroy() {
        factory.close();
    }

    @Test
    public void embeddedSaveTest() {
        org.neo4j.ogm.session.Session session = factory.openSession();
        User user = new User("djuba","test");
        session.delete(user);
    }
}
