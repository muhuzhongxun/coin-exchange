package ltd.muhuzhongxun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ltd.muhuzhongxun.domain.SysPrivilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.muhuzhongxun.domain.SysRole;
import ltd.muhuzhongxun.mapper.SysRoleMapper;
import ltd.muhuzhongxun.service.SysRoleService;
import org.springframework.util.StringUtils;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    /**
     * 判断一个用户是否为超级的管理员
     *
     * @param userId
     * @return
     */
    @Override
    public boolean isSuperAdmin(Long userId) {
        // 当用户的角色code 为：ROLE_ADMIN 时，该用户为超级的管理员
        // 用户的id->用户的角色->该角色的Code是否为ROLE_ADMIN
        String roleCode = sysRoleMapper.getUserRoleCode(userId);
        if (!StringUtils.isEmpty(roleCode) && roleCode.equals("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    /**
     * 使用角色的名称模糊分页角色查询
     *
     * @param page 分页数据
     * @param name 角色的名称
     * @return
     */
    @Override
    public Page<SysRole> findByPage(Page<SysRole> page, String name) {
        return page(page, new LambdaQueryWrapper<SysRole>()
                .like(
                !StringUtils.isEmpty(name),
                SysRole::getName,
                name
                )
        );
    }
}


