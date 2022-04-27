/* ENSF 409 W22 Project Example Database
 2022 Barcomb and Marasco
 
 Each time this file is executed, it will reset the database to the original state defined below.
 
 */

DROP DATABASE IF EXISTS FOOD_INVENTORY;
CREATE DATABASE FOOD_INVENTORY; 
USE FOOD_INVENTORY;


DROP TABLE IF EXISTS DAILY_CLIENT_NEEDS;
CREATE TABLE DAILY_CLIENT_NEEDS (
	ClientID		int not null AUTO_INCREMENT,
	Client			varchar(25),
	WholeGrains		int,
	FruitVeggies	int,
	Protein			int,
	Other			int,
	Calories		int,
	primary key (ClientID)
);

INSERT INTO DAILY_CLIENT_NEEDS (Client, WholeGrains, FruitVeggies, Protein, Other, Calories)
VALUES
('Adult Male',	16,	28,	26,	30,	2500),
('Adult Female', 16, 28, 26, 30, 2000),
('Child over 8', 21, 33, 31, 15, 2200),
('Child under 8', 21, 33, 31, 15, 1400);

DROP TABLE IF EXISTS AVAILABLE_FOOD;
CREATE TABLE AVAILABLE_FOOD (
	ItemID			int not null AUTO_INCREMENT,
	Name			varchar(50),
	GrainContent	int,
	FVContent		int,
	ProContent		int,
	Other			int,
	Calories		int,
	primary key (ItemID)
);

INSERT INTO AVAILABLE_FOOD (Name, GrainContent, FVContent, ProContent, Other, Calories)
VALUES
('Tomatoes, 1 kg', 0, 100, 0, 0, 180), 
('Pancake mix, 1 kg', 74, 0, 12, 14, 3400), 
('Maple syrup, 1 L', 0, 0, 0, 100, 3276), 
('Cauliflower, 2 heads', 0, 100, 0, 0, 420), 
('Strawberries, 2 kg', 0, 100, 0, 0, 640), 
('Bananas, bunch', 0, 100, 0, 0, 672),
('Cantaloupe, dozen', 0, 100, 0, 0, 3324), 
('Cottage cheese', 0, 0, 11, 89, 840), 
('Trail mix, 1 kg', 21, 0, 20, 59, 6000), 
('Soy protein, 1 kg', 0, 0, 88, 12, 3350), 
('Wheat bread, loaf', 96, 0, 4, 0, 2192), 
('Eggs, dozen', 0, 0, 9, 91, 864), 
('Avocado, dozen', 0, 100, 0, 0, 2880), 
('Corn tortillas, pound', 94, 0, 6, 0, 989), 
('Pasta, dry, two pounds', 75, 0, 13, 12, 3366), 
('Brown rice, large bag', 86, 0, 8, 6, 15000), 
('Raisin bran, 1 box', 87, 1, 9, 3, 1710), 
('Oatmeal, 10 packets', 69, 0, 15, 16, 1000), 
('Frozen waffles, pack of 10', 64, 0, 9, 27, 850), 
('Frozen blueberries, 2100 g', 0, 95, 5, 0, 1200);
