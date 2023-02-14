package demo.controller;

import demo.common.SHA1;
import demo.exception.AesException;
import demo.pojo.*;
import demo.service.MessageService;
import demo.wechat.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class WechatController {

    /** 工具类 */
    @Autowired
    private WechatUtils wechatUtils;

    /** 消息处理 */
    @Resource(name="MessageServiceImpl")
    private MessageService msgService;

    /**
     * 微信公众号接口配置验证
     */
    @RequestMapping(value = "/robot/hello", method = RequestMethod.GET)
    public String checkSignature(String signature, String timestamp, String nonce, String echostr) throws AesException {
        log.info("checking Signature...");
        log.info("signature = {}", signature);
        log.info("timestamp = {}", timestamp);
        log.info("nonce = {}", nonce);
        log.info("echostr = {}", echostr);
        log.info("token = {}", wechatUtils.getToken());

        String localSignature = SHA1.getSHA1(wechatUtils.getToken(), timestamp, nonce, "");
        log.info("localSignature = {}", localSignature);

        // 第三步：验证签名
        if (signature.equals(localSignature)) {
            log.info("echostr = {}", echostr);
            log.info("Signature checked successful!");
            return echostr;
        }
        log.info("Failed to check Signature =_= ");
        return null;
    }

    /**
     * 接收用户下消息
     * @param receiveMsgBody 消息
     */
    @RequestMapping(value = "/robot/hello", method = RequestMethod.POST, produces = {"application/xml; charset=UTF-8"})
    @ResponseBody
    public Object getUserMessage(@RequestBody ReceiveMsgBody receiveMsgBody) {
        log.info("接收到的消息：{}", receiveMsgBody);
        return msgService.handle(receiveMsgBody);
    }

    /**
     * 获取access_token
     */
    @RequestMapping("/robot/getAccessToken")
    public String getAccessToken() throws WxErrorException {
        String accessToken = wechatUtils.getAccessToken();
        log.info("access_token = {}", accessToken);
        return accessToken;
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/robot/getUserInfo")
    public void getUserInfo() {
        try {
            WxMpUserList userList = wechatUtils.getUserList();
            if (userList == null || userList.getOpenIds().isEmpty()) {
                log.warn("关注者openId列表为空");
                return;
            }
            List<String> openIds = userList.getOpenIds();
            log.info("关注者openId列表 = {}", openIds.toString());

            String openId = openIds.get(0);
            log.info("开始获取 {} 的基本信息", openId);
            WxMpUser userInfo = wechatUtils.getUserInfo(openId);
            if (userInfo == null) {
                log.warn("获取 {} 的基本信息为空", openId);
                return;
            }
            String city = userInfo.getCity();
            String nickname = userInfo.getNickname();
            log.info("{} 的昵称为：{}, 城市为：{}", openId, nickname, city);
        } catch (WxErrorException e) {
            log.error("获取用户消息失败", e);
        }
    }
}
