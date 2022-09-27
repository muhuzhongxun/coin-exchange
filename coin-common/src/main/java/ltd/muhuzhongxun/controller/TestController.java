package ltd.muhuzhongxun.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import ltd.muhuzhongxun.model.R;
import ltd.muhuzhongxun.model.WebLog;
import ltd.muhuzhongxun.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api(tags = "CoinCommon里面测试的接口")
public class TestController {
    // 弃用
    //    @Autowired
    //    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate ;

    @Autowired
    private TestService testService;

    @GetMapping("/common/test")
    @ApiOperation(value = "测试方法", authorizations = {@Authorization("Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "参数1", dataType = "String", paramType = "query", example = "paramValue"),
            @ApiImplicitParam(name = "param1", value = "参数2", dataType = "String", paramType = "query", example = "paramValue")
    })
    public R<String> testMethod(String param, String param1) {

        return R.ok("ok");
    }


    @GetMapping("/common/date")
    @ApiOperation(value = "日志格式化测试", authorizations = {@Authorization("Authorization")})
    public R<Date> testMethod() {
        return R.ok(new Date());
        //todo jackson配置文件的日期格式化好像没生效
//        return R.ok(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    @GetMapping("/redis/test")
    @ApiOperation(value = "redis测试",authorizations = {@Authorization("Authorization")})
    public R<String> testRedis(){
        WebLog webLog = new WebLog();
        webLog.setResult("ok");
        webLog.setMethod("ltd.muhuzhongxun.domain.WebLog.testRedis");
        webLog.setUsername("1110");
        redisTemplate.opsForValue().set("ltd.muhuzhongxun.domain.WebLog",webLog);
        return R.ok("OK") ; //
    }

    @GetMapping("/jetcache/test")
    @ApiOperation(value = "jetcache缓存的测试",authorizations = {@Authorization("Authorization")})
    public R<String> testJetCache(String username){
        WebLog webLog = testService.get(username);
        System.out.println(webLog);
        return R.ok("OK") ; //
    }



}
