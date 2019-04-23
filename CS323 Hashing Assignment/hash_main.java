/*
Author:Kang-hee Cho
*/

import java.util.*;
import java.io.*;
import java.lang.*;

public class hash_main{
	


	public static void main(String args[]){
		ArrayList<String[]> wordElements = new ArrayList<String[]>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("words.txt")));
			String line = br.readLine();
			while(line != null){
				String[] splitLine = line.split("\\s+");
				wordElements.add(splitLine);
				line = br.readLine();
			}
		} catch(Exception e){ e.printStackTrace(); }
		for(int i = 0; i < wordElements.size(); i++){
			System.out.println(wordElements.get(i)[0]);
		}	
		//we first test a hash table where the load factor is 0.8
		ArrayList<String> keys = new ArrayList<String>();
		for(int i = 0; i < wordElements.size(); i++){
			keys.add(wordElements.get(i)[0]);
		}
		double mu = 0.8;
		int approxLength = (int)Math.floor(mu * keys.size());
		hash_table hasht = new hash_table(approxLength, mu);
		for(int i = 0; i < keys.size(); i++){
			hasht.insert(keys.get(i));
		}
		
		//then, test a hash table where the load factor is 0.5
		mu = 0.5;
                approxLength = (int)Math.floor(mu * keys.size());
                hash_table hasht1 = new hash_table(approxLength, mu);
                for(int i = 0; i < keys.size(); i++){
                        hasht1.insert(keys.get(i));
                }

		//now print out the hash tables
		for(int i = 0; i < hasht.ht.size(); i++){
			hash_element h = hasht.ht.get(i);
			if(h == null){
				System.out.println(Integer.toString(i));
			}
			else{
				System.out.println(Integer.toString(i) + "  " + h.getValue());
				h = h.next;
				if(h != null){
				while(h != null){
					System.out.print("-> " + h.getValue());
					if(h.next == null){ System.out.println("\n"); }
					h = h.next;
				}}
			}
		}
		System.out.println("Hash table size when mu = 0.8: " + Integer.toString(hasht.getSize()));
		System.out.println("Collisions for when mu = 0.8: " + Integer.toString(hasht.getCollisions()) + "\n");
		

		for(int i = 0; i < hasht1.ht.size(); i++){
                         hash_element h = hasht1.ht.get(i);
                         if(h == null){
                                 System.out.println(Integer.toString(i));
                         }
                         else{
                                 System.out.println(Integer.toString(i) + "  " + h.getValue());
                                 h = h.next;
				 if(h != null){
                                 while(h != null){
                                         System.out.print("-> " + h.getValue());
                                         if(h.next == null){ System.out.println("\n"); }
					 h = h.next;
                                 }}
                         }
                 }
                 System.out.println("Hash table size when mu = 0.8: " + Integer.toString(hasht1.getSize()));
                 System.out.println("Collisions for when mu = 0.8: " + Integer.toString(hasht1.getCollisions()) + "\n");
		
		int iterations = 100;
		int avg1 = 0;
		int avg2 = 0;
		while(iterations > 0){
		int collisions1 = 0;
		int collisions2 = 0;
		for(int i = 0; i < 50; i++){
			Random r = new Random();
			int r1 = r.nextInt(keys.size());
			int r2 = r.nextInt(keys.size());
			String result1 = hasht.search(keys.get(r1));
			String result2 = hasht1.search(keys.get(r2));
			if(result1.equals("Collision!")){
				collisions1++;
			}
			if(result2.equals("Collision!")){
				collisions2++;
			}
		}
		avg1 += collisions1;
		avg2 += collisions2;
		iterations--;
		}

		avg1 = (int)Math.floor(avg1 / 100);
		avg2 = (int)Math.floor(avg2 / 100);
	
		System.out.println("Average collisions per 50 lookups after 100 iterations of the table where mu = 0.8: " + Integer.toString(avg1));
		System.out.println("Average collisions per 50 lookups after 100 iterations of the table where mu = 0.5: " + Integer.toString(avg2));
	}
}
