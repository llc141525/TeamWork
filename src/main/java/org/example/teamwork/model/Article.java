package org.example.teamwork.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.Objects;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Article {
    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 文章的标题
    @Column(unique = true)
    private String title;

    //文章的文本内容
    private String content;

    // 文章的分类
    private String classes;

    //文章的观看数量
    private Integer watchNum;

    //一对多, 一篇文章对应多个评论
    @OneToMany(cascade = CascadeType.PERSIST) // 设置级联删除, 删除一篇文章会一次性把所有评论删除.
    @ToString.Exclude
    private ArrayList<Comment> comment;

    //一对一, 一篇文章只有一个作者.
    @OneToOne(mappedBy = "user")
    private User user;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Article article = (Article) o;
        return getId() != null && Objects.equals(getId(), article.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
