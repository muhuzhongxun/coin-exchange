package ltd.muhuzhongxun.controller;

import ltd.muhuzhongxun.model.LoginResult;
import ltd.muhuzhongxun.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录的控制器
 */
@RestController
@Api(tags = "登录的控制器")
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "后台管理人员登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "username", value = "用户名称", dataTypeClass = String.class),
                    @ApiImplicitParam(name = "password", value = "用户的密码",dataTypeClass = String.class),
            }
    )
    public LoginResult login(
            @RequestParam(required = true) String username, // 用户名称
            @RequestParam(required = true) String password  // 用户的密码
    ) {
        return loginService.login(username, password);
    }

}
