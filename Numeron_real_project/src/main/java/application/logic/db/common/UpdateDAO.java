package application.logic.db.common;

/**
 * UpdateDAOインターフェース
 * <p>
 * 更新系SQLを実行するためのDAOインターフェース
 * </p>
 * @author shiraishitoshio
 *
 */
public interface UpdateDAO {

	/**
	 * <p>
	 * 引数sqlIDで指定されたSQLを実行して、処理件数を返却
	 * </p>
	 * @param sqlId 実行するSQLID
	 * @param params SQLにバインドする値を格納したオブジェクト
	 * @return 処理件数
	 */
	public int execute(String sqlId, Object params);

}
