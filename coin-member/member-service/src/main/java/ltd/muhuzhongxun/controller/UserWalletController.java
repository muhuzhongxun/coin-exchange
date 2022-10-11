package ltd.muhuzhongxun.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.domain.User;
import ltd.muhuzhongxun.domain.UserWallet;
import ltd.muhuzhongxun.model.R;
import ltd.muhuzhongxun.service.UserService;
import ltd.muhuzhongxun.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "用户的提币地址")
@RequestMapping("/userWallets")
public class UserWalletController {


    @Autowired
    private UserWalletService userWalletService ;

    @GetMapping
    @ApiOperation(value = "分页查询用户的提币地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户的id",dataTypeClass = String.class) ,
            @ApiImplicitParam(name = "current", value = "当前页",dataTypeClass = String.class),
            @ApiImplicitParam(name = "size", value = "每页显示的条数",dataTypeClass = String.class)
    })
    @PreAuthorize("hasAuthority('user_wallet_query')")
    public R<Page<UserWallet>> findByPage(@ApiIgnore  Page<UserWallet> page , Long userId){
        page.addOrder(OrderItem.desc("last_update_time")) ;
        Page<UserWallet> userWalletPage = userWalletService.findByPage(page ,userId) ;
        return R.ok(userWalletPage) ;
    }
}
