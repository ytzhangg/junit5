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

/**
 * @since 5.0
 */
class RepeatedTestDisplayNameFormatter {

	private final String namePattern;
	private final String displayName;
	private final int numRepetitions;

	RepeatedTestDisplayNameFormatter(String namePattern, String displayName, int numRepetitions) {
		this.namePattern = namePattern;
		this.displayName = displayName;
		this.numRepetitions = numRepetitions;
	}

	String format(int repetition) {
		return this.namePattern//
				.replace("{displayName}", this.displayName)//
				.replace("{repetition}", String.valueOf(repetition))//
				.replace("{repetitions}", String.valueOf(this.numRepetitions));
	}

}
