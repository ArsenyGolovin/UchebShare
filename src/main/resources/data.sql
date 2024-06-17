insert into flows (code, name) values ('idb', 'Программная инженерия');
insert into flows (code, name) values ('adb', 'Приборостроение');
insert into flows (code, name) values ('edb', 'Метрология');

insert into folders (id, name, parent_folder_id) values (0, 'main', 0);
insert into folders (name, parent_folder_id) values ('Программная инженерия', 0);
insert into folders (name, parent_folder_id) values ('Приборостроение', 0);
insert into folders (name, parent_folder_id) values ('Метрология', 0);
insert into folders (name, parent_folder_id) values ('ИДБ-23', 1);
insert into folders (name, parent_folder_id) values ('ИДБ-22', 1);
insert into folders (name, parent_folder_id) values ('Математический анализ', 4);
insert into folders (name, parent_folder_id) values ('Линейная алгебра и аналитическая геометрия', 4);
insert into folders (name, parent_folder_id) values ('Программирование на языке высокого уровня', 4);
insert into folders (name, parent_folder_id) values ('Лекции', 6);
insert into folders (name, parent_folder_id) values ('Семинары', 6);

insert into documents (name, parent_folder_id) values ('Lecture1.pdf', 9);
insert into documents (name, parent_folder_id) values ('Lecture2.pdf', 9);
insert into documents (name, parent_folder_id) values ('Lecture3.pdf', 9);