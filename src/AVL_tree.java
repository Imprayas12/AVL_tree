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
    public Node min(Node root){
        Node temp = root;
        while(temp.left != null) temp = temp.left;
        return temp;
    }
        public Node delete(Node root,int data){
        if(root == null) return root;
        if(data < root.data) root.left = delete(root.left,data);
        else if(data > root.data) root.right = delete(root.right,data);
        else {
            if(root.left == null || root.right == null)
            {
                Node temp = null;
                if(temp == root.left) temp = root.right;
                else temp = root.left;

                if(temp == null) // No child
                {
                    temp = root;
                    root = null;
                }
                else  // One child
                    root = temp;
            }
            else{
                Node temp = min(root.right);
                root.data = temp.data;
                root.right = delete(root.right,temp.data);
            }
        }
        if(root == null) return root;
        root.height = Math.max(height(root.left),height(root.right));
        int balance = getBal(root);
        if(balance > 1 && getBal(root.left)>=0)
            rightRotate(root);
        if(balance > 1 && getBal(root.left)<0){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance < -1 && getBal(root.right)<=0)
            leftRotate(root);
        if(balance < -1 && getBal(root.right)>0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
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
