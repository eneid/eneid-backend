package io.github.eneid.auth;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "accounts")
public class Account {

    private long id;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
