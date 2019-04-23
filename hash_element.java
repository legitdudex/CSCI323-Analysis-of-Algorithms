/*
Author: Kang-hee Cho
*/


import java.util.*;
import java.io.*;

public class hash_element{
	private String value;
	public hash_element next;

	public hash_element(String key){
		this.value = key;
		this.next = null;
	}

	public String getValue(){
		return this.value;
	}
}
