/**
 * Created by theoxola on 2017-04-01.
 */

import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainClass {

    private static MyTreeNode root;
    private static String toFind;
    private static boolean timeOut;
    private static long timeLimSecs = 0;
    private static long tStart;

    public static void main(String[] args){

        /**
         * DEVELOPMENT VERSION
         */
        /*
        // keep pointer to root
        Board bd = new Board(args[0]);
        root = new MyTreeNode(bd);

        // identify requested end state
        Board find = new Board(args[1]);
        toFind = find.getLayout();
        System.out.println("State to find: " + toFind);

        // begin search
        System.out.println("Root loaded with form " + root.getForm());
        load();
        */

        /**
         * END USER VERSION
         */

        Scanner in = new Scanner(System.in);

        // initialise and keep pointer to root
        System.out.print("Please enter your start-state: ");
        Board bd = new Board(in.next());
        root = new MyTreeNode(bd);
        System.out.println("(start date initialised: " + root.getForm() + ")\n");

        // initialise and store requested end state -- run through board to validate
        System.out.print("Please enter the state you wish to reach: ");
        Board tf = new Board(in.next());
        toFind = tf.getLayout();
        System.out.println("(end state initialised: " + toFind + ")\n");

        System.out.println("Do you want to set a maximum time to allow the code to run for? (y/n)");
        String ans = in.next();

        while (!ans.equals("y") && !ans.equals("n")){
            System.out.println("Please answer 'y' or 'n':");
            ans = in.next();
        }

        if (ans.equals("y")){
            timeOut = true;
        }

        else {
            timeOut = false;
        }

        if (timeOut){
            System.out.print("\nPlease enter the amount of minutes you wish the code to run for; ");
            do {
                System.out.print("Whole, positive minutes only, please: ");
                try {
                    timeLimSecs = in.nextInt() * 60;
                } catch (InputMismatchException e) {
                    // skip over users last input
                    in.next();
                }
            }
            while (timeLimSecs <= 0);
        }

        in.close();

        tStart = System.nanoTime();

        // begin search
        load();

    }



    private static void load(){

        if (root != null){

            System.out.println();
            System.out.println("Beginning search for the requested state...");
            System.out.println();

            Queue<MyTreeNode> queue = new LinkedList<MyTreeNode>();

            queue.add(root);

            boolean stateNotYetFound = true;
            boolean timeOver = false;

            while(!queue.isEmpty() && stateNotYetFound && !timeOver){

                MyTreeNode node = queue.remove();

                Board base = new Board(node.getForm());

                Board[] nextBoards = Board.nextBoards(base);

                for (Board b : nextBoards){

                    String form = b.getLayout();
                    MyTreeNode nd = MyTreeNode.getNode(form, root);

                    if (nd == null){
                        node.addChild(form);
                        //System.out.println("Board number " + count + " loaded: " + form);
                       // System.out.println("Board with form " + form + " loaded");

                        // attempt to find the state requested
                        if(find()){
                            stateNotYetFound = false;
                        }

                        else {
                            //enqueue next
                            nd = MyTreeNode.getNode(form, root);
                            queue.add(nd);
                        }

                    }

                }

                if(timeOut){
                    long elapsedTime = System.nanoTime() - tStart;

                    if (TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) > timeLimSecs) {
                        timeOver = true;
                    }
                }
            }

            if (timeOver){
                System.out.println("!!! No solution could be found in the given amount of time.");
            }

            System.out.print("\nProgram finished running. Goodbye.");
        }
    }

    private static boolean find() {

        MyTreeNode found = MyTreeNode.getNode(toFind, root);
        boolean toReturn = false;

        if (found != null) {
            List<MyTreeNode> parents = new ArrayList<>();

            List<MyTreeNode> foundPath = MyTreeNode.treePath(found, parents);

            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            System.out.println("---> REQUESTED STATE FOUND <---");
            System.out.println();

            int lvl = 0;
            for (MyTreeNode node : foundPath) {
                System.out.println();
                System.out.println(lvl + "\t" + node.getForm());
                lvl++;
            }


            toReturn = true;
        }

        return toReturn;

    }
}
