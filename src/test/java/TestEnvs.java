import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestEnvs {

    @Test
    public void testSettingEnvs() {
        boolean b = System.getenv().containsKey("GOOGLE_APPLICATION_CREDENTIALS") ||
                System.getProperties().containsKey("GOOGLE_APPLICATION_CREDENTIALS");
        Assert.assertTrue(b);
    }
}
