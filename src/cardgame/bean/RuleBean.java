package cardgame.bean;

public class RuleBean {

	/**
	 * プレイヤー人数
	 */
	private int playerNum = 4;

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public int getHitLine() {
		return hitLine;
	}

	public void setHitLine(int hitLine) {
		this.hitLine = hitLine;
	}

	/**
	 * CPUの得点
	 */
	private int hitLine = 16;

}
