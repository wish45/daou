insert into "user" (username, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
insert into "user" (username, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');


insert into payment_data (reg_date, regist_count, leave_count, pay_amount, used_amount, sales_amount ) values ('2022-07-22 00', '32', '4', '45100', '27,300', '95,000');



merchants 물건정보
id(int), name, business_id, tax_Type, category_id(int)

card_usages(결제정보)
id(int), created_at(datetime), category(int), amount(int), merchant_id(int)






SELECT M.ID ID , M.NAME NAME , SUM(C.AMOUNT) AMOUNT
FROM MERCHANTS M INNER JOIN CARD_USAGES C
                            ON M.ID = C.MERCHANT_ID
WHERE C.CATEGORY=0
GROUP BY C.MERCHANT_ID
ORDER BY C.AMOUNT DESC
    LIMIT 5
