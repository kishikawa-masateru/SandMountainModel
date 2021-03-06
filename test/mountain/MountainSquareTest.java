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

	private final int COLUMNS = 5;		// 行数
	private final int ROWS = 5;			// 列数

	/**
	 * コンストラクタのテスト
	 */
	@Test
	void test_CONSTRUCTOR() {

		// 列数で例外スロー
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(-1, 10));
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(0, 10));	// 列数の境界値

		// 行数で例外スロー
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(10, 0));
		assertThrows(InvalidArgumentException.class, () -> new MountainSquare(10, -1));	// 行数の境界値
	}

	/**
	 * 範囲外のx座標を指定したときのcanIncreaseメソッドのテスト<br>
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
	void tc1_canIncrease() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InvalidArgumentException {

		MountainSquare square = new MountainSquare(COLUMNS, ROWS);

		Method canIncrease = MountainSquare.class.getDeclaredMethod("canIncrease", int.class, int.class);
		canIncrease.setAccessible(true);

		assertFalse((boolean) canIncrease.invoke(square, -1, 0));		// y座標は正しい値
		assertFalse((boolean) canIncrease.invoke(square, ROWS + 1, 0));	// y座標は正しい値
	}

	/**
	 * 範囲外のy座標の値を指定したときのcanIncreaseメソッドのテスト
	 * x座標は正しい値
	 *
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InvalidArgumentException
	 */
	@Test
	void tc2_canIncrease() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InvalidArgumentException {

		MountainSquare square = new MountainSquare(COLUMNS, ROWS);

		Method canIncrease = MountainSquare.class.getDeclaredMethod("canIncrease", int.class, int.class);
		canIncrease.setAccessible(true);

		assertFalse((boolean) canIncrease.invoke(square, 2, -1));		// x座標は正しい値
		assertFalse((boolean) canIncrease.invoke(square, 8, ROWS + 1));	// x座標は正しい値
	}

	/**
	 * 範囲内のx，y座標を指定したときのcanIncreaseメソッドのテスト
	 */
	@Test
	void tc3_canIncrease() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InvalidArgumentException {

		MountainSquare square = new MountainSquare(COLUMNS, ROWS);

		Method canIncrease = MountainSquare.class.getDeclaredMethod("canIncrease", int.class, int.class);
		canIncrease.setAccessible(true);

		// 範囲内のすべての座標でテスト
		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				assertTrue((boolean) canIncrease.invoke(square, x, y));
			}
		}
	}

	/**
	 * 引数が無効の時のincreaseメソッドのテスト<br>
	 *
	 * 無効な引数を渡すと，MountainSquareオブジェクトが保持しているsquareフィールドが変わらないことを確認する
	 * @throws InvalidArgumentException
	 */
	@Test
	void tc1_increase() throws InvalidArgumentException {
		MountainSquare square = new MountainSquare(COLUMNS, ROWS);
		int[][] TEST_SQUARE = generate_TEST_SQUARE();

		// squareは，内部でcanIncreaseを呼ぶので，tc1 ~ tc2 を参考にして，無効な引数を渡す
		// x座標が0未満
		square.increase(-1, 0);
		assertArray(TEST_SQUARE, square.getSquare());

		// x座標が列の長さ以上
		square.increase(COLUMNS + 1, 0);
		assertArray(TEST_SQUARE, square.getSquare());

		// y座標が0未満
		square.increase(0, -1);
		assertArray(TEST_SQUARE, square.getSquare());

		// y座標が行の長さ以上
		square.increase(0, ROWS + 1);
		assertArray(TEST_SQUARE, square.getSquare());
	}

	/**
	 * 引数が有効で，値を増やせるときのincreaseのテスト<br>
	 *
	 * ただし，座標の値は4未満
	 * @throws InvalidArgumentException
	 */
	@Test
	void tc2_increase() throws InvalidArgumentException {
		MountainSquare square = new MountainSquare(COLUMNS, ROWS);
		int[][] TEST_SQUARE = generate_TEST_SQUARE();

		// 全座標を3増やす
		// increaseを一回呼ぶごとに，全座標を比較する
		for (int i = 0; i < 3; i++) {
			for (int y = 0; y < ROWS; y++) {
				for (int x = 0; x < COLUMNS; x++) {
					square.increase(x, y);
					TEST_SQUARE[y][x]++;
					assertArray(TEST_SQUARE, square.getSquare());
				}
			}
		}
	}

	/**
	 * 引数が有効で，値を増やせるときのincreaseのテスト<br>
	 *
	 * ・ 座標が4になったときに，周囲の値をインクリメントするのかテスト
	 * @throws InvalidArgumentException
	 */
	@Test
	void tc3_inncrease() throws InvalidArgumentException {
		MountainSquare square1 = new MountainSquare(COLUMNS, ROWS);

		// 周囲の値を増やしたときに，周囲の値が4にならない場合
		int[][] TEST_SQUARE1 = {
									{ 0, 1, 0, 1, 0 },
									{ 1, 0, 1, 0, 1 },
									{ 0, 1, 0, 1, 0 },
									{ 1, 0, 1, 0, 1 },
									{ 0, 1, 0, 1, 0 } };

		// 雪山の端，中心の座標を4回インクリメント
		for (int i = 0; i < 4; i++) {
			square1.increase(0, 0);
			square1.increase(COLUMNS - 1, 0);
			square1.increase(0, ROWS - 1);
			square1.increase(COLUMNS - 1, ROWS - 1);
			square1.increase(COLUMNS / 2, ROWS / 2);
		}

		// 各座標の値をテスト
		assertArray(TEST_SQUARE1, square1.getSquare());

		// 周囲の値を増やしたときに，周囲の値が4になる場合
		int[][] TEST_SQUARE2 = {
									{ 0, 0, 1, 0, 0 },
									{ 0, 2, 1, 2, 0 },
									{ 1, 1, 0, 1, 1 },
									{ 0, 2, 1, 2, 0 },
									{ 0, 0, 1, 0, 0 } };

		MountainSquare square2 = new MountainSquare(COLUMNS, ROWS);
		int mid = COLUMNS / 2;

		// 中心とその周囲の値を3増やす
		for (int i = 0; i < 3; i++) {
			square2.increase(COLUMNS / 2, ROWS / 2 - 1);
			square2.increase(COLUMNS / 2 - 1, ROWS / 2);
			square2.increase(COLUMNS / 2, ROWS / 2);
			square2.increase(COLUMNS / 2 + 1, ROWS / 2);
			square2.increase(COLUMNS / 2, ROWS / 2 + 1);
		}
		// 中心インクリメント
		square2.increase(COLUMNS / 2, ROWS / 2);

		// 各座標の値をテスト
		assertArray(TEST_SQUARE2, square2.getSquare());
	}

	/**
	 * int型の二次元配列同士の比較<br>
	 *
	 * @param expected
	 * @param actual
	 */
	private void assertArray(int[][] expected, int[][] actual) {

		// 配列の縦と横の長さが等しくないとテストに失敗する
		if (expected.length != actual.length)
			fail();
		if (expected[0].length != actual[0].length)
			fail();

		for (int y = 0; y < actual.length; y++) {
			for (int x = 0; x < actual[0].length; x++) {
				assertEquals(expected[y][x], actual[y][x]);
			}
		}
	}

	/**
	 * テストケースを作成
	 * @return
	 */
	private int[][] generate_TEST_SQUARE() {
		int[][] TEST_SQUARE = new int[COLUMNS][ROWS];

		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				TEST_SQUARE[y][x] = 0;
			}
		}

		return TEST_SQUARE;
	}
}
