select * from title;

-- 해당 직책을 가지고 있는 사원 목록을 검색
select empName, empNo from employee e join title t on e.title = t.tNo where tNo = 5; 

select * from employee where empNo  =1003;

select * from employee;

-- password 길이 확인
-- 단방향 함수(Hash 함수 : MD5)
-- hash 함수는 보안성이 좋음
select password('aaa'), length(password('aaa')) from dual;

-- emp_detail 
insert into erp.emp_detail (empno, pic, gender, hiredate, pass) values (?, ?, ?, ?, ?);

select * from emp_detail;

select empno, pic, gender, hiredate, pass from emp_detail where empno = 1003; 