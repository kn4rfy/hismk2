update t_user
set password='$2a$10$J3Xdf4fuDl2.Yr.tC31yH.Oa9r11hEk7xTfewF85VG0ddIl9xG8xO'
where login = 'system';
update t_user
set password='$2a$10$k/T2FaRVfWPKExvZkWV6Ze7BwLDpHErqP2aGLT/ZSJBR.Lld4LRA.'
where login = 'admin';