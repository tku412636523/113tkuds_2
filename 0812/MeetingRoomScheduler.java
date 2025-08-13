import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && meeting[0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(meeting[1]);
        }

        return minHeap.size();
    }

    public static int maxMeetingTimeWithRooms(int[][] meetings, int rooms) {
        if (rooms <= 0 || meetings == null || meetings.length == 0) return 0;

        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();
        PriorityQueue<int[]> candidates = new PriorityQueue<>((a, b) -> Integer.compare(b[1] - b[0], a[1] - a[0]));

        int totalTime = 0;

        for (int[] meeting : meetings) {
            while (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= meeting[0]) {
                roomEndTimes.poll();
            }

            if (roomEndTimes.size() < rooms) {
                roomEndTimes.offer(meeting[1]);
                totalTime += (meeting[1] - meeting[0]);
            } else {
                candidates.offer(meeting);
            }
        }

        return totalTime;
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); 
        System.out.println(minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}})); 
        System.out.println(minMeetingRooms(new int[][]{{1,5},{8,9},{8,9}}));

        System.out.println(maxMeetingTimeWithRooms(new int[][]{{1,4},{2,3},{4,6}}, 1)); 
    }
}
