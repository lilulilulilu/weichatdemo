package demo.service;

import demo.pojo.ReceiveMsgBody;
import demo.pojo.ResponseMsgBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface MessageService {
    ResponseMsgBody handle(ReceiveMsgBody receiveMsgBody);
}
