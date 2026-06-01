CREATE TABLE IF NOT EXISTS libreria (
    Libcodigo INT AUTO_INCREMENT PRIMARY KEY,
    Libtitulo VARCHAR(200) NOT NULL,
    Libautor VARCHAR(150) NOT NULL,
    Libcategoria VARCHAR(100),
    Libeditorial VARCHAR(100),
    Libanio YEAR,
    Libexistencias INT DEFAULT 0,

    LibIns BIT(1) DEFAULT b'0',
    LibSel BIT(1) DEFAULT b'0',
    LibUpd BIT(1) DEFAULT b'0',
    LibDel BIT(1) DEFAULT b'0',
    LibRep BIT(1) DEFAULT b'0'
);