create procedure loop_insert(IN cnt int)
begin
	declare i int default 1;
	while (i <= cnt) do
		insert into board (title, userid, contents)
		values ('aaaa','11','가나다라마');
		insert into board (title, userid, contents)
		values ('bbbb','555','제곧내~ 제곧내~');
		insert into board (title, userid, contents)
		values ('ㅋㅋㅋㅋ','11','아잉, 냉무~');
	    set i = i + 1;
	end while;
end;;		
		
delete from board;

call loop_insert(221004);	