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

import java.lang.reflect.Method;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.StringUtils;

/**
 * {@code TestTemplateInvocationContextProvider} that supports the
 * {@link RepeatedTest @RepeatedTest} annotation.
 *
 * @since 5.0
 */
class RepeatedTestExtension implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supports(ContainerExtensionContext context) {
		return isAnnotated(context.getTestMethod(), RepeatedTest.class);
	}

	@Override
	public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
		Method testMethod = Preconditions.notNull(context.getTestMethod().orElse(null), "test method must not be null");
		String displayName = context.getDisplayName();
		RepeatedTest repeatedTest = AnnotationUtils.findAnnotation(testMethod, RepeatedTest.class).get();

		// @formatter:off
		return IntStream
				.rangeClosed(1, numRepetitions(repeatedTest))
				.mapToObj(n -> new RepeatedTestInvocationContext(n, displayName, displayNameFormatter(repeatedTest)));
		// @formatter:on
	}

	private int numRepetitions(RepeatedTest repeatedTest) {
		return Math.max(1, repeatedTest.value());
	}

	private RepeatedTestDisplayNameFormatter displayNameFormatter(RepeatedTest repeatedTest) {
		String name = repeatedTest.name();
		if (StringUtils.isBlank(name)) {
			// TODO Introduce support for retrieving the default value of an annotation attribute.
			name = "{displayName} :: repetition {repetition}";
		}
		return new RepeatedTestDisplayNameFormatter(name);
	}

}
