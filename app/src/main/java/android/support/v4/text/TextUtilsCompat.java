package android.support.v4.text;

import com.baidu.location.BDLocation;
import java.util.Locale;

public class TextUtilsCompat {
    private static String ARAB_SCRIPT_SUBTAG = "Arab";
    private static String HEBR_SCRIPT_SUBTAG = "Hebr";
    public static final Locale ROOT = new Locale("", "");

    public static String htmlEncode(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\"':
                    stringBuilder.append("&quot;");
                    break;
                case '&':
                    stringBuilder.append("&amp;");
                    break;
                case '\'':
                    stringBuilder.append("&#39;");
                    break;
                case '<':
                    stringBuilder.append("&lt;");
                    break;
                case BDLocation.TypeCriteriaException /*62*/:
                    stringBuilder.append("&gt;");
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public static int getLayoutDirectionFromLocale(Locale locale) {
        if (!(locale == null || locale.equals(ROOT))) {
            String script = ICUCompat.getScript(ICUCompat.addLikelySubtags(locale.toString()));
            if (script == null) {
                return getLayoutDirectionFromFirstChar(locale);
            }
            if (script.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) || script.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG)) {
                return 1;
            }
        }
        return 0;
    }

    private static int getLayoutDirectionFromFirstChar(Locale locale) {
        switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
            case (byte) 1:
            case (byte) 2:
                return 1;
            default:
                return 0;
        }
    }
}
