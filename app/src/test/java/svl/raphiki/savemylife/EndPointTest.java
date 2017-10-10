package svl.raphiki.savemylife;

import org.junit.Test;

import java.net.InetAddress;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static svl.raphiki.savemylife.external.EndPoint.getAvailableHosts;

public class EndPointTest {

    @Test
    public void addition_isCorrect() throws Exception {

        final Map<String, InetAddress> availableHosts = getAvailableHosts();
        assertEquals(availableHosts.toString(), 30, availableHosts.size());
    }
}