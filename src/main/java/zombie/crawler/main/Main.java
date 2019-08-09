package zombie.crawler.main;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import zombie.crawler.dto.ArticleDTO;
import zombie.crawler.utils.ConstantVar;

public class Main {
    public static void main(String[] args) {
        System.out.println("start crawl links");
        SourceCrawler sourceCrawler = new SourceCrawler();
        sourceCrawler.addArticlesURLToQueue();
        //or
        System.out.println("start crawl articles");
        ArticleCrawler articleCrawler = new ArticleCrawler();
        articleCrawler.crawlArticles();

    }
}
