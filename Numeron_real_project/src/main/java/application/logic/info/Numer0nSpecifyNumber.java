package application.logic.info;

/**
 * 数々の情報をもとに、どの数値を候補として残すか思考するインターフェース
 * <p>
 * #1(オプションを使用して数字をコールしEatBite情報を得られた後)に、必要不要の数字を整理する
 * 整理する情報は、直近で得られた#1の情報を元に行う
 * </p>
 * @author shiraishitoshio
 *
 */
public interface Numer0nSpecifyNumber {

	/**
	 * n桁の数字を設定する。（各オプション使用時に取得した情報に合うかどうか判断する材料として必要）
	 * @param info 検索する情報（ex) NOOPTION,348,2EAT1BITE
	 */
	public void arrangeCandidateNumberLogic(String info);

	/**
	 * 得られたEatBite情報を元に候補の数値リストを登録する用のメソッド（オプションなし）
	 * @param info 得られたEatBite情報(ex) NONEOPTION,348,2EAT1BITE)
	 * @param chkNumber 不要か必要かを判定するn桁の数値（3桁...0,1,2 4桁...0,1,2,3 5桁...0,1,2,3,4）
	 * @param flag 今まで得られた情報から数字から削除するか(EVER_INFO_FLAG)、直前に得られた情報から削除するかを表すフラグ(LAST_INFO_FLAG)
	 * @return <code>MATCH:</code>登録完了,<code>NOT_MATCH:</code>未登録もしくはマッチしていない
	 */
	public int setNoneOptionAddCandidateMethod(String info, String chkNumber, String flag);

	/**
	 * 得られたEatBite情報を元に候補の数値リストを登録する用のメソッド（オプションあり）
	 * @param info 得られた情報
	 * @param chkNumber 不要か必要かを判定するn桁の数値（3桁...0,1,2 4桁...0,1,2,3 5桁...0,1,2,3,4）
	 * @param flag 今まで得られた情報から数字から削除するか(EVER_INFO_FLAG)、直前に得られた情報から削除するかを表すフラグ(LAST_INFO_FLAG)
	 * @return <code>MATCH:</code>登録完了,<code>NOT_MATCH:</code>未登録もしくはマッチしていない
	 */
	public int setOptionAddCandidateMethod(String info, String chkNumber, String flag);

}
