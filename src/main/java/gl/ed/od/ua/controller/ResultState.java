package gl.ed.od.ua.controller;

import gl.ed.od.ua.model.TitleCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class ResultState implements Consumer<TitleCount> {

    private static int limit;
    private final List<TitleCount> titleCountList = new CopyOnWriteArrayList<>();

    Logger logger = LoggerFactory.getLogger(ResultState.class);


    public ResultState(int limit) {
        this.limit = limit;
    }

    @Override
    public void accept(TitleCount titleCount) {
        logger.info("add title counter: " + titleCountList.size());
        titleCountList.add(titleCount);
        if(limit < titleCountList.size()){
            titleCountList
                    .stream()
                    .limit(limit)
                    .forEach(currentTitleCount-> System.out.println(currentTitleCount.getTitle()+"=>"+currentTitleCount.getCount()));
        }

    }
}
