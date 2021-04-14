package treeSort;

import java.util.ArrayList;

public class Tree {
    public Tree left;
    public Tree right;
    public String key;

    public Tree(String k) {        // конструктор с инициализацией ключа
        key = k;
    }

    public void insert( Tree aTree) {
        if ( aTree.key.compareTo(key) < 0 )
            if ( left != null ) left.insert( aTree );
            else left = aTree;
        else
        if ( right != null ) right.insert( aTree );
        else right = aTree;
    }

    public void insertArray(ArrayList<String> arr){
        for (String i : arr) {
            insert(new Tree(i));
        }
    }

    public ArrayList<String> traverse(ArrayList<String> visited) {
        if ( left != null)
            left.traverse(visited);

        visited.add(this.key);

        if ( right != null )
            right.traverse(visited);
        return visited;
    }
}
