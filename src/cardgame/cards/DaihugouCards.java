package cardgame.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.Consts;

/**
 *
 * @author kijima
 *
 */
public class DaihugouCards extends Tramp {

	/**
	 * カードを生成
	 */
	public List<Tramp> cardList = new ArrayList<>();

	/**
	 * トランプを生成
	 *
	 * @return
	 */
	@Override
	public List<Tramp> build() {

		// ♠︎を作成
		createSuit("♠︎");

		// ❤︎を作成
		createSuit("❤︎");

		// ☘を作成
		createSuit("☘");

		// ♦︎を作成
		createSuit("♦︎");

		// 山札をシャッフル
		Collections.shuffle(this.cardList);

		return this.cardList;
	}

	/**
	 * スートを作成
	 *
	 * @param suit
	 */
	public void createSuit(String suit) {
		for (int i = 1; i <= 13; i++) {
			Tramp tramp = new DaihugouCards();
			tramp.num = i;
			tramp.suit = suit;
			tramp.rank = Consts.numRankMap.get(i);
			this.cardList.add(tramp);
		}
	}
}
