public class Palindrome {
    public static void main(String[] args) {
        String[] words = {"madam", "racecar", "apple", "kayak", "song", "noon"};
        
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (isPalindrome(s)) {
                System.out.println(s);
            }
        }
    }
    
    public static String reverseString(String s) {
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        return result;
    }
    
    public static boolean isPalindrome(String s) {
        String reversed = reverseString(s);
        return s.equals(reversed);
    }
}