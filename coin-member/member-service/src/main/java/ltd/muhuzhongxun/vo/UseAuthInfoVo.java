package ltd.muhuzhongxun.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltd.muhuzhongxun.domain.User;
import ltd.muhuzhongxun.domain.UserAuthAuditRecord;
import ltd.muhuzhongxun.domain.UserAuthInfo;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "用户认证的详细信息")
public class UseAuthInfoVo  implements Serializable{

    @ApiModelProperty(value = "用户")
    private User user ;

    @ApiModelProperty(value = "用户认证的详情列表")
    private List<UserAuthInfo> userAuthInfoList ;

    @ApiModelProperty(value = "用户审核历史")
    private List<UserAuthAuditRecord> authAuditRecordList ;

}
