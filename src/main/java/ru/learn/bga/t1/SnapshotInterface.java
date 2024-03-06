package ru.learn.bga.t1;

import java.time.LocalDateTime;

public interface SnapshotInterface {
    void restoreState();
    LocalDateTime getcreatedDate();
}
