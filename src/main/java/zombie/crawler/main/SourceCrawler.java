package zombie.crawler.main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.Channel;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import zombie.crawler.dto.ArticleDTO;
import zombie.crawler.dto.CrawlerSourceDTO;
import zombie.crawler.utils.ConstantVar;
import zombie.crawler.utils.RabbitMQConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SourceCrawler {
    private final String CRAWLER_SOURCE_URL = "https://cabbagenews.appspot.com/api/v1/crawlersource";
    private final String CHECK_DUPLICATE_ARTICLE_URL = "https://cabbagenews.appspot.com/api/v1/article/check-duplicate";

    public void addArticlesURLToQueue(){
        try {
            String json = Jsoup.connect(CRAWLER_SOURCE_URL).ignoreContentType(true).execute().body();
            List<CrawlerSourceDTO> sources = new Gson().fromJson(json, new TypeToken<List<CrawlerSourceDTO>>(){}.getType());
            Channel channel = RabbitMQConnection.getChannel();
            channel.queueDeclare(ConstantVar.SOURCE_QUEUE, true, false, false, null);
            for (CrawlerSourceDTO crawlerSource: sources) {
                Document document = Jsoup.connect(crawlerSource.getUrl()).ignoreContentType(true).get();
                List<Element> elements = document.select(crawlerSource.getLinkSelector()).subList(0, crawlerSource.getLinkLimit());
                for (Element el : elements) {
                    String link = el.attr("href").trim();
                    // check duplicate link
                    JSONObject jsonObject = new JSONObject(
                            Jsoup.connect(CHECK_DUPLICATE_ARTICLE_URL)
                                    .ignoreContentType(true)
                                    .method(Connection.Method.POST)
                                    .data("url", link)
                                    .execute().body()
                    );

                    if(Integer.parseInt(jsonObject.get("status").toString()) == 1){
                        continue;
                    }
                    ArticleDTO article = ArticleDTO.Builder.anArticleDTO().withUrl(link).withCrawlerSource(crawlerSource).build();
                    channel.basicPublish("", ConstantVar.SOURCE_QUEUE, null, new Gson().toJson(article).getBytes());
                }
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
