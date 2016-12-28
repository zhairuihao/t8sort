package cn.com.t8sort.redpacket;

import java.util.ArrayList;
import java.util.List;

public class RedPacketUtil {
	private static final float MINMONEY = 0.01f;
	private static final float MAXMONEY = 200f;
	private static final double TIMES = 2.1;

	/**
	 * 
	 * @param money
	 * @param count
	 * @return
	 * 为了避免一个红包占用大量的资金，设定非最后一个红包的最大金额，可以设置为平均值的N倍，基于前面的方法就可以实现红包的分配了。
	 */
	public List<Float> splitRedPackets(float money, int count) {
		if (!isRight(money, count)) {
			return null;
		}
		List<Float> list = new ArrayList<Float>();
		float max = (float) (money * TIMES / count);

		max = max > MAXMONEY ? MAXMONEY : max;
		for (int i = 0; i < count; i++) {
			float one = randomRedPacket(money, MINMONEY, max, count - i);
			list.add(one);
			money -= one;
		}
		return list;
	}

	private float randomRedPacket(float money, float mins, float maxs, int count) {
		if (count == 1) {
			return (float) (Math.round(money * 100)) / 100;
		}
		if (mins == maxs) {
			return mins;
		}
		float max = maxs > money ? money : maxs;
		float one = ((float) Math.random() * (max - mins) + mins);
		one = (float) (Math.round(one * 100)) / 100;
		float moneyOther = money - one;
		if (isRight(moneyOther, count - 1)) {
			return one;
		} else {

			float avg = moneyOther / (count - 1);
			if (avg < MINMONEY) {
				return randomRedPacket(money, mins, one, count);
			} else if (avg > MAXMONEY) {
				return randomRedPacket(money, one, maxs, count);
			}
		}
		return one;
	}

	private boolean isRight(float money, int count) {
		double avg = money / count;
		if (avg < MINMONEY) {
			return false;
		} else if (avg > MAXMONEY) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {

		RedPacketUtil util = new RedPacketUtil();
		for(int i=0;i<100;i++){
		System.out.println(util.splitRedPackets(200, 3));
		}
	}
}