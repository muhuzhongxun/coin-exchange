package ltd.muhuzhongxun.controller;

import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    /**
     * 当前登录的用户对象
     * @param principal
     * @return
     */
    @GetMapping("/user/info")
    public Principal userInfo(Principal principal){
        // 使用ThredLocal来实现的
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return principal;
    }

}
