drop database Matador;
create database Matador;
use Matador;

create table Player(
ID int(1), 
Name varchar(20), 
Balance int(10), 
Field int(2),
primary key (ID));

create table Field (
ID int(2),
Name varchar(20),
Type enum('Street', 'Jail', 'Ferry', 'Brewery', 'Bank', 'Tax', 'Chance', 'Start'),
Price int(5),
Rent int(5),
Owner int(1),
primary key (ID),
foreign key (Owner) references player(ID));