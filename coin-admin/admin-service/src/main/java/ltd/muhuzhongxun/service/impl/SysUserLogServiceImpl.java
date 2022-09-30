package ltd.muhuzhongxun.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.muhuzhongxun.domain.SysUserLog;
import ltd.muhuzhongxun.mapper.SysUserLogMapper;
import ltd.muhuzhongxun.service.SysUserLogService;

@Service
public class SysUserLogServiceImpl extends ServiceImpl<SysUserLogMapper, SysUserLog> implements SysUserLogService {

}

