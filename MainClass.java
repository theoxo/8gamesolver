/**
 * Created by theoxola on 2017-04-01.
 */

import java.util.List;
import java.util.ArrayList;
public class MainClass {

    public static MyTreeNode root;

    public static void main(String[] args){
        /*
        Board b = new Board();
        MyTreeNode root = new MyTreeNode(b);

        root.addChild("7813*5642");
        root.addChild("7823*5641");

        for (MyTreeNode child : root.getChildren()){
            child.addChild("1");
        }

        // expected: 1 1 0 2 0
        System.out.println(MyTreeNode.contains(root, "7823*5641"));
        System.out.println(MyTreeNode.contains(root, "7813*5642"));
        System.out.println(MyTreeNode.contains(root, "7813*5641"));
        System.out.println(MyTreeNode.contains(root, "1"));
        System.out.println(MyTreeNode.contains(root, "2"));

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        List<MyTreeNode> testList = new ArrayList();

        List<MyTreeNode> test_getNode = new ArrayList();

        MyTreeNode.getNode("1", test_getNode, root);



        System.out.println("tn " + test_getNode.get(0).getForm());

        List<MyTreeNode> path1 = MyTreeNode.treePath(test_getNode.get(0), testList);


        int lvl = 0;
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("PARENT TABLE");

        for (MyTreeNode node : path1) {
            System.out.println();
            System.out.println(lvl + "\t" + node.getForm());
            lvl++;
        }*/





        // keep pointer to root
        Board bd = new Board(args[0]);
        root = new MyTreeNode(bd);
        Board find = new Board(args[1]);

        System.out.println("Root loaded with form " +root.getForm());
        load(root);

        System.out.println("State to find: " + find.getLayout());

        MyTreeNode found = MyTreeNode.getNode(find.getLayout(), root);

        if(found == null){
           System.out.println("Could not find node --- no solution could be found!");
        }

        else {

            List<MyTreeNode> parents = new ArrayList();

            List<MyTreeNode> foundPath = MyTreeNode.treePath(found, parents);

            int lvl = 0;
            for (MyTreeNode node : foundPath) {
                System.out.println();
                System.out.println(lvl + "\t" + node.getForm());
                lvl++;
            }
        }
    }


    static int count = 0;

    // Load all possible boards into memory
    private static void load(MyTreeNode trav){

        Board base = new Board(trav.getForm());

        //Board[] nextBoards = new Board[4];

        // generate next boards
        Board[] nextBoards = Board.nextBoards(base);

        /*
        // add next boards as children to current board
        for (Board b: nextBoards) {
            if (MyTreeNode.contains(root, b.getLayout()) == 0){
                trav.addChild(b.getLayout());
                List<MyTreeNode> tst = new ArrayList();

                MyTreeNode.getNode(b.getLayout(), tst, trav);
                MyTreeNode nd = tst.get(0);

                load(nd);
            }
        }
        */

        //add next boards as children to current board
        for (Board b : nextBoards){
            MyTreeNode nd = MyTreeNode.getNode(b.getLayout(), root);

            if (nd == null){
                trav.addChild( b.getLayout() );
                System.out.println("Board number " + count + " loaded: " + b.getLayout());

                nd = MyTreeNode.getNode(b.getLayout(), root);
                load(nd);

            }

            else {
                System.out.println("!!! Board number " + count + " already in memory");
            }

            count++;
        }
    }
}
