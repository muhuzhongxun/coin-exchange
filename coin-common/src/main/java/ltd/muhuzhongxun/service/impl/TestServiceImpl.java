package ltd.muhuzhongxun.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import ltd.muhuzhongxun.model.WebLog;
import ltd.muhuzhongxun.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    /**
     * 通过username 查询webLog
     * @param username
     * @return
     */

    @Cached(name = "ltd.muhuzhongxun.service.impl.TestServiceImpl:",key = "#username" ,cacheType = CacheType.BOTH)
    public WebLog get(String username) {
        WebLog webLog = new WebLog();
        webLog.setUsername(username);
        webLog.setResult("ok");
        return webLog;
    }
}
