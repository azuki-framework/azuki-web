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
package org.azkfw.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.azkfw.core.log.LoggerFactory;
import org.azkfw.core.util.StringUtility;
import org.azkfw.persistence.ConfigurationFormatException;
import org.azkfw.plugin.PluginManager;
import org.azkfw.plugin.PluginManager.PluginEntity;
import org.azkfw.plugin.PluginServiceException;
import org.azkfw.web.view.JSPView;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * このクラスは、プラグイン機能をロードするサーブレットクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/08/29
 * @author Kawakicchi
 */
public final class AzukiPluginServlet extends AbstractServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5612928065309901778L;

	/**
	 * プラグインコンフィグ
	 */
	private String plugin;

	/**
	 * コンストラクタ
	 */
	public AzukiPluginServlet() {
		super(AzukiPluginServlet.class);
	}

	@Override
	protected void doInitialize(final ServletConfig aConfig) {
		String logClass = aConfig.getInitParameter("logger-class");
		String logConfig = aConfig.getInitParameter("logger-config");
		LoggerFactory.load(logClass, logConfig, getContext());

		plugin = aConfig.getInitParameter("plugin-config");
		PluginManager.initialize();
		doLoad();
	}

	@Override
	protected void doDestroy() {
		PluginManager.destroy();
	}

	/**
	 * プラグインのロードを行います。
	 */
	private void doLoad() {
		if (StringUtility.isNotEmpty(plugin)) {
			DOMConfigurator.configure(getContext().getAbstractPath("config/log4j.xml"));

			info("Load plugin config file.[" + plugin + "]");
			try {
				PluginManager.load(plugin, getContext());
			} catch (PluginServiceException ex) {
				error(ex);
			} catch (ConfigurationFormatException ex) {
				error(ex);
			} catch (IOException ex) {
				error(ex);
			}
		} else {
			warn("Not setting plugin-config.[web.xml]");
		}
	}

	public void doGet(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		aReq.setCharacterEncoding("UTF-8");

		List<PluginEntity> plugins = PluginManager.getPluginList();

		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("plugins", plugins);

		JSPView view = new JSPView("plugin/plugin.jsp", attributes);
		view.setBasePath("/WEB-INF/azuki/jsp");
		view.view(aReq, aRes);
	}

}
