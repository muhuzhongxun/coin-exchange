package ltd.muhuzhongxun.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.muhuzhongxun.domain.Sms;
import ltd.muhuzhongxun.mapper.SmsMapper;
import ltd.muhuzhongxun.service.SmsService;
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements SmsService{

}
