package cardgame.player;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.BlackJackCard;
import cardgame.cards.Tramp;

public class BlackJackPlayer extends Player {

	/**
	 *
	 */
	public List<BlackJackPlayer> players;

	/**
	 * 持ち札
	 */
	public List<BlackJackCard> hand;

	/**
	 * 得点
	 */
	public int score;

	/**
	 * プレイヤー作成
	 */
	@Override
	public void build() {
		this.players = new ArrayList<>();
		createMyChar();
		createCPU();
	}

	/**
	 * プレイヤー作成
	 */
	private void createMyChar() {
		BlackJackPlayer player = new BlackJackPlayer();
		player.playerId = "kiji";
		player.name = "kiji";
		player.hand = new ArrayList<>();
		this.players.add(player);
	}

	/**
	 * CPU作成
	 */
	private void createCPU() {
		for (int i = 1; i <= 3; i++) {
			BlackJackPlayer player = new BlackJackPlayer();
			player.playerId = "COM" + String.valueOf(i);
			player.name = "COM" + String.valueOf(i);
			player.hand = new ArrayList<>();
			player.isCPU = true;
			this.players.add(player);
		}
	}

	@Override
	public void showMycard(List<Tramp> cardList) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
