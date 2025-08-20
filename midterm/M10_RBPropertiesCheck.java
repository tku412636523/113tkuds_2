
import java.util.*;

class RBNode {
    int val;
    char color; 
    RBNode(int val, char color) { this.val = val; this.color = color; }
}

public class M10_RBPropertiesCheck {

    static RBNode[] nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        nodes = new RBNode[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String colorStr = sc.next();
            char color = (val == -1) ? 'B' : colorStr.charAt(0); 
            nodes[i] = new RBNode(val, color);
        }

        if (nodes.length > 0 && nodes[0].val != -1 && nodes[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        int blackHeight = checkRB(0);
        if (blackHeight == -1) return; 

        System.out.println("RB Valid");
    }

    private static int checkRB(int index) {
        if (index >= nodes.length || nodes[index].val == -1) return 1; 

        RBNode node = nodes[index];

        int leftIdx = 2 * index + 1;
        int rightIdx = 2 * index + 2;

        if (node.color == 'R') {
            if ((leftIdx < nodes.length && nodes[leftIdx].color == 'R') ||
                (rightIdx < nodes.length && nodes[rightIdx].color == 'R')) {
                System.out.println("RedRedViolation at index " + index);
                return -1;
            }
        }

        int leftBH = checkRB(leftIdx);
        if (leftBH == -1) return -1;
        int rightBH = checkRB(rightIdx);
        if (rightBH == -1) return -1;

        if (leftBH != rightBH) {
            System.out.println("BlackHeightMismatch");
            return -1;
        }

        return leftBH + (node.color == 'B' ? 1 : 0);
    }
}
