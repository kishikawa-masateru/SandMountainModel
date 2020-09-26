package mountain;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import util.InvalidArgumentException;

/**
 * MountainSquareのテストコード
 * @author masateru
 *
 */
class MountainSquareTest {

	private final int COLUMNS = 10;		// 行数
	private final int ROWS = 10;		// 列数

	/**
	 * コンストラクタのテスト
	 */
	@Test
	void test_CONSTRUCTOR() {

		// 列数で例外スロー
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(-1, 10));
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(0, 10));

		// 行数で例外スロー
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(10, 0));
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(10, -1));
	}

	/**
	 * アクセスできないx座標の値を指定したときのテスト<br>
	 * y座標は正しい値
	 *
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvalidArgumentException
	 *
	 */
	@Test
	void tc1_canIncrease()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InvalidArgumentException {

		MountainSquare square = new MountainSquare(COLUMNS, ROWS);

		Method canIncrease = MountainSquare.class.getDeclaredMethod("canIncrease", int.class, int.class);
		canIncrease.setAccessible(true);

		assertFalse((boolean) canIncrease.invoke(square, -1, 0));		// y座標は正しい値
		assertFalse((boolean) canIncrease.invoke(square, ROWS + 1, 0));	// y座標は正しい値
	}

}
