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
 * {@code @InvocationIndex} is used to inject the current <em>invocation index</em>
 * into {@code @Test}, {@code @BeforeEach}, and {@code @AfterEach} methods.
 *
 * <p>An invocation index is a counter associated with a given
 * {@link org.junit.jupiter.api.extension.TestTemplateInvocationContext
 * TestTemplateInvocationContext}, such as the current repetition in a
 * {@link RepeatedTest @RepeatedTest}.
 *
 * <p>If a method parameter is of type {@code int} or {@code Integer} and
 * annotated with {@code @InvocationIndex}, JUnit will supply the current
 * <em>invocation index</em> as the value for the parameter.
 *
 * @since 5.0
 * @see RepeatedTest
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(Experimental)
public @interface InvocationIndex {
}
