package org.sitracel.paie.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimals {
	private BigDecimals() {}
    static final BigDecimal ZERO = BigDecimal.ZERO;

    static BigDecimal nvl(BigDecimal v) { return v != null ? v : ZERO; }
    static boolean isZero(BigDecimal v) { return v == null || v.compareTo(ZERO) == 0; }

    static BigDecimal ruleOfThree(BigDecimal a, Integer b, Integer c, int scale) {
        if (isZero(a) || b == null || b == 0 || c == null || c == 0) return ZERO;
        return a.multiply(BigDecimal.valueOf(c))
                .divide(BigDecimal.valueOf(b), scale, RoundingMode.HALF_UP);
    }
}
