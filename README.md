这是一个微信公众号的后台开发demo

公众号开发，需要的后端技术，是把一个后台服务注册到公众号，这样用户通过微信发给公众号的消息，都经过微信转发到后台服务了，这个后台服务要在5秒内响应，如果没有响应则触发重试机制，重试3次没有响应，连接将断开。

接入后端app：[接入概述 | 微信开放文档](https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html)
