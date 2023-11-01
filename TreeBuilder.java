

public class TreeBuilder<T> {                                                              // this class is used to create the binary trees 

   
   public LinkedBinaryTree<T> buildTree(T[] array){                                        // takes in a T array input parameter which contains the set of values that will be inserted in the new tree's nodes
       
       LinkedQueue<T> dataQueue = new LinkedQueue<T>();                                    // initialise dataQueue to contain all elements of the array
       
       for(int i = 0; i < array.length;i++){                                               // loop for all the elements to be added into the nodes in order
           dataQueue.enqueue(array[i]);
       }
      
       LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<BinaryTreeNode<T>>();  // empty queue
      
       BinaryTreeNode<T> root = new BinaryTreeNode<T>(dataQueue.dequeue());                // object with the first element of dataQueue
       
       parentQueue.enqueue(root);                                                          // enqueue the root node on parentQueue
      
       while(!dataQueue.isEmpty()) {                                                       // while dataQueue has elements
           T a = dataQueue.dequeue();                                                      // dequeue from dataQueue
           T b = dataQueue.dequeue();                                                      // dequeue from dataQueue
          
           BinaryTreeNode<T> parent = parentQueue.dequeue();
          
           if(a != null) {                                                                 // if a is not null
               BinaryTreeNode<T> node = new BinaryTreeNode<T>(a);                          // added a node storing a
               parent.setLeft(node);                                                       // set a as left child of parent
               parentQueue.enqueue(node);                                                  // enqueue on parentQueue
           }
          
           if(b != null) {                                                                 // if b is not null
               BinaryTreeNode<T> node = new BinaryTreeNode<T>(b);                          // added a node storing b
               parent.setRight(node);                                                      // set b as right child of parent
               parentQueue.enqueue(node);                                                  // enqueue on parentQueue
           }
       }
      
       return new LinkedBinaryTree<T>(root);                                               // return the LinkedBinaryTree object with the root node
   }

}