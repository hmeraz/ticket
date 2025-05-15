    CREATE KEYSPACE IF NOT EXISTS ticket_digital
    WITH REPLICATION = {
      'class' : 'SimpleStrategy',
      'replication_factor' : 1
    };

    USE ticket_digital;

    CREATE TABLE IF NOT EXISTS ticket (
        sk_id BIGINT PRIMARY KEY,
        dx_tcn TEXT,
        dx_digital INT,
        dx_email TEXT,
        dx_phone BIGINT,
        dx_ticket TEXT,
        dx_printed INT,
        dx_store_format TEXT,
        dx_date DATE,
        dx_time TIME,
        dx_tyc INT,
        dx_store_name TEXT,
        dx_tda INT,
        dx_op BIGINT,
        dx_te INT,
        dx_tr INT,
        dx_store_number INT,
        dx_uuid TEXT,
    );



    CREATE INDEX IF NOT EXISTS idx_dx_phone ON ticket (dx_phone);
    CREATE INDEX IF NOT EXISTS idx_dx_date ON ticket (dx_date);
    CREATE INDEX IF NOT EXISTS idx_dx_time ON ticket (dx_time);