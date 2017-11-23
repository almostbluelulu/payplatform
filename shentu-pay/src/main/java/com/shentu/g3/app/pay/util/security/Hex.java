//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shentu.g3.app.pay.util.security;

public class Hex {
	public Hex() {
	}

	public static String toHex(byte[] input) {
		if (input == null) {
			return null;
		} else {
			StringBuffer output = new StringBuffer(input.length * 2);

			for (int i = 0; i < input.length; ++i) {
				int current = input[i] & 255;
				if (current < 16) {
					output.append("0");
				}

				output.append(Integer.toString(current, 16));
			}

			return output.toString();
		}
	}

	public static byte[] fromHex(String input) {
		if (input == null) {
			return null;
		} else {
			byte[] output = new byte[input.length() / 2];

			for (int i = 0; i < output.length; ++i) {
				output[i] = (byte) Integer.parseInt(input.substring(i * 2, (i + 1) * 2), 16);
			}

			return output;
		}
	}
}
