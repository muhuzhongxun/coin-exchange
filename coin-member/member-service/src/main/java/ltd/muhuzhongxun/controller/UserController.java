package ltd.muhuzhongxun.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.domain.User;
import ltd.muhuzhongxun.domain.UserAuthAuditRecord;
import ltd.muhuzhongxun.domain.UserAuthInfo;
import ltd.muhuzhongxun.model.R;
import ltd.muhuzhongxun.model.UpdatePhoneParam;
import ltd.muhuzhongxun.model.UserAuthForm;
import ltd.muhuzhongxun.service.UserAuthAuditRecordService;
import ltd.muhuzhongxun.service.UserAuthInfoService;
import ltd.muhuzhongxun.service.UserService;
import ltd.muhuzhongxun.vo.UseAuthInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "会员的控制器")
public class UserController {


    @Autowired
    private UserService userService ;

    @Autowired
    private UserAuthInfoService userAuthInfoService;

    @Autowired
    private UserAuthAuditRecordService userAuthAuditRecordService;

    /**
     * "查询会员的列表"
     * @param page
     * @param mobile
     * @param userId
     * @param userName
     * @param realName
     * @param status
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询会员的列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "当前页",dataTypeClass = String.class),
            @ApiImplicitParam(name = "size",value = "每页显示的条数",dataTypeClass = String.class),
            @ApiImplicitParam(name = "mobile",value = "会员的手机号",dataTypeClass = String.class),
            @ApiImplicitParam(name = "userId",value = "会员的Id,精确查询",dataTypeClass = String.class),
            @ApiImplicitParam(name = "userName",value = "会员的名称",dataTypeClass = String.class),
            @ApiImplicitParam(name = "realName",value = "会员的真实名称",dataTypeClass = String.class),
            @ApiImplicitParam(name = "status",value = "会员的状态",dataTypeClass = String.class)

    })
    @PreAuthorize("hasAuthority('user_query')")
    public R<Page<User>> findByPage(@ApiIgnore Page<User> page ,
                                    String mobile ,
                                    Long userId ,
                                    String userName ,
                                    String realName ,
                                    Integer status
    ){
        page.addOrder(OrderItem.desc("last_update_time")) ;
        Page<User> userPage =  userService.findByPage(page,mobile,userId,userName,realName,status,null) ;
        return R.ok(userPage) ;
    }


    /** 修改用户的状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status")
    @ApiOperation(value = "修改用户的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,value = "会员的id",dataTypeClass = String.class) ,
            @ApiImplicitParam(name = "status" ,value = "会员的状态",dataTypeClass = String.class) ,
    })
    @PreAuthorize("hasAuthority('user_update')")
    public R updateStatus(Long id ,Byte status){
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        boolean updateById = userService.updateById(user);
        if(updateById){
            return R.ok("更新成功") ;
        }
        return R.fail("更新失败") ;
    }


    /** 修改用户
     * @param user
     * @return
     */
    @PatchMapping
    @ApiOperation(value = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user" ,value = "会员的json数据",dataTypeClass = String.class) ,
    })
    @PreAuthorize("hasAuthority('user_update')")
    public R updateStatus(@RequestBody @Validated User user){
        boolean updateById = userService.updateById(user);
        if(updateById){
            return R.ok("更新成功") ;
        }
        return R.fail("更新失败") ;
    }


    /** 获取用户的详情
     * @param id
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取用户的详情")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id" ,value = "用户的Id",dataTypeClass = String.class)
    })
    @PreAuthorize("hasAuthority('user_query')")
    public R<User> userInfo(Long id){
        return R.ok(userService.getById(id)) ;
    }

    /** 查询该用户邀请的用户列表
     * @param page
     * @param userId
     * @return
     */
    @GetMapping("/directInvites")
    @ApiOperation(value = "查询该用户邀请的用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "该用户的Id",dataTypeClass = String.class),
            @ApiImplicitParam(name = "current", value = "当前页",dataTypeClass = String.class),
            @ApiImplicitParam(name = "size", value = "每页显示的条数",dataTypeClass = String.class),

    })
    public R<Page<User>> getDirectInvites(@ApiIgnore Page<User> page ,Long userId){
        Page<User> userPage = userService.findDirectInvitePage(page ,userId)  ;
        return R.ok(userPage) ;
    }

    /** 查询用户的审核列表
     * @param page
     * @param mobile
     * @param userId
     * @param userName
     * @param realName
     * @param reviewsStatus
     * @return
     */
    @GetMapping("/auths")
    @ApiOperation(value = "查询用户的审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页",dataTypeClass = String.class),
            @ApiImplicitParam(name = "size", value = "每页显示的条数",dataTypeClass = String.class),
            @ApiImplicitParam(name = "mobile", value = "会员的手机号",dataTypeClass = String.class),
            @ApiImplicitParam(name = "userId", value = "会员的Id,精确查询",dataTypeClass = String.class),
            @ApiImplicitParam(name = "userName", value = "会员的名称",dataTypeClass = String.class),
            @ApiImplicitParam(name = "realName", value = "会员的真实名称",dataTypeClass = String.class),
            @ApiImplicitParam(name = "reviewsStatus", value = "会员的状态",dataTypeClass = String.class)

    })
    public R<Page<User>> findUserAuths(
            @ApiIgnore Page<User> page,
            String mobile,
            Long userId,
            String userName,
            String realName,
            Integer reviewsStatus
    ) {
        Page<User> userPage = userService.findByPage(page, mobile, userId, userName, realName, null, reviewsStatus);
        return R.ok(userPage);
    }

    /**
     * 查询用户的认证详情
     * {
     * user:
     * userAuthInfoList:[]
     * userAuditRecordList:[]
     * }
     */
    @GetMapping("/auth/info")
    @ApiOperation(value = "查询用户的认证详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的Id",dataTypeClass = String.class)
    })
    public R<UseAuthInfoVo> getUseAuthInfo(Long id) {

        User user = userService.getById(id);
        List<UserAuthAuditRecord> userAuthAuditRecordList = null;
        List<UserAuthInfo> userAuthInfoList = null;
        if (user != null) {
            // 用户的审核记录
            Integer reviewsStatus = user.getReviewsStatus();
            if (reviewsStatus == null || reviewsStatus == 0) { // 待审核
                userAuthAuditRecordList = Collections.emptyList(); // 用户没有审核记录
                //
                userAuthInfoList = userAuthInfoService.getUserAuthInfoByUserId(id);
            } else {
                userAuthAuditRecordList = userAuthAuditRecordService.getUserAuthAuditRecordList(id);
                // 查询用户的认证详情列表-> 用户的身份信息
                UserAuthAuditRecord userAuthAuditRecord = userAuthAuditRecordList.get(0);// 之前我们查询时,是按照认证的日志排序的,第0 个值,就是最近被认证的一个值
                Long authCode = userAuthAuditRecord.getAuthCode(); // 认证的唯一标识
                userAuthInfoList = userAuthInfoService.getUserAuthInfoByCode(authCode);
            }
        }
        return R.ok(new UseAuthInfoVo(user, userAuthInfoList, userAuthAuditRecordList));
    }


    /**
     * 审核的本质: 在于对一组图片(唯一Code)的认可,符合条件,审核通过
     *
     * @return
     */
    @PostMapping("/auths/status")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的ID",dataTypeClass = String.class),
            @ApiImplicitParam(name = "authStatus", value = "用户的审核状态",dataTypeClass = String.class),
            @ApiImplicitParam(name = "authCode", value = "一组图片的唯一标识",dataTypeClass = String.class),
            @ApiImplicitParam(name = "remark", value = "审核拒绝的原因",dataTypeClass = String.class),
    })
    public R updateUserAuthStatus(@RequestParam(required = true) Long id, @RequestParam(required = true) Byte authStatus, @RequestParam(required = true) Long authCode, String remark) {
        // 审核: 1 修改user 里面的reviewStatus
        // 2 在authAuditRecord 里面添加一条记录

        userService.updateUserAuthStatus(id, authStatus, authCode, remark);

        return R.ok();
    }

    /** 用户端部分 **/


    @GetMapping("/current/info")
    @ApiOperation(value = "获取当前登录用户的详情")
    public R<User> currentUserInfo(){
        // 获取用户的Id
        String idStr = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getById(Long.valueOf(idStr));
        user.setPassword("****");
        user.setPaypassword("*****");
        return R.ok(user) ;
    }

    //Todo 这个控制类有bug，java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because the return value of "springfox.documentation.service.Parameter.getName()" is null
    /** 用户的实名认证
     *
     * @param userAuthForm
     * @return
     */
    @PostMapping("/authAccount")
    @ApiOperation(value = "用户的实名认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "" ,value = "",dataTypeClass = String.class)
    })
    public R identifyCheck(@RequestBody UserAuthForm userAuthForm){
        String idStr = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean isOk = userService.identifyVerify(Long.valueOf(idStr),userAuthForm) ;
        if(isOk){
            return R.ok() ;
        }
        return R.fail("认证失败") ;
    }

    @PostMapping("/authUser")
    @ApiOperation(value = "用户进行高级认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imgs",value ="用户的图片地址" ,dataTypeClass = String.class)
    })
    public  R authUser(@RequestBody String[] imgs){
        String idStr = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        userService.authUser(Long.valueOf(idStr), Arrays.asList(imgs)) ;
        return R.ok() ;
    }

//http://localhost:8081/#/usercenter/modify-phone

    @PostMapping("/updatePhone")
    @ApiOperation(value = "修改手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "updatePhoneParam",value = "updatePhoneParam 的json数据",dataTypeClass = String.class)
    })
    public R updatePhone(@RequestBody UpdatePhoneParam updatePhoneParam){
        String idStr = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean isOk =  userService.updatePhone(Long.valueOf(idStr),updatePhoneParam) ;
        if(isOk){
            return R.ok() ;
        }
        return R.fail("修改失败") ;
    }


    @GetMapping("/checkTel")
    @ApiOperation(value = "检查新的手机号是否可用,如可用,则给该新手机发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile" ,value = "新的手机号",dataTypeClass = String.class),
            @ApiImplicitParam(name = "countryCode" ,value = "手机号的区域",dataTypeClass = String.class)
    })
    public R checkNewPhone(@RequestParam(required = true) String mobile,@RequestParam(required = true) String countryCode){
        boolean isOk =   userService.checkNewPhone(mobile,countryCode) ;
        return isOk ? R.ok():R.fail("新的手机号校验失败") ;
    }



}
