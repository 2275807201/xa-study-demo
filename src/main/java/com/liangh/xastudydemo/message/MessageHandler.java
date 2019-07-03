package com.liangh.xastudydemo.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    /**
     * 小明在这里收到老板的感谢
     * @param content
     */
    @JmsListener(destination = "thanks")
    public void onMessage(String content) {
        System.out.println("On Message ----> " + content);
        System.out.println("\n\n");
    }

}
