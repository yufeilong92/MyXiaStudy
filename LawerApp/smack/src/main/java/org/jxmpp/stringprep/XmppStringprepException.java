/**
 *
 * Copyright © 2014-2015 Florian Schmaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jxmpp.stringprep;

public class XmppStringprepException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8491853210107124624L;

	private final String causingString;

	public XmppStringprepException(String causingString, Exception exception) {
		super("XmppStringprepException caused by '" + causingString + "': " + exception, exception);
		this.causingString = causingString;
	}

	public XmppStringprepException(String causingString, String message) {
		super(message);
		this.causingString = causingString;
	}

	public String getCausingString() {
		return causingString;
	}
}
