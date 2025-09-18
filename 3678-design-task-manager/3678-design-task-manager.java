class TaskManager {

    // Stores userId + priority for each taskId
    static class TaskInfo {
        int userId;
        int priority;
        TaskInfo(int u, int p) { this.userId = u; this.priority = p; }
    }

    // Heap entries only need taskId + priority
    static class HeapEntry {
        int taskId;
        int priority;
        HeapEntry(int t, int p) { this.taskId = t; this.priority = p; }
    }

    private Map<Integer, TaskInfo> gTaskMap; // taskId -> TaskInfo
    private PriorityQueue<HeapEntry> gMaxHeap;

    // Comparator for Task objects
    private static final Comparator<HeapEntry> taskComparator = (a, b) -> {
        if (b.priority != a.priority) return b.priority - a.priority; // higher priority first
        return b.taskId - a.taskId; // higher taskId if priorities are the same
    };

    public TaskManager(List<List<Integer>> tasks) {
        gTaskMap = new HashMap<>();
        gMaxHeap = new PriorityQueue<>(taskComparator);
        for (List<Integer> t : tasks) add(t.get(0), t.get(1), t.get(2));
    }

    public void add(int userId, int taskId, int priority) {
        gTaskMap.put(taskId, new TaskInfo(userId, priority));
        gMaxHeap.offer(new HeapEntry(taskId, priority));
    }

    public void edit(int taskId, int newPriority) {
        TaskInfo info = gTaskMap.get(taskId);
        info.priority = newPriority;
        gMaxHeap.offer(new HeapEntry(taskId, newPriority));
    }

    public void rmv(int taskId) {
        TaskInfo info = gTaskMap.get(taskId);
        info.priority = -1; // lazy deletion
    }

    public int execTop() {
        while (!gMaxHeap.isEmpty()) {
            HeapEntry top = gMaxHeap.poll();
            TaskInfo info = gTaskMap.get(top.taskId);
            if (info.priority == top.priority) {
                info.priority = -1;
                return info.userId;
            }
        }
        return -1;
    }
}