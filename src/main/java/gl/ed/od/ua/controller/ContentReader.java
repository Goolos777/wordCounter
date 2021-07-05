package gl.ed.od.ua.controller;

import gl.ed.od.ua.model.TitleContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.function.Function;

public class ContentReader implements Function<String, TitleContent> {


    private static final String SELECT_CONTENT = "post__body post__body_full";

    @Override
    public TitleContent apply(String html) {
        return handler(html);
    }

    private static TitleContent handler(String data) {
        Document doc = Jsoup.parse(data);
        String title = doc.title();
        Elements elementsByClass = doc.getElementsByClass(SELECT_CONTENT);
        return new TitleContent(title, elementsByClass.text());
    }
}
