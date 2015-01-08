package cn.flaty.NettyPush.clientRepo.jdbcWrapper;


import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.flaty.NettyPush.base.PageBean;
import cn.flaty.NettyPush.base.QueryPageObject;
import cn.flaty.NettyPush.clientRepo.pageSetter.PageSqlSetter;


/**
 * Spring jdbcTemplate 包装器类，提供基本SQL操作
 * 
 * @author flatychen
 * 
 */

@Component
public class JdbcTemplateWrapper {

	private static Logger log = Logger.getLogger(JdbcTemplateWrapper.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setPageSqlSetter(PageSqlSetter pageSqlSetter) {
		this.pageSqlSetter = pageSqlSetter;
	}

	private PageSqlSetter pageSqlSetter;

	private final <T> List<T> query(String sql, Class<T> clazz, Object args[]) {
		List<T> l = null;
		try {
			if (log.isInfoEnabled()) {
				log.info(MessageFormat.format(
						"======>>query'{' sql:{1}, args:{3} '}'", 1, sql, 3,
						Arrays.toString(args)));
			}
			l = this.jdbcTemplate.query(sql, args,
					new BeanPropertyRowMapper<T>(clazz));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return l;

	}

	/**
	 * class得到对象
	 * 
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final <T> T queryForBean(String sql, Class<T> clazz, Object args[]) {
		List<T> l = this.query(sql, clazz, args);
		if ((l != null) && (l.size() == 1)) {
			return l.get(0);
		} else {

		}
		return null;
	}

	/**
	 * class得到对象list
	 * 
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final <T> List<T> queryForBeanList(String sql, Class<T> clazz,
			Object args[]) {
		return this.query(sql, clazz, args);

	}

	/**
	 * 返回 long --> count(1)
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final long queryForLong(String sql, Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>queryForLong[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(args)));
		}
		return this.jdbcTemplate.queryForLong(sql, args);

	}

	/**
	 * 返回 int -->count(1)
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final int queryForInt(String sql, Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>queryForInt[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(args)));
		}
		return this.jdbcTemplate.queryForInt(sql, args);

	}
	
	
	/**
	 * 返回 object  eg: Integer,Object
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @author flatychen
	 * @param <T>
	 * @date 2014-1-10
	 */
	public final <T> T queryForObject(String sql, Class<T> clazz,Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>queryForObject[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(args)));
		}
		return this.jdbcTemplate.queryForObject(sql, clazz, args);

	}

	/**
	 * 
	 * @author flatychen
	 * @param sql
	 * @param args
	 * @return
	 * @date 2014-1-12
	 */
	public int saveORUpdate(String sql, Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>saveORUpdate[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(args)));
		}
		int num = 0;
		try {
			num = jdbcTemplate.update(sql, args);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return num;
	}

	/**
	 * 批量更新
	 * 
	 * @author flatychen
	 * @param sql
	 * @param listArgs
	 * @return
	 */
	public int batchUpdate(String sql, List<Object[]> listArgs) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>batchInsert[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(listArgs.toArray())));
		}
		int num[] = null;
		try {
			num = jdbcTemplate.batchUpdate(sql, listArgs);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return num.length;
	}

	/**
	 *  分页方法 
	 * @author flatychen
	 * @param pageSize
	 * @param toPage
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public <T> PageBean<T> queryForPage(QueryPageObject query, String sql,
			Class<T> clazz, Object[] args) {
		String countSql = pageSqlSetter.getCountSql(sql);
		String querySql = pageSqlSetter.getQuerySql(sql);
		
		int totalRow = this.queryForInt(countSql, args);
		List<T> list = this.queryForBeanList(querySql, clazz, args);
		
		PageBean<T> page = new PageBean<T>(query.getPageNo(),query.getPageSize(),totalRow,list);
		return page;

	}

}
