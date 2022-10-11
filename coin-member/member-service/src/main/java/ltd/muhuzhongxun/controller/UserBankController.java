package ltd.muhuzhongxun.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.domain.UserBank;
import ltd.muhuzhongxun.model.R;
import ltd.muhuzhongxun.service.UserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userBanks")
@Api(tags = "会员的银行卡管理")
public class UserBankController {


    @Autowired
    private UserBankService userBankService ;


    @GetMapping
    @ApiOperation(value = "分页查询用户的银行卡")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usrId" ,value = "会员的Id",dataTypeClass = String.class) ,
            @ApiImplicitParam(name = "current" ,value = "当前页",dataTypeClass = String.class)  ,
            @ApiImplicitParam(name = "size" ,value = "每页显示的条数",dataTypeClass = String.class)
    })
    @PreAuthorize("hasAuthority('user_bank_query')")
    public R<Page<UserBank>> findByPage(Page<UserBank> page , Long usrId){
        page.addOrder(OrderItem.desc("last_update_time")) ;
        Page<UserBank> userBankPage = userBankService.findByPage(page ,usrId) ;
        return R.ok(userBankPage) ;
    }


    @PostMapping("/status")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,value = "银行卡的Id",dataTypeClass = String.class) ,
            @ApiImplicitParam(name = "status" ,value = "银行卡的状态",dataTypeClass = String.class) ,
    })
    @ApiOperation(value = "修改银行卡的状态")
    public R updateStatus(Long id ,Byte status){
        UserBank userBank = new UserBank();
        userBank.setId(id);
        userBank.setStatus(status);
        boolean updateById = userBankService.updateById(userBank);
        if(updateById){
            return R.ok() ;
        }
        return R.fail("银行卡状态修改失败") ;
    }


    @PatchMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userBank" ,value = "银行卡的json数据",dataTypeClass = String.class) ,
    })
    @ApiOperation(value = "修改银行卡")
    public R updateStatus(@RequestBody  @Validated  UserBank userBank){
        boolean updateById = userBankService.updateById(userBank);
        if(updateById){
            return R.ok() ;
        }
        return R.fail("银行卡状态修改失败") ;
    }
}
