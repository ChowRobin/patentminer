package org.patentminer.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.patentminer.bean.ResultBean;
import org.patentminer.exception.CheckException;
import org.patentminer.exception.ConflictException;
import org.patentminer.exception.UnloginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理和包装异常
 */
@Component
@Aspect
public class ControllerAOP {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    @Around("execution(public org.patentminer.bean.ResultBean *(..)) && args(.., res)")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp, HttpServletResponse res) {
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e, res);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e, HttpServletResponse res) {
        ResultBean<?> result = new ResultBean(res);

        // 已知异常
        if (e instanceof CheckException) {
            result.setMsg(e.getLocalizedMessage())
                    .setCode(ResultBean.FAIL)
                    .setHttpStatus(404);
        } else if (e instanceof UnloginException) {
            result.setMsg("Unlogin")
                    .setCode(ResultBean.NO_LOGIN)
                    .setHttpStatus(401);
        } else if (e instanceof ConflictException) {
            System.out.println("ConfictException");
            result.setMsg(e.getMessage())
                    .setCode(ResultBean.FAIL)
                    .setHttpStatus(409);
        } else {
            logger.error(pjp.getSignature() + " error ", e);
            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(e.toString())
                    .setCode(ResultBean.FAIL)
                    .setHttpStatus(400);
        }

        return result;
    }
}
