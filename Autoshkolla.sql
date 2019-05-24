--TABLES, COMPLEX DATA TYPES, NESTED TABLES, CHECK CONSTRAINTS
CREATE TABLE Users
(
id int PRIMARY KEY,
username varchar2(50) NOT NULL,
password varchar2(70),
status varchar2(20)
);

INSERT INTO Users
VALUES(user_seq.NEXTVAL,'argjenda dervisholli','123456','admin');
insert into Users
VALUES(user_seq.NEXTVAL,'mirjeta bytyqi','123456','admin');
insert  into Users
values(user_seq.NEXTVAL,'mihrije gashi','123456','instruktore');
insert into Users
values(user_seq.NEXTVAL,'Fatlind Fejzullahu','123456','instruktor');

select * from users


---------------------------------------------------------------------
CREATE TYPE LlojiVetures AS OBJECT
(
emri varchar2(15),
lloji varchar2(15),
ngjyra varchar2(15)
);

CREATE TYPE Lista_vetura AS TABLE OF LlojiVetures;

CREATE TABLE Kategorite(
kategoriaId int PRIMARY KEY,
emriKategorise varchar2(4) NOT NULL,
Cmimi number(7,2),
veturat Lista_vetura,
pershkrimiKategorise varchar2(100))
NESTED TABLE veturat STORE AS Lista_veturave; 


INSERT INTO Kategorite
VALUES(kategoria_seq.NEXTVAL,'D',160.00,Lista_vetura(LlojiVetures('Volvo Ailsa B55','Volvo','E zeze'),
                                 LlojiVetures('Volvo B6','Volvo','E bardhe')),
       'Per autobuse');

INSERT INTO Kategorite
VALUES(kategoria_seq.NEXTVAL,'C',200.00,Lista_vetura(LlojiVetures('Audi A3','Scania','E zeze'),
                                 LlojiVetures('Volvo C6','Volvo','E bardhe')),
       'Per kamione');
       
INSERT INTO Kategorite
VALUES(kategoria_seq.NEXTVAL,'B',160.00,Lista_vetura(LlojiVetures('Golf 6','Golf','E hirit'),
                                 LlojiVetures('Golf 5','Golf','E zeze')),'Per vetura');

ALTER TABLE Kategorite ADD CONSTRAINT check_cmimi
check (cmimi>0);

select * from Kategorite
-------------------------------------------------------------------------------------------------------

CREATE TYPE adresa AS OBJECT
(
rruga varchar2(30),
qyteti varchar2(30),
zipkodi varchar2(9)
);

CREATE TYPE emri AS OBJECT
(
emri varchar2(30),
emriPrindit varchar2(30),
mbiemri varchar2(30)
);

CREATE TABLE Ligjeruesit(
lId int,
emri emri NOT NULL,   
telefoni varchar(15),
email varchar(80),
nrPersonal varchar2(20) NOT NULL,
adresa adresa,
ditelindja date,
PRIMARY KEY (lId));

INSERT INTO Ligjeruesit
VALUES(ligjeruesit_seq.NEXTVAL,emri('Njomza','Gezimm','Mehmeti'),'049153456','njomzamehmeti@outlook.com',
       '123456789',adresa('Nena Tereze','Prishtine',10000),'15-jan-1996');
       
INSERT INTO Ligjeruesit
VALUES(ligjeruesit_seq.NEXTVAL,emri('Luan','Bujar','Ismaili'),'049123222','luanismaili@live.com',
       '23456781',adresa('Sylejman Vokshi','Prishtine',10000),'02-feb-1980');

------------------------------------------------------------------------------------------

CREATE TABLE Instruktoret(
iId int ,
emri emri NOT NULL,   
telefoni varchar(15),
email varchar(80),
nrPersonal varchar2(20) UNIQUE NOT NULL,
adresa adresa,
ditelindja date,
PRIMARY KEY (iId));


INSERT INTO Instruktoret
VALUES(instruktoret_seq.NEXTVAL,emri('Milot','Ardian','Shaqiri'),'049123446','milotshaqiri@hotmail.com',
       '12345689',adresa('2 Korriku','Prishtine',10000),'01-Jan-1972');
       
INSERT INTO Instruktoret
VALUES(instruktoret_seq.NEXTVAL,emri('Urate','Agim','Bekaj'),'049123222','uratebekaj@outlook.com',
       '258147369',adresa('Agim Ramadani','Prishtine',10000),'03-Feb-1986');
       
INSERT INTO Instruktoret
VALUES(instruktoret_seq.NEXTVAL,emri('Melisa','Agim','Bekaj'),'049123255','melisabekaj@outlook.com',
       '456789123',adresa('Agim Ramadani','Prishtine',10000),'29-march-1990');
       
select * from instruktoret 

----------------------------------------------------------------------------------------------

CREATE TABLE Mesimet(
instruktoriId int NOT NULL,
kategoriaId int NOT NULL,
FOREIGN KEY (instruktoriId) REFERENCES Instruktoret(iId) ON DELETE CASCADE,
FOREIGN KEY (kategoriaId) REFERENCES Kategorite(kategoriaId) ON DELETE CASCADE,
PRIMARY KEY(instruktoriId,kategoriaId)
);

INSERT INTO Mesimet
VALUES(1,1);
INSERT INTO Mesimet
VALUES(1,2);
INSERT INTO Mesimet
VALUES(1,3);
INSERT INTO Mesimet
VALUES(2,1);
INSERT INTO Mesimet
VALUES(3,1);
INSERT INTO Mesimet
VALUES(3,2);
INSERT INTO Mesimet
VALUES(3,3);

select * from Mesimet
---------------------------------------------------------------------------------------

CREATE TABLE Ligjeratat(
ligjeruesiId int NOT NULL,
kategoriaId int NOT NULL,
FOREIGN KEY (ligjeruesiId) REFERENCES Ligjeruesit(lId) ON DELETE CASCADE,
FOREIGN KEY (kategoriaId) REFERENCES Kategorite(kategoriaId) ON DELETE CASCADE,
PRIMARY KEY(ligjeruesiId,kategoriaId)
);

INSERT INTO Ligjeratat
VALUES(1,1);
INSERT INTO Ligjeratat
VALUES(1,2);
INSERT INTO Ligjeratat
VALUES(1,3);
INSERT INTO Ligjeratat
VALUES(2,1);
INSERT INTO Ligjeratat
VALUES(2,2);
INSERT INTO Ligjeratat
VALUES(2,3);


-------------------------------------------------------------------------------------------

CREATE TYPE LlojiTestimit AS OBJECT
(
data date,
lloji varchar2(15),
piket varchar2(15)
);

CREATE TYPE Testime AS TABLE OF LlojiTestimit;


CREATE TABLE Kandidatet(
kId int,
emri varchar2(30) NOT NULL,
emriPrindit varchar2(30) NOT NULL,
mbiemri varchar2(30) NOT NULL,
nrPersonal varchar2(20) NOT NULL,
telefoni varchar(15),
email varchar(80),
adresa adresa,
mosha int,
ditelindja date,
dataRegjistrimit date NOT NULL,
testimet Testime,
Aktiviteti varchar2(10),
PRIMARY KEY (kId))
NESTED TABLE testimet STORE AS Testimet;

       
INSERT INTO Kandidatet
VALUES(kandidatet_seq.NEXTVAL,'Mirjeta','Shyqiri','Bytyqi','1170955761','049362999','mirjetabytyqi@gmail.com',
       adresa('Sylejman Vokshi','Prishtine','10000'),21,'28-Sep-1997',
       '01-Jan-2018',Testime(LlojiTestimit('10-Jan-2018','Provues','95'),
                              LlojiTestimit('20-Jan-2018','Real','100')),'Aktiv');
                              
INSERT INTO Kandidatet
VALUES(kandidatet_seq.NEXTVAL,'Donjeta','Gani','Brahimi','1170955770','049258666','donjetabrahimi@gmail.com',
       adresa('Tringe Smajli','Prishtine','10000'),22,'23-Oct-1996',
       '01-Jan-2018',Testime(LlojiTestimit('10-Jan-2018','Provues','100'),
                              LlojiTestimit('20-Jan-2018','Real','100')),'Aktiv');
                              
INSERT INTO Kandidatet
VALUES(kandidatet_seq.NEXTVAL,'Gresa','Fadil','Ibrahimi','1340945770','049345566','gresaibrahimi@gmail.com',
       adresa('Abedin Bujupi','Prishtine','10000'),21,'21-Oct-1994',
       '01-Feb-2018',Testime(LlojiTestimit('10-Feb-2018','Provues','90'),
                              LlojiTestimit('20-Feb-2018','Real','100')),'JoAktiv');
                              
INSERT INTO Kandidatet
VALUES(kandidatet_seq.NEXTVAL,'Ubejd','Nazmi','Jolla','1770654781','049112554','ubejdjolla@gmail.com',
       adresa('Rruga B','Prishtine','10000'),19,'28-Sep-1999',
       '15-Jun-2018',Testime(LlojiTestimit('20-Oct-2018','Provues','100'),
                              LlojiTestimit('10-Dec-2018','Real','100')),'JoAktiv');
   

ALTER TABLE Kandidatet ADD CONSTRAINT check_mosha
check (mosha>18);

select * from kandidatet

-----------------------------------------------------------------------------------------------

CREATE TABLE Oret(
kandidatiId int NOT NULL,
kategoriaId int NOT NULL,
nrOreve int,
instruktoriId int,
ligjeruesiId int,
dataFillimit date,
dataMbarimit date,
FOREIGN KEY (instruktoriId,kategoriaId) REFERENCES Mesimet(instruktoriId,kategoriaId),
FOREIGN KEY (ligjeruesiId,kategoriaId) REFERENCES Ligjeratat(ligjeruesiId,kategoriaId),
PRIMARY KEY (kandidatiId,kategoriaId,dataFillimit)
);

INSERT INTO Oret
VALUES(1,1,16,1,1,'01-Jan-2018','14-Jan-2018');
INSERT INTO Oret
VALUES(2,1,16,1,1,'01-Jan-2018','14-Jan-2018');
INSERT INTO Oret
VALUES(3,3,16,3,2,'01-Feb-2018','15-Feb-2018');
INSERT INTO Oret
VALUES(4,1,16,2,2,'20-Jun-2018','10-Jul-2018');


SELECT k.emri ||' '|| k.mbiemri "Emri dhe Mbiemri", c.emriKategorise "Kategoria", o.nrOreve "oret", i.emri.emri "Emri Instruktorit/es", l.emri.emri "Emri Ligjeruesit/es", o.dataFillimit, o.dataMbarimit
FROM Kandidatet k, Oret o, Instruktoret i, Kategorite c, Ligjeruesit l
where i.iId=o.instruktoriId and k.kId=o.kandidatiId and c.kategoriaId=o.kategoriaId and o.ligjeruesiId=l.lId;


//----------------------------------------------------------------------------------------------


CREATE TABLE LlojiPageses(
LlojiPagesesId number(10) PRIMARY KEY,
emri varchar(80) NOT NULL);


INSERT INTO LlojiPageses
VALUES (2,'Bank');
INSERT INTO LlojiPageses
VALUES (1,'Kesh');
----------------------------------------------------------------------------------

CREATE TABLE Faturat(
NumriFatures int, 
kandidatiId int NOT NULL,      
kategoriaId int NOT NULL,
DataFillimit date NOT NULL,
DataLeshimit date NOT NULL,
ShumaTotale number(7,2) NOT NULL,
LlojiPagesesId number(10) NOT NULL,
ePerfunduar varchar2(2) NOT NULL,
PRIMARY KEY (NumriFatures),
FOREIGN KEY (kandidatiId,kategoriaId,dataFillimit) REFERENCES Oret(kandidatiId,kategoriaId,dataFillimit),
FOREIGN KEY (LlojiPagesesId) REFERENCES LlojiPageses(LlojiPagesesId));


INSERT INTO Faturat
VALUES(faturat_seq.NEXTVAL,1,1,'01-Jan-2018','14-Jan-2018','200.00',2,'Po');

INSERT INTO Faturat
VALUES(faturat_seq.NEXTVAL,2,1,'01-Jan-2018','14-Jan-2018','210.00',2,'Po');


INSERT INTO Faturat
VALUES(faturat_seq.NEXTVAL,3,3,'01-Feb-2018','15-Feb-2018','210.00',2,'Po');

INSERT INTO Faturat
VALUES(faturat_seq.NEXTVAL,4,1,'20-Jun-2018','10-Jul-2018','210.00',2,'Po');

select * from Faturat
delete from Fatura 
where NumriFatures=25;
-----------------------------------------------------------------------------------------


--SEQUENCES & TRIGGERS

CREATE SEQUENCE user_seq;

CREATE or REPLACE TRIGGER trg_userSeq
before insert on Users
for each row
begin 
select user_seq.NEXTVAL
into :new.id
from dual;
end;


CREATE SEQUENCE kandidatet_seq;


CREATE or REPLACE TRIGGER trg_kandidatetSeq
before insert on Kandidatet
for each row
begin 
select kandidatet_seq.NEXTVAL
into :new.kid
from dual;
end;


CREATE SEQUENCE instruktoret_seq;


CREATE or REPLACE TRIGGER trg_instruktoretSeq
before insert on Instruktoret
for each row
begin 
select instruktoret_seq.NEXTVAL
into :new.iId
from dual;
end;



CREATE SEQUENCE ligjeruesit_seq;


CREATE or REPLACE TRIGGER trg_ligjeruesitSeq
before insert on Ligjeruesit
for each row
begin 
select ligjeruesit_seq.NEXTVAL
into :new.lid
from dual;
end;


CREATE SEQUENCE kategoria_seq;


CREATE or REPLACE TRIGGER trg_kategoriaSeq
before insert on Kategorite
for each row
begin 
select kategoria_seq.NEXTVAL
into :new.kategoriaId
from dual;
end;



CREATE SEQUENCE llojiPageses_seq;

CREATE or REPLACE TRIGGER trg_llojiPagesesSeq
before insert on LlojiPageses
for each row
begin 
select llojiPageses_seq.NEXTVAL
into :new.llojiPagesesId
from dual;
end;



CREATE SEQUENCE faturat_seq;


CREATE or REPLACE TRIGGER trg_faturatSeq
before insert on Faturat
for each row
begin 
select faturat_seq.NEXTVAL
into :new.numriFatures
from dual;
end;
select * from users


--INSERT, UPDATE, DELETE, SELECT
 --    
INSERT INTO TABLE (SELECT veturat
                   FROM Kategorite k
		           WHERE k.emriKategorise='D')
VALUES('Volvo 5','Volvo','E hirit');

--
SELECT k.veturat
FROM Kategorite k
WHERE k.emriKategorise='D';

--
SELECT emriKategorise, pershkrimiKategorise, cardinality(veturat) as "Nurmi i veturave"
FROM Kategorite;

--
SELECT v.emri, v.ngjyra
FROM THE (SELECT Veturat
          FROM Kategorite k
          WHERE k.emriKategorise='B') v
WHERE v.lloji='Golf';

--
SELECT v.emri, v.ngjyra, k.emriKategorise "KATEGORIA"
FROM Kategorite k, THE (SELECT Veturat
                        FROM Kategorite k
                        WHERE k.emriKategorise='B') v
WHERE v.lloji='Golf' and k.emriKategorise='B';



--
SELECT k.emriKategorise "Kategoria", k.pershkrimiKategorise "Pershkrimi",
       v.emri "Vetura", v.ngjyra "Ngjyra"
FROM Kategorite k, TABLE (k.veturat) v
ORDER BY k.emriKategorise;

--
UPDATE TABLE (SELECT veturat
	          FROM Kategorite k
              WHERE k.emriKategorise='D') V
SET VALUE(v)=LlojiVetures('Volvo Ailsa B10','Volvo','E zeze')
WHERE VALUE(v)=LlojiVetures('Volvo Ailsa B55','Volvo','E zeze');

--
DELETE FROM TABLE (SELECT veturat
	               FROM Kategorite k
                   WHERE k.emriKategorise='D') V
WHERE VALUE(v)=LlojiVetures('Volvo B6','Volvo','E bardhe');

--
UPDATE Ligjeruesit l
SET l.adresa.rruga='Nene Tereza'
WHERE l.adresa.rruga='Nena Tereze';

--
SELECT DISTINCT i.emri.emri "Emri", i.emri.mbiemri "Mbiemri" 
FROM Instruktoret i;

--
SELECT k.emri "Emri", k.mbiemri "Mbiemri", k.adresa.qyteti "Qyteti", f.shumaTotale "Totali" 
FROM Kandidatet k, Faturat f
WHERE k.kId=f.kandidatiId;




--GROUP BY & HAVING

--1. Paraqitni instruktoret dhe numrin e kategorive qe mbajne ata. Ne rezultat te
--   paraqitet emri i instruktorit dhe numri i kategorive.

SELECT i.emri.emri  ||' '|| i.emri.mbiemri "Emri dhe Mbiemri", count(*) as "Numri i kategorive"
FROM Instruktoret i INNER JOIN Mesimet m ON i.iid=m.instruktoriId
GROUP BY i.emri.emri, i.emri.mbiemri
having count(*)>0;


--2. Paraqitni kandidatet te cileve iu kane leshuar dy e me shume fatura. Ne rezultat te
--   paraqitet id e kandidatit, emri dhe mbiemri, numri total i faturave, vlera minimale e 
--   faturave, vlera maksimale e faturave, vlera mesatare e faturave, dhe vlera totale e
--   faturave.

SELECT k.kid as "ID e kandidatit",
k.emri ||' '||k.mbiemri as "Emri dhe mbiemri",
count(*) as "Numri i faturave",
MIN(f.shumaTotale) as "Vlera minimale",
MAX(f.shumaTotale) as "Vlera maksimale",
AVG(f.shumaTotale) as "Vlera mesatare",
SUM(f.shumaTotale) as "Totali"
FROM Kandidatet k
inner join Faturat f on k.kid=f.kandidatiId
GROUP BY k.kId, k.emri, k.mbiemri
having count(*)>0


--UNIQUE CONSTRAINT
ALTER TABLE Users
ADD UNIQUE (username);


-- SQL OPTIMIZATION

--INDEXES



--BITMAP INDEXES

CREATE INDEX kandidati_epm_index
ON Kandidatet (emri, emriPrindit, mbiemri);

CREATE INDEX kandidati_em_index
ON Kandidatet (emri, mbiemri);

--UNIQUE INDEXES

CREATE UNIQUE INDEX kandidati_npdr_unique_index
ON Kandidatet (nrPersonal, dataRegjistrimit);

--BITMAP INDEXES

CREATE BITMAP INDEX fatura_ePerfunduar_index
on Faturat(ePerfunduar);

CREATE BITMAP INDEX mesimet_kid_index
on Mesimet(kategoriaId);

--FUNCTION-BASED INDEXES

CREATE INDEX fatura_shumaPaTVSH_index ON Faturat
(shumaTotale/1.16);

--Cluster

CREATE CLUSTER inst_mes(iId int);

CREATE INDEX cluster_index ON CLUSTER inst_mes;

CREATE TABLE cInstruktoret(
iId int,
emri emri NOT NULL,   
telefoni varchar(15),
email varchar(80),
nrPersonal varchar2(20) UNIQUE NOT NULL,
adresa adresa,
ditelindja date,
PRIMARY KEY (iId)) 
CLUSTER inst_mes(iId);



CREATE TABLE cMesimet(
iId int NOT NULL,
kategoriaId int NOT NULL,
FOREIGN KEY (iId) REFERENCES cInstruktoret(iId) ON DELETE CASCADE,
FOREIGN KEY (kategoriaId) REFERENCES Kategorite(kategoriaId) ON DELETE CASCADE,
PRIMARY KEY(iId,kategoriaId))
CLUSTER inst_mes(iId)


drop cluster inst_mes;
--DATA DICTIONARY

select *
from dict;


--1. Listoni kolonat e tabeles Kategorite. Ne rezultat te paraqiten : emri i
--   kolones, tipi i te dhenave, id e kolones.

SELECT column_name "Emri i kolones",data_type "Tipi i te dhenave",
       column_id "ID"
FROM dba_tab_columns --te dhenat per kolonat e tabelave jane ne kete tabele
WHERE table_name='KATEGORITE';

--1. Listoni sekuencat e krijuara nga user-i. Ne rezultat te paraqiten : emri i
--   sekuences, vlera min, vlera max,dhe inkrementi i sekuences.

SELECT sequence_name "Emri i sekuences", min_value "Vlera minimale",
       max_value "Vlera maksimale", increment_by "Inkrementi"
FROM dba_sequences
WHERE sequence_owner='MIRJETA';

--CURSORS




--RECURSIVE QUERIES

--1. Te gjendet fatura me vlere maksimale e leshuar brenda dites, per cdo dite.

with temp as 
(SELECT f.numriFatures, f.kandidatiId, f.dataLeshimit, f.shumaTotale,
row_number() over (partition by to_char(f.dataLeshimit,'YYYY-MM-DD') order by  f.shumaTotale desc)
rowNumber
FROM Faturat f)
SELECT k.kId, k.emri, k.mbiemri, t.numriFatures, t.dataLeshimit, t.shumaTotale
FROM temp t
INNER JOIN Kandidatet k on t.kandidatiId=k.kId
WHERE t.rownumber=1

--ADVANCED AGGREGATION

--RANKING

--1. Te rangohen kandidatet ne baze te numrit te testimeve te cilat i kane
--    perfunduar. Ne rezultat te paraqiten : rankNo, kId, emri dhe mbiemri,
--    dhe numri i testimeve.


SELECT dense_rank() over(order by cardinality(k.testimet) desc) RankNo,
       k.kid, k.emri||' '|| k.mbiemri "Emri dhe mbiemri",
       cardinality(k.testimet) "Numri i testimeve"
FROM kandidatet k

--2. Te rangohen instruktoret ne baze te numrit te kategorive per te cilat
--   mbajne ore praktike. Ne rezultat te paraqiten: rankNo, instruktoriId,
--   emri dhe mbiemri i instruktorit, dhe numri i kategorive.

with temp1 as(
SELECT i.iId, i.emri.emri ||' '|| i.emri.mbiemri Emri,
       count(*) NrKategorive
FROM Instruktoret i inner join Mesimet m on i.iId=m.instruktoriId
GROUP BY i.iId, i.emri.emri, i.emri.mbiemri)
SELECT dense_rank() over (order by t.NrKategorive desc) RankNo, t.iId "Id",
      t.Emri "Emri dhe Mbiemri", t.NrKategorive
FROM temp1 t

--3. Te grupohen faturat ne tri kategori/blloqe sipas shumes totale.

SELECT ntile(3) OVER (ORDER BY f.shumaTotale DESC) RankNo,
       f.numriFatures,
	   f.shumaTotale
FROM Faturat f

--WINDOWING

--1.

SELECT k.emriKategorise, k.cmimi,
       SUM(k.cmimi)
       OVER(ORDER BY k.cmimi ROWS BETWEEN 
       UNBOUNDED PRECEDING AND CURRENT ROW) AS rows_shuma
FROM Kategorite k

--2.

SELECT k.emriKategorise, k.cmimi,
       SUM(k.cmimi)
       OVER(ORDER BY k.cmimi RANGE BETWEEN 
       UNBOUNDED PRECEDING AND CURRENT ROW) AS range_shuma
FROM Kategorite k

--3.

SELECT k.kId,
	   k.emri "Emri",
	   k.mbiemri "Mbiemri", 
	   f.numriFatures,
	   f.shumaTotale,
	   LAST_VALUE(f.shumaTotale) IGNORE NULLS
	   OVER (PARTITION BY k.kid ORDER BY f.shumaTotale RANGE BETWEEN
	   UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS fatura_maksimale
FROM Faturat f
INNER JOIN Kandidatet k on k.kId = f.kandidatiId




--VIEW UPDATES

--1. Te krijohet view e cila permban faturat, duke perfshire edhe vleren e tyre
--   pa TVSH. Kolonat te jene: kandidatiId, kategoriaId, dataFillimit, 
--   dataLeshimit, shumaTotale, llojiPageses, ePerfunduar, shuma pa TVSH.

CREATE VIEW FaturatPaTVSH_VIEW AS
(
SELECT f.kandidatiId, f.kategoriaId, f.dataFillimit, f.dataLeshimit, 
       f.shumaTotale, f.llojiPagesesId, f.ePerfunduar,
       f.shumaTotale/1.16 "Shuma pa TVSH"
FROM Faturat f
)


INSERT INTO faturatPaTVSH_VIEW(kandidatiId,kategoriaId,dataFillimit,dataLeshimit,
                               shumaTotale,llojiPagesesId,ePerfunduar)
VALUES(1,1,'01-Jan-2018','14-Jan-2018',60,1,'Jo');

UPDATE faturatPaTVSH_VIEW
SET ePerfunduar='Po'
WHERE kandidatiId=1 AND kategoriaId=1 AND
      dataLeshimit='14-Jan-2018' AND shumaTotale='60'
      


--2. Te krijohet view e cila permban te dhenat rreth kandidateve, duke perfshire
--   testimet. View te kete keto kolona: kId, emri, emriPrindit, mbiemri, 
--   nrPersonal, telefoni, dataRegjistrimit, testimet.


CREATE VIEW Kandidatet_VIEW AS
(
SELECT k.kid, k.emri, k.emriPrindit, k.mbiemri, k.nrPersonal, k.telefoni,
       k.dataRegjistrimit, t.data dite, t.lloji, t.piket, cardinality(testimet) "nr"
FROM Kandidatet k, TABLE(k.testimet) t
)

INSERT INTO Kandidatet_VIEW (kid,emri,emriPrindit,mbiemri,nrPersonal,telefoni,
                                  dataRegjistrimit,dite,lloji,piket)
VALUES(kandidatet_seq.NEXTVAL,'Ariana','Korab','Arifaj','456128965','049/526-666','06-Jan-2018',
       '25-Jan-2018','Test real','94');


--3.VIEW
CREATE VIEW InstruktoretM_VIEW AS
(
SELECT i.iid, i.emri.emri "Emri", i.emri.emriPrindit "Emri i prindit",
       i.emri.mbiemri "Mbiemri", i.nrPersonal, m.kategoriaId, k.emriKategorise
FROM Instruktoret i, Mesimet m, Kategorite k
WHERE  i.Iid=m.instruktoriId AND m.kategoriaId=k.kategoriaId
);


UPDATE InstruktoretM_VIEW
SET emriKategorise='D'
WHERE iid=2 and kategoriaId=1; 
   
      
      



