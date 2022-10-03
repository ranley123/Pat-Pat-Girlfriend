package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static HttpServer server = null;
    private static final int PORT = 8080;
    private static final String CONTEXT = "/";
    private static final int N_THREADS = 8;

    public static void main (String[] args) {
        try {
            server = HttpsServer.create(new InetSocketAddress(PORT), 0);
            server.createContext(CONTEXT, new Handler());

            ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
            server.setExecutor(executor);
            server.start();
            System.out.println("启动端口:" + PORT);
            System.out.println("根节点:" + CONTEXT);
            System.out.println("并发数:" + N_THREADS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
