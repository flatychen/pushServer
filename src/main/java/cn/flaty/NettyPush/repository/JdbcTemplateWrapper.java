package cn.flaty.NettyPush.repository;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Spring jdbcTemplate 包装器类，提供基本SQL操作
 *
 * @author flatychen
 *
 */

@Component
public class JdbcTemplateWrapper {

	private static Logger log = LoggerFactory
			.getLogger(JdbcTemplateWrapper.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final <T> List<T> query(String sql, Class<T> clazz, Object args[]) {
		List<T> l = null;
		try {
			log.info("====>>query{ sql:{}, args:{} }", sql,
					Arrays.toString(args));
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
		log.info("====>>queryForLong[ sql:[ {} ] , args:[ {} ]  ]", sql,
				Arrays.toString(args));
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
		log.info("====>>queryForInt[ sql:[ {} ] , args:[ {} ]  ]", sql,
				Arrays.toString(args));
		return this.jdbcTemplate.queryForInt(sql, args);

	}

	/**
	 * 返回 object eg: Integer,Object
	 *
	 * @param sql
	 * @param args
	 * @return
	 * @author flatychen
	 * @param <T>
	 * @date 2014-1-10
	 */
	public final <T> T queryForObject(String sql, Class<T> clazz, Object args[]) {
		log.info("====>>queryForObject[ sql:[{}] , args:[{}]  ]", sql,
				Arrays.toString(args));
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
		log.info("====>>saveORUpdate[ sql:[ {} ] , args:[ {} ]  ]", sql,
				Arrays.toString(args));
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
		log.info("====>>batchInsert[ sql:[ {} ] , args:[ {} ] ]", sql,
				Arrays.toString(listArgs.toArray()));
		int num[] = null;
		try {
			num = jdbcTemplate.batchUpdate(sql, listArgs);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return num.length;
	}

}
