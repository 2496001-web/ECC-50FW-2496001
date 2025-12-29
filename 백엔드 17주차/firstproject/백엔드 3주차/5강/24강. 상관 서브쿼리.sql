#24강. 상관 서브쿼리

#예제 5-30. sample551  테이블과 sample552 테이블
mysql> SELECT * FROM sample551;
+------+------+
| no   | a    |
+------+------+
|    1 | NULL |
|    2 | NULL |
|    3 | NULL |
|    4 | NULL |
|    5 | NULL |
+------+------+
5 rows in set (0.377 sec)

mysql> SELECT * FROM sample552;
+------+
| no2  |
+------+
|    3 |
|    5 |
+------+
2 rows in set (0.350 sec)

#예제 5-31. EXISTS를 사용해 ‘있음’으로 갱신하기

mysql> UPDATE sample551 SET a = '있음' WHERE
    -> EXISTS (SELECT * FROM sample552 WHERE no2 = no);
Query OK, 2 rows affected (0.177 sec)
Rows matched: 2  Changed: 2  Warnings: 0

mysql> SELECT * FROM sample551;
+------+------+
| no   | a    |
+------+------+
|    1 | NULL |
|    2 | NULL |
|    3 | 있음 |
|    4 | NULL |
|    5 | 있음 |
+------+------+
5 rows in set (0.116 sec)

#서브쿼리의 WHERE 구는 no2 = no라는 조건식
#no2는 sample552의 열이고 no는 sample551의 열
#no가 3과 5, 즉 no2에 값일 때만 서브쿼리가 행을 반환

#예제 5-32. NOT EXISTS를 사용해 ‘없음’으로 갱신하기
mysql> UPDATE sample551 SET a = '없음' WHERE
    -> NOT EXISTS (SELECT * FROM sample552 WHERE no2 = no);
Query OK, 3 rows affected (0.144 sec)
Rows matched: 3  Changed: 3  Warnings: 0

mysql> SELECT * FROM sample551;
+------+------+
| no   | a    |
+------+------+
|    1 | 없음 |
|    2 | 없음 |
|    3 | 있음 |
|    4 | 없음 |
|    5 | 있음 |
+------+------+
5 rows in set (0.117 sec)

#예제 5-33. 열에 테이블명 붙이기
#열멸 앞에 '테이블명.'을 붙이기만 하면 됨
mysql> UPDATE sample551 SET a = '있음' WHERE
    -> EXISTS (SELECT * FROM sample552 WHERE sample552.no2 = sample551.no);
Query OK, 0 rows affected (0.021 sec)
Rows matched: 2  Changed: 0  Warnings: 0

mysql> SELECT * FROM sample551;
+------+------+
| no   | a    |
+------+------+
|    1 | 없음 |
|    2 | 없음 |
|    3 | 있음 |
|    4 | 없음 |
|    5 | 있음 |
+------+------+
5 rows in set (0.012 sec)

#예제 5-34. IN을 사용해 조건식 기술
mysql> SELECT * FROM sample551 WHERE no IN (3, 5);
+------+------+
| no   | a    |
+------+------+
|    3 | 있음 |
|    5 | 있음 |
+------+------+
2 rows in set (0.017 sec)

#예제 5-35. IN의 오른쪽을 서브쿼리로 지정하기
mysql> SELECT * FROM sample551 WHERE no IN (SELECT          no2 FROM sample552);
+------+------+
| no   | a    |
+------+------+
|    3 | 있음 |
|    5 | 있음 |
+------+------+
2 rows in set (0.130 sec)
#서브쿼리는 스칼라 서브쿼리가 될 필요는 없음.mysql> SELECT * FROM sample551 WHERE no IN (SELECT          no2 FROM sample552);
+------+------+
| no   | a    |
+------+------+
|    3 | 있음 |
|    5 | 있음 |
+------+------+
2 rows in set (0.130 sec)
#서브쿼리는 스칼라 서브쿼리가 될 필요는 없음.