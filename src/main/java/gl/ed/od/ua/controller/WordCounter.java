package gl.ed.od.ua.controller;

import gl.ed.od.ua.model.TitleContent;
import gl.ed.od.ua.model.TitleCount;

import java.util.function.Function;
import java.util.stream.Stream;

public class WordCounter implements Function<TitleContent, TitleCount> {

    @Override
    public TitleCount apply(TitleContent titleContent) {
        String title = titleContent.getTitle();
        String content = titleContent.getContent();
        long count = Stream.of(content)
                .flatMap(line -> Stream.of(line.split("\n")))
                .flatMap(line -> Stream.of(line.split(" ")))
                .count();
        return new TitleCount(title, count);
    }


}
