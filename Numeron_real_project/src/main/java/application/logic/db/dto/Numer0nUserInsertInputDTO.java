package application.logic.db.dto;

import java.sql.Timestamp;

/**
 * 利用者テーブル（numer0n_user）のDB情報検索DTO
 * @author shiraishitoshio
 *
 */
public class Numer0nUserInsertInputDTO extends AbstractInputDTO {

	/**
	 * 名前
	 */
	private String name;

	/**
	 * 桁
	 */
	private Integer digit;

	/**
	 * 難易度
	 */
	private String difficulty;

	/**
	 * 最終対戦日時
	 */
	private Timestamp lastPlayTime;

	/**
	 * 有効無効フラグ
	 */
	private String validFlg;

	/**
	 * 名前を取得する
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定する
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 桁を取得する
	 * @return digit
	 */
	public Integer getDigit() {
		return digit;
	}

	/**
	 * 桁を設定する
	 * @param digit
	 */
	public void setDigit(Integer digit) {
		this.digit = digit;
	}

	/**
	 * 難易度を取得する
	 * @return difficulty
	 */
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * 難易度を設定する
	 * @param difficulty
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * 最終対戦日時を取得する
	 * @return lastPlayTime
	 */
	public Timestamp getLastPlayTime() {
		return lastPlayTime;
	}

	/**
	 * 最終対戦日時を設定する
	 * @param lastPlayTime
	 */
	public void setLastPlayTime(Timestamp lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}

	/**
	 * 有効無効フラグを取得する
	 * @return validFlg
	 */
	public String getValidFlg() {
		return validFlg;
	}

	/**
	 * 有効無効フラグを設定する
	 * @param validFlg
	 */
	public void setValidFlg(String validFlg) {
		this.validFlg = validFlg;
	}

}
