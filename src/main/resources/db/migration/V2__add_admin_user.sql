-- Password is 'admin123'
INSERT INTO users (
    username,
    email,
    password,
    first_name,
    last_name,
    phone_number,
    user_role,
    is_active,
    created_at,
    updated_at
) VALUES (
    'admin',
    'admin@example.com',
    '$2a$10$rS.PDWdPPWWI0.St0P8YXOTxe3RB8zB0Uf0UZOzwGnX1WKj8Z5mGi',
    'Admin',
    'User',
    '+905551234567',
    'ADMIN',
    1,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
