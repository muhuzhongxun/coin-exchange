package ltd.muhuzhongxun.service;

import ltd.muhuzhongxun.model.WebLog;

public interface TestService {

    /**
     * 通过username 查询weblog
     *
     */
    WebLog get(String username) ;
}
