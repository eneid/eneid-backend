package io.github.eneid.timeline;

import io.github.eneid.actions.QuickAction;
import io.github.eneid.auth.Account;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "messages")
public class Message {

    private long id;
    private Account author;
    private Date date;
    private String contents;
    private QuickAction action;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "contents", nullable = false)
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @ManyToOne
    @JoinColumn(name = "action", nullable = true)
    public QuickAction getAction() {
        return action;
    }

    public void setAction(QuickAction action) {
        this.action = action;
    }
}
