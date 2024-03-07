package application.logic.info;

/**
 * オプションコール→数字コールの次の行動のCPUの選択をまとめるインターフェース
 * <p>
 * ・DBに登録する<br>
 * ・次行動のオプション選択時にMapに登録する<br>
 * など
 * </p>
 * @author shiraishitoshio
 *
 */
public interface Numer0nNextAction {

	/**
	 * オプションコール→数字コールの次の行動のCPUの選択を難易度によって異らせるロジック<br>
	 * 以下の難易度によって次行動を変える。<br>
	 * 1, CPUに格納したInfo情報の最後の情報を考慮して次行動を決める。<br>
	 * 2, CPUに格納したInfo情報の全ての情報を考慮して次行動を決める。<br>
	 * 3, DBに今まで得た情報を格納する。またゲーム開始時にDBから情報を取得する。<br>
	 * 4, 各オプションの選択、オプション内の選択Mapに優先度フラグを設定する。<br>
	 * 5, PYTHONを使用して機械学習させる。（JAVA→PYTHONの情報受け渡し）<br>
	 * <p>
	 * ・EASY:何も考慮しない。ランダムでコールする数字、オプションを選ぶ。<br>
	 * ・NORMAL:1のみ<br>
	 * ・HARD:2のみ<br>
	 * ・EXHAUSTED:2,3,4のみ<br>
	 * ・INSANE:全て<br>
	 * </p>
	 */
	public void nextActionLogic();

}
