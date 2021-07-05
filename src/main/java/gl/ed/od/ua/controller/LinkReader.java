package gl.ed.od.ua.controller;

import org.jsoup.Jsoup;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LinkReader implements Function<String, List<String>> {

    private static final String SELECT_CONTENT = "a[href*=/habr.com/ru/post/]";
    private static final String ATTR = "href";
    private static final Predicate<? super String> CONTROL = (val)-> val.charAt(val.length()-1)=='/';

    @Override
    public List<String> apply(String htmlContent) {
        return readTitle(htmlContent);
    }

    private static List<String> readTitle(String content) {
        return Jsoup.parse(content)
                .select(SELECT_CONTENT)
                .stream()
                .map(el -> el.attr(ATTR))
                .filter(CONTROL)
                .collect(Collectors.toList());
    }
}
