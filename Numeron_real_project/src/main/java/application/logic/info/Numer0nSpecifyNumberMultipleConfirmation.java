package application.logic.info;

/**
 * 得られた全ての情報に対して候補の数字かそうでないか確認するインターフェース
 */
public interface Numer0nSpecifyNumberMultipleConfirmation {

	/**
	 * 今まで得られた情報を使用して候補リストの中身を整理する。候補リストにあったとしても今まで得られた情報に合っていなければ<br>
	 * その数字は候補でない数字リストに移動する。Numer0nSpecifyNumberクラスを利用する。<br>
	 * 以下得られた情報の格納形式<br>
	 * NOOPTION（[NOOPTION,234,1EAT0BITE]）<br>
	 * DOUBLE（[DOUBLE,0,1]（桁、その数字）or[DOUBLE,234,1EAT0BITE]（連続コール時））<br>
	 * HIGH&LOW（[HIGH&LOW,LOW,LOW,LOW,LOW,HIGH]（左からHIGHかLOWか））<br>
	 * SLASH（[SLASH,7]（SLASHナンバー））<br>
	 * TARGET（[TARGET,2orDONTTEACHINDEX,EXISTLISTOFNUMBER]（宣言した数字、存在するか））<br>
	 * CHANGE（[CHANGE,0orDONTTEACHINDEX,HIGH]（桁、HIGHかLOWか））<br>
	 * SHUFFLEなし<br>
	 */
	public void arrangeCandidateNumberMultipleConfirmationLogic();

}
