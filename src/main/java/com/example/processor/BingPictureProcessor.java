package com.example.processor;

import org.apache.log4j.Logger;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 处理页面，获取页面中图片的链接
 *
 * @author 0xl2oot@gmail.com
 * @date 2018/10/3 6:13 PM
 */
public class BingPictureProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private Logger logger = Logger.getLogger(this.getClass());

    public void process(Page page) {

        List<String> pictureUrls = page.getHtml()
                .regex("/photo/\\S+force=ranking")
                .replace("ranking", "download")
                .replace("/photo", "https://bing.ioliu.cn/photo")
                .all();

        page.putField("urls", pictureUrls);

        List<String> targetUrls = page.getHtml()
                .regex("/ranking\\?p=\\d+")
                .replace("/ranking", "https://bing.ioliu.cn/ranking")
                .all();

        logger.info("【targetUrls】"+targetUrls);

        page.addTargetRequests(targetUrls);
    }

    public Site getSite() {
        return site;
    }
}
