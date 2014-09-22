package immutableQueue2;

import java.util.NoSuchElementException;

public class ImmutableQueue<E>{//ImmutableQueue class
	
	private static class ImmutableStack<E>{	//ImmutableStack Class
		
		private E head;   					// head is an object of generic type E
		private ImmutableStack<E> tail; 	// tail is an ImmutableStack object
		private int size;					// size of the stack
		
		/**
		 * Default constructor
		 * head to null
		 * tail to null
		 * size to 0
		 * */
		
		private ImmutableStack(){
			this.head = null;
			this.tail = null;
			this.size = 0;
		}
		
		
		/**
		 * Constructor Overloading
		 * E Object
		 * ImmutableStack object tail
		 * head is object
		 * tail is tail (ImmutableStack object)
		 * size = size of the tail +1
		 * */
		
		private ImmutableStack(E obj,ImmutableStack<E> tail){
			this.head = obj;
			this.tail = tail;
			this.size = tail.size+1;
		}
		
		/**
		 * Emptying the stack
		 * returning the ImmutableStack
		 * */
		
		public static ImmutableStack emptyStack(){
			return new ImmutableStack();
		}
		
		/**
		 * Checking if stack is empty
		 * with their size
		 * 
		 * @return true of false if Stack is empty or not
		 * */
		
		public boolean isEmpty(){
			return this.size ==0;
		}
		
		/**
		 * Push into the stack 
		 * 
		 * @param E object
		 * @return ImmutableStack with object
		 * */
		
		public ImmutableStack<E> push(E obj){
			return new ImmutableStack(obj,this);
		}
		/**
		 * Take stack object and push the head of the tail stack
		 * to the stack
		 * do this until the stack is empty
		 * 
		 * @return reverse stack
		 * */

		public ImmutableStack<E> toReverseStack(){
			ImmutableStack<E> stack = new ImmutableStack<E>();
			ImmutableStack<E> tail = this;
			while(!tail.isEmpty()){
				stack = stack.push(tail.head);
				tail = tail.tail;
			}
			return stack;
		}
		
	}
	
	/**
	 * Two stack for enqueue and dequeue the element from the queue
	 * order for enqueue  
	 * reverse for dequeue
	 * 
	 * */
	
	private ImmutableStack<E> order;
	private ImmutableStack<E> reverse;
	
	/**
	 * Default constructor ImmutableQueue 
	 * two empty stacks
	 *	
	 * */
	
	public ImmutableQueue(){
		this.order = ImmutableStack.emptyStack();
		this.reverse = ImmutableStack.emptyStack();
	}
	
	/**
	 * Constructor overloading 
	 * Using two immutable stack order and reverse
	 * 
	 * 
	 * */
	
	public ImmutableQueue(ImmutableStack<E> order,ImmutableStack<E> reverse){
		this.order = order;
		this.reverse = reverse;
	}
	
	/**
	 * Balancing the Queue
	 * reverse the order stack and assign it to reverse stack
	 * and make order stack empty 
	 * 
	 * */
	
	private void balanceQueue(){
		this.reverse= this.order.toReverseStack();
		this.order = ImmutableStack.emptyStack();
	}
	
	/**
	 * Enqueue Object
	 * if object is null throw IllegalArgumentException
	 * 
	 * @return ImmutableQueue with object 
	 * */
	
	
	public ImmutableQueue<E> enqueue(E object){
		if(object==null)
			throw new IllegalArgumentException();
		return new ImmutableQueue<E>(this.order.push(object),this.reverse);
	}

	/**
	 * Dequeue from the queue
	 * if Queue is empty then throw NoSuchElementException
	 * 
	 * if Reverse Stack is not empty then return Immutable queue with 
	 * reverse stack's tail object
	 * 
	 * else reverse the Order ImmutableStack and take the tail of this
	 * and clean the order ImmutableStack 
	 * 
	 * @return ImmutableQueue 
	 * */
	
	public ImmutableQueue<E> dequeue(){
		if(this.isEmpty())
			throw new NoSuchElementException();
		if(!this.reverse.isEmpty()){
			return new ImmutableQueue<E>(this.order,this.reverse.tail);
		}else{
			return new ImmutableQueue<E>(ImmutableStack.emptyStack(),this.order.toReverseStack().tail);
		}		
	}
	
	
	/**
	 * Getting the peek of the queue
	 * if Object is empty throw and Exception NoSuchElementException.
	 * if reverse stack is Empty balanceQueue.
	 * 
	 * @return head of the reverse stack
	 * */
	
	
	public E peek(){
		if(this.isEmpty())
			throw new NoSuchElementException();
		if(this.reverse.isEmpty())
			balanceQueue();
		return this.reverse.head;
	}
	
	public boolean isEmpty(){
		return size() ==0;
	}
	
	public int size(){
		return this.order.size + this.reverse.size;
	}
	
	public double percentage(double x){
		double value = 0;
		if(!this.isEmpty()){
			value = size()*x/100;
		}
		return value;
	}
	
	public static void reverse(double x){
		
	}
	/**
	 * Print method prints the elements of the Queue
	 * 
	 * */
	
	public void print(){
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		else{
				balanceQueue();
				System.out.println(this.reverse.head);
				reverse = reverse.tail;
		}
	}
	
	 
	
}
