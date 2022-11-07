package ltd.muhuzhongxun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.muhuzhongxun.domain.UserAuthAuditRecord;

import java.util.List;

public interface UserAuthAuditRecordService extends IService<UserAuthAuditRecord> {

    /**
     * 获取一个用户的审核记录
     * @param id
     * @return
     */
    List<UserAuthAuditRecord> getUserAuthAuditRecordList(Long id);
}
