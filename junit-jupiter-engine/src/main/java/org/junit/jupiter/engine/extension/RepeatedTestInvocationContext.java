/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.jupiter.engine.extension;

import static java.util.Collections.singletonList;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

/**
 * {@code TestTemplateInvocationContext} for a {@link RepeatedTest @RepeatedTest}.
 *
 * @since 5.0
 */
class RepeatedTestInvocationContext implements TestTemplateInvocationContext {

	private final int currentRepetition;
	private final RepeatedTestDisplayNameFormatter formatter;

	RepeatedTestInvocationContext(int currentRepetition, RepeatedTestDisplayNameFormatter formatter) {
		this.currentRepetition = currentRepetition;
		this.formatter = formatter;
	}

	@Override
	public String getDisplayName(int invocationIndex) {
		return this.formatter.format(this.currentRepetition);
	}

	@Override
	public List<Extension> getAdditionalExtensions() {
		return singletonList(new InvocationIndexParameterResolver(this.currentRepetition));
	}

}
