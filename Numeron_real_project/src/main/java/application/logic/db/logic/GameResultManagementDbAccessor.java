package application.logic.db.logic;

import java.util.List;

import application.logic.db.dto.AbstractInputDTO;

/**
 * Numer0nDBアクセス用整理クラス
 * <p>
 * 難易度:EXHAUSTED,INSANEで主に使用
 * </p>
 * @author shiraishitoshio
 *
 */
public interface GameResultManagementDbAccessor {

	/**
	 * Numer0nで得た結果をDBに登録する
	 */
	public Integer insertResultData(String dbAccessId, AbstractInputDTO dto);

	/**
	 * Numer0nで得た結果をDBから取得する
	 * @return 処理結果リスト
	 */
	public <E> List<E> getResultData(String dbAccessId, AbstractInputDTO dto);

}
