ALTER TABLE tb_pessoa RENAME COLUMN id TO pessoa_id;

ALTER TABLE tb_endereco RENAME COLUMN id TO endereco_id;

ALTER TABLE tb_eletrodomestico RENAME COLUMN id TO eletrodomestico_id;

CREATE TABLE tb_casa (
    casa_id                BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY
);

