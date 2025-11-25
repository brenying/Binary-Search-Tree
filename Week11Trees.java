/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package week.pkg11.trees;

/**
 *
 * @author SFU
 */
public class Week11Trees {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Tree<Integer> tree = new Tree<>();

        int[] values = {10, 20, 5, 15, 25, 30, 2, 8, 18, 12};
        for (int v : values) {
            tree.insert(v);
        }

        tree.printTree();
    }
    
}
