import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false; 
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}

/*
範例：
s = "([])"
流程：
1. '(' 入 stack → ['(']
2. '[' 入 stack → ['(', '[']
3. ']' → pop '[' 
4. ')' → pop '(' 
stack 空 → true
*/
