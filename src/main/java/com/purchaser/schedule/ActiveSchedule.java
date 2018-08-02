package com.purchaser.schedule;
/*
 * 作者: dou
 * 时间: 2018-08-01 17:28:37
 * desc: 活动定时任务接口
 * */
public interface ActiveSchedule {

	
	/**
	 * 活动开始前通知用户
	 */
	public void activeNotify();
	
	
}
