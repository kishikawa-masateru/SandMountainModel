package util;

/**
 * 無効な引数の時にスローする例外クラス
 * @author masateru
 *
 */
public class InvalidArgumentException extends Exception {

	public InvalidArgumentException(String argName) {
		super("引数" + argName + "が無効です．");
	}
}
