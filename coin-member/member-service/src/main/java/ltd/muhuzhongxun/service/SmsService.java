package ltd.muhuzhongxun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.domain.Sms;

public interface SmsService extends IService<Sms>{


    /**
     * 短信的发现
     * @param sms
     *  短信
     * @return
     * 是否发送成功
     */
    boolean sendSms(Sms sms);
}
