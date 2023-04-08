INSERT INTO customer(login, password, role_name, date_created, date_updated)
VALUES ('owner', '$2a$10$C2VWCKVr4urats6cKEgIfOr8OLMaTvTYfgCvhylzFZpLPwK4WxiTy', 'ROLE_OWNER', '2020-03-17',
        NULL), -- password for owner => owner-space-y
       ('admin-1', '$2a$10$i.p6iRAb.RxBsRTSPRM2Suyaz1p3Z34S6jKhM.jDBOTsZGgLEKmKO', 'ROLE_ADMIN', '2020-06-19',
        NULL), -- password for admin => admin-1-space-y
       ('sample-user', '$2a$10$QZDalpzOUv87Ku73vfS0XuMpGWZ83Ba41uW93Zo7pQZj2hj8mTiI2', 'ROLE_USER', '2023-03-15',
        NULL), -- password for user => user
       ('admin-2', '$2a$10$x1.f.0rIgMuEKUADxH6y.e4jMzQRbDt1kL8sF7I8dhWHONEL/uXLq', 'ROLE_ADMIN', '2023-03-14',
        NULL), -- admin-2
       ('user-1', '$2a$10$o5iMun7xM8OyAyHOVyE8UeZOBOv6Rs3JfTcq6mnA5upEW159Fm/cq', 'ROLE_USER', '2023-02-17',
        NULL), -- user-1
       ('user-2', '$2a$10$8jYud2jKWuIwhBhUIjeDTOxTrZjimCS0lwpCTyJJE1c40NvINvj6K', 'ROLE_USER', '2022-06-17',
        NULL), -- user-2,
       ('user-3', '$2a$10$GGXi1iUNiuplG2zca.Y2R.bfckvcjvbgARXFNkfKOA3PiuhBrkQV2', 'ROLE_USER', '2023-03-21',
        NULL), -- user-3
       ('user-4', '$2a$10$YxE8PQqTwliaPmkdnUfSx.EoM3r3IMr.3eNt7fnqlv4tzF6W7UY8G', 'ROLE_USER', '2023-01-26',
        NULL), -- user-4
       ('user-5', '$2a$10$Tn8fgv2/P5Wr0ygGZ.ArOOTwTjcaswP4oRqW9cs2cRb4jiPqtTeRy', 'ROLE_USER', '2023-02-12',
        NULL); -- user-5
