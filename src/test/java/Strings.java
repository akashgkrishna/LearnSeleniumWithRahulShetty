public class Strings {
    public static void main(String[] args) {
        String s1 = "FirstString";

//        String s2 = new String("SecondString");
//
//        String s3 = "Hello World String";
//        String[] s = s3.split("World");
//        System.out.println(Arrays.toString(s));
//
//        System.out.println(s[1].trim());
//        System.out.println(s[2]);

//        System.out.println(reverseString(s1));
        System.out.println(createEmail("Diego Stoval"));

    }

//    public static String reverseString(String s) {
//        int len = s.length();
//        char[] chars = new char[len];
//        int count = 0;
//        for (int i = s.length() - 1; i >= 0; i--) {
//            chars[count] = s.charAt(i);
//            count++;
//        }
//
//        return new String(chars);
//    }

    public static String createEmail(String string){
        String lowerCase = string.replace(" ", "").toLowerCase();
        System.out.println(lowerCase);
        lowerCase = lowerCase.concat("@gmail.com");
        return lowerCase;
    }
}
