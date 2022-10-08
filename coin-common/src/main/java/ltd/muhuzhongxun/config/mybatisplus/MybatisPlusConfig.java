package ltd.muhuzhongxun.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.val;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("ltd.muhuzhongxun.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
//    @Bean 3.4.0以后new MybatisPlusInterceptor();
//    public PaginationInnerInterceptor paginationInnerInterceptor(){
//        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
//        paginationInnerInterceptor.setDbType(DbType.MYSQL);
//        return paginationInnerInterceptor;
//    }
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 乐观锁
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor(){
        OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor = new OptimisticLockerInnerInterceptor();
        return optimisticLockerInnerInterceptor;
    }


    /**
     * Id 生成器-->

     * 特殊的一些类使用
     * 默认使用
     * @return
     */
    @Bean
    public IKeyGenerator iKeyGenerator(){
        H2KeyGenerator h2KeyGenerator = new H2KeyGenerator();
        return h2KeyGenerator ;
    }
}
