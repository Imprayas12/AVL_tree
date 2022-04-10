class Node {
    int data,height;
    Node left,right;
    public Node(int data){
        this.data= data;
        height = 1;
    }
}

public class AVL_tree {
    Node root;
    public int height(Node root){
        if(root==null)
            return 0;
        return root.height;
    }
    public Node insert(Node root,int data){
        if(root == null)
            return new Node(data);
        if(data > root.data){
            root.right = insert(root.right, data);
        }
        else if(data < root.data) {
            root.left = insert(root.left,data);
        }
        else return root;
        root.height = 1 + (Math.max(height(root.left),height(root.right)));
        int bal = getBal(root);
        if(bal > 1 && data < root.left.data)
            return rightRotate(root);
        if(bal < -1 && data > root.right.data)
            return leftRotate(root);
        if(bal > 1 && data > root.left.data){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(bal < -1 && data < root.right.data){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    public int getBal(Node root){
        if(root == null) return 0;
        return height(root.left) - height(root.right);
    }
    public Node rightRotate(Node x){
        Node y = x.left;
        Node t2 = y.right;
        y.right = x;
        x.left = t2;

        x.height = Math.max(height(x.left),height(x.right)) +1;
        y.height = Math.max(height(y.left),height(y.right)) +1;

        return y;
    }
    public Node leftRotate(Node x){
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;
        x.height = Math.max(height(x.left),height(x.right)) +1;
        y.height = Math.max(height(y.left),height(y.right)) +1;
        return y;
    }
    public void preOrder(Node root){
     if(root==null) return;
        System.out.print(root.data +" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
    AVL_tree tree = new AVL_tree();
    tree.root = tree.insert(tree.root,10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        tree.preOrder(tree.root);
    }
}
