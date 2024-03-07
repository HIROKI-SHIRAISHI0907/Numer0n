package application;

import java.util.ArrayList;
import java.util.Map;

import application.component.consts.Message;

/**
 * メッセージログ管理クラス<br>
 * @author shiraishitoshio
 *
 */
public class MessageLogParam {

	private static final String IN_FRONT_OF_MESSAGE_PARENTHESIS = "【";
	private static final String IN_REAR_OF_MESSAGE_PARENTHESIS = "】";
	private static final int SLEEP_TIME_THREE = 3;

	StringBuilder strBuild = new StringBuilder();

	/**
	 * コンソールに表示するメッセージを管理する。<br>
	 * 引数をキーにして、【】内の変数を該当定数に変更する。<br>
	 * @param keyInfo メッセージを表示するキー（オプションならDOUBLEなどが該当）<br>
	 * @param messageNum メッセージNo.<br>
	 * @param args 【】内の変数に登録する引数<br>
	 */
	public void setLogParam(String keyInfo, int messageNum, ArrayList<String> args) {
		// 定数配下のメッセージ取得
		Map<Integer, String> getMap = Message.MESSAGE_MAP.get(keyInfo);
		// メッセージNo.に紐づくメッセージを取得
		String message = getMap.get(messageNum);
		// 【】が含まれているかで判断。ある場合argsを用いて定数化
		if ((message.contains(IN_FRONT_OF_MESSAGE_PARENTHESIS)
				&& message.contains(IN_REAR_OF_MESSAGE_PARENTHESIS))
					&& !args.isEmpty()) {
			// メッセージを調べる（【】以外の場合はそのまま追加）
			// パスフラグ（【から】までの変数を追加しないために用意）
			boolean pathFlag = false;
			// スキップフラグ（すでに追加した文字列を再度追加しないように用意）
			boolean skipStrFlag = false;
			for (int mes = 0; mes < message.length(); mes++) {
				// メッセージの中で【が出てきたら、フラグをtrue
				if (IN_FRONT_OF_MESSAGE_PARENTHESIS.equals(message.substring(mes, mes+1))) {
					pathFlag = true;
				}

				// パスフラグがtrueの時、変数を定数化
				if (pathFlag && !args.isEmpty()) {
					strBuild.append(args.get(0));
					args.remove(0);

					// スキップフラグをtrue
					skipStrFlag = true;
					// この時点でargsの引数を入れたのでパスフラグをfalse
					pathFlag = false;
				// 一文字ずつ格納（パスフラグandスキップフラグがfalseのみ）
				} else {
					if (!skipStrFlag) {
						strBuild.append(message.substring(mes, mes+1));
					}
				}

				// メッセージの中で】が出てきたら、パスフラグ及びスキップフラグをfalseし、再び1文字ずつ文字列を追加できるようにする
				if (IN_REAR_OF_MESSAGE_PARENTHESIS.equals(message.substring(mes, mes+1))) {
					pathFlag = false;
					skipStrFlag = false;
				}
			}
			message = strBuild.toString();
		// 含まれていない場合引数がない扱い
		}

		System.out.println(message);


		// 何秒か処理中のタイムログを発生させる。
		try {
			Thread.sleep(SLEEP_TIME_THREE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
