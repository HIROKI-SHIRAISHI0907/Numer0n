package application.logic.db.common;

import org.apache.ibatis.session.SqlSession;

/**
 * UpdateDAOインターフェースのMyBatis用実装クラス
 * @author shiraishitoshio
 *
 */
public class UpdateQueryDAOMyBatisImpl extends AbstractMyBatisImplBase implements UpdateDAO {

	/**
	 * コンストラクタ
	 * @param commonNameSpace ネームスペース
	 */
	public UpdateQueryDAOMyBatisImpl(String commonNameSpace) {
		super(commonNameSpace);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int execute(String sqlId, Object params) {
		SqlSession sqlSessionTemplate = getSqlSession();
		int row = sqlSessionTemplate.update(getSqlId(sqlId), params);
		return row;
	}


}
