package com.purchaser.schedule;
/*
 * 作者: dou
 * 时间: 2018-08-02 13:22:02
 * desc: 会员定时任务接口
 * */
public interface MemberSchedule {
	
	
	
	/**
	 * 会员续费提醒	
	 */
	public void memberExpiredNotify ();
		
	
	
	/**
	 * 会员过期状态修改
	 */
	public void memberExpired();

}
