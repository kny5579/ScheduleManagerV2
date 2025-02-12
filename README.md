### ERD

![img.png](img.png)

<br>

### SQL

```sql
CREATE TABLE author
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(20) NOT NULL,
    created_date DATETIME,
    updated_date DATETIME
);

CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    contents VARCHAR(200) NOT NULL,
    created_date DATETIME,
    updated_date DATETIME,
    author_id BIGINT,
    foreign key (author_id) references author(id)
);
```
<br>

### API 명세서
![img_1.png](img_1.png)


- signup
![img_2.png](img_2.png)

- login
![img_3.png](img_3.png)

- schedule create
![img_4.png](img_4.png)

- paging
![img_5.png](img_5.png)