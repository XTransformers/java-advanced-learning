package com.xtransformers.designpattern.design.factory;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class RuleConfig {

    @Test
    public void test() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse("2021-05-30 12:39:33", dtf);
        LocalDateTime now = LocalDateTime.of(2021, 5, 30, 12, 43, 33);
        Duration duration = Duration.between(parse, now);
        long minutes = duration.toMinutes();
        System.out.println(minutes);

        // 是否可登录 可登录直接登录，不可登录时显示还剩几分钟可登录
        // {"canLogin": true/false, "leftMinutes": 5}
        // 登录是否成功
        // {"loginSuccess": true/false, "time": "2021-05-30 16:30:33", "phone": "13399995555"}

        // 获取有无错误次数
        // 无，返回可登录 {"canLogin": true}
            // 登录成功，无操作 {"loginSuccess": true, "time": "2021-05-30 16:30:33"}
            // 登录失败，记录 错误次数为1及登录时间，开始计时 24 小时（ttl）{"loginSuccess": false, "time": "2021-05-30 16:30:33", "phone": "13399995555"}
        // 有，次数 < 5，返回可登录 {"canLogin": true}
            // 登录成功，无操作 {"loginSuccess": true, "time": "2021-05-30 16:30:33", "phone": "13399995555"}
            // 登录失败，错误次数 +1，更新错误时间 {"loginSuccess": false, "time": "2021-05-30 16:30:33", "phone": "13399995555"}
        // 有，次数 >= 5
            // 计算传入当前时间与错误时间差值
                // 差值 < 10 分钟，不允许登录，返回 10-差值 {"canLogin": false, "leftMinutes": 4}
                // 差值 >= 10 分钟，允许登录 {"canLogin": true}
                    // 登录成功，无操作 {"loginSuccess": true, "time": "2021-05-30 16:30:33", "phone": "13399995555"}
                    // 登录失败，错误次数 +1，更新错误时间 {"loginSuccess": true, "time": "2021-05-30 16:30:33", "phone": "13399995555"}

    }
}
