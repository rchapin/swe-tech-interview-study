public class IPV4Encoding {

    public static final long LSB_MASK = 0x01;
    public static final char ON = 0x01;
    public static final char OFF = 0x00;
    public static final long EIGHT_BIT_MASK = 0xFF;

    public static String toBinary(long ipv4Addr) {
        StringBuilder sb = new StringBuilder();
        long lsb = 0;
        while(ipv4Addr > 0) {
            // Mask off and isolate all but the least significant bit from the
            // input data and then append it to the StringBuilder
            lsb = ipv4Addr & LSB_MASK;
            sb.append(lsb);
            // Use the unsigned right shift operator to push the lsb off the
            // end of the input data in preparation for the next iteration of
            // the loop
            ipv4Addr = ipv4Addr >>> 1;
            lsb = 0;
        }
        // Because Java always uses big endian we need to reverse the current
        // contents of the StringBuilder for the bits to be in the correct
        // order.
        return sb.reverse().toString();
    }

    public static String decode(long ipv4Addr) {
        String[] octets = new String[4];
        // System.out.println(String.join(".", octets));
        long octet = 0L;
        
        for (int i = (octets.length - 1); i > -1; i--) {
            // Mask all but the 8 least most significant bits for each octet
            // and add to the array.
            octet = ipv4Addr & EIGHT_BIT_MASK;
            octets[i] = String.valueOf(octet);
            // Shift the 8 least most significant bits off the end of the input
            // value in preparation for the next iteration of the loop.
            ipv4Addr = ipv4Addr >>> 8;
        }

        return String.join(".", octets);
    }

    public static long encode(String ipv4Addr) {
        String[] tokens = ipv4Addr.split("\\.");
        long retVal = 0L;
        long octet = 0L;
        for (int i = 0; i < tokens.length; i++) {
            octet = Long.parseLong(tokens[i]);
            retVal = retVal | octet;
            if (i < (tokens.length - 1)) {
                retVal = retVal << 8;
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
        String[] ipv4Addrs = {
            "192.168.0.1",
            "8.8.8.8"
        };
        for (String ipv4Addr: ipv4Addrs) {
            long encoded = encode(ipv4Addr);
            // String bits = toBinary(encoded);
            String bits = String.format("%64s", toBinary(encoded)).replace(" ", "0");
            String decode = decode(encoded);
            System.out.printf("%15s encoded is %12s, bits=%s, decoded=%s%n", ipv4Addr, encoded, bits, decode);
        }
    }
}
