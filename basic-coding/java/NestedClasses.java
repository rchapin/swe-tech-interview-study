
public class NestedClasses {

    int a;

    public NestedClasses(int a) {
        this.a = a;
    }

    public static void main(String[] arg) {
        // With Java 16+ we can define static members of Inner Classes, or
        // Non-Static Nested Classes.
//        MemberInner.doSomething();
        System.out.println("MemberInner.CONST=" + MemberInner.CONST);
    }

    public class MemberInner {
        public static final String CONST = "const";
//        public static int b;
        public int a;

//        public static void doSomething() {
//            System.out.printf("MemberInner.doSomething(), b=%d%n", b);
//        }

        public MemberInner(int a) {
            this.a = a;
        }
    }
}

