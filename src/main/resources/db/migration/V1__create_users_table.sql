CREATE TABLE IF NOT EXISTS users(
    id uuid primary key default pg_catalog.gen_random_uuid(),
    name varchar(255) not null ,
    phone varchar(9) not null unique check ( length(phone) = 9 ),
    pin varchar(5) not null check ( length(pin) = 5 ) ,
    role varchar(10) not null check ( role in ('CUSTOMER', 'AGENT', 'MERCHANT', 'ADMIN') ) default 'CUSTOMER',
    active boolean not null default true,
    verified boolean not null default false,
    created_at timestamptz not null default current_timestamp,
    updated_at timestamptz
);

CREATE INDEX IF NOT EXISTS idx_users_phone ON users(phone);
CREATE INDEX IF NOT EXISTS idx_users_active ON users(active);
CREATE INDEX IF NOT EXISTS idx_users_verified ON users(verified);