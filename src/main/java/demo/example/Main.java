package demo.example;

import java.io.IOException;

/**
 * 测试文生图api
 */
public class Main {
    public static void main(String[] args) throws IOException {

        String url = "https://api.fengshenbang-lm.com/v1/TextGeneratePicture";
        String apiKey = "n0GVEI8EQletdPJIgkyHfw==";
        String secretKey = "dJloh+2DW6xJ6kTG0AlOXMJ13YKaCmvEtbDLq7Y4foI=";
        String prompt = "机器人";

        Text2Image.generateImage(url, apiKey, secretKey, prompt);

    }

}
