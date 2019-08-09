package zombie.crawler.main;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import zombie.crawler.dto.ArticleDTO;
import zombie.crawler.utils.ConstantVar;
import zombie.crawler.utils.RabbitMQConnection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ArticleCrawler {
    private final String SAVE_ARTICLE_URL = "https://cabbagenews.appspot.com/api/v1/article";

    public void crawlArticles(){
        try {
            Channel channel = RabbitMQConnection.getChannel();
            channel.basicQos(1);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String articleJsonObject = new String(delivery.getBody(), StandardCharsets.UTF_8);
                ArticleDTO article = new Gson().fromJson(articleJsonObject, ArticleDTO.class);
                Document document = Jsoup.connect(article.getUrl()).ignoreContentType(true).get();

                String title = document.select(article.getCrawlerSource().getTitleSelector()).text();
                String description = document.select(article.getCrawlerSource().getDescriptionSelector()).text();
                String content = document.select(article.getCrawlerSource().getContentSelector()).html();
                String thumbnail = document.select(article.getCrawlerSource().getContentSelector()).select("img:first-child").attr("src");
                String author = document.select(article.getCrawlerSource().getAuthorSelector()).text();
                System.out.println(thumbnail);
                if(thumbnail == null || thumbnail.isEmpty() || thumbnail.contains("base64")){
                    thumbnail = "https://i0.wp.com/www.freezkart.com/wp-content/uploads/2018/08/cauliflowers.jpg?fit=500%2C500&ssl=1";
                }
                if(!title.isEmpty() && !description.isEmpty() && !content.isEmpty()){
                    article.setTitle(title);
                    article.setDescription(description);
                    article.setContent(content);
                    article.setThumbnail(thumbnail);
                    article.setAuthor(author);

                    // Call API to save article
                    String body = Jsoup.connect(SAVE_ARTICLE_URL)
                            .method(Connection.Method.POST)
                            .header("Content-Type", "application/json")
                            .requestBody(new Gson().toJson(article))
                            .execute().body();
                    System.out.println(body);
                }
            };
            String s = channel.basicConsume(ConstantVar.SOURCE_QUEUE, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
