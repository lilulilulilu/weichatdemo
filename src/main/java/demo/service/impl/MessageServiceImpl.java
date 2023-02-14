package demo.service.impl;

import demo.pojo.*;
import demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("MessageServiceImpl")
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Override
    public ResponseMsgBody handle(ReceiveMsgBody receiveMsgBody) {
        MsgType msgType = MsgType.getMsgType(receiveMsgBody.getMsgType());
        switch (msgType) {
            case text:
                return this.handleText(receiveMsgBody);
            case image:
                return this.handleImage(receiveMsgBody);
            case voice:
                return this.handleVoiceToText(receiveMsgBody);
            case event:
                return this.handleEvent(receiveMsgBody);
            default:
                break;
        }
        return null;
    }

    private ResponseMsgBody handleText(ReceiveMsgBody receiveMsgBody) {
        log.info("接收到的消息类型:{}", MsgType.text.getMsgTypeDesc());
        ResponseMsgBody textMsg = new ResponseMsgBody();
        textMsg.setToUserName(receiveMsgBody.getFromUserName());
        textMsg.setFromUserName(receiveMsgBody.getToUserName());
        textMsg.setCreateTime(new Date().getTime());
        textMsg.setMsgType(MsgType.text.getMsgType());
        textMsg.setContent(receiveMsgBody.getContent());
        return textMsg;
    }

    private ResponseMsgBody handleImage(ReceiveMsgBody receiveMsgBody) {
        log.info("接收到的消息类型:{}", MsgType.image.getMsgTypeDesc());
        ResponseImageMsg imageMsg = new ResponseImageMsg();
        imageMsg.setToUserName(receiveMsgBody.getFromUserName());
        imageMsg.setFromUserName(receiveMsgBody.getToUserName());
        imageMsg.setCreateTime(new Date().getTime());
        imageMsg.setMsgType(MsgType.image.getMsgType());
        imageMsg.setMediaId(new String[]{receiveMsgBody.getMediaId()});
        return imageMsg;
    }

    private ResponseMsgBody handleVoiceToText(ReceiveMsgBody receiveMsgBody) {
        log.info("接收到的消息类型:{}", MsgType.voice.getMsgTypeDesc());
        log.info("接收到的语音内容是{}", receiveMsgBody.getRecognition());
        String responsePrefix = "哈哈😄，我听懂了！你说：";
        String responseText = responsePrefix + receiveMsgBody.getRecognition();
        ResponseMsgBody textMsg = new ResponseMsgBody();
        textMsg.setToUserName(receiveMsgBody.getFromUserName());
        textMsg.setFromUserName(receiveMsgBody.getToUserName());
        textMsg.setCreateTime(new Date().getTime());
        textMsg.setMsgType(MsgType.text.getMsgType());
        textMsg.setContent(responseText);
        return textMsg;
    }

    private ResponseMsgBody handleEvent(ReceiveMsgBody receiveMsgBody) {
        log.info("接收到的消息类型:{}", MsgType.event.getMsgTypeDesc());
        String responseText = "欢迎关注！我是一个机器人哦😁。我可以根据文字生成图片，还能把语音转成文字，快试试吧！";
        ResponseMsgBody textMsg = new ResponseMsgBody();
        textMsg.setToUserName(receiveMsgBody.getFromUserName());
        textMsg.setFromUserName(receiveMsgBody.getToUserName());
        textMsg.setCreateTime(new Date().getTime());
        textMsg.setMsgType(MsgType.event.getMsgType());
        textMsg.setContent(responseText);
        return textMsg;
    }

    private ResponseMsgBody handleVoice(ReceiveMsgBody receiveMsgBody) {
        log.info("接收到的消息类型:{}", MsgType.voice.getMsgTypeDesc());
        ResponseVoiceMsg voiceMsg = new ResponseVoiceMsg();
        voiceMsg.setToUserName(receiveMsgBody.getFromUserName());
        voiceMsg.setFromUserName(receiveMsgBody.getToUserName());
        voiceMsg.setCreateTime(new Date().getTime());
        voiceMsg.setMsgType(MsgType.voice.getMsgType());
        voiceMsg.setMediaId(new String[]{receiveMsgBody.getMediaId()});
        return voiceMsg;
    }

}
