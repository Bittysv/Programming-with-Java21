package example.coding.practice;

public class Singleton {
	
	
	private static volatile Singleton instance;
	
	public String str;
	private Singleton(String value) {
		this.str = value;
	}
	
	public static Singleton getInstance(String value) {
		Singleton result = instance;
		if(result != null) {
			return result;
		}
		
		synchronized (Singleton.class) {
			if(instance == null)
				instance = new Singleton(value);
		}
		
		return instance;
	}
}

class DemoMultiThread{
	public static void main(String[] args) {
		System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
		Thread threadFoo = new Thread(new ThreadFoo());
		Thread threadBar = new Thread(new ThreadBar());
		
		threadFoo.start();
		threadBar.start();	
	}
	
    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.str);
        }
    }
    
    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.str);
        }
    }
}
