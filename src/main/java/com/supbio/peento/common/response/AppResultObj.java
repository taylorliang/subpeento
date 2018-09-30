package com.supbio.peento.common.response;


/**
 * 接口统一返回对象
 * @author Administrator
 */
public class AppResultObj<T> {

	public final static int CODE_OK = 2000;// 成功
	public final static int CODE_NOT_PASS = 3000;// uri权限校验不通过
	public final static int CODE_NOT_ALLOW = 3425;// 不允许
	public final static int CODE_PARAM_ERR = 3406;// 参数错误
	public final static int CODE_SERVER_ERR = 4000;// 服务器异常
    public static final int CODE_TOKEN_INVALID = 3426;//token失效

    private int code = CODE_OK; // 状态码，默认成功
	private String msg = "OK"; // 调用结果消息，默认OK
	private T data = null; // 结果数据
	private boolean success = true;

	public AppResultObj() {
	}

	public AppResultObj(T data) {
		this.data = data;
	}

	public AppResultObj(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public AppResultObj(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

    public AppResultObj(int code, String msg, T data, boolean success) {
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
    }

    /**
	 * 接口调用成功，传入需要返回的data数据
	 * 
	 * @param data
	 * @return
	 */
	public static <T> AppResultObj<T> success(T data) {
		return new AppResultObj<T>(data);
	}

	/**
	 * uri权限校验不通过
	 *
	 * @return
	 */
	public static <T> AppResultObj<T> notPass() {
		return new AppResultObj<T>(AppResultObj.CODE_NOT_PASS, "权限不足，无法操作该资源", null);
	}

	/**
	 * uri权限校验不通过
	 *
	 * @return
	 */
	public static <T> AppResultObj<T> notPass(String msg) {
		return new AppResultObj<T>(AppResultObj.CODE_NOT_PASS, msg, null);
	}

	/**
	 * 接口调用失败：不允许未登录用户调用此接口
	 * 
	 * @return
	 */
	public static <T> AppResultObj<T> notAllow() {
		return new AppResultObj<T>(AppResultObj.CODE_NOT_ALLOW, "未经许可的用户", null);
	}

	/**
	 * 接口调用失败：不允许未登录用户调用此接口
	 * 
	 * @return
	 */
	public static <T> AppResultObj<T> notAllow(String msg) {
		return new AppResultObj<T>(AppResultObj.CODE_NOT_ALLOW, msg, null);
	}

	/**
	 * 接口调用失败：参数错误
	 * 
	 * @return
	 */
	public static <T> AppResultObj<T> parameterError() {
		return new AppResultObj<T>(AppResultObj.CODE_PARAM_ERR, "参数解析错误", null);
	}

	/**
	 * 接口调用失败：参数错误
	 * 
	 * @return
	 */
	public static <T> AppResultObj<T> parameterError(String msg) {
		return new AppResultObj<T>(AppResultObj.CODE_PARAM_ERR, msg, null);
	}

	/**
	 * 接口调用失败：服务器异常
	 * 
	 * @return
	 */
	public static <T> AppResultObj<T> serverError() {
		return new AppResultObj<T>(AppResultObj.CODE_SERVER_ERR, "服务器异常", null);
	}

	/**
	 * 接口调用失败：服务器异常
	 * 
	 * @return
	 */
	public static <T> AppResultObj<T> serverError(String msg) {
		return new AppResultObj<T>(AppResultObj.CODE_SERVER_ERR, msg, null);
	}

	public static <T> AppResultObj<T> newResult(int code, String msg, T data) {
		return new AppResultObj<T>(code, msg, data);
	}

	public static <T> AppResultObj<T> newResult(int code, String msg) {
		return new AppResultObj<T>(code, msg);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
