package io.kinhoy;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.net.InetAddress;

/**
 * @Description
 * @Date 2020-11-29 01:24
 * @Author kinhoy
 **/
@SpringBootApplication
public class StartServer {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(StartServer.class);
        builder.headless(false)
                // .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        applicationReadyEvent();
    }

    public static void applicationReadyEvent() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("*********************************************************************************");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("          遥控应用程序已经准备就绪，请使用同一局域网下的设备扫码访问网页进行控制");
            System.out.println();
            System.out.println("                                    by HQH");
            System.out.println();
            System.out.println();
            System.out.println("*********************************************************************************");
            String url = "http://localhost:8080/remote/createCommonQRCode?url=http://" + addr.getHostAddress() + ":8080/remote/index";
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}