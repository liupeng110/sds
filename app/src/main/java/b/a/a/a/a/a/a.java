package b.a.a.a.a.a;

/* AbstractContentBody */
public abstract class a implements b {
    private final String a;
    private final String b;
    private final String c;

    public a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("MIME type may not be null");
        }
        this.a = str;
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            this.b = str.substring(0, indexOf);
            this.c = str.substring(indexOf + 1);
            return;
        }
        this.b = str;
        this.c = null;
    }

    public String a() {
        return this.a;
    }
}
