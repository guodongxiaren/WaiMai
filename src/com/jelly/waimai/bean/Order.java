package com.jelly.waimai.bean;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String usr;
	private double cost=0;
	private List<Food> foods;
	
	private static Order order;
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public void addFood(Food food){
		if(foods==null)
			foods = new ArrayList<Food>();
		foods.add(food);
		cost+=food.getPrice();
	}
	public double getCost(){
		return cost;
	}
	public void rmFood(){
		
		
	}
	// constructor 
	private Order(){
		
	}
	public static Order getOrder(){
		if(order==null){
			synchronized(Order.class){
				if(order==null){
					order = new Order();
				}
			}
		}
		return order;
	}

}
