package com.example;

import com.example.pipeline.CommonPicturePipeline;
import com.example.processor.BingPictureProcessor;

import us.codecraft.webmagic.Spider;

public class Main {

    public static void main(String[] args) {

        // 爬取 Bing 图片排行榜图片
        Spider.create(new BingPictureProcessor())
                .addUrl("https://bing.ioliu.cn/ranking")
                .addPipeline(new CommonPicturePipeline())
                .thread(5)
                .run();

    }
}
