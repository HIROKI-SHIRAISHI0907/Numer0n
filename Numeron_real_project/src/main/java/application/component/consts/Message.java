package application.component.consts;

import java.util.HashMap;
import java.util.Map;

public class Message {

		// セリフマップ
		public static Map<String, HashMap<Integer, String>> MESSAGE_MAP = new HashMap<String, HashMap<Integer, String>>() {
			// 共通メッセージ
			{
				put(Const.QUESTION_THREE, new HashMap<Integer, String>() {
					{
						put(1, "皆。こんばんは。");
						put(2, "GameMasterです。Numer0nへようこそ。");
					}
				});
				put(Const.GAMEMASTER, new HashMap<Integer, String>() {
					{
						put(1, "【GameMaster】:あなたの名前を入力してください。");
						put(2, "【GameMaster】:CPUの難易度を選択してください。");
						put(3, "1:EASY,2:NORMAL,3:HARD,4:EXHAUSTED,5:INSANE:>>");
						put(4, "【GameMaster】:勝負する桁数を選択してください。");
						put(5, "1:3桁,2:4桁,3:5桁（1〜3から選択）>>");
						put(6, "【GameMaster】:では、CPU:【CPUの難易度】、登場して下さい。");
						put(7, "【GameMaster】:【名前】、オプションを3つ選んでください。");
						put(8, "【GameMaster】:【名前】、オプションを使用しますか？");
						put(9, "1:使う,2:使わない,3:戻る（1〜3から選択）>>");
						put(10, "1:使う,2:使わない（1,2から選択）>>");
						put(11, "【GameMaster】:「【選択肢】」ですね。");
						put(12, "【GameMaster】:では、使うオプションを下から選んでください。");
						put(13, "【文字羅列（オプションなど）】（【選択番号】から選択）>>");
						put(14, "【名前】、数字をコールしてください。（【桁】桁で入力）");
						put(15, "【名前】の勝ちです。"); //OK
						put(16, "【GameMaster】:オプション（【item】）が選択されました。"); //OK
						put(17, "【GameMaster】:結果は、【EATBITEの結果】でした。"); // OK
						put(18, "【GameMaster】:【名前】は今回、オプションを使用しませんでした。"); //OK
						put(19, "【GameMaster】:【名前】はすでに攻撃用オプションを使用しているため、" //OK
								+ "このターンではオプションを使用できません。");
						put(20, "【GameMaster】:難易度が【難易度名】のため、数字の重複はできません。");
					}
				});
				put(Const.VALIDATION, new HashMap<Integer, String>() {
					{
						put(1, "桁数が合いません。");
						put(2, "難易度は現在【CPUの難易度】です。難易度が【条件付き難易度】以外は同じ数字が入ってはいけません。");
						put(3, "正しい選択肢を選んでください。");
						put(4, "【GameMaster】:入力した桁が外れ値です。【制限範囲】から選択してください。");
						put(5, "【GameMaster】:難易度がEASY,NORMALであるため、必ずシャッフルする必要があります。");
					}
				});
				put(Const.EASY, new HashMap<Integer, String>() {
					{
						put(1, "よろしくお願いします！");
					}
				});
				put(Const.NORMAL, new HashMap<Integer, String>() {
					{
						put(1, "どうも。");
					}
				});
				put(Const.HARD, new HashMap<Integer, String>() {
					{
						put(1, "Mucho gusto（よろしく。）");
					}
				});
				put(Const.EXHAUSTED, new HashMap<Integer, String>() {
					{
						put(1, "ヨろシクおネがイすル。");
					}
				});
				put(Const.INSANE, new HashMap<Integer, String>() {
					{
						put(1, "%$'+*`%)&><>$<'?*+LL`（よろしく。）");
					}
				});
				// DOUBLE（メッセージを出す前にGAMEMASTERを表示する）
				put(Const.DOUBLE, new HashMap<Integer, String>() {
					{
						put(1, "【GameMaster】:【名前】がDOUBLEを使用しましたので、"
								+ "【相手の名前】は代償として相手に指定した桁の数字を教える必要があります。"); //OK
						put(2, "【GameMaster】:【相手の名前】、知りたい正解ナンバーの桁を指定してください。"); //OK
						put(3, "【GameMaster】:【名前】、【n】回目の数字をコールしてください。"
								+ "（【桁】桁で入力））"); //OK
					}
				});
				// HIGH&LOW（メッセージを出す前にGAMEMASTERを表示する）
				put(Const.HIGH_LOW, new HashMap<Integer, String>() {
					{
						put(2, "【GameMaster】:それでは【名前】のHIGHLOWを内訳を教えます。"); //OK
						put(3, "【GameMaster】:【配列】です。"); //OK
					}
				});
				// SLASH（メッセージを出す前にGAMEMASTERを表示する）
				put(Const.SLASH, new HashMap<Integer, String>() {
					{
						put(2, "【GameMaster】:それでは【名前】のスラッシュナンバーを教えます。"); //OK
						put(3, "【GameMaster】:スラッシュナンバーは【ナンバー】です。"); //OK
					}
				});
				// TARGET（メッセージを出す前にGAMEMASTERを表示する）
				put(Const.TARGET, new HashMap<Integer, String>() {
					{
						put(2, "【GameMaster】:それでは【名前】、含まれていそうな数字を1つ宣言してください。"); //OK
						put(3, "【GameMaster】:その数字は、【含まれている桁】の桁にあるようです。"); //OK
						put(4, "【GameMaster】:その数字は、含まれていないようです。"); //OK
						put(5, "【GameMaster】:難易度がEASY,NORMAL,HARDであるため、どの桁に入っているか及び数字が含まれているかを教えます。"); //OK
						put(6, "【GameMaster】:難易度がEXHAUSTED,INSANEであるため、選択した数字が含まれているかどうかのみ教えます。"); //OK
						put(7, "【GameMaster】:その数字は、どこかの桁に含まれているようです。"); //OK
					}
				});
				// CHANGE（メッセージを出す前にGAMEMASTERを表示する）
				put(Const.CHANGE, new HashMap<Integer, String>() {
					{
						put(2, "【GameMaster】:【名前】、現在の正解ナンバーの任意の桁の数字を交換してください。"); //OK
						put(3, "【GameMaster】:【名前】が現在の正解ナンバーの数字を交換しました。"); //OK
						put(4, "【GameMaster】:【名前】が現在の正解ナンバーの数字を交換しました。（CPUには交換したかどうかはわかりません。）"); //OK
						put(5, "【GameMaster】:難易度がEASY,NORMALであるため、交換した桁の位置とその数字がHIGHかLOWかが相手に知られます。"); //OK
						put(6, "【GameMaster】:【名前】、【相手の名前】の交換した桁の位置は【桁の位置】、その数字は【HIGHorLOW】です。"); //OK
						put(7, "【GameMaster】:難易度がHARDであるため、交換した数字がHIGHかLOWかのみが相手に知られます。"); //OK
						put(8, "【GameMaster】:【名前】、【相手の名前】の交換した数字は【HIGHorLOW】です。"); //OK
						put(9, "【GameMaster】:難易度がEXHAUSTEDであるため、交換した桁の位置のみ相手に知られます。ただし、実際に交換しているかもわかりません。"); //OK
						put(10, "【GameMaster】:【名前】、【相手の名前】の交換した桁の位置は【桁の位置】の桁です。"); //OK
						put(11, "【GameMaster】:難易度がINSANEであるため、何も情報は得られません。また、実際に交換しているかもわかりません。"); //OK
						put(12, "【GameMaster】:【名前】、特に情報はありません。"); //OK
						put(13, "【GameMaster】:0:100の位、1:10の位、2:1の位（選択肢（0〜2を指定））"); //OK
						put(14, "【GameMaster】:0:1000の位、1:100の位、2:10の位、3:1の位（選択肢（0〜3を指定））"); //OK
						put(15, "【GameMaster】:0:10000の位、1:1000の位、2:100の位、3:10の位、4:1の位（選択肢（0〜4を指定））"); //OK
					}
				});
				// SHUFFLE（メッセージを出す前にGAMEMASTERを表示する）
				put(Const.SHUFFLE, new HashMap<Integer, String>() {
					{
						put(2, "【GameMaster】:【名前】が現在の正解ナンバーをシャッフルしました。"); //OK
						put(3, "【GameMaster】:難易度がHARD,EXHAUSTED,INSANEであるため、シャッフルしたかどうかはわかりません。"); //OK
					}
				});
			}
		};
	}
