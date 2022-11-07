package ltd.muhuzhongxun.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.domain.UserAddress;

public interface UserAddressService extends IService<UserAddress>{

    /**
     * 通过用户的Id 分页查询用户的钱包地址
     * @param page
     * 分页参数
     * @param userId
     * 用户的Id
     * @return
     */
    Page<UserAddress> findByPage(Page<UserAddress> page, Long userId);
}
