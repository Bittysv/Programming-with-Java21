package example.coding.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeOverlappingIntervals {
	
	class Interval {
		int start;
		int end;
		
		public Interval() {}

		public Interval(int s, int e) {
			this.start = s;
			this.end = e;
		}

		public void setEnd(int end) {
			this.end = end;
		}
		
		public void printMethod(Interval i) {
			System.out.println("(" + i.start + ","+ i.end + ")");
		}
	}

	public List<Interval> doMerge(List<Interval> intervals) {
		intervals.sort(Comparator.comparingInt(interval -> interval.start));
		ArrayList<Interval> result = new ArrayList<>();
		result.add(intervals.get(0));

		intervals.forEach(interval -> {
			Interval lastMerged = result.get(result.size() - 1);
			if (interval.start <= lastMerged.end) {
				lastMerged.setEnd(Math.max(lastMerged.end, interval.end));
			} else {
				result.add(interval);
			}
		});

		return result;
	}

	public static void main(String[] args) {
		MergeOverlappingIntervals obj = new MergeOverlappingIntervals();
		List<Interval> intervals = new ArrayList<>();
		intervals.add(obj.new Interval(3, 5));
		intervals.add(obj.new Interval(13, 20));
		intervals.add(obj.new Interval(11, 15));
		intervals.add(obj.new Interval(15, 16));
		intervals.add(obj.new Interval(1, 3));
		intervals.add(obj.new Interval(4, 5));
		intervals.add(obj.new Interval(16, 17));
		intervals.add(obj.new Interval(21, 25));
		
		obj.doMerge(intervals).forEach(i -> obj.new Interval().printMethod(i));
	}

}
