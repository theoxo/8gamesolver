/**
 * Created by theoxola on 2017-03-31.
 */

import java.util.List;
import java.util.ArrayList;


public class MyTreeNode {

    private String form = null;
    private List<MyTreeNode> children = new ArrayList();
    private MyTreeNode parent = null;

    // construct from Board
    public MyTreeNode(Board b){

        this.form = b.getLayout();
    }

    // construct from String (PRIVATE)
    private MyTreeNode(String form){
        this.form = form;
    }

    // add a new child to current node from String
    public void addChild(String data){
        MyTreeNode child = new MyTreeNode(data);
        this.children.add(child);
        child.setParent(this);
    }

    // set parent of current node
    private void setParent(MyTreeNode parent){
        this.parent = parent;
    }

    public String getForm() {
        return form;
    }

    public MyTreeNode getParent() {
        return parent;
    }


    public List<MyTreeNode> getChildren(){
        return children;
    }

    // returns 1 if it finds the given string; else 0.
    public static int contains(MyTreeNode root, String s){
        if (root.getForm().equals(s)){
            return 1;
        }

        else {

            List<MyTreeNode> kids = root.getChildren();
            int sumChildren = 0;

            for (MyTreeNode kid : kids){
                sumChildren += contains(kid, s);
            }

            return 0 + sumChildren;
        }
    }

    // get tree treePath to node by recursively returning parents
    public static List<MyTreeNode> treePath(MyTreeNode node, List<MyTreeNode> path){

        path.add(node);

        if (node.getParent() != null){
            node = node.getParent();
            treePath(node, path);
        }

        return path;
    }


    /*
    // get Node by search after string
    public static void getNode(String searchLayout, List<MyTreeNode> nodeList, MyTreeNode trav){
        List<MyTreeNode> kids = trav.getChildren();

        if (trav.getForm().equals(searchLayout)){
            nodeList.add(trav);
            System.out.println("Node found");
            System.out.println(nodeList.get(0).getForm());
        }

        else if (!kids.isEmpty()){
            for (MyTreeNode kid : kids){

                getNode(searchLayout, nodeList, kid);
            }
        }

    }*/




    public static MyTreeNode getNode(String searchLayout, MyTreeNode trav){
        MyTreeNode res = null;
        if (trav.getForm().equals(searchLayout)){
            return trav;
        }

        else {
            List<MyTreeNode> children = trav.getChildren();

            int childrenLength = children.size();

            for (int i = 0; i < childrenLength && res == null; i++){
                res = getNode(searchLayout, children.get(i));
               // System.out.println("Test\t" +i);
            }

            return res;
        }
    }
    /*
    public MyTreeNode getNode(String searchLayout){

        List<MyTreeNode> kids = this.getChildren();

        if (this.getForm().equals(searchLayout)){
            return (this);
        }

        else if(!kids.isEmpty()){
            for (MyTreeNode kid : kids){
                kid.getNode(searchLayout);

            }
        }

        else {
            return null;
        }

    }
    */
}
