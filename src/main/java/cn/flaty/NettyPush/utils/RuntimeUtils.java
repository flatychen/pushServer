package cn.flaty.NettyPush.utils;

public class RuntimeUtils {

	private static Runtime runtime = Runtime.getRuntime();
	
	public static int getProcessors(){
		return runtime.availableProcessors() ;
	}
	
}
