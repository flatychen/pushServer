package cn.flaty.NettyPush.utils;


import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *	json 工具类，依赖fastjson，反序列化对象时支持嵌套引用;
 *
 * @author flatychen
 * @date 2014-5-21
 */
public class FastJsonUtils {

	/**
	 * 默认时间格式
	 */
	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * fastjson 默认转换，时间为long类型，支持所有对象
	 * 
	 * @author flatychen
	 * @date 2014-5-21
	 * @param o
	 * 
	 * @return
	 */
	public static String toJsonString(Object o) {
		return JSON.toJSONString(o);
	}

	/**
	 * 使用时间｛yyyy-MM-dd HH:mm:ss｝的转换;
	 * 
	 * @author flatychen
	 * @date 2014-5-21
	 * @param o
	 * @return
	 */
	public static String toJsonStringWithDefaultDateFormat(Object o) {
		return toJsonStringWithDateFormat(o, dateFormat);
	}

	/**
	 * 带时间格时的转换
	 * 
	 * @author flatychen
	 * @date 2014-5-21
	 * @param o
	 * @param dateFormat
	 *            时间格式
	 * @return
	 */
	public static String toJsonStringWithDateFormat(Object o, String dateFormat) {
		return JSON.toJSONStringWithDateFormat(o, dateFormat, new SerializerFeature[0]);
	}

	/**
	 * 反列化对象，支持嵌套引用
	 * @author flatychen
	 * @date 2014-5-21
	 * @param json
	 * @param clazz class类型
	 * @return
	 */
	public static <T> T praseToObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	/**
	 * 反列化数组类,JSON格式必须为[]，支持嵌套引用
	 * @author flatychen
	 * @date 2014-5-21
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> praseToList(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}

	
	/**
	 * 解析成Map<String,String>,用于从JSON从筛选数据再解析;
	 * @author flatychen
	 * @date 2014-5-22
	 * @param json
	 * @return
	 */
	public static Map<String, String> praseToMap(String json) {
		return JSON.parseObject(json, new TypeReference<Map<String,String>>(){});
	}
	
}
