/*
Author: Kang-hee Cho
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class hash_table{
	public ArrayList<hash_element> ht;
	private int collisions;
	private double mu;
	
	public hash_table(int size, double mu){
		this.ht = new ArrayList<hash_element>(size);
		for(int i = 0; i < size; i++){
			ht.add(null);
		}
		this.collisions = 0;
		this.mu = mu;
	}	

	public int getCollisions(){
		return this.collisions;
	}
	
	public int getSize(){
		return this.ht.size();
	}

	public void increaseCollisions(){
		this.collisions++;
	}

	public int hash_function(String key){
		int asciiValue = 1;
		for(int i = 0; i < key.length(); i++){
			asciiValue = asciiValue * (int)key.charAt(i);
		}
		//double paren = asciiValue * (mu % 1);
		//paren = paren * this.ht.size();
		//return (int) Math.floor(paren);
		return asciiValue % this.ht.size();
	}
	
	public String search(String key){
		int hash_value = hash_function(key);
		if(hash_value < 0){ hash_value = hash_value * -1; }
		hash_element ourElement = this.ht.get(hash_value);
		if(ourElement != null){
			if(ourElement.getValue().equals(key)){
				return ourElement.getValue();
			}
			else{
				while(!ourElement.getValue().equals(key)){
					ourElement = ourElement.next;
				}
				return "Collision!";
			}
		}
		return null;
	}


	/*
	This is the insert method that uses the multiplication method defined in the texbook at page 264 to
		calculate the hash value of the given hash element, given the key of the hash element.
	I had to use the method entailed in page 263 of the textbook in order to convert the ascii character key given
		in the excel spreadsheet into an integer value we could use in order to create our hash value
	*/
	public void insert(String key){
		int hash_value = hash_function(key);
		if(hash_value < 1){ hash_value = hash_value * -1; }
		if(this.ht.get(hash_value) == null){
			this.ht.set(hash_value, new hash_element(key));
		}
		else{
			hash_element pointer = this.ht.get(hash_value);
			while(pointer.next != null){
				pointer = pointer.next;
			}
			pointer.next = new hash_element(key);
			this.collisions++;
			
		}
	}

	public void remove(String key){
		int hash_value = hash_function(key);
		hash_element h = ht.get(hash_value);
		if(h != null && h.next != null){
			while(!h.next.getValue().equals(key)){
				h = h.next;
			}
			h.next = h.next.next;
		}
		else if(h != null && h.next == null){
			ht.set(hash_value, null);
		}
	}
}
