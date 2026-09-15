CREATE TABLE IF NOT EXISTS transactions(
    id uuid PRIMARY KEY DEFAULT pg_catalog.gen_random_uuid(),
    reference VARCHAR(255) NOT NULL UNIQUE ,
    initiator_phone VARCHAR(9) NOT NULL ,
    beneficiary_phone VARCHAR(9) NOT NULL ,
    amount NUMERIC(15, 2) NOT NULL CHECK ( amount >= 0.00 ),
    type VARCHAR(12) NOT NULL DEFAULT 'TRANSFER',
    status VARCHAR(12) NOT NULL DEFAULT 'INITIATED',
    idempotency_key uuid NOT NULL UNIQUE ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz
);


CREATE TABLE ledger_entries (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              transaction_ref VARCHAR(255) NOT NULL,
                              user_phone VARCHAR(9) NOT NULL,
                              amount NUMERIC(15, 2) NOT NULL,
                              type VARCHAR(50) NOT NULL,
                              created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_transactions_initiator_phone ON transactions(initiator_phone);
CREATE INDEX IF NOT EXISTS idx_transactions_receiver_phone ON transactions(beneficiary_phone);
CREATE INDEX IF NOT EXISTS idx_transactions_reference ON transactions(reference);
CREATE INDEX IF NOT EXISTS idx_transactions_created_at ON transactions(created_at);