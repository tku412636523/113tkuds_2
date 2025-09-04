import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();
        sc.close();

        System.out.println("Combination Sum I (reusable):");
        List<List<Integer>> res1 = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackI(candidates, target, 0, new ArrayList<>(), res1);
        for (List<Integer> comb : res1) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i) + (i < comb.size()-1 ? " " : ""));
            }
            System.out.println();
        }

        System.out.println("Combination Sum II (each once, unique):");
        List<List<Integer>> res2 = new ArrayList<>();
        backtrackII(candidates, target, 0, new ArrayList<>(), res2);
        for (List<Integer> comb : res2) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i) + (i < comb.size()-1 ? " " : ""));
            }
            System.out.println();
        }
    }

    // I 版：同一數字可重複使用
    public static void backtrackI(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remain) break;
            path.add(candidates[i]);
            backtrackI(candidates, remain - candidates[i], i, path, res); // 可重複使用
            path.remove(path.size()-1);
        }
    }

    // II 版：每個數字僅一次，需去重
    public static void backtrackII(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i-1]) continue; // 同層跳過重複
            if (candidates[i] > remain) break;
            path.add(candidates[i]);
            backtrackII(candidates, remain - candidates[i], i+1, path, res); // 每個元素僅一次
            path.remove(path.size()-1);
        }
    }
}
