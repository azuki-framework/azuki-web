/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.azkfw.web.constant;

import java.util.Properties;

import org.azkfw.util.StringUtility;

/**
 * このクラスは、Web定数を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public final class WebConstant {

	/** host name */
	private static String hostName;

	/** host port */
	private static String hostPort;

	/** protocol */
	private static String protocol;

	/** web application */
	private static String webApp;

	/** servlet file upload temporary directory */
	private static String servletFileUploadTemporaryDirectory;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private WebConstant() {
		;
	}

	/**
	 * ロードします。
	 * 
	 * @param p プロパティ
	 */
	public static void load(final Properties p) {
		protocol = p.getProperty("host.protocol", "http");
		hostName = p.getProperty("host.name", StringUtility.EMPTY);
		hostPort = p.getProperty("host.port", StringUtility.EMPTY);

		webApp = p.getProperty("webapp.name", StringUtility.EMPTY);

		servletFileUploadTemporaryDirectory = p.getProperty("servlet.fileUpload.temporary.directory");
	}

	/**
	 * ホスト名を取得する。
	 * 
	 * @return ホスト名
	 */
	public static String getHostName() {
		return hostName;
	}

	/**
	 * ポート番号を取得する。
	 * 
	 * @return ポート番号
	 */
	public static String getHostPort() {
		return hostPort;
	}

	/**
	 * Webアプリケーション名を取得する。
	 * 
	 * @return Webアプリケーション名
	 */
	public static String getWebApp() {
		return webApp;
	}

	/**
	 * プロトコルを取得する。
	 * 
	 * @return プロトコル
	 */
	public static String getProtocol() {
		return protocol;
	}

	/**
	 * サーブレットファイルアップロード一時ディレクトリパスを取得する。
	 * 
	 * @return ディレクトリパス
	 */
	public static String getServletFileUploadTemporaryDirectory() {
		return servletFileUploadTemporaryDirectory;
	}

	/**
	 * URLを取得する。
	 * 
	 * @param aAlias エイリアス
	 * @return URL
	 */
	public static String getUrl(final String aAlias) {
		return getUrl(aAlias, false);
	}

	/**
	 * URLを取得する。
	 * 
	 * @param aAlias エイリアス
	 * @param aAbsolute 絶対URL
	 * @return　URL
	 */
	public static String getUrl(final String aAlias, final boolean aAbsolute) {
		StringBuffer s = new StringBuffer();
		if (aAbsolute) {
			s.append(getProtocol()).append("://").append(getHostName());
			if (StringUtility.isNotEmpty(getHostPort())) {
				s.append(":").append(getHostPort());
			}
			if (StringUtility.isNotEmpty(getWebApp())) {
				s.append("/").append(getWebApp());
			}
			if (StringUtility.isNotEmpty(aAlias)) {
				if (!aAlias.startsWith("/")) {
					s.append("/");
				}
				s.append(aAlias);
			} else {
				s.append("/");
			}
		} else {
			if (aAlias.startsWith("/")) {
				if (StringUtility.isNotEmpty(getWebApp())) {
					s.append("/").append(getWebApp());
				}
			}
			s.append(aAlias);
		}
		return s.toString();
	}
}
