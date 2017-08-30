/**
 *
 * Copyright © 2014 Florian Schmaus
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
package org.jxmpp.jid.impl;

import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.DomainBareJid;
import org.jxmpp.jid.DomainFullJid;
import org.jxmpp.jid.FullJid;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.jxmpp.util.XmppStringUtils;


public class LocalAndDomainpartJid extends DomainpartJid implements BareJid {

	protected final Localpart localpart;

	private String cache;
	private String unescapedCache;

	LocalAndDomainpartJid(String localpart, String domain) throws XmppStringprepException {
		super(domain);
		this.localpart = Localpart.from(localpart);
	}

	public final String getLocalpart() {
		return localpart.toString();
	}

	@Override
	public String toString() {
		if (cache != null) {
			return cache;
		}
		cache = getLocalpart() + '@' + super.toString();
		return cache;
	}

	@Override
	public String asUnescapedString() {
		if (unescapedCache != null) {
			return unescapedCache;
		}
		unescapedCache = XmppStringUtils.unescapeLocalpart(getLocalpart()) + '@' + super.toString();
		return unescapedCache;
	}

	@Override
	public BareJid asBareJid() {
		return this;
	}

	@Override
	public String asBareJidString() {
		return toString();
	}

	@Override
	public BareJid asBareJidIfPossible() {
		return asBareJid();
	}

	@Override
	public FullJid asFullJidIfPossible() {
		return null;
	}

	@Override
	public DomainBareJid asDomainBareJidIfPossible() {
		return null;
	}

	@Override
	public DomainFullJid asDomainFullJidIfPossible() {
		return null;
	}

	@Override
	public boolean isParentOf(BareJid bareJid) {
		return domain.equals(bareJid.getDomain()) && localpart.equals(bareJid.getLocalpart());
	}

	@Override
	public boolean isParentOf(FullJid fullJid) {
		return isParentOf((BareJid) fullJid);
	}

	@Override
	public boolean isParentOf(DomainBareJid domainBareJid) {
		return false;
	}

	@Override
	public boolean isParentOf(DomainFullJid domainFullJid) {
		return false;
	}
}
