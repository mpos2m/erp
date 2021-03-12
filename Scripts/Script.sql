select * from title;

-- 해당 직책을 가지고 있는 사원 목록을 검색
select empName, empNo from employee e join title t on e.title = t.tNo where tNo = 5; 