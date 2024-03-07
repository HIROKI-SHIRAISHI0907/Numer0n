package application.logic.info;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.Getter;

/**
 * 相手の情報をまとめておくクラス
 * @author shiraishitoshio
 */
@Service
public class Numer0nInfo {

	/**
	 * （CPU）が宣言した数字リスト
	 */
	@Getter
	public ArrayList<String> declaredCpuNumberList = new ArrayList<String>();

	/**
	 * （CPU）使用したアイテムリスト
	 */
	@Getter
	public ArrayList<String> usedCpuOptionList = new ArrayList<String>();

	/**
	 * （CPU）得られた情報リスト
	 */
	@Getter
	public ArrayList<String> cpuInfoList = new ArrayList<String>();

	/**
	 * （プレーヤー）が宣言した数字リスト
	 */
	@Getter
	public ArrayList<String> declaredPlayerNumberList = new ArrayList<String>();

	/**
	 * （プレーヤー）使用したアイテムリスト
	 */
	@Getter
	public ArrayList<String> usedPlayerOptionList = new ArrayList<String>();

	/**
	 * （プレーヤー）得られた情報リスト
	 */
	@Getter
	public ArrayList<String> playerInfoList = new ArrayList<String>();

	/**
	 * （CPU）宣言した数字を格納する
	 */
	public void addDeclaredCpuNumberList(String declaredCpuNumber) {
		this.declaredCpuNumberList.add(declaredCpuNumber);
	}

	/**
	 * （プレーヤー）宣言した数字を格納する
	 */
	public void addDeclaredPlayerNumberList(String declaredPlayerNumber) {
		this.declaredPlayerNumberList.add(declaredPlayerNumber);
	}

	/**
	 * （CPU）使用したオプションを格納する
	 */
	public void addUsedCpuOptionList(String usedCpuOption) {
		this.usedCpuOptionList.add(usedCpuOption);
	}

	/**
	 * （プレーヤー）使用したオプションを格納する
	 */
	public void addUsedPlayerOptionList(String usedPlayerOption) {
		this.usedPlayerOptionList.add(usedPlayerOption);
	}

	/**
	 * （CPU）オプションを使用した時などに得られた情報を格納する
	 */
	public void addCpuInfoList(String cpuInfoList) {
		this.cpuInfoList.add(cpuInfoList);
	}

	/**
	 * （プレーヤー）オプションを使用した時などに得られた情報を格納する
	 */
	public void addPlayerInfoList(String playerInfoList) {
		this.playerInfoList.add(playerInfoList);
	}

}
