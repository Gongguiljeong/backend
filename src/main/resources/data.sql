-- brand password = kwon
insert into brands (brand_id, name, username, password, email, link, status, create_date, update_date)
values (1, 'kwon1', 'kwon1', '$2a$10$ols0oALJCphdO58Zn5j2r.GIXKZTCsxKQw0XyN17treUmzB9vuJ52', 'kwon@naver.com','www.naver.com', 'Y', now(), now());
insert into brands (brand_id, name, username, password, email, link, status, create_date, update_date)
values (2, 'kwon2', 'kwon2', '$2a$10$ols0oALJCphdO58Zn5j2r.GIXKZTCsxKQw0XyN17treUmzB9vuJ52', 'kwon@naver.com','www.naver.com', 'Y', now(), now());
insert into brands (brand_id, name, username, password, email, link, status, create_date, update_date)
values (3, 'kwon3', 'kwon3', '$2a$10$ols0oALJCphdO58Zn5j2r.GIXKZTCsxKQw0XyN17treUmzB9vuJ52', 'kwon@naver.com', 'www.naver.com', 'Y', now(), now());

-- user
insert into users(user_id, name, email, gender, age,  status, create_date, update_date)
values(1, '카카오닉네임', 'rldh11111@naver.com', 'M', '30', 'Y', now(), now());
insert into users(user_id, name, email, gender, age,  status, create_date, update_date)
values(2, '카카오닉네임', 'rldh11112@naver.com', 'M', '30', 'Y', now(), now());
insert into users(user_id, name, email, gender, age,  status, create_date, update_date)
values(3, '카카오닉네임', 'rldh11113@naver.com', 'M', '30', 'Y', now(), now());


-- admin
insert into admins (admin_id, name, username, password, email,status, create_date, update_date)
values (1, 'kwon1', 'kwon1', '$2a$10$ols0oALJCphdO58Zn5j2r.GIXKZTCsxKQw0XyN17treUmzB9vuJ52', 'kwon1@naver.com', 'Y', now(), now());
insert into admins (admin_id, name, username, password, email, status, create_date, update_date)
values (2, 'kwon2', 'kwon2', '$2a$10$ols0oALJCphdO58Zn5j2r.GIXKZTCsxKQw0XyN17treUmzB9vuJ52', 'kwon2@naver.com', 'Y', now(), now());
insert into admins (admin_id, name, username, password, email, status, create_date, update_date)
values (3, 'kwon3', 'kwon3', '$2a$10$ols0oALJCphdO58Zn5j2r.GIXKZTCsxKQw0XyN17treUmzB9vuJ52', 'kwon3@naver.com', 'Y', now(), now());


-- Influencer
insert into influencers (influencer_id, name, link, status, create_date, update_date)
values (1, '김태희1', 'www.naver.com1', 'N', now(), now());
insert into influencers (influencer_id, name, link, status, create_date, update_date)
values (2, '김태희2', 'www.naver.com2', 'N', now(), now());
insert into influencers (influencer_id, name, link, status, create_date, update_date)
values (3, '김태희3', 'www.naver.com3', 'N', now(), now());


-- MainCateogry
insert into main_categories (main_category_id, name, status, create_date, update_date)
values  (1, '뷰티', 'Y', now(), now());
insert into main_categories (main_category_id, name, status, create_date, update_date)
values  (2, '패션', 'Y', now(), now());
insert into main_categories (main_category_id, name, status, create_date, update_date)
values  (3, '푸드', 'Y', now(), now());

-- SubCategory
insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (1, '스킨케어', 1, 'Y', now(), now());
insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (2, '마스크/팩', 1, 'Y', now(), now());
insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (3, '헤어케어', 1, 'Y', now(), now());

insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (4, '모자', 2, 'Y', now(), now());
insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (5, '상의', 2, 'Y', now(), now());
insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (6, '바지', 2, 'Y', now(), now());

insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (7, '족발', 3, 'Y', now(), now());
insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (8, '과자', 3, 'Y', now(), now());
insert into sub_categories (sub_category_id, name, main_category_id, status, create_date, update_date)
values  (9, '음료', 3, 'Y', now(), now());