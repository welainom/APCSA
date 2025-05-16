/**
 *	SinglyLinkedList is a Linked List that supports operations
 * 	such as adding, removing, and searching.
 *
 *	@author	    William Liu
 *	@since	    May 6th, 2024
 */

import java.util.NoSuchElementException;

public class SinglyLinkedList<E extends Comparable<E>> {
    private ListNode<E> head, tail;		// head and tail
    private int size;					// number of elements 
    
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public SinglyLinkedList(SinglyLinkedList<E> oldList) {
        head = null;
        tail = null;
        size = 0;
        
        // Check if the old list is not null
        if (oldList != null) {
            ListNode<E> current = oldList.head;
            
            // Add each node
            while(current != null)
            {
                add(current.getValue());
                current = current.getNext();
            }
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * 	Add the object to the end of the list
    */
    public boolean add(E obj) {
        ListNode<E> node = new ListNode<E>(obj);
        
        // If the list is empty set head and tail to node
        if (head == null) {
            head = node;
            tail = node;
        }
        
        // Otherwise add node after the current tail
        else {
            tail.setNext(node);
            tail = node;
        }
        
        size++; 
        return true;
    }
    
    /**
     * 	Add the object at the specified index
    */
    public boolean add(int index, E obj) {
        if (index < 0 || index > size) throw new NoSuchElementException();

        ListNode<E> node = new ListNode<E>(obj);
        
        // If adding at the beginning of the list
        if (index == 0) {
            node.setNext(head);
            head = node;
            
            if (tail == null)
            {
                tail = node;
            }
        }
        
        else if (index == size) {
            tail.setNext(node);
            tail = node;
        }
        
        else {
            ListNode<E> prev = head;
        
            for (int i = 0; i < index - 1; i++) {
                prev = prev.getNext();
            }
            
            node.setNext(prev.getNext());
            prev.setNext(node);
        }

        size++;
        
        return true;
    }
    
    public int size() {
        return size;
    }
    
    /**
     * 	Return the ListNode at the specified index
    */
    public ListNode<E> get(int index) {
        if(index < 0 || index >= size) throw new NoSuchElementException();

        ListNode<E> node = head;
        
        for(int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }
    
    /**
     * 	Replace the object at the specified index
    */
    public E set(int index, E obj) {
        if(index < 0 || index >= size)
        {
            throw new NoSuchElementException();
        }
    
        ListNode<E> node = head;
         
        for(int i = 0; i < index; i++) {
            if (node == null) {
                throw new NoSuchElementException();
            }
            
            node = node.getNext();
        }
        
        // Store the old value before replacing
        E old = node.getValue();
        node.setValue(obj);
        
        return old;
    }

    /**
     * 	Remove the element at the specified index
    */
    public E remove(int index) {
        E val;
    
        if (isEmpty() || index < 0 || index >= size) throw new NoSuchElementException();
        

        // If removing the first element
        if (index == 0) {
            val = head.getValue();
            head = head.getNext();
            
            // Update tail if head is null
            if (head == null) {
                tail = null;
            }
        }
        else {
            ListNode<E> prev = head;
            
            // Traverse to the node before the specified index
            for(int i = 0; i < index - 1; i++) {
                prev = prev.getNext();
            }

            val = prev.getNext().getValue();
            prev.setNext(prev.getNext().getNext());
            
            // Update tail if removing the last element
            if (index == size - 1) {
                tail = prev;
            }
        }
        
        size--; 
        
        return val;
    }

    /**
     *	Replace the object at the specified index
    */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *	Return true if the list contains the specified object
    */
    public boolean contains(E value) {
        ListNode<E> curr = head;
        
        // Traverse the list to find the specified object
        while(curr != null) {
            if(curr.getValue().equals(value)) {
                return true;
            }

            curr = curr.getNext();
        }
        
        return false;
    }

    /**
     *	Return the index of the specified object
    */
    public int indexOf(E obj)
    {
        int index = 0;
        ListNode<E> curr = head;
        
        while(curr != null) {
            // Traverse the list to find the index of the specified object
            if(curr.getValue().equals(obj)) {
                return index;
            }
            
            curr = curr.getNext();
            index++;
        }
        
        return -1; // Not found in list
    }
    
    public void printList() {
        ListNode<E> ptr = head;
        while(ptr != null)
        {
            System.out.print(ptr.getValue() + "; ");
            ptr = ptr.getNext();
        }
    }
}