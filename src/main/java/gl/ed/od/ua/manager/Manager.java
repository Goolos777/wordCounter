package gl.ed.od.ua.manager;

import gl.ed.od.ua.controller.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Manager {

    public static final String CONTROL_REQUEST = "https://habr.com/ru/flows/develop/page";
    private static final int LIMIT_LIST = 100;
    private static final int FIRST_PAGE = 1;
    private static final int LAST_PAGE = 10;


    public static Manager create(){
        return new Manager();
    }

    public void run() {
        AtomicInteger counter = new AtomicInteger(0);
        ResultState resultState = new ResultState(LIMIT_LIST);
        WordCounter wordCounter = new WordCounter();
        ContentReader contentReader = new ContentReader();
        LinkReader linkReader = new LinkReader();
        Consumer<String>handlerContent = (html)->resultState.accept(wordCounter.apply(contentReader.apply(html)));
        TcpClient tcpClientReaderContent = new TcpClient(handlerContent);
        Consumer<String>readerPost = (html)->linkReader.apply(html).forEach(post-> {
            if(counter.incrementAndGet() <= LIMIT_LIST + 1) tcpClientReaderContent.send(post);
        });
        TcpClient tcpClientReaderPost = new TcpClient(readerPost);
        IntStream.range(FIRST_PAGE, LAST_PAGE).forEach(page-> tcpClientReaderPost.send(requestPage(page)));
    }


    private String requestPage(int page){
        return CONTROL_REQUEST + page +"/";
    }


}
