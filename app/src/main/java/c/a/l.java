package c.a;

/* ErrorSource */
public enum l {
    LEGIT(1),
    ALIEN(2);
    
    private final int c;

    private l(int i) {
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public static l a(int i) {
        switch (i) {
            case 1:
                return LEGIT;
            case 2:
                return ALIEN;
            default:
                return null;
        }
    }
}
