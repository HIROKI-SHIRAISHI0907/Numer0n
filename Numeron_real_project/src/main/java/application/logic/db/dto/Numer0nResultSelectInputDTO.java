package application.logic.db.dto;

/**
 * 結果テーブル（numer0n_result）のDB情報検索DTO
 * @author shiraishitoshio
 *
 */
public class Numer0nResultSelectInputDTO extends AbstractInputDTO {

	/**
	 * ユーザーID
	 */
	private String userid;

	/**
	 * 取得数字情報
	 */
	private String infoNum;

	/**
	 * ユーザーIDを取得する
	 * @return userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * ユーザーIDを設定する
	 * @param userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * 取得数字情報を取得する
	 * @return infoNum
	 */
	public String getInfoNum() {
		return infoNum;
	}

	/**
	 * 取得数字情報を設定する
	 * @return infoNum
	 */
	public void setInfoNum(String infoNum) {
		this.infoNum = infoNum;
	}

}
