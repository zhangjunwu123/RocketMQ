# 消息存储实现相关类

## 三大组件：CommitLog、ConsumeQueue、IndexFile
### 1.CommitLog
#### 所有消息都存储在同一个文件中，保证顺序写，提高性能




### 2.ConsumeQueue
#### 直接从CommitLog读取某个主题的消息性能很差，此时需要ConsumeQueue【参照Kafka设计】，
#### 一个主题的一个队列为一个文件
#### 消息到达CommitLog之后，会异步转发消息到消息队列，供消费者消费
#### 内容：
     CommitLogOffset【CommitLog存储消息的定位】
     Size【消息大小】
     tagHashCode
      


### 3.IndexFile
#### 加速消息的检索性能，根据消息属性直接从CommitLog来检索消息
#### 消息索引文件，主要存储消息key与offset对应关系
#### 存储内容：
      key:  key的hashcode
      value： CommitLogOffset


### 其他1：事务状态服务
#### 存储每条消息的事务状态


### 其他2：定时消息服务
#### 每一个延迟级别对应着一个消息消费队列，存储延迟消息的拉取进度



