package application.logic.info;

/**
 * 相手（プレーヤー）が得ている情報を元に、優先フラグ等のフラグを設定するクラス<br>
 * 基本的にはランダムでオプションなどを選ぶが、優先フラグなどがついている場合はそこから選ぶようにする。<br>
 * よりゲームさを出すためのクラス<br>
 * [基本前提条件]<br>
 * 当クラスはHARD以上のレベルのみ使用する
 * <p>
 * オプションを使用するようにした場合、以下の優先順位をつける。
 * 1,SLASH
 * 2,HIGHLOW
 * 3,TARGET
 * 4,SHUFFLE（数字が当てられそうになった場合の使用1。）
 * 5,CHANGE（数字が当てられそうになった場合の使用2。SHUFFLEに比べて数字そのものを変えられるため、SHUFFLEよりも使用優先度は低い）
 * 6,DOUBLE（ある程度の相手の数字が絞られた場合の最終手段として使用。）
 * </p>
 * @author shiraishitoshio
 */
public interface Numer0nNextActionSetFlagOnMap {

	/**
	 * Mapを所有しているオプションの管理ロジックメソッド
	 */
	public void setFlagLogic();

}
