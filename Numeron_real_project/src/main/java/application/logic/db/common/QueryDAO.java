package application.logic.db.common;

import java.util.List;

/**
 * QueryDAOインターフェース
 * <p>
 * 更新系SQLを実行するためのDAOインターフェース
 * </p>
 * @author shiraishitoshio
 *
 */
public interface QueryDAO {

	/**
	 * <p>
	 * 引数sqlIDで指定されたSQLを実行して、処理結果リストを返却
	 * </p>
	 * @param sqlId 実行するSQLID
	 * @param params SQLにバインドする値を格納したオブジェクト
	 * @return 処理結果リスト
	 */
	public <E> List<E> executeForObjectList(String sqlId, Object params);

}
