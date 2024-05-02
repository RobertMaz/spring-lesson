- [x] Entity
  - ```java
    @Entity
    @Table(name = "books")
    public class Book { id, name, description}
    ```
- [ ] DAO/Repository/CRUD in class
- [x] EntityManager
- [x] EntityTransaction
- [ ] Query: позволяет создавать и выполнять запросы к базе данных. Он предоставляет методы для создания запросов на основе  языка  запросов,  таких  как  JPQL  (Java  Persistence  Query  Language)  или  Criteria  API.  Методы  getResultList()  и getSingleResult() используются для получения результатов запроса.
- [ ] TypedQuery: является расширением интерфейса Query и предоставляет типизированные методы для работы с запросами.
- [ ] CriteriaQuery: представляет типобезопасный способ создания запросов с использованием Criteria API. Он позволяет строить запросы в программном коде с помощью объектов-критериев, представляющих различные части запроса, такие как выборка, условия и сортировка.
- [ ] CriteriaBuilder: предоставляет методы для создания объектов-критериев (CriteriaQuery, Predicate,  Order и других) и выполнения операций сравнения, логических операций и математических операций в запросах.
- [ ] Требования Entity
  - @Entity
  - empty constructor
  - top-level class
  - @ID
- [ ] entity annotations
  - @Entity
  - @Table
  - @Id
  - @GeneratedValue
  - @Column
  - @OneToOne,  @OneToMany,  @ManyToOne,  @ManyToMany
  - @JoinColumn: Эта аннотация используется в сочетании с @ManyToOne или @OneToOne и указывает столбец, который используется для связи между сущностями
  - @Transient
  - @Inheritance
- [ ] HQL
- [ ] Native sql query
  ```java
  session.createNativeQuery("select * from books")
  ```
- [ ] @NamedQueries, @NamedQuery
```java
@NamedQueries({
        @NamedQuery(name="bookFinder", query="from Book where name = :bookName")
})
```
- [ ] Типы связей
  - OneToOne - один к одному
  - OneToMany - один ко многим
  - ManyToOne - многие к одному
  - ManyToMany - многие ко многим
- [ ] Стратегии генерации id
- [ ] Наследование(@MappedSuperclass)
- [ ] Проблема N+1


```java abstract dao

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable> {
    private Class<T> clazz;

    @Autowired
    protected SessionFactory sessionFactory;

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet, null);
    }

    public T findOne(final long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        Session session = getCurrentSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + clazz.getName(), clazz).list();
        session.close();
        return list;
    }

    public T create(final T entity) {
        Preconditions.checkNotNull(entity, null);
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity, null);
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity, null);
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkNotNull(entity, null);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
```

```java book dao
@Repository
public class BookDAO extends AbstractHibernateDao<Book> {

  public BookDAO(){
    setClazz(Book.class );
  }
}
```

```java

    private static Transaction transactionExample() {
        Book book = new Book("Core Java", "Learn Core Java with Coding Examples");
        Book book1 = new Book("Learn Hibernate", "Learn Hibernate with building projects");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the book objects
            session.persist(book);
            session.persist(book1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return transaction;
    }

    private static void nativeQuery() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = session.createQuery("from Book", Book.class).list();
            books.forEach(b -> {
                System.out.println("Print book name : " + b.getName());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void namedQuery() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = session.getNamedQuery("bookFinder").setParameter("bookName", "Core Java").getResultList();
            books.forEach(b -> {
                System.out.println("Named: Print book name : " + b.getName());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void criteria() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            JpaRoot<Book> from = query.from(Book.class);
            query.select(from).where(criteriaBuilder.equal(from.get("name"), "Core Jav"));
            List<Book> books = session.createQuery(query).getResultList();
            books.forEach(b -> {
                System.out.println("Criteria: Print book name : " + b.getName());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

```xml resources/hibernate.cfg.xml
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>

    <session-factory>

        <!-- JDBC Database connection settings -->
        <property name="connection.driver_class">org.postgresql.Driver</property>
        <property name="connection.url">jdbc:postgresql://localhost:5432/postgres?useSSL=false</property>
        <property name="connection.username">postgres</property>
        <property name="connection.password">postgres</property>

        <!-- JDBC connection pool settings ... using built-in test pool -->
        <property name="connection.pool_size">1</property>

        <!-- Echo the SQL to stdout -->
        <property name="show_sql">true</property>

        <!-- Set the current session context -->
        <property name="current_session_context_class">thread</property>

        <!-- Drop and re-create the database schema on startup -->
        <property name="hbm2ddl.auto">create-drop</property>

        <!-- dbcp connection pool configuration -->
        <property name="hibernate.dbcp.initialSize">5</property>
        <property name="hibernate.dbcp.maxTotal">20</property>
        <property name="hibernate.dbcp.maxIdle">10</property>
        <property name="hibernate.dbcp.minIdle">5</property>
        <property name="hibernate.dbcp.maxWaitMillis">-1</property>

        <mapping class="org.example.hibernateexample.entity.Book" />

    </session-factory>
</hibernate-configuration>
```


- [ ] Specification https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html
- [ ] Flyway https://www.baeldung.com/database-migrations-with-flyway
- [ ] Liquibase https://www.baeldung.com/liquibase-refactor-schema-of-java-app