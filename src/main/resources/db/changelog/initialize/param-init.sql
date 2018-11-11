CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
INSERT INTO param_group (pg_name, pg_type) VALUES
  ('Занятия', 'STUDY'),
  ('Методическая работ', 'STUDY'),
  ('Дополнительно', 'STUDY'),
  ('Аспирантура (учёба, руководство, присвоение степени)', 'SCIENCE'),
  ('НИР', 'SCIENCE'),
  ('Патентно-публикационная активность', 'SCIENCE'),
  ('Работа с талантливой молодёжью', 'SCIENCE'),
  ('Подтверждение научной квалификации', 'SCIENCE'),
  ('Спорт', 'OTHER'),
  ('Воспитательная деятельность', 'OTHER'),
  ('Награды', 'OTHER');
INSERT INTO param (p_name, pg_id) VALUES
  ('Чтение лекций', (SELECT pg_id FROM param_group WHERE pg_name = 'Занятия')),
  ('Общий объём аудиторной нагрузки (лекции, практические, семинарские и лабораторные занятия)', (SELECT pg_id FROM param_group WHERE pg_name = 'Занятия')),
  ('Публикации учебно-методического характера', (SELECT pg_id FROM param_group WHERE pg_name = 'Методическая работ')),
  ('Участие в разработке учебно-программной и учебно-методической документации', (SELECT pg_id FROM param_group WHERE pg_name = 'Методическая работ')),
  ('Участие в работе по организации курсов повышения квалификации и новых направлений по переподготовке специалистов', (SELECT pg_id FROM param_group WHERE pg_name = 'Дополнительно')),
  ('Оказание платных услуг', (SELECT pg_id FROM param_group WHERE pg_name = 'Дополнительно')),
  ('Работа в приёмной комиссии', (SELECT pg_id FROM param_group WHERE pg_name = 'Дополнительно')),
  ('Участие в программах академической мобильности для прохождения стажировки', (SELECT pg_id FROM param_group WHERE pg_name = 'Дополнительно')),
  ('Работа на общественных началах', (SELECT pg_id FROM param_group WHERE pg_name = 'Дополнительно')),
  ('Защита диссертации (соискателю)', (SELECT pg_id FROM param_group WHERE pg_name = 'Аспирантура (учёба, руководство, присвоение степени)')),
  ('Изобретательская и патентнолицензионная работа.', (SELECT pg_id FROM param_group WHERE pg_name = 'Патентно-публикационная активность')),
  ('Участие в Республиканской универсиаде', (SELECT pg_id FROM param_group WHERE pg_name = 'Спорт')),
  ('Работа с талантливой молодежью', (SELECT pg_id FROM param_group WHERE pg_name = 'Спорт'));