package ltd.muhuzhongxun.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.muhuzhongxun.domain.UserLoginLog;
import ltd.muhuzhongxun.mapper.UserLoginLogMapper;
import ltd.muhuzhongxun.service.UserLoginLogService;
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService{

}
