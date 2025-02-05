CREATE INDEX idx_logs_created_at
    ON logs (created_at)
    TABLESPACE fast_space;
