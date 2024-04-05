
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class RecoverBST {
    private TreeNode firstElement = null;
    private TreeNode secondElement = null;
    private TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        traverse(node.left);

        if (firstElement == null && node.val < prevElement.val) {
            firstElement = prevElement;
        }
        if (firstElement != null && node.val < prevElement.val) {
            secondElement = node;
        }
        prevElement = node;

        traverse(node.right);
    }

    private static void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("Original BST:");
        printInOrder(root);

        RecoverBST recoverBST = new RecoverBST();
        recoverBST.recoverTree(root);

        System.out.println("Recovered BST:");
        printInOrder(root);
    }
}

    