package com.xiaomai.supershopowner.common;

public class BizErr {

	public final static String EX_TRANSACTION_FAIL = "-1";

	// 增删改失败
	public final static String EX_ADD_FAIL = "-111";
	public final static String EX_UPDATE_FAIL = "-112";
	public final static String EX_DELETE_FAIL = "-113";

	// 用户
	public final static String EX_USER_NAMEEXIST = "-501"; // 登录名已经存在
	public final static String EX_USER_PASSWORDINVALID = "-502"; // 密码错误
	public final static String EX_USER_BYLOGOUT = "-503"; // 被注销
	public final static String EX_USER_BYLOGIN = "-504"; // 已经登录
	public final static String EX_USER_NOREGISTER = "-505"; // 用户未注册
	public final static String EX_USER_CHECKCODEINVALID = "-506"; // 验证码错误
	public final static String EX_USER_CHECKCODEINLIMIT = "-507"; // 验证码过期
	public final static String EX_USER_LOGINFAIL = "-511"; // 登陆失败

	public final static String EX_PUSH_RUNNING = "-801"; //
}
