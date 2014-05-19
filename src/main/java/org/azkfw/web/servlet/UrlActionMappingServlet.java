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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.azkfw.web.action.Action;
import org.azkfw.web.action.urlmapper.UrlActionMapper;

/**
 * このクラスは、URL情報よりアクションのマッピングコントロールを行うサーブレットクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/01
 * @author Kawakicchi
 */
public final class UrlActionMappingServlet extends AbstractActionMappingServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2110980628087062116L;

	/**
	 * Url action mapper class
	 */
	private UrlActionMapper mapper;

	/**
	 * コンストラクタ
	 */
	public UrlActionMappingServlet() {
		super(UrlActionMappingServlet.class);
	}

	@Override
	protected void doInitialize(final ServletConfig aConfig) throws ServletException {
		try {
			String mapperClass = aConfig.getInitParameter("UrlActionMapper");
			Class<?> clazz = Class.forName(mapperClass);
			Object obj = clazz.newInstance();
			if (obj instanceof UrlActionMapper) {
				mapper = (UrlActionMapper) obj;
			} else {
				error("This class is unsupported UrlActionMappter.[" + mapperClass + "]");
			}
		} catch (ClassNotFoundException ex) {
			throw new ServletException(ex);
		} catch (IllegalAccessException ex) {
			throw new ServletException(ex);
		} catch (InstantiationException ex) {
			throw new ServletException(ex);
		}
	}

	@Override
	protected void doDestroy() {

	}

	@Override
	public void doGet(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		doTask(aReq, aRes);
	}

	@Override
	public void doPost(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		doTask(aReq, aRes);
	}

	@Override
	protected final Action createAction(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		return mapper.mapping(aReq, aRes);
	}

}
