CREATE TABLE IF NOT EXISTS wallets(
                                    id uuid primary key default pg_catalog.gen_random_uuid(),
                                    user_id uuid not null  references users(id) on delete restrict on update cascade ,
                                    balance numeric(12,1) check ( balance >= 0.0 ),
                                    currency varchar(10) default 'XAF',
                                    created_at timestamptz not null default current_timestamp,
                                    updated_at timestamptz
);

CREATE INDEX idx_wallets_user_id ON wallets(user_id);