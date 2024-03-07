package application.component.message;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageAccessor {

	/**
	 * static参照用field
	 */
	private final MessageAccessorIF messageIF;

	/**
	 * static参照用field
	 */
	private static MessageAccessorIF messageStatic;

	/**
	 * <p>
	 * 初期化処理実行
	 * </p>
	 */
	@PostConstruct
	private void init() {
		messageStatic = this.messageIF;
	}

	/**
	 * <p>
	 * ログ出力処理
	 * </p>
	 * @param code 検索コード
	 * @param args 引数など
	 */
	public static String getMessage(String code, String... args) {
		return messageStatic.getMessage(code, args);
	}

}
