/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.shentu.g3.app.pay.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NetworkUtils {

	private static final Log logger = LogFactory.getLog(NetworkUtils.class);
	// 通过前端的负载均衡服务器时，请求对象中的IP会变成负载均衡服务器的IP，因此需要特殊处理，下同。
	public static final String X_REAL_IP = "X-Real-IP";

	public static final String X_FORWARDED_FOR = "X-Forwarded-For";

	public static final String LOCALHOST = "127.0.0.1";

	private static final Pattern ADDRESS_PATTERN = Pattern
			.compile("^\\d{1,3}(\\.\\d{1,3}){3}\\:\\d{1,5}$");

	public static boolean isValidAddress(String address) {
		return ADDRESS_PATTERN.matcher(address).matches();
	}

	private static final Pattern LOCAL_IP_PATTERN = Pattern
			.compile("127(\\.\\d{1,3}){3}$");

	public static boolean isLocalHost(String host) {
		return host != null
				&& (LOCAL_IP_PATTERN.matcher(host).matches() || host
						.equalsIgnoreCase("localhost"));
	}

	public static boolean isInvalidLocalHost(String host) {
		return host == null || host.length() == 0
				|| host.equalsIgnoreCase("localhost")
				|| (LOCAL_IP_PATTERN.matcher(host).matches());
	}

	public static boolean isValidLocalHost(String host) {
		return !isInvalidLocalHost(host);
	}

	public static InetSocketAddress getLocalSocketAddress(String host, int port) {
		return isInvalidLocalHost(host) ? new InetSocketAddress(port)
				: new InetSocketAddress(host, port);
	}

	private static final Pattern IP_PATTERN = Pattern
			.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

	private static boolean isValidAddress(InetAddress address) {
		if (address == null || address.isLoopbackAddress())
			return false;
		String name = address.getHostAddress();
		return (name != null && !LOCALHOST.equals(name) && IP_PATTERN.matcher(
				name).matches());
	}

	public static String getLocalHost() {
		InetAddress address = getLocalAddress();
		return address == null ? LOCALHOST : address.getHostAddress();
	}

	private static volatile InetAddress LOCAL_ADDRESS = null;

	/**
	 * 遍历本地网卡，返回第一个合理的IP。
	 *
	 * @return 本地网卡IP
	 */
	public static InetAddress getLocalAddress() {
		if (LOCAL_ADDRESS != null)
			return LOCAL_ADDRESS;
		InetAddress localAddress = getLocalAddress0();
		LOCAL_ADDRESS = localAddress;
		return localAddress;
	}

	private static InetAddress getLocalAddress0() {
		InetAddress localAddress = null;
		try {
			localAddress = InetAddress.getLocalHost();
			if (isValidAddress(localAddress)) {
				return localAddress;
			}
		} catch (Throwable e) {
			logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
		}
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			if (interfaces != null) {
				while (interfaces.hasMoreElements()) {
					try {
						NetworkInterface network = interfaces.nextElement();
						Enumeration<InetAddress> addresses = network
								.getInetAddresses();
						if (addresses != null) {
							while (addresses.hasMoreElements()) {
								try {
									InetAddress address = addresses
											.nextElement();
									if (isValidAddress(address)) {
										return address;
									}
								} catch (Throwable e) {
									logger.warn(
											"Failed to retriving ip address, "
													+ e.getMessage(), e);
								}
							}
						}
					} catch (Throwable e) {
						logger.warn(
								"Failed to retriving ip address, "
										+ e.getMessage(), e);
					}
				}
			}
		} catch (Throwable e) {
			logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
		}
		logger.error("Could not get local host ip address, will use "
				+ LOCALHOST + " instead.");
		return localAddress;
	}

	private static final Map<String, String> hostNameCache = new HashMap<String, String>();

	public static String getHostName(String address) {
		try {
			int i = address.indexOf(':');
			if (i > -1) {
				address = address.substring(0, i);
			}
			String hostname = hostNameCache.get(address);
			if (hostname != null && hostname.length() > 0) {
				return hostname;
			}
			InetAddress inetAddress = InetAddress.getByName(address);
			if (inetAddress != null) {
				hostname = inetAddress.getHostName();
				hostNameCache.put(address, hostname);
				return hostname;
			}
		} catch (Throwable e) {
			// ignore
		}
		return address;
	}

	/**
	 * @param hostName
	 * @return ip address or hostName if UnknownHostException
	 */
	public static String getIpByHost(String hostName) {
		try {
			return InetAddress.getByName(hostName).getHostAddress();
		} catch (UnknownHostException e) {
			return hostName;
		}
	}

	public static String toAddressString(InetSocketAddress address) {
		return address.getAddress().getHostAddress() + ":" + address.getPort();
	}

	public static InetSocketAddress toAddress(String address) {
		int i = address.indexOf(':');
		String host;
		int port;
		if (i > -1) {
			host = address.substring(0, i);
			port = Integer.parseInt(address.substring(i + 1));
		} else {
			host = address;
			port = 0;
		}
		return new InetSocketAddress(host, port);
	}

	public static String getRemoteHost(HttpServletRequest request) {
		// nginx反向代理
		String ipAddress = request.getHeader(X_REAL_IP);
		if (StringUtils.isBlank(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader(X_FORWARDED_FOR);
		}
		if (StringUtils.isBlank(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();

			if (ipAddress.equals(LOCALHOST)) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		// "***.***.***.***".length() = 15
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
