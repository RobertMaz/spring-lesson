ALTER TABLE book
    ADD CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES app_users (id);

