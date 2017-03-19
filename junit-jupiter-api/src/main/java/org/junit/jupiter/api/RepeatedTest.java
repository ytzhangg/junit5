/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.jupiter.api;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.platform.commons.meta.API;

/**
 * {@code @RepeatedTest} is used to signal that the annotated method is a
 * <em>test template</em> method that should be repeated a specified number of
 * times.
 *
 * <p>Each invocation of the repeated test behaves like the execution of a
 * regular {@link Test @Test} method will full support for the same lifecycle
 * callbacks and extensions.
 *
 * <p>{@code @RepeatedTest} methods must not be {@code private} or {@code static}
 * and must return {@code void}.
 *
 * <p>{@code @RepeatedTest} methods may optionally declare parameters to be
 * resolved by {@link org.junit.jupiter.api.extension.ParameterResolver
 * ParameterResolvers}.
 *
 * <p>{@code @RepeatedTest} may also be used as a meta-annotation in order to
 * create a custom <em>composed annotation</em> that inherits the semantics
 * of {@code @RepeatedTest}.
 *
 * @since 5.0
 * @see DisplayName
 * @see TestInfo
 * @see TestTemplate
 * @see Test
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(Experimental)
@TestTemplate
public @interface RepeatedTest {

	/**
	 * The number of times the test should be repeated.
	 *
	 * <p>Any value less than {@code 1} will be treated as {@code 1}.
	 */
	int value();

}
