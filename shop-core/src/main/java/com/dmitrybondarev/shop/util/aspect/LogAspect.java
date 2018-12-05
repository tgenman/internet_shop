package com.dmitrybondarev.shop.util.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = Logger.getLogger(LogAspect.class);

    @Around("@annotation(loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {

        try {
            logBefore(joinPoint, loggable);
            Object object = joinPoint.proceed();
            logAfter(joinPoint, object, loggable);
            return object;
        }
        catch (Exception ex) {
            logException(joinPoint, ex, loggable);
            throw ex;
        }
    }

    public void logBefore(ProceedingJoinPoint joinPoint, Loggable loggable) {
        if (loggable.logBefore() && !loggable.logOnlyExceptions()) {
            writeToLog(new StringBuilder().append(" [Before] ")
                    .append(buildMethodString(joinPoint, loggable)).toString(), loggable);
        }
    }

    public void logAfter(ProceedingJoinPoint joinPoint, Object response, Loggable loggable) {
        if (loggable.logResponse() && !loggable.logOnlyExceptions()) {
            StringBuilder builder = new StringBuilder().append(" [After] ").append(buildMethodString(joinPoint, loggable));

            if (loggable.logArgumentsAndResults())
                builder.append(" [Return] ").append(response);

            writeToLog(builder.toString(), loggable);
        }
    }

    public void logException(ProceedingJoinPoint pjp, Exception ex, Loggable loggable) {
        if (loggable.logExceptions() || loggable.logOnlyExceptions()) {
            logger.error(new StringBuilder().append(" [Exception] ").append(buildMethodString(pjp, loggable))
                    .append(" [Message] ")
                    .append(ex.getLocalizedMessage()).append("\n")
                    .append(getStackTrace(ex)).toString());
        }
    }

    private String buildMethodString(ProceedingJoinPoint pjp, Loggable loggable)
    {

        StringBuilder builder = new StringBuilder("[class] ").append(pjp.getTarget()
                .getClass()
                .getSimpleName())
                .append(" [method] ")
                .append(pjp.getSignature());

        if (loggable.logArgumentsAndResults())
            builder.append(" [args] ").append(Arrays.toString(pjp.getArgs()));

        return builder.toString();
    }

    private static String getStackTrace(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        return writer.toString();
    }

    private void writeToLog(String msg, Loggable loggable)
    {
        switch (loggable.logLevel())
        {
            case TRACE:
                logger.trace(msg);
                break;

            case DEBUG:
                logger.debug(msg);
                break;

            case WARN:
                logger.warn(msg);
                break;

            default:
                logger.info(msg);
                break;
        }
    }

}
