package zombie.crawler.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQConnection {
    private static Connection connection;
    private static Channel channel;

    public static Connection getConnection() throws IOException, TimeoutException {
        if(connection == null || !connection.isOpen()){
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(System.getenv("host"));
            factory.setUsername(System.getenv("user"));
            factory.setPassword(System.getenv("password"));
            connection = factory.newConnection();
        }
        return connection;
    }

    public static Channel getChannel() throws IOException, TimeoutException {
        if(channel == null || !channel.isOpen()){
            channel = getConnection().createChannel();
        }
        return channel;
    }
}
