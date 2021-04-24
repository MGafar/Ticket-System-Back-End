insert into department(name)
values ('FX');

insert into department(name)
values ('Credit');

insert into topic(name)
values ('Remote Connectivity Issues');

insert into topic(name)
values ('Messaging Problems');


insert into tickets(author, description, time_created, time_updated, title, department_id, status, solution, topic_id)
values ('Muhamad Gafar', 'The VDI has decided to stop working again', '2021-04-13 11:02:02', '2021-04-13 11:02:03', 'VDI Issues', 1L, 'DONE', 'Restart the VDI', 1L);

insert into tickets(author, description, time_created, time_updated, title, department_id, status, topic_id)
values ('Not Muhamad Gafar', 'My Symphony has stopped receiving messages', '2021-04-13 11:02:02', '2021-04-13 11:02:03', 'Symphony problems', 2L, 'OPEN', 2L);