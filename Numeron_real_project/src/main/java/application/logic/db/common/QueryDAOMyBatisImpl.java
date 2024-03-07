package application.logic.db.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * QueryDAOインターフェースのMyBatis用実装クラス
 * @author shiraishitoshio
 *
 */
public class QueryDAOMyBatisImpl extends AbstractMyBatisImplBase implements QueryDAO {

	/**
	 * コンストラクタ
	 * @param commonNameSpace ネームスペース
	 */
	public QueryDAOMyBatisImpl(String commonNameSpace) {
		super(commonNameSpace);
	}

	/**
	 * {@inheritDoc}
	 * @return
	 */
	@Override
	public <E> List<E> executeForObjectList(String sqlId, Object params) {
		SqlSession sqlSessionTemplate = getSqlSession();
		List<E> list = sqlSessionTemplate.selectList(getSqlId(sqlId), params);
		return list;
	}


}
