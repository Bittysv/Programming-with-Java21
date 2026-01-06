package example.coding.practice;

import java.util.*;
import java.util.stream.Collectors;

public class BankFraudulantTransaction {
	record Transaction(String userId, int amount, int timestamp) {}
	
	public static void main(String[] args) {
		List<Transaction> transactions = List.of(
                new Transaction("alice", 5000, 10060),
                new Transaction("bob", 12000, 10150),
                new Transaction("dan", 5000, 10060),
                new Transaction("alice", 2000, 10200),
                new Transaction("alice", 6000, 10050)
        );

        List<String> badActors = getBadActors(transactions);
        System.out.println(badActors); // Expected output: [bob, alice]

	}
	
	  public static List<String> getBadActors(List<Transaction> transactions) {
	        Set<String> badActors = new HashSet<>();
	        
	        // Rule 1: Amount > 10,000
	        for (Transaction t : transactions) {
	            if (t.amount() > 10_000) {
	                badActors.add(t.userId());
	            }
	        }
	        //Rule 2: If the time difference between consecutive transaction < 100
	        Map<String, List<Transaction>> byUser = transactions.stream().collect(Collectors.groupingBy(Transaction::userId));
	        
	        for( Map.Entry<String, List<Transaction>> entry: byUser.entrySet()) {
	        	String user = entry.getKey();
	        	List<Transaction> trns = entry.getValue();
	        	
	        	trns.sort(Comparator.comparingInt(Transaction::timestamp));
	        	
	        	for(int i = 1; i<trns.size(); i++) {
	        		int diff = trns.get(i).timestamp() - trns.get(i-1).timestamp();
	        		if(diff < 100) {
	        			badActors.add(user);
	        			break;
	        		}
	        	}
	        }
	        return new ArrayList<>(badActors);
	  }

}
