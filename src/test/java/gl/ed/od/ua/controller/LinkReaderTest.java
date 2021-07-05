package gl.ed.od.ua.controller;

import org.junit.Test;

import java.util.List;

public class LinkReaderTest {


    @Test
    public void simpleRequest() {
        LinkReader linkReader = new LinkReader();
        TcpClient tcpClient = new TcpClient((val) -> {
            List<String> apply = linkReader.apply(val);
            apply.stream().forEach(ref -> System.out.println(ref));
        });
        tcpClient.send("https://habr.com/ru/flows/develop/page1/");
    }
}