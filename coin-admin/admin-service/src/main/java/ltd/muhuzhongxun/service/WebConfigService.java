package ltd.muhuzhongxun.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.domain.WebConfig;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WebConfigService extends IService<WebConfig> {


    Page<WebConfig> findByPage(Page<WebConfig> page, String name, String type);
}

