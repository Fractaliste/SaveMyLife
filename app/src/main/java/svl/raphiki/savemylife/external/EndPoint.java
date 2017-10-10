package svl.raphiki.savemylife.external;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Raphiki on 10/10/2017.
 */

public class EndPoint {

    private static Map<String, InetAddress> addresses = new ConcurrentHashMap<>();
    private static int nbAddress = 200;

    public static Map<String, InetAddress> getAvailableHosts() throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(nbAddress);

        for (int i = 0; i < nbAddress; i++) {
            threadPool.submit(new InetResolver(addresses, i));
        }

        try {
            boolean ok = threadPool.awaitTermination(5, TimeUnit.SECONDS);

            System.out.println(ok);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return addresses;
    }


    private static class InetResolver implements Runnable {

        private final Map<String, InetAddress> addresses;
        private final int i;

        public InetResolver(Map<String, InetAddress> addresses, int i) {
            this.addresses = addresses;
            this.i = i;
        }

        private static InetAddress getInetAddress(byte i) throws UnknownHostException {
            return InetAddress.getByAddress(new byte[]{(byte) 192, (byte) 168, 0, i});
        }

        public void run() {

            try {
                long b = System.currentTimeMillis();
                InetAddress address = getInetAddress((byte) i);
                long a = 0;
                long a1 = 0;
                //String hostname = address.getHostName();
                long c = System.currentTimeMillis();

                if (address.isReachable(300)) {
                    a = System.currentTimeMillis();
                    addresses.put(String.valueOf(address.getAddress()), address);
                    a1 = System.currentTimeMillis();
                    System.out.println(String.format("Adresse %s / times %d %d %d %d", address, c - b, a - b, a1 - b, a - c));
                }
                portIsOpen(address, 1724, 100);
            } catch (IOException e) {
                System.out.println("IO sur l''adresse " + i);
            }
        }

        private boolean portIsOpen(InetAddress address, int port, int timeout) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(address, port), timeout);
                socket.close();
                return true;
            } catch (Exception e) {
                System.out.println("Exception sur l'adresse " + address + " " + e);
                return false;
            }
        }
    }
}
