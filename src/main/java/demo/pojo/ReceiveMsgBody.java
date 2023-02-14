package demo.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

/**
 * 接收消息实体
 */
@Getter
@Setter
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReceiveMsgBody {
    /**开发者微信号*/
    private String ToUserName;
    /** 发送消息用户的openId */
    private String FromUserName;
    /** 消息创建时间 */
    private long CreateTime;
    /** 消息类型 */
    private String MsgType;
    /** 事件类型: subscribe, unsubscribe, SCAN, LOCATION, CLICK，VIEW */
    private String Event;
    /** 事件类型为click时菜单栏的key */
    private String EventKey;
    /** 消息ID，根据该字段来判重处理 */
    private long MsgId;
    /** 文本消息的消息体 */
    private String Content;

    /** 媒体ID */
    private String MediaId;
    /** 图片消息的图片链接（由系统生成） */
    private String PicUrl;
    /** 语音消息的语音格式 */
    private String Format;
    /** 语音消息的语音识别结果 */
    private String Recognition;
    /** 视频消息的缩略图的媒体id */
    private String ThumbMediaId;
    /** 位置消息的纬度 */
    private String Location_X;
    /** 位置消息的经度 */
    private String Location_Y;
    /** 地图缩放大小 */
    private String Scale;
    /** 地理位置信息 */
    private String Label;
    /** 链接消息的标题 */
    private String Title;
    /** 链接消息的描述 */
    private String Description;
    /** 链接消息的url */
    private String Url;

    @Override
    public String toString() {
        return new StringJoiner(", ", ReceiveMsgBody.class.getSimpleName() + "[", "]")
                .add("ToUserName='" + ToUserName + "'")
                .add("FromUserName='" + FromUserName + "'")
                .add("CreateTime=" + CreateTime)
                .add("MsgType='" + MsgType + "'")
                .add("Event='" + Event + "'")
                .add("MsgId=" + MsgId)
                .add("MediaId='" + MediaId + "'")
                .add("Content='" + Content + "'")
                .add("PicUrl='" + PicUrl + "'")
                .add("Format='" + Format + "'")
                .add("Recognition='" + Recognition + "'")
                .add("ThumbMediaId='" + ThumbMediaId + "'")
                .add("Location_X='" + Location_X + "'")
                .add("Location_Y='" + Location_Y + "'")
                .add("Scale='" + Scale + "'")
                .add("Label='" + Label + "'")
                .add("Title='" + Title + "'")
                .add("Description='" + Description + "'")
                .add("Url='" + Url + "'")
                .toString();
    }
}

