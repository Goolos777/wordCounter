package gl.ed.od.ua.controller;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static org.asynchttpclient.Dsl.asyncHttpClient;

public class TcpClient {

    private static final int LIMIT_THREAD = 2;
    private final Consumer<String> handler;
    private final AsyncHttpClient asyncHttpClient;
    public final ExecutorService executorService;

    public TcpClient(Consumer<String> handler) {
        this.handler = handler;
        asyncHttpClient = asyncHttpClient();
        executorService = Executors.newFixedThreadPool(LIMIT_THREAD);
    }

    public void send(String request) {
        asyncHttpClient
                .prepareGet(request)
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .thenAccept(handler)
                .join();
    }

}
