package example.coding.practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BankFraudulantTransactionTest {

	@Test
	@DisplayName("User with transaction amount > 10000 should be flagged")
	void shouldFlagUserWithLargeTransaction() {
		List<BankFraudulantTransaction.Transaction> transactions = List.of(
				new BankFraudulantTransaction.Transaction("bob", 12000, 1000),
				new BankFraudulantTransaction.Transaction("alice", 5000, 2000));
		List<String> badActors = BankFraudulantTransaction.getBadActors(transactions);

		assertTrue(badActors.contains("bob"));
		assertFalse(badActors.contains("alice"));
	}

	@Test
	@DisplayName("User with fast consecutive transactions should be flagged")
	void shouldFlagUserWithFastConsecutiveTransactions() {
		List<BankFraudulantTransaction.Transaction> transactions = List.of(
				new BankFraudulantTransaction.Transaction("alice", 2000, 1000),
				new BankFraudulantTransaction.Transaction("alice", 5000, 1050));
		List<String> badActors = BankFraudulantTransaction.getBadActors(transactions);

		assertTrue(badActors.contains("alice"));
	}

	@Test
	@DisplayName("User with safe transactions should not be flagged")
	void shouldNotFlagSafeUser() {
		List<BankFraudulantTransaction.Transaction> transactions = List.of(
				new BankFraudulantTransaction.Transaction("bob", 4000, 1000),
				new BankFraudulantTransaction.Transaction("dan", 5000, 1200));
		List<String> badActors = BankFraudulantTransaction.getBadActors(transactions);

		assertTrue(badActors.isEmpty());
	}

	@Test
	@DisplayName("Multiple Users can be flagged for different rules")
	void shouldFlagMultipleBadActors() {
		List<BankFraudulantTransaction.Transaction> transactions = List.of(
				new BankFraudulantTransaction.Transaction("bob", 15000, 1000), // Rule 1
				new BankFraudulantTransaction.Transaction("alice", 3000, 2000),
				new BankFraudulantTransaction.Transaction("alice", 4000, 2050), // Rule 2
				new BankFraudulantTransaction.Transaction("dan", 5000, 3000));
		List<String> badActors = BankFraudulantTransaction.getBadActors(transactions);

		assertTrue(badActors.contains("bob"));
		assertTrue(badActors.contains("alice"));
		assertFalse(badActors.contains("dan"));
	}

	@Test
	@DisplayName("Order of Transactions should not matter")
	void shouldHandleUnsortedTransactions() {
		List<BankFraudulantTransaction.Transaction> transactions = List.of(
				new BankFraudulantTransaction.Transaction("alice", 4000, 1010),
				new BankFraudulantTransaction.Transaction("alice", 3000, 1000));
		List<String> badActors = BankFraudulantTransaction.getBadActors(transactions);
		
		assertTrue(badActors.contains("alice"));
		assertEquals(List.of("alice"), badActors);
	}
}
