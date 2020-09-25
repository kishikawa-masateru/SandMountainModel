package mountain;

/**
 * 上から見た砂山
 * @author masateru
 *
 */
public class MountainSquare {

	private int[][] square;		//	上から見た砂山の各地点

	public MountainSquare(int x, int y) {
		// square を初期化
		square = new int[y][x];
		for (int Y = 0; Y < y; Y++) {
			for (int X = 0; X < x; X++) {
				square[Y][X] = 0;
			}
		}
	}

	/**
	 * (x, y) の数値を1つ増やす
	 */
	public void increase(int x, int y) {
		square[y][x]++;

		if (square[y][x] == 4) {
			// (x, y) の値が4になったら，以下の処理を実行する
			// (x, y) の値を0にする
			square[y][x] = 0;

			// 周囲の数値を+1する
			increaseAround(x, y);
		}
	}

	/**
	 * (x, y) の周囲の数値を1つ増やす
	 * @param x
	 * @param y
	 */
	private void increaseAround(int x, int y) {
		// (x - 1, y) の数値を+1
		if (canIncrease(x - 1, y))
			increase(x - 1, y);

		// (x, y - 1) の数値を+1
		if (canIncrease(x, y - 1))
			increase(x, y - 1);

		// (x + 1, y) の数値を+1
		if (canIncrease(x + 1, y))
			increase(x + 1, y);

		// (x, y + 1) の数値を+1
		if (canIncrease(x, y + 1))
			increase(x, y + 1);
	}

	/**
	 * (x, y)の座標をインクリメントできるか
	 * @param x
	 * @param y
	 * @return インクリメントできるとtrue<br>引数の値が，行列の範囲がだったらfalse<br>
	 */
	private boolean canIncrease(int x, int y) {
		if (x < 0 || x > square[0].length || y < 0 || y > square.length)
			return false;

		return true;
	}

	/**
	 * @return 砂山の各地点
	 */
	public int[][] getSquare() {
		return square;
	}

	/**
	 * @return 各座標の値
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < square.length; y++) {
			for (int x = 0; x < square[0].length; x++) {
				builder.append(square[y][x]).append(" , ");
			}
			builder.replace(builder.length() - 3, builder.length(), "\n");
		}

		return builder.toString();
	}
}