import java.util.*;

class BinaryTreeNode {
    int data;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(int data) {
        this.data = data;
        left = null;
        right = null; 
    }
}

public class AverageOfLevels {
    static BinaryTreeNode root;

    static void insert(int data) {
        if(root==null) {
            root = new BinaryTreeNode(data);
            return;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            BinaryTreeNode temp = queue.poll();
            if(temp.left==null) {
                temp.left = new BinaryTreeNode(data);
                break;
            }
            queue.add(temp.left);
            if(temp.right==null) {
                temp.right = new BinaryTreeNode(data);
                break;
            }
            queue.add(temp.right);
        }
    }

    static List<Double> avg() {
        List<Double> result = new ArrayList<>();
        if(root==null) return result;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int target = 1;
        int count = 0;
        int sum = 0;
        while(!queue.isEmpty()) {
            BinaryTreeNode temp = queue.poll();
            count += 1;
            sum += temp.data;
            if(count==target) {
                result.add((double)sum/target);
                target *= 2;
                count = 0;
                sum = 0;
            }
            if(temp.left!=null) queue.add(temp.left);
            if(temp.right!=null) queue.add(temp.right);
        }
        if(count!=0 && count!=target) result.add((double)sum/count);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        for(String t : s) {
            insert(Integer.parseInt(t));
        }
        System.out.println(avg());
    }
}

