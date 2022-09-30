package ltd.muhuzhongxun.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.muhuzhongxun.domain.Notice;
import ltd.muhuzhongxun.mapper.NoticeMapper;
import ltd.muhuzhongxun.service.NoticeService;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}

