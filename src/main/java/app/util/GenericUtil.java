package app.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class GenericUtil {

    public static int uInt(Object x) {
        if (x == null)
            return 0;
        if (x instanceof Integer)
            return (Integer) x;
        if (x instanceof BigDecimal)
            return ((BigDecimal) x).intValue();
        if (x instanceof BigInteger)
            return ((BigInteger) x).intValue();
        try {
            String s = x.toString();
            if (s.endsWith(".0"))
                s = s.substring(0, s.length() - 2);
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static BigDecimal uBigDecimal(Object y) {
        String x = y.toString();
        if (x == null || x.trim().length() == 0)
            return null;
        try {
            return new BigDecimal(x);
        } catch (Exception e) {
            if (x.indexOf(',') == -1)
                return null;
            x = x.trim();
            while (x.indexOf('.') != -1)
                x = x.substring(0, x.indexOf('.')) + x.substring(x.indexOf('.') + 1);
            x = x.replace(',', '.');
            try {
                return new BigDecimal(x);
            } catch (Exception z) {
                return null;
            }
        }
    }
}
