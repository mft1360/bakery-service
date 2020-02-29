package com.hexad.service;

import com.hexad.exception.BusinessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author R.fattahi
 * 		this calss First chooses maximums number of packs then
 *         other packs. Each order definitely contains the minimal number of
 *         packs. this is dynamic it means you can choose kind of packs for
 *         example 5,7 or 8,9 or 1,2,3,4,5,6,7,8,9,10,11,..., ...
 *
 */
public class BreakDownProcess {

	private static final int MAXIMUMSEARCH = 1;

	public static List<String> breakDownProcessPack(int order, int[] packs) {
		List<String> breakDown = breakDownProcessPack(order, packs, 0, 0);
		if (breakDown.size() == 0) {
			throw new BusinessException("It is not possible");
		}
		return breakDown;
	}

	private static List<String> breakDownProcessPack(int order, int[] packs, int reset, int count) {
		Arrays.sort(packs);
		List<String> breakDown = new ArrayList<>();
		int maxCount = packs[packs.length - 1];
		if (order >= maxCount) {
			int maxMultiple = order / maxCount;
			int mod = order % maxCount;
			if (mod == 0) {
				breakDown.add(maxMultiple + "x" + maxCount);
			} else {
				if (maxMultiple - reset > 0) {
					breakDown.add((maxMultiple - reset) + "x" + maxCount);
				}
				if (packs.length > 1) {
					breakDown.addAll(breakDownProcessPack(order - ((maxMultiple - reset) * maxCount), removeEndFromTheAry(packs), 0,
							0));
				}
			}
		} else if (packs.length > 1) {
			breakDown = breakDownProcessPack(order, removeEndFromTheAry(packs), 0, 0);
		}
		if (packs.length == 1 && order < maxCount && calculate(breakDown) != order) {
			return new ArrayList<>();
		}
		while (calculate(breakDown) != order && count <= MAXIMUMSEARCH) {
			// reset and again
			reset++;
			count++;
			breakDown = breakDownProcessPack(order, packs, reset, count);
			if (calculate(breakDown) == order) {
				return breakDown;
			} else {
				breakDown = new ArrayList<>();
			}
		}
		count = 0;
		if (calculate(breakDown) != order) {
			breakDown = new ArrayList<>();
		}
		return breakDown;
	}

	public static int calculate(List<String> breakDown) {
		if (breakDown.size() == 0) {
			return 0;
		}
		int ret = 0;
		for (String str : breakDown) {
			String[] parts = str.split("x");
			ret += Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
		}
		return ret;
	}

	private static int[] removeEndFromTheAry(int[] packs) {
		return Arrays.copyOfRange(packs, 0, packs.length - 1);
	}
}
