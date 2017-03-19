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

import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

import org.junit.jupiter.api.InvocationIndex;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * {@link ParameterResolver} that supports resolution of the current
 * <em>invocation index</em> for parameters annotated with {@code @InvocationIndex}.
 *
 * @since 5.0
 */
class InvocationIndexParameterResolver implements ParameterResolver {

	private final Integer invocationIndex;

	InvocationIndexParameterResolver(int invocationIndex) {
		this.invocationIndex = invocationIndex;
	}

	@Override
	public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return isAnnotated(parameterContext.getParameter(), InvocationIndex.class);
	}

	@Override
	public Integer resolve(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return this.invocationIndex;
	}

}
