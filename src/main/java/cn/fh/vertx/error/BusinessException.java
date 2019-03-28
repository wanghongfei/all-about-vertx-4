package cn.fh.vertx.error;

/**
 * Created by wanghongfei on 2019-03-27.
 */
public class BusinessException extends RuntimeException {
    /**
     * 仅包含message, 没有cause, 也不记录栈异常, 性能最高
     * @param msg
     */
    public BusinessException(String msg) {
        this(msg, false);
    }

    /**
     * 包含message, 可指定是否记录异常
     * @param msg
     * @param recordStackTrace
     */
    public BusinessException(String msg, boolean recordStackTrace) {
        super(msg, null, false, recordStackTrace);
    }

    /**
     * 包含message和cause, 会记录栈异常
     * @param msg
     * @param cause
     */
    public BusinessException(String msg, Throwable cause) {
        super(msg, cause, false, true);
    }
}
