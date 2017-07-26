package com.gosun.isap.warn.impl.alert.aop;

import com.alibaba.dubbo.rpc.RpcContext;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.PermissionService;
import com.gosun.isap.warn.api.alert.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>创建时间：2017/5/15 14:52</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Component
@Aspect
public class CheckPermissionAspect {
    private static final String POINTCUT_ALERT_LIST = "@within(com.gosun.isap.warn.impl.alert.aop.CheckListMenuPermission) "
            + "|| @annotation(com.gosun.isap.warn.impl.alert.aop.CheckListMenuPermission)";
    private static final String POINTCUT_GUARD_MENU = "@within(com.gosun.isap.warn.impl.alert.aop.CheckGuardMenuPermission) "
            + "|| @annotation(com.gosun.isap.warn.impl.alert.aop.CheckGuardMenuPermission)";
    private static final String POINTCUT_ALERT_PROCESS = "@within(com.gosun.isap.warn.impl.alert.aop.CheckProcessMenuPermission) "
            + "|| @annotation(com.gosun.isap.warn.impl.alert.aop.CheckProcessMenuPermission)";

    private static final String POINTCUT_ALERT = "@within(com.gosun.isap.warn.impl.alert.aop.CheckAlertPermission) "
            + "|| @annotation(com.gosun.isap.warn.impl.alert.aop.CheckAlertPermission)";
    private static final String POINTCUT_GUARD = "@within(com.gosun.isap.warn.impl.alert.aop.CheckGuardPermission) "
            + "|| @annotation(com.gosun.isap.warn.impl.alert.aop.CheckGuardPermission)";

    private static final String TOKEN = "token";

    private static final String ALERT_ID_PATTERN = "/alerts/(\\d+)";
    private static final int ALERT_ID_INDEX = 1;

    private static final String GUARD_ID_PATTERN = "/alerts/(\\d+)";
    private static final int GUARD_ID_INDEX = 1;

    @Autowired
    @Qualifier(AlertServiceId.PERMISSION_SERVICE)
    private PermissionService permissionService;

    @Autowired
    @Qualifier(AlertServiceId.USER_SERVICE)
    private UserService userService;


    /**
     * 权限验证 aop
     *
     * @param joinPoint 切面
     * @return 执行结果
     * @throws Throwable 执行中的异常
     */
    @Around(POINTCUT_ALERT_LIST)
    public Object checkListPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        Long userId = getUserId();
        System.out.println("check list permission");
        if (!permissionService.checkAlertListPermission(userId)) {
//            return ResponseData.error(AlertError.NO_PERMISSION);
        }
        return joinPoint.proceed();
    }

    /**
     * 权限验证 aop
     *
     * @param joinPoint 切面
     * @return 执行结果
     * @throws Throwable 执行中的异常
     */
    @Around(POINTCUT_GUARD_MENU)
    public Object checkGuardMenuPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        Long userId = getUserId();
        if (!permissionService.checkGuardManagementPermission(userId)) {
//            return ResponseData.error(AlertError.NO_PERMISSION);
        }
        return joinPoint.proceed();
    }

    /**
     * 权限验证 aop
     *
     * @param joinPoint 切面
     * @return 执行结果
     * @throws Throwable 执行中的异常
     */
    @Around(POINTCUT_ALERT_PROCESS)
    public Object checkProcessPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        Long userId = getUserId();
        if (!permissionService.checkAlertProcessPermission(userId)) {
//            return ResponseData.error(AlertError.NO_PERMISSION);
        }
        return joinPoint.proceed();
    }

    /**
     * 权限验证 aop
     *
     * @param joinPoint 切面
     * @return 执行结果
     * @throws Throwable 执行中的异常
     */
    @Around(POINTCUT_ALERT)
    public Object checkAlertPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        Long alertId = getIdFromUrl(ALERT_ID_PATTERN, ALERT_ID_INDEX);
        Long userId = getUserId();
        if (alertId == null || userId == null) {
            //            return ResponseData.error(AlertError.NO_PERMISSION);
        }

        if (!permissionService.checkAlertPermission(userId, alertId)) {
            //            return ResponseData.error(AlertError.NO_PERMISSION);
        }

        return joinPoint.proceed();
    }

    /**
     * 权限验证 aop
     *
     * @param joinPoint 切面
     * @return 执行结果
     * @throws Throwable 执行中的异常
     */
    @Around(POINTCUT_GUARD)
    public Object checkGuardPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        Long guardId = getIdFromUrl(GUARD_ID_PATTERN, GUARD_ID_INDEX);
        Long userId = getUserId();
        if (guardId == null || userId == null) {
            //            return ResponseData.error(AlertError.NO_PERMISSION);
        }

        if (!permissionService.checkGuardPermission(userId, guardId)) {
            //            return ResponseData.error(AlertError.NO_PERMISSION);
        }

        return joinPoint.proceed();
    }

    private Long getIdFromUrl(String regex, int index) {
        HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
        String url = request.getRequestURI();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            try {
                return Long.valueOf(matcher.group(index));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Long getUserId() {
        return userService.getUserId(getToken());
    }

    /**
     * 从上下文获取 httpRequest header 中的 token
     *
     * @return token
     */
    private String getToken() {
        HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
        if (request == null) {
            return null;
        }
        return request.getHeader(TOKEN);
    }

}
