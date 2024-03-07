package application.logic.option;

public interface CommonOption {

	/**
	 * <p>
	 * 与えられたリストやオブジェクトに対して各オプションの設定をした後、<br>
	 * 各オプションによって、続けるか続けないかを確定させる。
	 * </p>
	 * @param item オプション名
	 * @return <code>0:</code>Numer0nを続ける、<code>1:</code>Numer0nを続けない
	 */
	public int summarizeOption(String item);

}
