package com.purchaser.constants;

/*
 * 作者: dou
 * 时间: 2018-06-19 13:13:52
 * des: 常量类
 * */
public class Constant {

	public final static String APP_ID = "wxa8d461eba30468fd";

	public final static String APP_SECRET = "c4feeff6c6cb6473428b3beb5f048605";

	public final static String MCH_ID = "1507655211";

	public final static String APP_KEY = "6F0CC7EDB4E4E828BB4E37BE5B93E4C7";

	/**
	 * 签到最大距离
	 */
	public final static double SIGN_MAX_DISTANCE = 3000;

	/**
	 * 审核状态(未审核)
	 */
	public final static Integer AUDIT_STATUS_NOT = 0;

	/**
	 * 审核状态(审核通过)
	 */
	public final static Integer AUDIT_STATUS_PASS = 1;

	/**
	 * 审核状态(审核不通过)
	 */
	public final static Integer AUDIT_STATUS_UN_PASS = 2;

	/**
	 * 图片路径
	 */
	public final static String PICTURE_PATH = "D:/java/purchaser/picture";

	/**
	 * 活动商品类型
	 */
	public final static String ACTIVE_PRODUCT_TYPE = "A";

	/**
	 * 会员订单类型
	 */
	public final static String MEMBER_PRODUCT_TYPE = "M";

	/**
	 * 订单状态(未支付)
	 */
	public final static Integer ORDER_STATUS_BEPAIED = 0;

	/**
	 * 订单状态(已支付)
	 */
	public final static Integer ORDER_STATUS_PAIED = 1;

	/**
	 * 成功
	 */
	public final static String SUCCESS = "SUCCESS";

	/**
	 * 会员无效
	 */
	public final static Integer MEMBER_INVALID = 0;

	/**
	 * 会员有效
	 */
	public final static Integer MEMBER_VALID = 1;

	/**
	 * 会员有效期已过，需要重新缴费
	 */
	public final static Integer MEMBER_VALID_EXPIRED = 2;

	/**
	 * 活动邀请码(未使用)
	 */
	public final static Integer ACTIVE_CODE_USE = 0;

	/**
	 * 活动邀请码(已使用)
	 */
	public final static Integer ACTIVE_CODE_USED = 1;

	/**
	 * 活动邀请码类型(嘉宾)
	 */
	public final static Integer ACTIVE_CODE_DISTINGUISHED = 0;

	/**
	 * 活动邀请码类型(厂商)
	 */
	public final static Integer ACTIVE_CODE_MANUFACTURER = 1;
	
	/**
	 * 活动状态(关闭)
	 */
	public final static Integer ACTIVE_STATUS_CLOSE = 0;

	/**
	 * 活动状态(开启)
	 */
	public final static Integer ACTIVE_STATUS_OPEN = 1;
	
	/**
	 * 学历parent
	 */
	public final static Integer PARAMETER_STUDY_PARENT = 23;
}
