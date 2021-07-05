package gl.ed.od.ua.controller;

import gl.ed.od.ua.model.TitleContent;
import org.junit.Test;

public class ContentReaderTest {

    @Test
    public void simpleRequest() {
        ContentReader contentReader = new ContentReader();
        TcpClient tcpClient = new TcpClient((val) -> {
            TitleContent apply = contentReader.apply(val);
            System.out.println(apply);
        });
        tcpClient.send("https://habr.com/ru/post/564966/");
    }
}