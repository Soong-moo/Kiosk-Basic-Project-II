DROP DATABASE IF EXISTS kioskDB;
CREATE DATABASE kioskDB;
commit;

USE kioskDB;




CREATE TABLE categoryData (
	CategoryID		INTEGER	PRIMARY KEY,
	CategoryName	VARCHAR(10)
);


CREATE TABLE productData (
	ProductID		INTEGER PRIMARY KEY,
    CategoryID		INTEGER,
	NAME			VARCHAR(20),
    PRICE			INTEGER,
    FOREIGN KEY (CategoryID) REFERENCES categoryData(CategoryID)
);


CREATE TABLE optionData (
	optionID		INTEGER	PRIMARY KEY,
	optionName		VARCHAR(10),
    optionPrice  	INTEGER
);


CREATE TABLE orderData (
	orderID			INTEGER PRIMARY KEY,
    orderDate		DATE,
	orderPrice		INTEGER,
	orderWay		VARCHAR(10),
	eatingWay		VARCHAR(10)
);



CREATE TABLE selectData (
	orderID			INTEGER,
    categoryID		INTEGER,
    productID		INTEGER,
    option1ID		INTEGER,
    option2ID		INTEGER,
    option3ID		INTEGER,
    number			INTEGER,
    FOREIGN KEY (orderID) REFERENCES orderData(orderID),
    FOREIGN KEY (categoryID) REFERENCES categoryData(categoryID),
    FOREIGN KEY (productID) REFERENCES productData(productID)
);



CREATE TABLE dataResult (
	productName		VARCHAR(20),
    option1Name		VARCHAR(20),
    option2Name		VARCHAR(20),
    option3Name		VARCHAR(20),
    count			INTEGER
);








#카테고리 데이터
INSERT INTO categoryData VALUES(1, '인기메뉴');
INSERT INTO categoryData VALUES(2, '햄버거');
INSERT INTO categoryData VALUES(3, '사이드');
INSERT INTO categoryData VALUES(4, '음료');



#옵션 데이터
INSERT INTO optionData VALUES(0, '선택안함', 0);
INSERT INTO optionData VALUES(1, '비프', 1500);
INSERT INTO optionData VALUES(2, '통새우 4개', 1000);
INSERT INTO optionData VALUES(3, '통새우 8개', 2000);
INSERT INTO optionData VALUES(4, '치즈 1장', 500);
INSERT INTO optionData VALUES(5, '치즈 2장', 1000);
INSERT INTO optionData VALUES(6, '치즈 3장', 1500);
INSERT INTO optionData VALUES(7, '아보카도', 1500);
INSERT INTO optionData VALUES(8, '파인애플', 1000);
INSERT INTO optionData VALUES(9, '피클', 500);
INSERT INTO optionData VALUES(10, '할라피뇨', 500);
INSERT INTO optionData VALUES(11, '기본 야채 추가', 1000);



#상품 데이터
INSERT INTO productData VALUES(1, 2, '치즈버거', 4000);
INSERT INTO productData VALUES(2, 2, '치킨버거', 4500);
INSERT INTO productData VALUES(3, 2, '불고기버거', 5000);
INSERT INTO productData VALUES(4, 2, '새우버거', 5000);
INSERT INTO productData VALUES(5, 3, '감자튀김', 2000);
INSERT INTO productData VALUES(6, 3, '치즈 감자튀김', 3000);
INSERT INTO productData VALUES(7, 3, '베이컨 치즈 감자튀김', 4000);
INSERT INTO productData VALUES(8, 3, '스파이시 치플레 감자튀김', 4000);
INSERT INTO productData VALUES(9, 3, '치킨텐더', 4000);
INSERT INTO productData VALUES(10, 3, '치즈볼', 4000);
INSERT INTO productData VALUES(11, 4, '코카콜라', 2000);
INSERT INTO productData VALUES(12, 4, '제로 코카콜라', 2000);
INSERT INTO productData VALUES(13, 4, '스프라이트', 2000);
INSERT INTO productData VALUES(14, 4, '제로 스프라이트', 2000);
INSERT INTO productData VALUES(15, 4, '환타', 2000);
INSERT INTO productData VALUES(16, 4, '밀크쉐이크', 3000);
INSERT INTO productData VALUES(17, 4, '페리에 라임', 3000);
INSERT INTO productData VALUES(18, 4, '닥터페퍼', 2500);
INSERT INTO productData VALUES(19, 4, '크림소다', 5000);
INSERT INTO productData VALUES(20, 4, '하이볼', 4500);
INSERT INTO productData VALUES(21, 4, '진저하이볼', 4500);



#주문 데이터
INSERT INTO orderData VALUES(1, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 13000, 'SamsungPay', '매장');
INSERT INTO orderData VALUES(2, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 16000, '신용카드', '매장');
INSERT INTO orderData VALUES(3, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 9000, '신용카드', '포장');
INSERT INTO orderData VALUES(4, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 9000, '신용카드', '매장');
INSERT INTO orderData VALUES(5, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 18500, 'SamsungPay', '매장');
INSERT INTO orderData VALUES(6, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 10000, '신용카드', '매장');
INSERT INTO orderData VALUES(7, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 8500, 'SamsungPay', '포장');
INSERT INTO orderData VALUES(8, STR_TO_DATE('2022-11-16', '%Y-%m-%d'), 9500, 'SamsungPay', '포장');



#선택 데이터
INSERT INTO selectData VALUES(1, 2, 1, 0, 5, 0, 1); 
INSERT INTO selectData VALUES(1, 2, 2, 1, 4, 7, 5);
INSERT INTO selectData VALUES(2, 2, 2, 1, 4, 7, 1);
INSERT INTO selectData VALUES(2, 2, 2, 1, 4, 7, 2);
INSERT INTO selectData VALUES(3, 2, 3, 0, 0, 0, 1);
INSERT INTO selectData VALUES(3, 3, 5, 0, 0, 0, 1);
INSERT INTO selectData VALUES(3, 4, 11, 0, 0, 0, 1);
INSERT INTO selectData VALUES(4, 2, 1, 0, 0, 0, 1); 
INSERT INTO selectData VALUES(4, 2, 4, 0, 0, 0, 1);
INSERT INTO selectData VALUES(5, 2, 2, 2, 4, 0, 3);
INSERT INTO selectData VALUES(5, 2, 4, 3, 0, 11, 10);
INSERT INTO selectData VALUES(5, 4, 20, 0, 0, 0, 1);
INSERT INTO selectData VALUES(6, 2, 4, 3, 6, 7, 2);
INSERT INTO selectData VALUES(7, 2, 1, 0, 0, 0, 1); 
INSERT INTO selectData VALUES(7, 2, 2, 0, 0, 0, 1);
INSERT INTO selectData VALUES(8, 2, 3, 0, 0, 0, 1);
INSERT INTO selectData VALUES(8, 4, 21, 0, 0, 0, 1);


