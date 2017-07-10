package com.xiaomai.supershopowner.common;


import java.io.File;

public class Constants {

	// 用户相关
	public final static String INIT_PASSWORD = "123456";
	public final static int CHECKCODE_VALIDTIME = 5;
	// public final static String LOGIN_USER_SESSION = "loginUserInfo";

	// mark (system)
	public final static String FILE_SEPERATOR = File.separator;
	public final static String LINE_SEPERATOR = System.getProperty("line.separator");
	public final static String SUBFIX_TXT = ".txt";
	public final static String SUBFIX_LOG = ".log";
	public final static String SUBFIX_CSV = ".csv";
	public final static String SUBFIX_PDF = ".pdf";
	public final static String SUBFIX_JPG = ".jpg";
	public final static String SUBFIX_PNG = ".png";

	// mark
	public final static String MARK_BLANK = " ";
	public final static String MARK_ZERO = "0";
	public final static String MARK_CARET = "^";
	public final static String MARK_DOLLAR = "$";
	public final static String MARK_AND = "&";
	public final static String MARK_TILDE = "~";
	public final static String MARK_EXCLAMATION = "!";
	public final static String MARK_EQUAL = "=";
	public final static String MARK_POUND = "#";
	public final static String MARK_ASTERISK = "*";
	public final static String MARK_DOT = ".";
	public final static String MARK_COLON = ":";
	public final static String MARK_COMMA = ",";
	public final static String MARK_SEMICOLON = ";";
	public final static String MARK_QUESTION = "?";
	public final static String MARK_QUOTATION = "'";
	public final static String MARK_DOUBLE_QUOTATION = "\"";
	public final static String MARK_LEFT_PAREN = "(";
	public final static String MARK_RIGHT_PAREN = ")";
	public final static String MARK_LEFT_BRACKET = "[";
	public final static String MARK_RIGHT_BRACKET = "]";
	public final static String MARK_LEFT_BRACE = "{";
	public final static String MARK_RIGHT_BRACE = "}";
	public final static String MARK_DOUBLESLASH = "//";
	public final static String MARK_SLASH = "/";
	public final static String MARK_BACKLASH = "\\";
	public final static String MARK_UNDERLINE = "_";
	public final static String MARK_VIRGULE = "-";
	public final static String MARK_VIRTICALVIRGULE = "|";
	public final static String MARK_TAB = "\t";
	public final static String MARK_WRAP = "\n";
	public final static String MARK_RETURN = "\r";

	// tag
	public final static String TAG_HTTP = "http://";
	public final static String TAG_START_LEFT = "<";
	public final static String TAG_START_RIGHT = ">";
	public final static String TAG_END_LEFT = "</";
	public final static String TAG_END_RIGHT = "/>";
	public final static String TAG_A = "a";
	public final static String TAG_A_START = "<a>";
	public final static String TAG_A_END = "</a>";
	public final static String TAG_BLANK = "&nbsp;";

	// encode
	public final static String ENCODE_UTF8 = "utf-8";
	public final static String ENCODE_GBK = "gbk";
	public final static String URL_VIRTICALVIRGULE = "%7C";

	// format
	public final static String REGULAR_FNAME_ILLEGAL = "[/\\\\:?*\"<>|]";
	public final static String REGULAR_VIRTICALVIRGULE = "\\|";
	public final static String REGULAR_VIRTICALVIRGULE_THREE = "\\|\\|\\|";
	public final static String REGULAR_TAB = "\t";
	public final static String REGULAR_NUMBER = "[0-9.:]+";
	public final static String REGULAR_IP = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
	public final static String REGULAR_DOMAIN = "[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+";

	// 资源存储策略,add by Chenzhijun
	public static final String RESOURCE_USER_ABOUT = "public/pic/%s/";
	public static final String RESOURCE_SHARE_QR_ABOUT = "public/qrcode/";
	public static final String RESOURCE_IMAGE_THUMB = "public/thumb/%s/";
	public static final String RESOURCE_ASK_REPORT = "public/report/ask/";
	public static final String RESOURCE_IMAGE_REPORT = "public/report/image/";
	public static final String RESOURCE_DEFAULT_LOGO = "title.png";
	public static final String RESOURCE_DEFAULT_BACKGROUD = "bg.png";

	// MIME
	public static final String MIME_PDF = "application/pdf";
	public static final String MIME_JSON = "application/json";

	// String
	public static final String NOTHING = " ";
	public static final String TAB = "\t";
	public static final String CHINESE_DOT = "。";

	// Session key

	public static final String SESSION_KEY_USER_ID = "SESSION_KEY_USER_ID";
	public static final String SESSION_KEY_LOGIN_TIME = "SESSION_KEY_LOGIN_TIME";

	//

}
