package io.kinhoy.controller;

import io.kinhoy.util.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.InetAddress;

/**
 * @Description
 * @Date 2020-11-29 01:15
 * @Author kinhoy
 **/
@Controller
@RequestMapping("/remote")
public class RemoteController {

    @GetMapping("/index")
    public String indexPage(Model model){
        try{
            InetAddress addr = InetAddress.getLocalHost();
            model.addAttribute("IP",addr.getHostAddress());
            model.addAttribute("HOSTNAME",addr.getHostName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "mouse";
    }

    @GetMapping("/keyboard")
    public String toKeyboard(Model model){
        try{
            InetAddress addr = InetAddress.getLocalHost();
            model.addAttribute("IP",addr.getHostAddress());
            model.addAttribute("HOSTNAME",addr.getHostName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "keyboard";
    }


    /**
     * 根据 url 生成 普通二维码
     */
    @RequestMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成二维码
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 根据 url 生成 带有logo二维码
     */
    @RequestMapping(value = "/createLogoQRCode")
    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
                    + "templates" + File.separator + "logo.jpg";
            //使用工具类生成二维码
            QRCodeUtil.encode(url, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
