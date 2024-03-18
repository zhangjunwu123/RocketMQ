# NameServer实现相关类
## NameSrv启动
### Broker消息服务器在启动时向所有NameServer注册
### 消息生产者Producer在发送消息前先从NamerServer获取Broker的服务器地址列表，然后根据负载均衡算法从列表中选择一台服务器进行消息发送。
### NameServer与每台Broker服务器保持长连接，并间隔30s检查broker是否存活。如果检测到Broker宕机，则从路由注册表中将其移除。但是路由变化不会
### 马上通知消息生产者。这样设计是为了降低Nameserver实现复杂度，
### 在消息发送端提供容错机制来保证消息发送的高可用性。

