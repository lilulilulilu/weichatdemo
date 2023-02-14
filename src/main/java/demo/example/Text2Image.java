package demo.example;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Response;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;

import java.io.*;

public class Text2Image {


    public static void generateImage(String url, String apiKey, String secretKey,String prompt) throws IOException {
        Long timestamp = System.currentTimeMillis();
        String key = secretKey + timestamp;
        String signature = DigestUtils.md5DigestAsHex(key.getBytes());;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("apiKey", apiKey);
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("signature", signature);
        jsonObject.put("prompt", prompt);

        String content = jsonObject.toJSONString();
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject json = JSONObject.parseObject(response.body().string());
        String data = json.getString("data");
        JSONObject dataJson = JSONObject.parseObject(data);

        String imageStr = dataJson.getString("resultData");

        boolean isSuccess = base64ToImage(imageStr);
        System.out.println("the image generated sucessful: " + isSuccess);
    }

    //base64字符串转化成图片
    public static boolean base64ToImage(String imgStr) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "image.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}


