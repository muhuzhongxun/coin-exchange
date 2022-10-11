package ltd.muhuzhongxun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.muhuzhongxun.domain.UserAddress;
import ltd.muhuzhongxun.mapper.UserAddressMapper;
import ltd.muhuzhongxun.service.UserAddressService;
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService{

    /**
     * 通过用户的Id 分页查询用户的钱包地址
     *
     * @param page   分页参数
     * @param userId 用户的Id
     * @return
     */
    @Override
    public Page<UserAddress> findByPage(Page<UserAddress> page, Long userId) {
        return page(page ,new LambdaQueryWrapper<UserAddress>().eq(UserAddress::getUserId,userId));
    }
}
