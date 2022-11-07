package ltd.muhuzhongxun.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.domain.UserAddress;
import ltd.muhuzhongxun.model.R;
import ltd.muhuzhongxun.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "用户钱包地址")
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService ;


    @GetMapping
    @ApiOperation(value = "查阅用户的钱包地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户的Id",dataTypeClass = String.class),
            @ApiImplicitParam(name = "current" ,value = "当前页",dataTypeClass = String.class) ,
            @ApiImplicitParam(name = "size" ,value = "每页显示的条数",dataTypeClass = String.class)
    })
    public R<Page<UserAddress>> findByPage(@ApiIgnore Page<UserAddress> page , Long userId){
        page.addOrder(OrderItem.desc("last_update_time")) ;
        Page<UserAddress> userAddressPage = userAddressService.findByPage(page,userId) ;
        return R.ok(userAddressPage) ;
    }
}
