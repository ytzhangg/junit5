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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;

/**
 * Integration tests for {@link RepeatedTest}.
 *
 * @since 5.0
 */
class RepeatedTestTests {

	private static int fortyTwo = 0;

	@RepeatedTest(42)
	void repeatedFortyTwoTimes(TestInfo testInfo) {
		assertThat(testInfo.getDisplayName()).startsWith("repeatedFortyTwoTimes(TestInfo) :: repetition ");
		fortyTwo++;
	}

	@AfterAll
	static void afterAll() {
		assertEquals(42, fortyTwo);
	}

	@RepeatedTest(-99)
	void negativeRepeatCount(TestInfo testInfo) {
		assertThat(testInfo.getDisplayName()).isEqualTo("negativeRepeatCount(TestInfo) :: repetition 1");
	}

	@RepeatedTest(0)
	void zeroRepeatCount(TestInfo testInfo) {
		assertThat(testInfo.getDisplayName()).isEqualTo("zeroRepeatCount(TestInfo) :: repetition 1");
	}

	@RepeatedTest(1)
	void repeatedOnce(TestInfo testInfo) {
		assertThat(testInfo.getDisplayName()).isEqualTo("repeatedOnce(TestInfo) :: repetition 1");
	}

	@RepeatedTest(1)
	void defaultDisplayName(TestInfo testInfo) {
		assertThat(testInfo.getDisplayName()).isEqualTo("defaultDisplayName(TestInfo) :: repetition 1");
	}

	@RepeatedTest(1)
	@DisplayName("Repeat!")
	void customDisplayName(TestInfo testInfo) {
		assertThat(testInfo.getDisplayName()).isEqualTo("Repeat! :: repetition 1");
	}

}
