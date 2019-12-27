package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      return blackHeight((RBNode<T>) getRoot());
   }

   //the class colour was modified, so will not be compiled by the professor's test. be careful UFCG students.
   private int blackHeight(RBNode<T> node) {
      if (node.isEmpty())
         return 0;

      if (node.getColour() == Colour.BLACK)
         return Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight())) + 1;
      else
         return Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodes((RBNode<T>) getRoot());
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
      if (node.isLeaf() || node.isEmpty())
         return true;

      else if (!node.isBlack()) {
         if (((RBNode<T>) node.getLeft()).isBlack() && ((RBNode<T>) node.getRight()).isBlack())
            return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
                  && verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
         else
            return false;
      } else
         return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
               && verifyChildrenOfRedNodes((RBNode<T>) node.getRight());

   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      return verifyBlackHeight((RBNode<T>) getRoot());
   }

   private boolean verifyBlackHeight(RBNode<T> node) {
      if (node.isEmpty())
         return true;
      else if (blackHeight((RBNode<T>) node.getLeft()) != blackHeight((RBNode<T>) node.getRight()))
         return false;
      else
         return verifyBlackHeight((RBNode<T>) node.getLeft()) && verifyBlackHeight((RBNode<T>) node.getRight());
   }

   @Override
   public void insert(T element) {
      auxInsert(element, (RBNode<T>) getRoot(), new RBNode<T>());
   }

   private void auxInsert(T element, RBNode<T> node, RBNode<T> parent) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new RBNode<T>());
         node.setRight(new RBNode<T>());
         node.setParent(parent);
         node.setColour(Colour.RED);
         fixUpCase1((RBNode<T>) node);
      }

      else if (element.compareTo(node.getData()) > 0)
         auxInsert(element, (RBNode<T>) node.getRight(), node);

      else if (element.compareTo(node.getData()) < 0)
         auxInsert(element, (RBNode<T>) node.getLeft(), node);
   }

   @SuppressWarnings("unchecked")
   @Override
   public RBNode<T>[] rbPreOrder() {
      List<RBNode<T>> list = new ArrayList<>();
      rbPreOrderSupport(list, (RBNode<T>) getRoot());
      RBNode<T>[] arr = new RBNode[size()];
      return list.toArray(arr);
   }

   private void rbPreOrderSupport(List<RBNode<T>> list, RBNode<T> node) {
      if (!node.isEmpty()) {
         list.add(node);
         rbPreOrderSupport(list, (RBNode<T>) node.getLeft());
         rbPreOrderSupport(list, (RBNode<T>) node.getRight());
      }
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (getRoot().getData().equals(node.getData())) {
         node.setColour(Colour.BLACK);
      } else
         fixUpCase2(node);
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (((RBNode<T>) node.getParent()).getColour() == Colour.RED)
         fixUpCase3(node);
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> father = (RBNode<T>) node.getParent();
      RBNode<T> grandFather = (RBNode<T>) father.getParent();
      RBNode<T> uncle;
      if (isLeftChild(father))
         uncle = (RBNode<T>) grandFather.getRight();
      else
         uncle = (RBNode<T>) grandFather.getLeft();

      if (uncle.getColour() == Colour.RED) {
         father.setColour(Colour.BLACK);
         uncle.setColour(Colour.BLACK);
         grandFather.setColour(Colour.RED);
         fixUpCase1(grandFather);
      } else
         fixUpCase4(node);
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> father = (RBNode<T>) node.getParent();
      RBNode<T> next = node;
      if (isRightChild(node) && isLeftChild(father)) {
         Util.leftRotation(father);
         next = father;
      } else if (isLeftChild(node) && isRightChild(father)) {
         Util.rightRotation(father);
         next = father;
      }

      fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> father = (RBNode<T>) node.getParent();
      RBNode<T> grandFather = (RBNode<T>) father.getParent();
      father.setColour(Colour.BLACK);
      grandFather.setColour(Colour.RED);
      if (isLeftChild(node))
         Util.rightRotation(grandFather);
      else
         Util.leftRotation(grandFather);
   }
}
