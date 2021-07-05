package gl.ed.od.ua.controller;

import gl.ed.od.ua.model.TitleContent;
import gl.ed.od.ua.model.TitleCount;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordCounterTest {

    @Test
    public void simpleRequest() {
        ContentReader contentReader = new ContentReader();
        WordCounter wordCounter = new WordCounter();
        TcpClient tcpClient = new TcpClient((val) -> {
            TitleContent titleContent = contentReader.apply(val);
            TitleCount titleCount = wordCounter.apply(titleContent);
            System.out.println(titleCount);
        });
        tcpClient.send("https://habr.com/ru/post/564966/");
    }
}