//package ltd.muhuzhongxun.config.jackson;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.text.SimpleDateFormat;
//import java.util.TimeZone;
//
//已弃用.由于WebMvcConfigurationSupport会影响导致objectMapper无效.
//改用MappingJackson2HttpMessageConverter来设置json格式.坐标'../webMvc/WebMvcConfig'
//
//@Configuration
//public class JacksonConfig {
//    /**
//     * 这里我自己加了static,方便重复使用objectMapper单例
//     * 配置ObjectMapper.  JSON格式化处理
//     * @return
//     */
//    @Bean
//    public static ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//
//        return objectMapper;
//    }
//}
