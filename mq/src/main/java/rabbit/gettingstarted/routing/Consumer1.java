package rabbit.gettingstarted.routing;

import rabbit.gettingstarted.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * routing-direct mode
 *
 * @author Suz1
 * @date 2020/6/27 10:35 下午
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMqUtils.getConn();
        assert conn != null;
        Channel channel = conn.createChannel();
        channel.exchangeDeclare("routing-direct", "direct");

        // 创建临时对列
        String queue = channel.queueDeclare().getQueue();
        // bind queue and exchange based on routing key
        channel.queueBind(queue, "routing-direct", "error");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("error queue get message :" + new String(body));
            }
        });
    }
}
