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
		Method templateMethod = Preconditions.notNull(context.getTestMethod().orElse(null),
			"test method must not be null");

		RepeatedTestInvocationContext testInvocationContext = new RepeatedTestInvocationContext(
			createDisplayNameFormatter(templateMethod), context.getDisplayName());

		// @formatter:off
		return IntStream
				.rangeClosed(1, getRepeatCount(templateMethod))
				.mapToObj(n -> testInvocationContext);
		// @formatter:on
	}

	private int getRepeatCount(Method templateMethod) {
		// @formatter:off
		int count = AnnotationUtils.findAnnotation(templateMethod, RepeatedTest.class)
				.map(RepeatedTest::value)
				.orElse(1);
		// @formatter:on
		return Math.max(1, count);
	}

	private RepeatedTestDisplayNameFormatter createDisplayNameFormatter(Method templateMethod) {
		String name = AnnotationUtils.findAnnotation(templateMethod, RepeatedTest.class).get().name();
		return new RepeatedTestDisplayNameFormatter(name);
	}

}
