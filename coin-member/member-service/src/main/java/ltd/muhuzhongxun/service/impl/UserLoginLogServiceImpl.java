package ltd.muhuzhongxun.service.impl;

import ltd.muhuzhongxun.domain.UserLoginLog;
import ltd.muhuzhongxun.mapper.UserLoginLogMapper;
import ltd.muhuzhongxun.service.UserLoginLogService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
