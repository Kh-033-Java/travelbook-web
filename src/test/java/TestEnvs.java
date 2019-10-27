import org.junit.Assert;
import org.junit.Test;

public class TestEnvs {

    @Test
    public void testSettingEnvs() {
        boolean b = System.getenv().containsKey("GOOGLE_APPLICATION_CREDENTIALS") ||
                System.getProperties().containsKey("GOOGLE_APPLICATION_CREDENTIALS");
        Assert.assertTrue(b);
    }
}
