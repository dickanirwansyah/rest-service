CREATE DATABASE rest;

USE rest;

CREATE TABLE kategori(

    idkategori VARCHAR(255) NOT NULL,
    nama VARCHAR(255) NOT NULL,

    CONSTRAINT pk_idkategori PRIMARY KEY(idkategori) 

);

CREATE TABLE transaksi(

    idtransaksi VARCHAR(255) NOT NULL,
    tanggal DATE,

    CONSTRAINT pk_idtransaksi PRIMARY KEY(idtransaksi)
);

INSERT INTO transaksi(idtransaksi, tanggal) VALUES('T001', '2017-08-07');
INSERT INTO transaksi(idtransaksi, tanggal) VALUES('T002', '2017-08-07');
INSERT INTO transaksi(idtransaksi, tanggal) VALUES('T003', '2017-08-07');
INSERT INTO transaksi(idtransaksi, tanggal) VALUES('T004', '2017-08-07');
INSERT INTO transaksi(idtransaksi, tanggal) VALUES('T005', '2017-08-07');

CREATE TABLE transaksi_detil(

    idtransaksi VARCHAR(255) NOT NULL,
    idbuku VARCHAR(255) NOT NULL,

    CONSTRAINT fk_idtransaksi FOREIGN KEY(idtransaksi) REFERENCES transaksi(idtransaksi),
    CONSTRAINT fk_idbuku FOREIGN KEY(idbuku) REFERENCES buku(idbuku)
);

INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T001', 'b001');
INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T001', 'b002');
INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T002', 'b003');
INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T003', 'b004');
INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T004', 'b001');
INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T001', 'b001');
INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T001', 'b002');
INSERT into transaksi_detil(idtransaksi, idbuku) VALUES('T002', 'b004');


CREATE TABLE buku(

    idbuku VARCHAR(255) NOT NULL,
    judul VARCHAR(255) NOT NULL,
    penerbit VARCHAR(255) NOT NULL,

    CONSTRAINT pk_idbuku PRIMARY KEY(idbuku)
);

INSERT buku(idbuku, judul, penerbit) VALUES('b001', 'Java Restfull', 'java indonesia');
INSERT buku(idbuku, judul, penerbit) VALUES('b002', 'Java Fundamental', 'java indonesia');
INSERT buku(idbuku, judul, penerbit) VALUES('b003', 'Java Framework', 'java indonesia');
INSERT buku(idbuku, judul, penerbit) VALUES('b004', 'Java JSP', 'java indonesia');
INSERT buku(idbuku, judul, penerbit) VALUES('b005', 'Java JSF', 'java indonesia');



INSERT kategori (idkategori, nama) values('121', 'kategori1');
INSERT kategori (idkategori, nama) values('122', 'kategori2');
INSERT kategori (idkategori, nama) values('123', 'kategori3');
INSERT kategori (idkategori, nama) values('124', 'kategori4');
INSERT kategori (idkategori, nama) values('125', 'kategori5');
INSERT kategori (idkategori, nama) values('126', 'kategori6');


CREATE TABLE product(

    idproduct VARCHAR(255) NOT NULL,
    nama VARCHAR(255) NOT NULL,
    jumlah INT NOT NULL,
    kadaluarsa DATE NOT NULL,
    idkategori VARCHAR(255) NOT NULL,

    CONSTRAINT pk_idproduct PRIMARY KEY(idproduct),
    CONSTRAINT fk_product_idkategori FOREIGN KEY(idkategori) REFERENCES kategori (idkategori)
);



INSERT product(idproduct, nama, jumlah, kadaluarsa, idkategori)
VALUES ('p001', 'product1', 2, '2019-11-03', '121');
INSERT product(idproduct, nama, jumlah, kadaluarsa, idkategori)
VALUES ('p002', 'product2', 3, '2020-11-03', '122');
INSERT product(idproduct, nama, jumlah, kadaluarsa, idkategori)
VALUES ('p003', 'product3', 4, '2021-11-03', '123');
INSERT product(idproduct, nama, jumlah, kadaluarsa, idkategori)
VALUES ('p004', 'product4', 5, '2022-11-03', '124');
INSERT product(idproduct, nama, jumlah, kadaluarsa, idkategori)
VALUES ('p005', 'product5', 5, '2022-11-03', '124');
INSERT product(idproduct, nama, jumlah, kadaluarsa, idkategori)
VALUES ('p006', 'product6', 5, '2022-11-03', '124');
INSERT product(idproduct, nama, jumlah, kadaluarsa, idkategori)
VALUES ('p007', 'product7', 5, '2022-11-03', '124');