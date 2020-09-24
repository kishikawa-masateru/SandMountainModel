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
				square[y][x] = 0;
			}
		}
	}

	/**
	 * (x, y) の数値を1つ増やす
	 */
	public void increase(int x, int y) {
		square[y][x]++;
	}
}
