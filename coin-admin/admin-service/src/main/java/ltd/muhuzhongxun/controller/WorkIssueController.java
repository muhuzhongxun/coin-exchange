package ltd.muhuzhongxun.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import ltd.muhuzhongxun.domain.WorkIssue;
import ltd.muhuzhongxun.model.R;
import ltd.muhuzhongxun.service.WorkIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/workIssues")
@Api(tags = "客户工单的控制器")
public class WorkIssueController {


    @Autowired
    private WorkIssueService workIssueService;


    @GetMapping
    @ApiOperation(value = "分页条件查询工单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "工单当前的处理状态", dataTypeClass = String.class),
            @ApiImplicitParam(name = "startTime", value = "工单创建的起始时间", dataTypeClass = String.class),
            @ApiImplicitParam(name = "endTime", value = "工单创建的截至时间", dataTypeClass = String.class),
            @ApiImplicitParam(name = "current", value = "当前页", dataTypeClass = String.class),
            @ApiImplicitParam(name = "size", value = "每页显示的条数", dataTypeClass = String.class),
    })
    @PreAuthorize("hasAuthority('work_issue_query')")
    public R<Page<WorkIssue>> findByPage(@ApiIgnore Page<WorkIssue> page, Integer status, String startTime, String endTime) {
        page.addOrder(OrderItem.desc("last_update_time"));
        Page<WorkIssue> workIssuePage = workIssueService.findByPage(page, status, startTime, endTime);
        return R.ok(workIssuePage);
    }


    @PatchMapping
    @ApiOperation(value = "回复某个工单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "工单的ID", dataTypeClass = String.class),
            @ApiImplicitParam(name = "answer", value = "工单的answer", dataTypeClass = String.class),
    })
    @PreAuthorize("hasAuthority('work_issue_update')")
    public R work_issue_update(Long id, String answer) {
        WorkIssue workIssue = new WorkIssue();
        workIssue.setId(id);
        workIssue.setAnswer(answer);
        // 设置回复人Id
        String userIdstr = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        workIssue.setAnswerUserId(Long.valueOf(userIdstr));
        boolean updateById = workIssueService.updateById(workIssue);
        if (updateById) {
            return R.ok();
        }
        return R.fail("回复失败");
    }
}
