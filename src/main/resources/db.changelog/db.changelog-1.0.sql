--liqquibase formatted sql


--changeset team3:1
CREATE TABLE IF NOT EXISTS company_storage.company
(
    id   INT,
    PRIMARY KEY (id)
);