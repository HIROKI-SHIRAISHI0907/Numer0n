package application.logic.db.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AbstractOutputDTO {

	/**
	 * 登録名
	 */
	private String registerName;

	/**
	 * タイムスタンプ(登録日時)
	 */
	private Timestamp registerTime;

	/**
	 * 更新名
	 */
	private String updateName;

	/**
	 * タイムスタンプ(更新日時)
	 */
	private Timestamp updateTime;

}
