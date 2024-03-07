package application.logic.db.common;

import java.util.List;

import application.logic.db.dto.AbstractInputDTO;
import application.logic.db.dto.AbstractOutputDTO;

/**
 * データベースアクセス処理。共通DAOインターフェース
 * @author shiraishitoshio
 *
 */
public interface CommonDAO {

	/**
	 * 複数検索用
	 * @param <E>
	 * @param dbAccessId DBアクセスID
	 * @param dto input用条件DTO(sqlMapにて使用)
	 * @return 検索結果
	 */
	public <E extends AbstractOutputDTO> List<E> selectForObjectList(String dbAccessId, AbstractInputDTO dto);

	/**
	 * 登録用
	 * @param <E>
	 * @param dbAccessId DBアクセスID
	 * @param dto input用条件DTO(sqlMapにて使用)
	 * @return 処理数
	 */
	public int insert(String dbAccessId, AbstractInputDTO dto);

}
