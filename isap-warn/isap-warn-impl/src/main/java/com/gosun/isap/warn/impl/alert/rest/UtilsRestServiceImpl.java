package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.rest.UtilsRestService;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;

/**
 * <p>创建时间：2017-6-2 18:08</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckEmptyParams
public class UtilsRestServiceImpl implements UtilsRestService {
    @Autowired
    private AlertCommonService commonService;

    private static final int START = 0;
    private static final int SUGGEST_LIMIT = 10;

    @Override
    @GET
    @Path(GUARDS_GROUPS_SUGGEST)
    public ResponseData<List<Map<String, Object>>> suggestGuardGroup(@QueryParam(KEYWORD) String key) {

        List<Map<String, Object>> list = commonService.suggestGuardGroup(key);
        return ResponseData.init(list);
    }

    @Override
    @GET
    @Path(ALERTS_DEPARTMENT_SUGGEST)
    public ResponseData<List<Map<String, Object>>> suggestDepartment(@QueryParam(KEYWORD) String key) {
        List<Map<String, Object>> list = commonService.suggestDepartment(key);
        return ResponseData.init(list);
    }
}
