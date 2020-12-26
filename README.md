#README
##Background
1. A system can produce various types of messages such as WeChat messages and mail messages. These messages are sent to users through different ways.
2. The messages can be produced with a very high frequency, however, due to capacity limitation, the servers cannot send messages very fast. Therefore, messages need to be cached in a message queue.
Based on the points above, we need to design a server that can cache messages and send them evenly and slowly to the users.
##Project Architecture
For this project, it consists of following parts.
1. MessageQueue. The core part of this demo. It is implemented by blocking queue. Also, due to the capacity limitation, the messages cannot be stored infinitely. Therefore, we have to assign
rejection/eviction policy. In this demo, users can assign them through `application.yml`.
2. Producer. When the interface `/send` is requested, the coming messages will be processed by the producer. It will cache message by using message queue that mentioned above.
3. Consumer. In order to send messages smoothly, `RateLimter` is adopted to implement the token algorithm. ALso, due to the message is a blocking queue, when the queue is empty, the consumer thread will be blocked and will be notified by the queue automatically. To some extent, it improves the CPU utilization.
4. Client. It can mimic the messages production with a high frequency by using thread pool. It will request server `/send` interface frequently.

