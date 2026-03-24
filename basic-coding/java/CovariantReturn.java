
public class CovariantReturn {

    public static void main(String[] args) {
        System.out.printf("A.doSomething=%s%n", A.doSomething().toString());
        System.out.printf("B.doSomething=%s%n", B.doSomething().toString());
    }

    public static class A {
        protected static Number doSomething() {
            Integer retVal = 3;
            return retVal;
        }
    }

    public static class B extends A {
        protected static Double doSomething() {
            Double retVal = 3.14D;
            return retVal;
        }

    }
}
