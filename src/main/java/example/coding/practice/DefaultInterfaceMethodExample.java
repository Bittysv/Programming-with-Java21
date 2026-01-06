package example.coding.practice;

public class DefaultInterfaceMethodExample {
	interface Vehicle {
		// abstract method
		void start();

		// default method
		default void stop() {
			System.out.println("Vehicle stopped.");
		}

		default void honk() {
			System.out.println("Vehicle honking!");
		}
	}

	class Car implements Vehicle {

		@Override
		public void start() {
			System.out.println("Car started.");

		}
	}

	// Overriding default method
	class Bike implements Vehicle {

		@Override
		public void start() {
			System.out.println("Bike started.");

		}

		@Override
		public void stop() {
			System.out.println("Bike stopped.");
		}

	}

	// Multiple Inheritance
	interface Airplane{
		default void honk() {
			System.out.println("Airplane honking!");
		}
	}
	
	class FlyingCar implements Vehicle, Airplane{
		@Override
		public void start() {
			System.out.println("FlyingCar started.");

		}
		@Override
		public void honk() {
//			Airplane.super.honk(); //One solution to Conflict Resolution
			System.out.println("FlyingCar honking!"); //another solution
		}
		
	}

	public static void main(String[] args) {
		DefaultInterfaceMethodExample mainObj = new DefaultInterfaceMethodExample();
		Vehicle car = mainObj.new Car();
		car.start();
		car.stop();
		Vehicle bike = mainObj.new Bike();
		bike.start();
		bike.stop();
		FlyingCar flyingCar = mainObj.new FlyingCar();
		flyingCar.start();
		flyingCar.honk();

	}

}
