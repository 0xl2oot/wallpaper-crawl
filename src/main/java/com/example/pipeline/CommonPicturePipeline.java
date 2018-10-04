package com.example.pipeline;

import com.example.util.ImageDownloadUtils;

import org.apache.log4j.Logger;

import java.util.List;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Bing 图片 Pipeline
 *
 * @author 0xl2oot@gmail.com
 * @date 2018/10/3 7:28 PM
 */
public class CommonPicturePipeline implements Pipeline {

    private Logger logger = Logger.getLogger(this.getClass());

    public void process(ResultItems resultItems, Task task) {

        logger.info("【下载图片 from】" + resultItems.getRequest().getUrl());

        List<String> urls = resultItems.get("urls");

        for (String url : urls) {
            ImageDownloadUtils.download(url);
        }
    }
}