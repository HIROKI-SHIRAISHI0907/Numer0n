package application.logic.human;

/**
 * プレーヤーCPUが行う処理をまとめたインターフェース
 * @author shiraishitoshio
 *
 */
public abstract class Human {

	/**
	 * 使用オプション,正解の数字を決定する
	 */
	abstract void decideHumanLogic();

	/**
	 * 数字をコールする
	 */
	abstract void doCallNumber();

	/**
	 * オプションを使用する(攻撃)
	 */
	abstract String useOffenseOption();

	/**
	 * オプションを使用する(防御)
	 */
	abstract String useDiffenseOption();

	/**
	 * 候補の数字をリストに追加する
	 * @param number n桁の数字
	 */
	abstract void addCandidateNumberList(String number);

	/**
	 * 候補ではない数字をリストに追加する
	 * @param number n桁の数字
	 */
	abstract void addNotCandidateNumberList(String number);

}
