/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core;
/**
 * 类名称: OprServiceTest <br>
 * 类描述: <br>
 *
 * @author: xxxx.xxx
 * @since: 17/9/25 下午4:24
 * @version: 1.0.0
 */

import com.yeepay.g3.core.whitebroad.BaseTest;
import com.yeepay.g3.core.whitebroad.biz.OprServiceBiz;
import com.yeepay.g3.core.whitebroad.entity.trade.Order;
import com.yeepay.g3.core.whitebroad.service.trade.OrderService;
import com.yeepay.g3.frame.yop.ca.rsa.RSAKeyUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by dongxulu on 17/9/25.
 */
public class OprServiceTest extends BaseTest {
    @Autowired
    OprServiceBiz oprServiceBiz;
    @Autowired
    OrderService orderService;

    @Test
    public void getPayUrl(){
       Order order = orderService.findbyRequestNo("wb1506146479385","10040011560");
        System.out.println(oprServiceBiz.getPayUrl(order));

    }
    @Test
    public void getPriviteKey(){
        String privateKeys = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCE4jSXi9K5kv+y2vLQ2Flyn4DBzaZGIaOodTx1fh8LIMuBIF7gGae44h8D4+RqU4eCDiL+1d9Dz0aCEv12GRviapPep6pTmDbg/ZUIKW7NQ54jkawv46UiRTBYaUxpCFcIY7qQerd5Gk0Eyzb6e0WGnIMA+ihzHA1kbq5+3NsbJaY4L3rS/V/7T4Q8ZaIlneTpITneUBYM8DEc5nlPsXFU+SDkbXOJxk/ubvt8Iu7Vdgamm6wRkXgwEwL8x5GYPFzQVvAWXg6uM7/GjLLktGAjfH5EvS9tIf4mg4sDLKf5JaFpsLl4cw1PjfAqI0mDZ/sN3bToZcmQV/QsgnnxiINhAgMBAAECggEABAbe1SDVl+4OdZsQ9Kc5vrF6s2TJ/yIR1Y8H7/+MbUfnItiaR2w/muivS+ziBR7W4mgPQ22zwFhNqK9uot+L6saUCJFIJhRcxWCp3X9z7iB4cFd/wkvBu1Ihbuxx9lTcXlK3Wp19ZdwYLWcDGYAW0Ud46NSr2SEmfr6j5xBGS4JDTTncz478GA19gh8GAU9AKL8/+8bpbqX88a1fc91fpakwVGBJg0d+rM9b45g7PDGNwd7B5RvoDVPRo7KQ6pFlbCG5XEplI1JEFsgdcfd/IuxLH2NnknBtA+YGbu/g2uWu5fodCEWd6N5cBF7Yw9xVvqn2yKrvHRMGKhlBxvsVKQKBgQDQ82PHvEuyOl3KKxGWjPVwf79WIl0Tr5FYk5GFzeI4JxqLW4anzpbOk4e0gksAVVOXbi1N7SwOF2oYKvuwCoc38jqaipJuQaGFqwJ4XIlon60k1knv2GHSxYzucel4OqKhLodMa4Ifco/JKD+GoUkd8NBTE8lVHqjG/U98ozDymwKBgQCizg41WsflDEbb4+LaUA2VJzPn/Ab2TfoYxeAFItSzPsbeCLFx0DIW160uXJEvq5oP6DACYYV9h8pOpvlu8S4OPd3Aynkz7MblJ6Fm3R85M3LKphFqp5IXvmmMt6KZ3BcB0lzbV1ztBvrxzjeowPrSaixjz7/NYrbAPv/1zI4zswKBgAfMbbbwsiqOvx1iih+CV07BldSTJ8IqVVRm/ZFu47iQzQ7Sda4hqcNAAlqcWkxC4wHN3OcBqRlFinIoeIhTfER8R++oqFpxHKQug8MWQRKkkwPC87N98fi54s6rdblFBvjZEbeqc4rU7cTmOai+sEnryPqwzFze7hGvNp5CQPttAoGACB5GTxrLxPwrvGNMuJ4tTza5O+QxZK7Wvc2AMkFeYeHLgN7mGkrLdKAknp83Di18bTaizjuWsH/I8ssYvROBvGwRVr/B83jnIh4ryjlUiUGrg7dsPulEO7LfCIlPrmRvMUQQJU/h7l/eu5gQ5nVZDI0G0aNWaSN3AXcixFsC7EkCgYAng5NGXTCnxGSPeiA5INIT9sqXuu2qdWjSj3e2o1yoy0XJjumbrPjSu3vNkMWBZ2ctZZmcfJjisBGMQCMi99g6R6fKvkOTLjkNHvcZXhJ18gTi9Q3nZQkm5CGx8oKWiyjak0bPcy+4+1Nnydz++SZC0DbgvdUL0+BtakAbR6+xrg==";
        try {
            PrivateKey privateKey = RSAKeyUtils.string2PrivateKey(privateKeys);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}