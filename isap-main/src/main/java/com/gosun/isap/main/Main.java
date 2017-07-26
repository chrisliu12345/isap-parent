package com.gosun.isap.main;

/**
 * main入口
 * 
 * @author lzk
 *
 */
public class Main {
	/**
	 * 主函数入口
	 * 
	 * @param args
	 *            args
	 */
	public static void main(String[] args) {
		PidHelper pid = new PidHelper();
		pid.savePid();
		com.alibaba.dubbo.container.Main.main(args);
	}

}
