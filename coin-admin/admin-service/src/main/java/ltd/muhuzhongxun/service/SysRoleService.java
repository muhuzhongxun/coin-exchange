package ltd.muhuzhongxun.service;

import ltd.muhuzhongxun.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysRoleService extends IService<SysRole> {


    /**
     * 判断一个用户是否为超级的管理员
     * @param userId
     * @return
     */
    boolean isSuperAdmin(Long userId);
}

