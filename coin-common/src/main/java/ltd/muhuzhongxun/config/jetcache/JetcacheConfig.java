package ltd.muhuzhongxun.config.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

@Configuration
// jetcache启动缓存
@EnableCreateCacheAnnotation
// 开启方法注解缓存
@EnableMethodCache(basePackages = "ltd.muhuzhongxun.service.impl")
public class JetcacheConfig {
}
