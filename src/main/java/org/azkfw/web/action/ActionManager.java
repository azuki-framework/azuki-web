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
package org.azkfw.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.azkfw.business.manager.AbstractManager;
import org.azkfw.context.Context;
import org.azkfw.persistence.entity.Entity;
import org.azkfw.util.StringUtility;
import org.xml.sax.SAXException;

/**
 * このクラスは、アクションの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/04
 * @author Kawakicchi
 */
public final class ActionManager extends AbstractManager {

	/**
	 * Instance
	 */
	private static final ActionManager INSTANCE = new ActionManager();

	/**
	 * action map
	 */
	private Map<String, Class<? extends Action>> actions;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private ActionManager() {
		super(ActionManager.class);
		actions = new HashMap<String, Class<? extends Action>>();
	}

	/**
	 * 初期化処理を行う。
	 */
	public static void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public static void destroy() {
		INSTANCE.doDestroy();
	}

	/**
	 * アクション設定をロードする。
	 * 
	 * @param aStream 設定ストリーム
	 * @param aContext コンテキスト
	 * @throws IOException　IO操作時に問題が発生した場合
	 */
	public static void load(final InputStream aStream, final Context aContext) throws IOException {
		INSTANCE.doLoad(aStream, aContext);
	}

	/**
	 * アクションを生成する。
	 * 
	 * @param aName アクション名
	 * @return アクション
	 */
	public static final Action create(final String aName) {
		return INSTANCE.doCreate(aName);
	}

	/**
	 * 初期化処理を行う。
	 */
	private void doInitialize() {
		synchronized (actions) {

		}
	}

	/**
	 * 解放処理を行う。
	 */
	private void doDestroy() {
		synchronized (actions) {

		}
	}

	/**
	 * アクション設定をロードする。
	 * 
	 * @param aStream 設定ストリーム
	 * @param aContext コンテキスト
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	@SuppressWarnings("unchecked")
	private void doLoad(final InputStream aStream, final Context aContext) throws IOException {
		synchronized (actions) {
			List<ActionEntity> as = null;
			try {
				Digester digester = new Digester();
				digester.addObjectCreate("azuki/action-list", ArrayList.class);
				digester.addObjectCreate("azuki/action-list/action", ActionEntity.class);
				digester.addSetProperties("azuki/action-list/action");
				digester.addSetNext("azuki/action-list/action", "add");
				as = (List<ActionEntity>)digester.parse(aStream);
			} catch (SAXException ex) {
				error(ex);
				throw new IOException(ex);
			} catch (IOException ex) {
				error(ex);
				throw ex;
			}

			try {
				for (ActionEntity entity : as) {
					if (actions.containsKey(entity.getName())) {
						error("Duplicate action name.[" + entity.getName() + "]");
						continue;
					}
					Class<? extends Action> clazz = (Class<? extends Action>) Class.forName(entity.getAction());
					actions.put(entity.getName(), clazz);
				}
			} catch (ClassNotFoundException ex) {
				error(ex);
			}
		}
	}

	/**
	 * アクションを生成する。
	 * 
	 * @param aName アクション名
	 * @return アクション。アクションが存在しない場合、<code>null</code>を返す。
	 */
	private Action doCreate(final String aName) {
		Action action = null;
		Class<? extends Action> clazz = null;
		if (actions.containsKey(aName)) {
			clazz = actions.get(aName);
		}
		if (null != clazz) {
			try {
				action = clazz.newInstance();
			} catch (InstantiationException ex) {
				error(ex);
			} catch (IllegalAccessException ex) {
				error(ex);
			}
		}
		return action;
	}

	/**
	 * このクラスは、アクション情報を保持するエンティティクラスです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 2013/02/18
	 * @author Kawakicchi
	 */
	public static class ActionEntity implements Entity {

		/**
		 * Action name
		 */
		private String name;

		/**
		 * Action class
		 */
		private String action;

		/**
		 * コンストラクタ
		 */
		public ActionEntity() {

		}

		/**
		 * アクション名を設定する。
		 * 
		 * @param aName アクション名
		 */
		public void setName(final String aName) {
			name = aName;
		}

		/**
		 * アクション名を取得する。
		 * 
		 * @return アクション名
		 */
		public String getName() {
			return name;
		}

		/**
		 * アクションクラスを設定する。
		 * 
		 * @param aAction アクションクラス
		 */
		public void setAction(final String aAction) {
			action = aAction;
		}

		/**
		 * アクションクラスを取得する。
		 * 
		 * @return アクションクラス
		 */
		public String getAction() {
			return action;
		}

		@Override
		public boolean isEmpty() {
			if (StringUtility.isNotEmpty(name)) {
				return false;
			}
			if (StringUtility.isNotEmpty(action)) {
				return false;
			}
			return true;
		}
	}

}
