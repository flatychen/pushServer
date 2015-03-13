package cn.flaty.NettyPush.base;

/**
 * 查询bean
 * @author flatychen
 * @date 2014-5-8
 */
public class QueryPageObject {

	public static final int DEFAULT_PAGE_SIZE = 15;

	public static final int DEFAULT_PAGE_NAV_SIZE = 5;

	public static final int DEFAULT_PAGE_NO = 1;
	
	public static final int ALL_DATA = 0;

	/**
	 * 页面大小
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 请求页
	 */
	private Integer pageNo = DEFAULT_PAGE_NO;

	/**
	 * 分页数字导航显示
	 */
	private Integer pageNavSize = DEFAULT_PAGE_NAV_SIZE;

	public QueryPageObject(Integer pageNo,Integer pageSize,  Integer pageNavSize) {
		super();
		this.setPageNavSize(pageNavSize);
		this.setPageNo(pageNo);
		this.setPageSize(pageSize);
	}

	public QueryPageObject(Integer pageNo, Integer pageSize) {
		this(pageNo,pageSize,DEFAULT_PAGE_NAV_SIZE);
	}

	public QueryPageObject() {
		this(DEFAULT_PAGE_NO , DEFAULT_PAGE_SIZE);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 *
	 * @author flatychen
	 * @date 2014-11-17
	 * @param pageSize 当为0时，查找所有
	 * @return
	 * @version 
	 */
	public QueryPageObject setPageSize(Integer pageSize) {
		this.pageSize = initPageNumberValid(pageSize);
		return this;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public QueryPageObject setPageNo(Integer pageNo) {
		this.pageNo = initPageNumberValid(pageNo);
		return this;
	}

	public int getPageNavSize() {
		return pageNavSize;
	}

	public QueryPageObject setPageNavSize(Integer pageNavSize) {
		this.pageNavSize = initPageNumberValid(pageNavSize);
		return this;
	}

	private int initPageNumberValid(Integer number) {
		return (number == null || number < 0) ? 1 : number;
	}

}
