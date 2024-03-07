package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Numer0n実行設定クラス
 * </p>
 * @author shiraishitoshio
 *
 */
@Configuration
@ComponentScan(
		basePackages = {
				"component",
				"log",
				"logic"
		})
public class Numer0nConfig {

}
