package ltd.muhuzhongxun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.muhuzhongxun.domain.UserBank;
import ltd.muhuzhongxun.mapper.UserBankMapper;
import ltd.muhuzhongxun.service.UserBankService;

@Service
public class UserBankServiceImpl extends ServiceImpl<UserBankMapper, UserBank> implements UserBankService{

    /**
     * 查询用户的银行卡信息
     *
     * @param page  分页参数
     * @param usrId 用户的Id
     * @return
     */
    @Override
    public Page<UserBank> findByPage(Page<UserBank> page, Long usrId) {
        return page(page ,new LambdaQueryWrapper<UserBank>().eq(usrId != null ,UserBank::getUserId ,usrId));
    }
}
