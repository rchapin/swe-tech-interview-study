import java.util.ArrayList;
import java.util.List;

public class PowersOfTwo {

    public static String formatBinary(long l) {
        // Convert the long to a String representation of the bits
        StringBuffer lBuf = new StringBuffer(Long.toBinaryString(l));
        StringBuffer buf = new StringBuffer();

        // Add additional 0 bits to the left to round out to the nearest
        // nibble.
        int mod = lBuf.length() % 4;
        int numZerosToPad = mod == 0 ? 0 : 4 - mod;
        for (int i = 0; i < numZerosToPad; i++) {
            buf.append("0");
        }

        int currIdx = numZerosToPad;
        for (int i = 0; i < lBuf.length(); i++) {
            currIdx++;
            buf.append(lBuf.charAt(i));
            if (currIdx % 4 == 0) {
                // Add a space to separate the nibbles
                buf.append(" ");
            }
        }
        return buf.toString().trim();
    }

    public static void printOutput(List<BinaryOutput> output, boolean html) {
        for (BinaryOutput o : output) {
            String format = "%s, %d, %s%n";
            if (html) {
                format = "<tr><td>%s</td><td>%d</td><td>%s</td></tr>%n";
            }
            System.out.printf(format, o.decimal, o.bits, o.binary);
        }
    }

    public static void main(String[] args) {
        boolean html = false;
        if (args.length > 0) {
            // Check to see if we should output HTML.
            if (args[0].equals("html")) {
                html = true;
            }
        }
        List<BinaryOutput> output = new ArrayList<>();
        long x;
        for (int i = 0; i < 65; i++) {
            x = 1;
            x = x << i;
            output.add(
                new BinaryOutput(
                    String.format("%,d", x),
                    i+1,
                    formatBinary(x))
            );
        }
        printOutput(output, html);
    }

    public static class BinaryOutput {
        String decimal;
        int bits;
        String binary;

        public BinaryOutput(String decimal, int bits, String binary) {
            this.decimal = decimal;
            this.bits = bits;
            this.binary = binary;
        }
    }
}
