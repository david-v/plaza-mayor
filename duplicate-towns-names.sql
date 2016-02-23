select distinct(x.id), x.name, regions.name from 
    (select t1.* from towns t1, towns t2 where t1.name like t2.name and t1.id != t2.id) as x
join regions on (regions.id = x.region_id);