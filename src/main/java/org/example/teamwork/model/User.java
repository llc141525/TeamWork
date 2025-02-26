package org.example.teamwork.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名, 且必须是特殊的
    @Column(unique = true)
    private String username;

    // 密码
    private String passwd;

    // 这个人的文章
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Article> articles = new ArrayList<>();

    // 这个人的评论
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();


    // 用于维护双向关系. 如果删除文章的时候不调用 setUser(null), 那么 spring boot 任然会认为这个用户还有这么一篇文章.
    // 如果添加文章的时候不调用 setUser(this) 那么此时新添加进来的 article 的 user 为 null 他没有对应的外键.

    public void removeArticle(Article article) {
        articles.remove(article);
        article.setUser(null);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setUser(null);
    }

    public void addArticle(Article article) {
        articles.add(article);
        article.setUser(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }



    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
