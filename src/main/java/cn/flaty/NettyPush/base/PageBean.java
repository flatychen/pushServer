package cn.flaty.NettyPush.base;

import java.util.List;

/**
 * 
 * 分页
 * @author flatychen
 *
 * @param <T>
 */
public class PageBean<T> {
	
	public final int DEFAULT_PAGE_SIZE = 15;
	
	private int pageSize = DEFAULT_PAGE_SIZE ;
	
	private int toPage;
	
	private int totalPage;
	
	private int totalRows;
	
	private List<T> objList;
	
	

	public PageBean(int toPage, int pageSize, int totalRows, List<T> objList) {
		super();
		this.objList = objList;
		initPageParams(toPage,pageSize,totalRows);
	}

	private void initPageParams(int toPage, int pageSize, int totalRows) {
		//页大小
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
		
		//分页数
		if(totalRows > 0){
			this.totalRows = totalRows;
			int total = totalRows / pageSize;
			if (totalRows % pageSize != 0) {
				total++;
			}
			totalPage= total;
		}else{
			totalPage = 1;
		}
		
		//请求页合法化
		if (toPage <= 0 ) {
			this.toPage = 1;
		} else if(toPage >= totalPage){
			toPage= totalPage;
		} else{
			this.toPage = toPage;
		} 
		
		
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setObjList(List<T> objList) {
		this.objList = objList;
	}
	
	

}
