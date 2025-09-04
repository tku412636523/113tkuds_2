import java.util.*;

public class LC17_PhoneCombos_CSShift {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        sc.close();
        
        if (digits.isEmpty()) return; 
        
        String[] mapping = {
            "",    
            "",    
            "abc",  
            "def", 
            "ghi", 
            "jkl",  
            "mno",  
            "pqrs", 
            "tuv",  
            "wxyz"  
        };
        
        List<String> res = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), mapping, res);
        
        for (String s : res) {
            System.out.println(s);
        }
    }
    
    private static void backtrack(String digits, int index, StringBuilder path, String[] mapping, List<String> res) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }
        
        int digit = digits.charAt(index) - '0';
        String letters = mapping[digit];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, index + 1, path, mapping, res);
            path.deleteCharAt(path.length() - 1); 
        }
    }
}
